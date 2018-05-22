import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;

public class HelloServer {

	 public void start(int port) throws Exception {  
	        EventLoopGroup bossGroup = new NioEventLoopGroup();   
	        EventLoopGroup workerGroup = new NioEventLoopGroup();  
	        try {  
	            ServerBootstrap b = new ServerBootstrap();   
	            b.group(bossGroup, workerGroup).channel(NioServerSocketChannel.class)   
	                    .childHandler(new ChannelInitializer() {   
	                                @Override  
	                                public void initChannel(Channel ch) throws Exception {  
	                                    // 注册两个OutboundHandler，执行顺序为注册顺序的逆序，所以应该是OutboundHandler2 OutboundHandler1  
	                                    ch.pipeline().addLast(new OutboundHandler1());  
	                                    ch.pipeline().addLast(new OutboundHandler2());  
	                                    // 注册两个InboundHandler，执行顺序为注册顺序，所以应该是InboundHandler1 InboundHandler2  
	                                    ch.pipeline().addLast(new InboundHandler1());  
	                                    ch.pipeline().addLast(new InboundHandler2());  
	                                }

									
	                            }).option(ChannelOption.SO_BACKLOG, 128)   
	                    .childOption(ChannelOption.SO_KEEPALIVE, true);   
	  
	            ChannelFuture f = b.bind(port).sync();   
	  
	            f.channel().closeFuture().sync();  
	        } finally {  
	            workerGroup.shutdownGracefully();  
	            bossGroup.shutdownGracefully();  
	        }  
	    }  
	  
	    public static void main(String[] args) throws Exception {  
	        HelloServer server = new HelloServer();  
	        server.start(8000);  
	    }  

}
