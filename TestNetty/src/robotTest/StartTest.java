package robotTest;

public class StartTest {
	public StartTest()
	{
		//公司服务器
		//ClientNetty cn = new ClientNetty("10.11.100.115",8020);
		//王的服务器
		//ClientNetty cn = new ClientNetty("10.11.160.129",8020);
		try {
			//for(int i = 0;i<10;i++)
				new ClientNetty("10.11.100.115",8020).start();
			//cn.start();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//cn.addNetListener(new Bytes2LoginStructS2C());
		//cn.sendMsg(LoginStructC2S.getOneInstance("wl",false));
	}
    public static void main(String[] a)
    {
    	
    	new StartTest();
    }
}
