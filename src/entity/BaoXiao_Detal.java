package entity;

public class BaoXiao_Detal {
	private Integer id;
	private BaoXiao baoXiao;
	private String item;
	private Double account;
	private String des;//费用说明
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public BaoXiao getBaoXiao() {
		return baoXiao;
	}
	public void setBaoXiao(BaoXiao baoXiao) {
		this.baoXiao = baoXiao;
	}
	public String getItem() {
		return item;
	}
	public void setItem(String item) {
		this.item = item;
	}
	public Double getAccount() {
		return account;
	}
	public void setAccount(Double account) {
		this.account = account;
	}
	public String getDes() {
		return des;
	}
	public void setDes(String des) {
		this.des = des;
	}
	
	
	
}
