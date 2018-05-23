package robotTest;

import java.util.ArrayList;
import java.util.List;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import robotTest.beans.LoginStructC2S;
import robotTest.beans.tools.GetBytes;
import robotTest.beans.tools.Common;

public class ClientNettyHandler extends ChannelInboundHandlerAdapter{
/*
 * 记得等会儿要close掉ctx
 */
	//private ChannelHandlerContext ctx;
	private List<NetListener> netL = new ArrayList<>();
	
	 public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {  
	   ByteBuf result = (ByteBuf) msg;
	   
	   //short mid = result.readShort();
	   
	   //System.out.println("msg id ===== " + mid);
	   
	   //if (mid == 2002) {
	   
	   //bytes2LoginStructS2C.setChannel(ctx.channel());
	   new Bytes2LoginStructS2C().update(result.nioBuffer());
		   
		   
		   
	   //} else if (mid == 3002) {
		   
	   //}
	   
//       byte[] result1 = new byte[result.readableBytes()];  
//       result.readBytes(result1);  
//       result.release(); 
//       for(NetListener nl : netL)
//       	nl.update(result1);
       
       //mid
       
      // new Bytes2LoginStructS2C().update(result1);
//       for(int j = 0;j<result1.length;j++)
//       	System.out.print((((short)result1[j]) & 0x00ff) +" ");
//       System.out.println();
       //System.out.println("sdsdsdfdsf");
	 }
	 public void channelActive(ChannelHandlerContext ctx) throws Exception {  
		  LoginStructC2S c = LoginStructC2S.getOneInstance("kwg", false);
			byte[] bt = c.getBytes();
	    	ByteBuf encoded = ctx.alloc().buffer(bt.length);  
	        encoded.writeBytes(bt);  
	        ctx.channel().writeAndFlush(encoded);
	        Common.channel = ctx.channel();
	 }
//	@Override
//	protected void channelRead0(ChannelHandlerContext arg0, Object msg) throws Exception {
//		// TODO Auto-generated method stub
//		//System.out.println("dsdsdsds");
//		ByteBuf result = (ByteBuf) msg;  
//        byte[] result1 = new byte[result.readableBytes()];  
//        result.readBytes(result1);  
//        result.release(); 
////        for(NetListener nl : netL)
////        	nl.update(result1);
//        for(int j = 0;j<result1.length;j++)
//        	System.out.print(result1[j]+" ");
//        //ctx.close();  
//        //System.out.println("Server said:" + new String(result1));  
//	}
	public void addNetListener(NetListener nl)
	{
		netL.add(nl);
	}
	public void removeNetListener(NetListener nl)
	{
		netL.remove(nl);
	}
    
//	@Override
//    public void channelActive(ChannelHandlerContext ctx) throws Exception {
//		//this.ctx = ctx;
//		LoginStructC2S c = LoginStructC2S.getOneInstance("wl", false);
//		byte[] bt = c.getBytes();
//    	ByteBuf encoded = ctx.alloc().buffer(bt.length);  
//        encoded.writeBytes(bt);  
//        ctx.write(encoded);
//        ctx.flush(); 
//    }
//	 @Override
//	    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
//	        cause.printStackTrace();//打印堆栈的错误日志
//	        ctx.close();
//	        System.out.println("dsdsdsds");
//	    }
	
}
