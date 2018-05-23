package robotTest.beans;

import java.io.ByteArrayOutputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;

import robotTest.beans.tools.GetBytes;

public class LoginStructC2S implements GetBytes {

	private String token;
	private boolean reconn = false;
	private LoginStructC2S(String token,boolean reconn)
	{
		this.token = token;
		this.reconn = reconn;
	}
	public static LoginStructC2S getOneInstance(String token,boolean reconn)
	{
		return new LoginStructC2S(token,reconn);
	}
	@Override
	public byte[] getBytes() {
		// TODO Auto-generated method stub
//		byte[] tkl = transToBigEndian(tk.length);
		//String长度不可超过2的16次方
		
		//byte[] length = transToBigEndian(5+tk.length);
		ByteBuffer bb = ByteBuffer.allocate(1024);
		bb.order(ByteOrder.BIG_ENDIAN);
		//bb.put(length);
//		bb.putShort((short) 7);
		bb.putShort((short) 2001);
		bb.putShort((short) token.length());
		bb.put(token.getBytes());
		bb.put((byte)0);
		bb.flip();
		byte[] kk = new byte[bb.remaining()];
		bb.get(kk, 0, kk.length);
		
		return kk;
	}
//	private byte[] getOneBytes()
//	{
//		
//	}
//	private byte[] transToBigEndian(int n)
//	{
//		byte[] l = new byte[2];
//		l[1] = (byte)(n%256);
//		l[0] = (byte)(n>>8);//String长度不可超过2的16次方
//		return l;
//	}
}
