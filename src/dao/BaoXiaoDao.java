package dao;

import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import entity.BaoXiao;
import entity.Emp;

public class BaoXiaoDao extends HibernateDaoSupport {
	/**
	 * ������߸��±�����
	 * @param baoXiao
	 * @return
	 */
	public boolean addBaoXiao(BaoXiao baoXiao){
		try {
			getHibernateTemplate().saveOrUpdate(baoXiao);
			return true;
		} catch (DataAccessException e) {
			e.printStackTrace();
			return false;
		}
	}
	
	/**
	 * ���ұ�����,ͨ������
	 * @param baoXiao
	 * @return
	 */
	public BaoXiao findBaoXiao(BaoXiao baoXiao){
		String hql="from BaoXiao b where b.bxid=?";
		Object[] o=new Object[]{baoXiao.getBxid()};
		return (BaoXiao) getHibernateTemplate().find(hql,o).get(0);
	}
	
	/**
	 * ����Ա�������ĵı�����
	 */
	@SuppressWarnings("unchecked")
	public List<BaoXiao> findAllBaoXiao(Emp emp){
		String hql="from BaoXiao b where b.create_empno.empno=?";
		Object[] o=new Object[]{emp.getEmpno()};
		return (List<BaoXiao>) getHibernateTemplate().find(hql,o);
	}
	
}
