package fixedLengthFrameDecoder;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.FixedLengthFrameDecoder;
import io.netty.handler.codec.string.StringDecoder;

/**
 * Created by louyuting on 16/11/27.
 */
public class EchoServer {
    private final int port;//定义服务器端监听的端�?
    /** 构�?�函数中传入参数 **/
    public EchoServer(int port){
        this.port = port;
    }

    /** 启动服务�? **/
    public void start() throws Exception{
        //县城�?
        EventLoopGroup boss = new NioEventLoopGroup();
        EventLoopGroup worker = new NioEventLoopGroup();

        //创建�?个serverbootstrap实例
        ServerBootstrap serverBootstrap = new ServerBootstrap();

        try {
            serverBootstrap.group(boss, worker)
                    .channel(NioServerSocketChannel.class)//指定使用�?个NIO传输Channel
                    .option(ChannelOption.SO_BACKLOG, 100)
                    .childHandler(new ChannelInitializer<SocketChannel>() {
                        //在channel的ChannelPipeline中加入EchoServerHandler到最�?
                        @Override
                        protected void initChannel(SocketChannel channel) throws Exception {
                            channel.pipeline().addLast(new FixedLengthFrameDecoder(10));

                            channel.pipeline().addLast(new StringDecoder());

                            channel.pipeline().addLast(new EchoServerHandler());
                        }
                    });
            //异步的绑定服务器,sync()�?直等到绑定完�?.
            ChannelFuture future = serverBootstrap.bind(this.port).sync();
            System.out.println(EchoServer.class.getName()+" started and listen on '"+ future.channel().localAddress());
            future.channel().closeFuture().sync();//获得这个channel的CloseFuture,阻塞当前线程直到关闭操作完成
        } finally {
            boss.shutdownGracefully().sync();//关闭group,释放�?有的资源
            worker.shutdownGracefully().sync();//关闭group,释放�?有的资源
        }
    }


    /**
     * main
     * @param args
     * @throws Exception
     */
    public static void main(String[] args) throws Exception {
        new EchoServer(8000).start();
    }

}