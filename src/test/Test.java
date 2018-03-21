package test;

import util.SpringUtil;
import dao.EmpDao;
import entity.Emp;


public class Test {

	public static void main(String[] args) {
		EmpDao dao=(EmpDao) SpringUtil.getAC().getBean("empDao");
		Emp e=dao.findGeneralManager();
		System.out.println(e.getEname());
	}

}
