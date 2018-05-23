package robotTest.beans;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;

import robotTest.beans.tools.GetBytes;

public class PlayerLocationC2S implements GetBytes{

	private double longitude;
	private double latitude;
	private PlayerLocationC2S(double longitude,double latitude)
	{
		this.longitude = longitude;
		this.latitude = latitude;
	}
	
	public double getLongitude() {
		return longitude;
	}

	public double getLatitude() {
		return latitude;
	}

	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}

	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}

	public static PlayerLocationC2S getOneInstance(double longitude,double latitude)
	{
		return new PlayerLocationC2S(longitude,latitude);
	}
	@Override
	public byte[] getBytes() {
		// TODO Auto-generated method stub
		ByteBuffer bb = ByteBuffer.allocate(1024);
		bb.order(ByteOrder.BIG_ENDIAN);
		bb.putShort((short)2004);
		bb.putDouble(longitude);
		bb.putDouble(latitude);
		bb.flip();
		byte[] kk = new byte[bb.remaining()];
		bb.get(kk, 0, kk.length);
		return kk;
	}
}
