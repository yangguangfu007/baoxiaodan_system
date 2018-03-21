package entity;

import java.util.HashSet;
import java.util.Set;

public class Emp {
	private String empno;
	private String ename;
	private String password;
	private String status;
	private Dept dept;
	private Position position;
	private Set<BaoXiao> baoXiao1=new HashSet<BaoXiao>();//¥¥Ω®
	private Set<BaoXiao> baoXiao2=new HashSet<BaoXiao>();//…Û∫À
	public String getEmpno() {
		return empno;
	}
	public void setEmpno(String empno) {
		this.empno = empno;
	}
	public String getEname() {
		return ename;
	}
	public void setEname(String ename) {
		this.ename = ename;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public Dept getDept() {
		return dept;
	}
	public void setDept(Dept dept) {
		this.dept = dept;
	}
	public Position getPosition() {
		return position;
	}
	public void setPosition(Position position) {
		this.position = position;
	}
	public Set<BaoXiao> getBaoXiao1() {
		return baoXiao1;
	}
	public void setBaoXiao1(Set<BaoXiao> baoXiao1) {
		this.baoXiao1 = baoXiao1;
	}
	public Set<BaoXiao> getBaoXiao2() {
		return baoXiao2;
	}
	public void setBaoXiao2(Set<BaoXiao> baoXiao2) {
		this.baoXiao2 = baoXiao2;
	}
	
	
	
	
	
	
	
}
