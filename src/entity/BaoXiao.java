package entity;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

public class BaoXiao {
	private Integer bxid;
	private Result result;
	private Emp deal_empno;//下一个审核人
	private Emp create_empno;//创建人
	private Date create_time;
	private Date modify_time;
	private String event;//事由
	private Double total_account;//总金额
	private String status;//状态
	private Set<BaoXiao_Detal> detals=new HashSet<BaoXiao_Detal>();
	public Integer getBxid() {
		return bxid;
	}
	public void setBxid(Integer bxid) {
		this.bxid = bxid;
	}
	public Result getResult() {
		return result;
	}
	public void setResult(Result result) {
		this.result = result;
	}
	public Emp getDeal_empno() {
		return deal_empno;
	}
	public void setDeal_empno(Emp deal_empno) {
		this.deal_empno = deal_empno;
	}
	public Emp getCreate_empno() {
		return create_empno;
	}
	public void setCreate_empno(Emp create_empno) {
		this.create_empno = create_empno;
	}
	public Date getCreate_time() {
		return create_time;
	}
	public void setCreate_time(Date create_time) {
		this.create_time = create_time;
	}
	public Date getModify_time() {
		return modify_time;
	}
	public void setModify_time(Date modify_time) {
		this.modify_time = modify_time;
	}
	public String getEvent() {
		return event;
	}
	public void setEvent(String event) {
		this.event = event;
	}
	public Double getTotal_account() {
		return total_account;
	}
	public void setTotal_account(Double total_account) {
		this.total_account = total_account;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public Set<BaoXiao_Detal> getDetals() {
		return detals;
	}
	public void setDetals(Set<BaoXiao_Detal> detals) {
		this.detals = detals;
	}
	
	
	
	
	
}
