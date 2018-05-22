package fixedLengthFrameDecoder;

import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 *
 * Created by louyuting on 16/12/1.
 *
 */
public class EchoClientHandler extends SimpleChannelInboundHandler<String>{

    private int counter=0;

    private static final String REQ = "LOU.";

    /**
     * ���յ����ӳɹ���֪ͨ,����һ����Ϣ.
     * @param ctx
     * @throws Exception
     */
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {

        for(int i=0; i<10; i++){
            ctx.writeAndFlush( Unpooled.copiedBuffer(REQ.getBytes()) );
        }
    }

    /**
     * ÿ���յ�����ʱ��������ᱻ����.��ӡ�յ�����Ϣ��־
     * @param channelHandlerContext
     * @param msg
     * @throws Exception
     */
    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, String msg) throws Exception {
        System.out.println("client received: " + "counter:" + (++counter) + "  msg:"+msg);
    }

    /**
     * �쳣����ʱ,��¼������־,�ر�channel
     * @param ctx
     * @param cause
     * @throws Exception
     */
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();//��ӡ��ջ�Ĵ�����־
        ctx.close();
    }

}