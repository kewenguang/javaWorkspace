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
	private final int port;// ����������˼����Ķ˿�
	/** ���캯���д������ **/
	  
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

	/** ���������� **/
	public void start() throws Exception {
		EventLoopGroup group = new NioEventLoopGroup();
		// ����һ��client ��bootstrapʵ��
		Bootstrap clientBootstrap = new Bootstrap();

		try {
			clientBootstrap.group(group).channel(NioSocketChannel.class)// ָ��ʹ��һ��NIO����Channel
					.remoteAddress(new InetSocketAddress(host, port))// ����Զ�˷�������host�Ͷ˿�
					.option(ChannelOption.TCP_NODELAY, true).handler(new ChannelInitializer<SocketChannel>() {
						// ��channel��ChannelPipeline�м���EchoClientHandler�����
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
			ChannelFuture f = clientBootstrap.connect().sync();// ���ӵ�Զ��,һֱ�ȵ��������

			 f.channel().closeFuture().sync();//һֱ������channel�ر�
			// System.out.println("dfdfdfdf");
		} finally {
			group.shutdownGracefully().sync();// �ر�group,�ͷ����е���Դ
		}
	}
}
