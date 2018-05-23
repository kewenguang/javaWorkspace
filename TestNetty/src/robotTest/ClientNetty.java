package robotTest;

import java.net.InetSocketAddress;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;
import io.netty.handler.codec.LengthFieldPrepender;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;
import robotTest.beans.tools.GetBytes;

public class ClientNetty {

	private final String host;
	private final int port;// 定义服务器端监听的端口
	/** 构造函数中传入参数 **/
	  
    //private Channel channel;
	public ClientNetty(String host, int port) {
		this.host = host;
		this.port = port;

	}

//	public void sendMsg(ChannelSend c) {
//		handler.sendByBytes(c);
//	}
//
//	public void addNetListener(NetListener nl) {
//		handler.addNetListener(nl);
//	}
//
//	public void removeNetListener(NetListener nl) {
//		handler.removeNetListener(nl);
//	}

	/** 启动服务器 **/
	public void start() throws Exception {
		EventLoopGroup group = new NioEventLoopGroup();
		// 创建一个client 的bootstrap实例
		Bootstrap clientBootstrap = new Bootstrap();

		try {
			clientBootstrap.group(group).channel(NioSocketChannel.class)// 指定使用一个NIO传输Channel
					.remoteAddress(new InetSocketAddress(host, port))// 设置远端服务器的host和端口
					.option(ChannelOption.TCP_NODELAY, true).handler(new ChannelInitializer<SocketChannel>() {
						// 在channel的ChannelPipeline中加入EchoClientHandler到最后
						@Override
						protected void initChannel(SocketChannel channel) throws Exception {
							// channel.pipeline().addLast(new FixedLengthFrameDecoder(10));

							//channel.pipeline().addLast("logging", new LoggingHandler(LogLevel.INFO));
							channel.pipeline().addLast(new LengthFieldBasedFrameDecoder(Integer.MAX_VALUE, 0, 2, 0, 2));
							ClientNettyHandler handler = new ClientNettyHandler();
							// channel.pipeline().addLast(new StringDecoder());
							channel.pipeline().addLast(handler);
							channel.pipeline().addLast(new LengthFieldPrepender(2, false));
							//ClientNetty.this.channel = channel;
							
//							channel.pipeline().addLast(new AddMsgId());
//							channel.pipeline().addLast(new ObjectToByteHandler());
						}
					});
			ChannelFuture f = clientBootstrap.connect().sync();// 连接到远端,一直等到连接完成

			 f.channel().closeFuture().sync();//一直阻塞到channel关闭
			// System.out.println("dfdfdfdf");
		} finally {
			group.shutdownGracefully().sync();// 关闭group,释放所有的资源
		}
	}
}
