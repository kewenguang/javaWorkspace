package robotTest.beans;

import robotTest.beans.tools.Common;
import robotTest.beans.tools.TimeHandler;

public class IntervalSend implements TimeHandler{

	PlayerLocationC2S pl = PlayerLocationC2S.getOneInstance(113.34, 27.17);
	public void work()
	{
		 Common.sendBytes(pl.getBytes());
		 pl.setLongitude(pl.getLongitude()+0.009);
		 System.out.println("sended"+pl.getLongitude());
	}
}
