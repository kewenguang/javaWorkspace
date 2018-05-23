package robotTest.beans;

import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.List;

public class PlayerItemS2C {

	private List<Item> list = new ArrayList<>();
	private class Item
	{
		private short id;
		private short num;
		public Item(short id,short num)
		{
			this.id = id;
			this.num = num;
		}
		public short getId()
		{
			return id;
		}
		public short getNum()
		{
			return num;
		}
	}
	public PlayerItemS2C(ByteBuffer bb)
	{
		ByteBufferToObject(bb);
	}
	public List getList()
	{
		return list;
	}
	private void ByteBufferToObject(ByteBuffer bb)
	{
		int length = bb.getShort();
		//List<Item> list = new ArrayList<>();
		for(int i = 0;i<length;i++)
		{
			short id = bb.getShort();
			short num = bb.getShort();
			list.add(new Item(id,num));
		}
		
	}
	
	public String toString()
	{
		StringBuilder sb = new StringBuilder();
		sb.append("the list has ");
		sb.append(list.size());
		sb.append(" items\n");
		for(Item item:list)
		sb.append("id="+item.getId()+";num="+item.getNum()+"\n");
		return sb.toString();
	}
}
