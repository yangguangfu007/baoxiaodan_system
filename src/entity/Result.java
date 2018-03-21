package entity;

import java.util.Date;

public class Result {
	private Integer id;
	private BaoXiao baoXiao;
	private Date check_date;
	private String check_emp;
	private String result;//审核结果
	private String comm;//批复意见
	
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
	public Date getCheck_date() {
		return check_date;
	}
	public void setCheck_date(Date check_date) {
		this.check_date = check_date;
	}
	public String getCheck_emp() {
		return check_emp;
	}
	public void setCheck_emp(String check_emp) {
		this.check_emp = check_emp;
	}
	public String getResult() {
		return result;
	}
	public void setResult(String result) {
		this.result = result;
	}
	public String getComm() {
		return comm;
	}
	public void setComm(String comm) {
		this.comm = comm;
	}
	
	
}
