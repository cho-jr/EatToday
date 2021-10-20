package eatToday;

public class MenuVO {
	private int idx;
	private String classi;
	private String shop;
	private String menu;
	private int prefer;
	private String locate;
	private int choose;
	
	public int getIdx() {
		return idx;
	}
	public void setIdx(int idx) {
		this.idx = idx;
	}
	public String getClassi() {
		return classi;
	}
	public void setClassi(String classi) {
		this.classi = classi;
	}
	public String getShop() {
		return shop;
	}
	public void setShop(String shop) {
		this.shop = shop;
	}
	public String getMenu() {
		return menu;
	}
	public void setMenu(String menu) {
		this.menu = menu;
	}
	public int getPrefer() {
		return prefer;
	}
	public void setPrefer(int prefer) {
		this.prefer = prefer;
	}
	public String getLocate() {
		return locate;
	}
	public void setLocate(String locate) {
		this.locate = locate;
	}
	public int getChoose() {
		return choose;
	}
	public void setChoose(int choose) {
		this.choose = choose;
	}
	@Override
	public String toString() {
		return "MenuVO [idx=" + idx + ", classi=" + classi + ", shop=" + shop + ", menu=" + menu + ", prefer=" + prefer
				+ ", locate=" + locate + ", choose=" + choose + "]";
	}
}
