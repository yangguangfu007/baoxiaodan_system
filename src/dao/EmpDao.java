package dao;

import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import entity.Emp;

public class EmpDao extends HibernateDaoSupport {
	@SuppressWarnings("unchecked")
	public Emp login(Emp emp){
		String hql="from Emp where ename=? and password=?";
		Object[] o=new Object[]{emp.getEname(),emp.getPassword()};
		List<Emp> list=(List<Emp>) getHibernateTemplate().find(hql, o);
		if (list==null||list.size()<1) {
			return null;
		}
		return list.get(0);
	}
	
	@SuppressWarnings("unchecked")
	public Emp findManager(Emp emp){
		String hql="from Emp e where e.dept.deptno=? and e.position.pname=?";
		Object[] o=new Object[]{emp.getDept().getDeptno(),"部门经理"};
		List<Emp> list=(List<Emp>) getHibernateTemplate().find(hql, o);
		if (list==null||list.size()!=1) {
			return null;
		}
		return list.get(0);
	}
	
	public Emp findEmp(Emp emp){
		String hql="from Emp e where e.empno=?";
		Object[] o=new Object[]{emp.getEmpno()};
		return (Emp) getHibernateTemplate().find(hql,o).get(0);
	}
	
	public Emp findGeneralManager(){
		String hql="from Emp e where e.position.pname=?";
		Object[] o=new Object[]{"总经理"};
		return (Emp) getHibernateTemplate().find(hql,o).get(0);
	}
	
	public Emp findCashier(){
		String hql="from Emp e where e.position.pname=?";
		Object[] o=new Object[]{"财务"};
		return (Emp) getHibernateTemplate().find(hql,o).get(0);
	}
	
	
}
