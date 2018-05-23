package robotTest;

import java.nio.ByteBuffer;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import io.netty.util.HashedWheelTimer;
import io.netty.util.Timeout;
import io.netty.util.Timer;
import io.netty.util.TimerTask;
import robotTest.beans.IntervalSend;
import robotTest.beans.LoginStructS2C;
import robotTest.beans.PlayerItemS2C;
import robotTest.beans.PlayerLocationC2S;
import robotTest.beans.PlayerLocationS2C;
import robotTest.beans.PlayerS2C;
import robotTest.beans.StrongHoldListS2C;
import robotTest.beans.tools.Common;
import robotTest.beans.tools.TimeWork;

public class Bytes2LoginStructS2C implements NetListener{

//	private short code;
//	
//	private String msg;
	
	
	
	@Override
	public void update(ByteBuffer bb) {
		
		short mid = bb.getShort();
		System.out.println(mid);
		switch(mid)
		{
		case 2002:
			LoginStructS2C s2c = LoginStructS2C.ByteBufferToObject(bb);
			System.out.println(s2c);
			TimeWork tw = new TimeWork();
			tw.setTimeHandler(new IntervalSend());
			tw.intervalSend(TimeUnit.SECONDS, 2);
//			PlayerLocationC2S pl = PlayerLocationC2S.getOneInstance(113.34, 27.17);      	 
//			intervalSend(TimeUnit.SECONDS,2,pl.getBytes());
			break;
		case 2013:
			PlayerItemS2C playerS2c = new PlayerItemS2C(bb);
			//System.out.println(playerS2c);
			break;
		case 3001:
			StrongHoldListS2C stringHolderListS2c = new StrongHoldListS2C(bb);
			//System.out.println(stringHolderListS2c);
			break;
		case 2005:
			PlayerLocationS2C playerLocationS2C = new PlayerLocationS2C(bb);
			//System.out.println(playerLocationS2C);
			break;
		case 2003:
			PlayerS2C playerS2C = new PlayerS2C(bb);
			//System.out.println(playerS2C);
			break;
		}
//		code = bb.getShort();
//		short msgLength = bb.getShort();
//		if (msgLength > 0) {
//			byte[] msgBytes = new byte[msgLength];
//			bb.get(msgBytes);
//			msg = new String(msgBytes);
//			
//			System.out.println(code+msg);
		}
	

}
