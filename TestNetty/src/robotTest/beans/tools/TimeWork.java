package robotTest.beans.tools;

import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import io.netty.util.HashedWheelTimer;
import io.netty.util.Timeout;
import io.netty.util.Timer;
import io.netty.util.TimerTask;

public class TimeWork {

	private TimeHandler th;
	public TimeWork(TimeHandler th)
	{
		this.th = th;
	}
	public TimeWork()
	{
		
	}
	public void intervalSend(TimeUnit tm,int interval)
	{
		Timer timer = new HashedWheelTimer(Executors.defaultThreadFactory(), 1, tm, 10);
		TimerTask task1 = new TimerTask() {
            public void run(Timeout timeout) throws Exception {
                //System.out.println("task 1 will run per 5 seconds ");
            	if(th != null)
            	{
            		th.work();
                    timer.newTimeout(this, interval, tm);//结束时候再次注册
            }}
        };
        timer.newTimeout(task1, interval, tm);
	}
	public void setTimeHandler(TimeHandler b)
	{
		th = b;
	}
}
