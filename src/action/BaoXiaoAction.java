package action;

import java.util.Date;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import service.EmpService;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

import entity.BaoXiao;
import entity.BaoXiao_Detal;
import entity.Emp;
import entity.Result;

@SuppressWarnings("serial")
public class BaoXiaoAction extends ActionSupport {
	private BaoXiao_Detal detal;
	private BaoXiao baoXiao;
	private ActionContext ac=ActionContext.getContext();
	private Set<BaoXiao_Detal> set;
	private Map<String, Object> session=ac.getSession();
	private Emp emp=(Emp) session.get("emp");
	private EmpService empService;
	private String msg;
	private String flag;
	private String[] item;
	private Double[] account;
	private String[] des;
	private String replay;
	private String comm;//�������
	/**
	 * ��ӱ�������ϸ
	 * �ѱ�����ϸ����session
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public String addDetal(){
		set=(Set<BaoXiao_Detal>) session.get("detalset");
		if (set==null) {
			set=new HashSet<BaoXiao_Detal>();
		}
		set.add(detal);
		session.put("detalset", set);
		return SUCCESS;
		
	}
	
	/**
	 * ��ӱ��������������ݿ�
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public String addBaoXiao(){
		set=(Set<BaoXiao_Detal>) session.get("detalset");
		//�����ݿ��в�Ա������sessionͨ��
		Emp createEmp=empService.findEmp(emp);
		Emp dealEmp=empService.findManager(emp);
		Double total=0D;
		for (BaoXiao_Detal detal : set) {
			total+=detal.getAccount();
			detal.setBaoXiao(baoXiao);
		}
		baoXiao.setTotal_account(total);
		baoXiao.setDetals(set);
		baoXiao.setCreate_empno(createEmp);
		baoXiao.setCreate_time(new Date());
		baoXiao.setDeal_empno(dealEmp);
		baoXiao.setStatus("�����");
		createEmp.getBaoXiao1().add(baoXiao);
		dealEmp.getBaoXiao2().add(baoXiao);
		//�����˽����
		Result result=new Result();
		result.setCheck_emp(dealEmp.getEname());
		result.setBaoXiao(baoXiao);
		baoXiao.setResult(result);
		if (empService.addOrUpdateBaoXiao(baoXiao)) {
			//���session����ֹ�ٴ���ӱ�����ʱ����֮ǰ�������һ�Ρ�
			session.remove("detalset");
			return SUCCESS;
		}
		return "fail";
	}
	
	/**
	 * ���ҵ���Ա��������������
	 * @return
	 */
	public String findAll(){
		//����Լ������ı���������,���Ա������jspҳ���ٲ��Ա�������ı���������
		emp=empService.findEmp(emp);
		if (emp.getBaoXiao1().size()==0) {
			msg="����û�д�����������Ŷ��";
			return "fail";
		}
		return SUCCESS;
	}
	
	/**
	 * ����Ա������˵ı�����,
	 * ��session��ȡ���û�,��Ա�����������Ҫ��˵ı��������ϡ�
	 * @return
	 */
	public String findWaitCheckBX(){
		//���Ҫ��˵ı���������
		emp=empService.findEmp(emp);
		if (emp.getBaoXiao2().size()==0) {
			if ("����".equals(emp.getPosition().getPname())) {
				msg="��û��Ҫ����ı�����Ŷ��";
			}else {
				msg="��û��Ҫ��˵ı�����Ŷ��";
			}
			return "fail";
		}
		return SUCCESS;
	}
	
	/**
	 * ���ұ�����������ϸ,���ݲ�ͬԱ������ͬ��ַ
	 * @return
	 */
	public String findOneBX(){
		baoXiao=empService.findBaoXiao(baoXiao);
		emp=empService.findEmp(emp);
		if ("��ͨԱ��".equals(emp.getPosition().getPname())) {
			flag="update";//�޸�
		}else if ("���ž���".equals(emp.getPosition().getPname())
				||"�ܾ���".equals(emp.getPosition().getPname())) {
			flag="check";//���
		}
		return flag;
	}
	
