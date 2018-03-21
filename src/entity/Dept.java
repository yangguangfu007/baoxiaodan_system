package entity;

import java.util.HashSet;
import java.util.Set;

public class Dept {
	private Integer deptno;
	private String dname;
	private Set<Emp> emps=new HashSet<Emp>();
	
	public Integer getDeptno() {
		return deptno;
	}
	public void setDeptno(Integer deptno) {
		this.deptno = deptno;
	}
	public String getDname() {
		return dname;
	}
	public void setDname(String dname) {
		this.dname = dname;
	}
	public Set<Emp> getEmps() {
		return emps;
	}
	public void setEmps(Set<Emp> emps) {
		this.emps = emps;
	}
	
	
	
	
}
