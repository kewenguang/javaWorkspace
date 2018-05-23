package robotTest.beans;

import java.io.ByteArrayInputStream;
import java.io.ObjectInputStream;
import java.io.Serializable;
import java.nio.ByteBuffer;

import robotTest.beans.tools.Common;

public class LoginStructS2C  {

	private int code;
	private String msg;
	private LoginStructS2C(int token,String reconn)
	{
		this.code = token;
		this.msg = reconn;
	}
//	public static LoginStructS2C getOneInstance(String token,boolean reconn)
//	{
//		return new LoginStructS2C(token,reconn);
//	}
	public static LoginStructS2C ByteBufferToObject(ByteBuffer bb)
	{
		//LoginStructS2C obj = null;
		int code = bb.getShort();
		String msg = Common.getStringByByte(bb);
		return new LoginStructS2C(code,msg);
	}
	
	public String toString()
	{
		return "code="+code+";msg="+msg;
	}
	
}
