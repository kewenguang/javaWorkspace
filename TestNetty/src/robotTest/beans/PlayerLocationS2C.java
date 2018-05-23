package robotTest.beans;

import java.nio.ByteBuffer;

public class PlayerLocationS2C {

	private int id ;
	private double longitude;
	private double latitude;
	
	public void setId(int id) {
		this.id = id;
	}
	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}
	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}
	public int getId() {
		return id;
	}
	public double getLongitude() {
		return longitude;
	}
	public double getLatitude() {
		return latitude;
	}
	public PlayerLocationS2C(ByteBuffer bb)
	{
		ByteBufferToObject(bb);
	}
	private void ByteBufferToObject(ByteBuffer bb)
	{
		setId(bb.getInt());
		setLongitude(bb.getDouble());
		setLatitude(bb.getDouble());
	}
	public String toString()
	{
		StringBuilder sb = new StringBuilder();
		sb.append("id="+getId()+";\n");
		sb.append("longitude="+getLongitude()+";\n");
		sb.append("latitude="+getLatitude()+";\n");
		return sb.toString();
	}
}
