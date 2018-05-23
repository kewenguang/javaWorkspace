package robotTest.beans;

import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;

import robotTest.beans.tools.Common;

public class PlayerS2C {

	private String nickName;
	private String avatarUrl;
	private byte camp;
	private int exp;
	private byte level;
	private int energy;
	
	public String getNickName() {
		return nickName;
	}
	public void setNickName(String nickName) {
		this.nickName = nickName;
	}
	public String getAvatarUrl() {
		return avatarUrl;
	}
	public void setAvatarUrl(String avatarUrl) {
		this.avatarUrl = avatarUrl;
	}
	public byte getCamp() {
		return camp;
	}
	public void setCamp(byte camp) {
		this.camp = camp;
	}
	public int getExp() {
		return exp;
	}
	public void setExp(int exp) {
		this.exp = exp;
	}
	public byte getLevel() {
		return level;
	}
	public void setLevel(byte level) {
		this.level = level;
	}
	public int getEnergy() {
		return energy;
	}
	public void setEnergy(int energy) {
		this.energy = energy;
	}
	public PlayerS2C(ByteBuffer bb)
	{
		setNickName(Common.getStringGB2312ByByte(bb));
		setAvatarUrl(Common.getStringGB2312ByByte(bb));
		setCamp(bb.get());
		setExp(bb.getInt());
		setLevel(bb.get());
		setEnergy(bb.getInt());
	}
	public String toString()
	{
		StringBuilder sb = new StringBuilder();
		sb.append("nickName="+getNickName()+";\n");
		sb.append("avatarUrl="+getAvatarUrl()+";\n");
		sb.append("camp="+getCamp()+";\n");
		sb.append("exp="+getExp()+";\n");
		sb.append("level="+getLevel()+"\n");
		sb.append("energy="+getEnergy()+"\n");
		return sb.toString();
	}
	
}
