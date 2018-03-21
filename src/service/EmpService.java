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
	 * Ա����¼
	 * @param emp
	 * @return
	 */
	public Emp login(Emp emp){
		return empDao.login(emp);
	}
	/**
	 * ���Ҹ�Ա���ľ���
	 * @param emp
	 * @return
	 */
	public Emp findManager(Emp emp){
		return empDao.findManager(emp);
	}
	
	/**
	 * �����ܾ���
	 * @param emp
	 * @return
	 */
	public Emp findGeneralManager(){
		return empDao.findGeneralManager();
	}
	
	
	/**
	 * ��ӱ���������±�����
	 * @param baoXiao
	 * @return
	 */
	public boolean addOrUpdateBaoXiao(BaoXiao baoXiao){
		return baoXiaoDao.addBaoXiao(baoXiao);
	}
	
	/**
	 * ���ұ�����
	 * @param baoXiao
	 * @return
	 */
	public BaoXiao findBaoXiao(BaoXiao baoXiao){
		return baoXiaoDao.findBaoXiao(baoXiao);
	}
	
	/**
	 * ���Ҹ�Ա�������ı�����
	 */
	public List<BaoXiao> findAllBaoXiao(Emp emp){
		return baoXiaoDao.findAllBaoXiao(emp);
	}
	
	/**
	 * ����һ��Ա��
	 * @return
	 */
	public Emp findEmp(Emp emp){
		return empDao.findEmp(emp);
	}
	
	/**
	 * ����һ������
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
