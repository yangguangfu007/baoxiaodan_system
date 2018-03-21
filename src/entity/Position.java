package entity;

import java.util.HashSet;
import java.util.Set;

public class Position {
	private Integer pid;
	private String pname;
	private Set<Emp> emps =new HashSet<Emp>();
	public Integer getPid() {
		return pid;
	}
	public void setPid(Integer pid) {
		this.pid = pid;
	}
	public String getPname() {
		return pname;
	}
	public void setPname(String pname) {
		this.pname = pname;
	}
	public Set<Emp> getEmps() {
		return emps;
	}
	public void setEmps(Set<Emp> emps) {
		this.emps = emps;
	}
	
	
	
}
