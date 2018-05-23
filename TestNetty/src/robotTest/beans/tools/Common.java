package robotTest.beans.tools;

import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;

import io.netty.buffer.ByteBuf;
import io.netty.channel.Channel;

public class Common {

	public static String getStringByByte(ByteBuffer bb)
	{
		short nameL = bb.getShort();
		byte[] bt = new byte[nameL];
		bb.get(bt,0,bt.length);
		return new String(bt);
	}
	public static Channel channel;
	public static void sendBytes(byte[] bt)
	{
		if(channel != null)
		{
			ByteBuf encoded = channel.alloc().buffer(bt.length);  
            encoded.writeBytes(bt);  
            channel.writeAndFlush(encoded);
        }
	}
	public static String getStringGB2312ByByte(ByteBuffer bb)
	{
		short nameL = bb.getShort();
		byte[] bt = new byte[nameL];
		bb.get(bt,0,bt.length);
		try {
			return new String(bt,"UTF-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return new String(bt);
	}
}
