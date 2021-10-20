package eatToday;

public class UserVO {
	private int idx;
	private String id;
	private String password;
	private String name;
	private String birthDay;
	
	public int getIdx() {
		return idx;
	}
	public void setIdx(int idx) {
		this.idx = idx;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getBirthDay() {
		return birthDay;
	}
	public void setBirthDay(String birthDay) {
		this.birthDay = birthDay;
	}
	@Override
	public String toString() {
		return "UserVO [idx=" + idx + ", id=" + id + ", password=" + password + ", name=" + name + ", birthDay="
				+ birthDay + "]";
	}
}