	/**
	 * Ա���޸ı�����
	 */
	public String updateBX(){
		BaoXiao bx=empService.findBaoXiao(baoXiao);
		int i=0;
		Double totalAccount=0D;
		for (BaoXiao_Detal b : bx.getDetals()) {
			b.setItem(item[i]);
			b.setAccount(account[i]);
			b.setDes(des[i]);
			b.setBaoXiao(bx);
			bx.getDetals().add(b);
			totalAccount+=account[i];
			i++;
		}
		Emp dealEmp=empService.findManager(emp);
		bx.setTotal_account(totalAccount);
		bx.getResult().setResult(null);
		bx.setStatus("�����");
		bx.getResult().setCheck_emp(dealEmp.getEname());
		bx.getResult().setComm(null);
		bx.setEvent(baoXiao.getEvent());
		bx.setDeal_empno(dealEmp);
		bx.setModify_time(new Date());
		empService.addOrUpdateBaoXiao(bx); 
		msg="�޸ĳɹ���";
		return SUCCESS;
	}
	/**
	 * ���ž�����˱�����
	 * @return
	 */
	public String checkBX(){
		baoXiao=empService.findBaoXiao(baoXiao);
		if ("pass".equals(replay)) {
			baoXiao.getResult().setResult("ͨ��");
			//���ݱ��������޸ı�����״̬
			if (baoXiao.getTotal_account()<5000) {
				//ע�⣬��ӱ�����ʱ�Ѿ������˽�����˴�ֱ��get�����޸ļ���
				baoXiao.setStatus("������");
				baoXiao.setDeal_empno(empService.findCashier());
			}else {
				baoXiao.setStatus("���ܾ������");
				baoXiao.setDeal_empno(empService.findGeneralManager());
			}
		}else if ("return".equals(replay)) {
			baoXiao.getResult().setResult("���");
			baoXiao.setStatus("���޸�");
			baoXiao.setDeal_empno(null);
		}else if("refuse".equals(replay)){
			baoXiao.getResult().setResult("�ܾ�");
			baoXiao.setStatus("��ֹ");
			baoXiao.setDeal_empno(null);
		}
		baoXiao.setModify_time(new Date());
		baoXiao.getResult().setCheck_emp(emp.getEname());
		baoXiao.getResult().setCheck_date(new Date());
		baoXiao.getResult().setComm(comm);
		empService.addOrUpdateBaoXiao(baoXiao);
		msg="��˳ɹ�!";
		return SUCCESS;
		
	}
	
	/**
	 * �ܾ�����˱�����
	 * @return
	 */
	public String checkBX_manager(){
		baoXiao=empService.findBaoXiao(baoXiao);
		if ("pass".equals(replay)) {
			//ע�⣬��ӱ�����ʱ�Ѿ������˽�����˴�ֱ��get�����޸ļ���
			baoXiao.getResult().setResult("ͨ��");
			baoXiao.setStatus("������");
			baoXiao.setDeal_empno(empService.findCashier());
		}else if ("return".equals(replay)) {
			baoXiao.getResult().setResult("���");
			baoXiao.setStatus("���޸�");
			baoXiao.setDeal_empno(null);
		}else if("refuse".equals(replay)){
			baoXiao.getResult().setResult("�ܾ�");
			baoXiao.setStatus("��ֹ");
			baoXiao.setDeal_empno(null);
		}
		baoXiao.setModify_time(new Date());
		baoXiao.getResult().setCheck_emp(emp.getEname());
		baoXiao.getResult().setCheck_date(new Date());
		baoXiao.getResult().setComm(comm);
		empService.addOrUpdateBaoXiao(baoXiao);
		msg="��˳ɹ�!";
		return SUCCESS;
	}
	
	/**
	 * ���񸶿�
	 * @return
	 */
	public String pay(){
		baoXiao=empService.findBaoXiao(baoXiao);
		//ע�⣬��ӱ�����ʱ�Ѿ������˽�����˴�ֱ��get�����޸ļ���
		baoXiao.getResult().setResult("�Ѹ���");
		baoXiao.getResult().setCheck_emp(emp.getEname());
		baoXiao.getResult().setCheck_date(new Date());
		baoXiao.getResult().setComm(null);
		baoXiao.setStatus("��ֹ");
		baoXiao.setDeal_empno(null);
		baoXiao.setModify_time(new Date());
		empService.addOrUpdateBaoXiao(baoXiao);
		msg="����ɹ�!";
		return SUCCESS;
	}

	public BaoXiao_Detal getDetal() {
		return detal;
	}

	public void setDetal(BaoXiao_Detal detal) {
		this.detal = detal;
	}

	public BaoXiao getBaoXiao() {
		return baoXiao;
	}

	public void setBaoXiao(BaoXiao baoXiao) {
		this.baoXiao = baoXiao;
	}

	public ActionContext getAc() {
		return ac;
	}

	public void setAc(ActionContext ac) {
		this.ac = ac;
	}

	public Set<BaoXiao_Detal> getSet() {
		return set;
	}

	public void setSet(Set<BaoXiao_Detal> set) {
		this.set = set;
	}

	public Map<String, Object> getSession() {
		return session;
	}

	public void setSession(Map<String, Object> session) {
		this.session = session;
	}

	public Emp getEmp() {
		return emp;
	}

	public void setEmp(Emp emp) {
		this.emp = emp;
	}

	public EmpService getEmpService() {
		return empService;
	}

	public void setEmpService(EmpService empService) {
		this.empService = empService;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public String getFlag() {
		return flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}


	public String[] getItem() {
		return item;
	}

	public void setItem(String[] item) {
		this.item = item;
	}

	public Double[] getAccount() {
		return account;
	}

	public void setAccount(Double[] account) {
		this.account = account;
	}

	public String[] getDes() {
		return des;
	}

	public void setDes(String[] des) {
		this.des = des;
	}

	public String getReplay() {
		return replay;
	}

	public void setReplay(String replay) {
		this.replay = replay;
	}

	public String getComm() {
		return comm;
	}

	public void setComm(String comm) {
		this.comm = comm;
	}
	
	
	
	
	
	
}
