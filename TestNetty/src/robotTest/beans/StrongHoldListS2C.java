package robotTest.beans;

import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.List;

import robotTest.beans.tools.Common;

public class StrongHoldListS2C {

	private class Item
	{
		private int id;
		private String name;
		private double longitude;
		private double latitude;
		private int blood;
		private int bloodMax;
		private byte camp;
		private byte level;
		private  String owner_id;
		private  String owner_name;
		@SuppressWarnings("unused")
		public Item(int id, String name, double longitude, double latitude, int blood, int bloodMax, byte camp,
				byte level, String owner_id, String owner_name) {
			super();
			this.id = id;
			this.name = name;
			this.longitude = longitude;
			this.latitude = latitude;
			this.blood = blood;
			this.bloodMax = bloodMax;
			this.camp = camp;
			this.level = level;
			this.owner_id = owner_id;
			this.owner_name = owner_name;
		}
		public int getId() {
			return id;
		}
		public String getName() {
			return name;
		}
		public double getLongitude() {
			return longitude;
		}
		public double getLatitude() {
			return latitude;
		}
		public int getBlood() {
			return blood;
		}
		public int getBloodMax() {
			return bloodMax;
		}
		public byte getCamp() {
			return camp;
		}
		public byte getLevel() {
			return level;
		}
		public String getOwner_id() {
			return owner_id;
		}
		public String getOwner_name() {
			return owner_name;
		}
		
		
	}
	private List<Item> list = new ArrayList<>();
	public List getList()
	{
		return list;
	}
	public StrongHoldListS2C(ByteBuffer bb)
	{
		ByteBufferToObject(bb);
	}
	private void ByteBufferToObject(ByteBuffer bb)
	{
		int length = bb.getShort();
		for(int i = 0;i<length;i++)
		{
			int id = bb.getInt();			
			String name = Common.getStringGB2312ByByte(bb);
			double longitude = bb.getDouble();
			double latitude = bb.getDouble();
			int blood = bb.getInt();
			int bloodMax = bb.getInt();
			byte camp = bb.get();
			byte level = bb.get();
			String owner_id = Common.getStringGB2312ByByte(bb);
			String owner_name = Common.getStringGB2312ByByte(bb);
			list.add(new Item(id,name,longitude,latitude,blood,bloodMax,camp,level,owner_id,owner_name));
		}
	}
	public String toString()
	{
		StringBuilder sb = new StringBuilder();
		sb.append("the list has ");
		sb.append(list.size());
		sb.append(" items\n");
		for(Item item:list)
		{
			sb.append("id="+item.getId()+";\n");
			sb.append("name="+item.getName()+";\n");
			sb.append("longitude="+item.getLongitude()+";\n");
			sb.append("latitude="+item.getLatitude()+";\n");
			sb.append("blood="+item.getBlood()+";\n");
			sb.append("bloodMax="+item.getBloodMax()+";\n");
			sb.append("camp="+item.getCamp()+";\n");
			sb.append("level="+item.getLevel()+";\n");
			sb.append("owner_id="+item.getOwner_id()+";\n");
			sb.append("owner_name="+item.getOwner_name()+";\n");
		}
		return sb.toString();
	}
	
}
