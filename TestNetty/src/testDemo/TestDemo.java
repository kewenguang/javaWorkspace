package testDemo;

public class TestDemo {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		//System.out.println(oneByteToInteger(b));
	}

	private static int oneByteToInteger(byte b)
	{
		int low;
		if(b<0)
		{
			low = 128 + b&0x7f;
		}
		else
			low = b;
		return low;
	}
}
