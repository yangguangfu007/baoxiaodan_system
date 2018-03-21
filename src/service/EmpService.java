package service;

import java.util.List;

import dao.BaoXiaoDao;
import dao.EmpDao;
import entity.BaoXiao;
import entity.Emp;

public class EmpService {
	private EmpDao empDao;
	private BaoXiaoDao baoXiaoDao;
	/**
	 * 员工登录
	 * @param emp
	 * @return
	 */
	public Emp login(Emp emp){
		return empDao.login(emp);
	}
	/**
	 * 查找该员工的经理
	 * @param emp
	 * @return
	 */
	public Emp findManager(Emp emp){
		return empDao.findManager(emp);
	}
	
	/**
	 * 查找总经理
	 * @param emp
	 * @return
	 */
	public Emp findGeneralManager(){
		return empDao.findGeneralManager();
	}
	
	
	/**
	 * 添加报销单或更新报销单
	 * @param baoXiao
	 * @return
	 */
	public boolean addOrUpdateBaoXiao(BaoXiao baoXiao){
		return baoXiaoDao.addBaoXiao(baoXiao);
	}
	
	/**
	 * 查找报销单
	 * @param baoXiao
	 * @return
	 */
	public BaoXiao findBaoXiao(BaoXiao baoXiao){
		return baoXiaoDao.findBaoXiao(baoXiao);
	}
	
	/**
	 * 查找该员工创建的报销单
	 */
	public List<BaoXiao> findAllBaoXiao(Emp emp){
		return baoXiaoDao.findAllBaoXiao(emp);
	}
	
	/**
	 * 查找一个员工
	 * @return
	 */
	public Emp findEmp(Emp emp){
		return empDao.findEmp(emp);
	}
	
	/**
	 * 查找一个财务
	 * @return
	 */
	public Emp findCashier(){
		return empDao.findCashier();
	}
	
	
	public EmpDao getEmpDao() {
		return empDao;
	}
	public void setEmpDao(EmpDao empDao) {
		this.empDao = empDao;
	}
	public BaoXiaoDao getBaoXiaoDao() {
		return baoXiaoDao;
	}
	public void setBaoXiaoDao(BaoXiaoDao baoXiaoDao) {
		this.baoXiaoDao = baoXiaoDao;
	}
	
	
	
	
	
}
