package action;

import java.util.Map;

import service.EmpService;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

import entity.Emp;

@SuppressWarnings("serial")
public class EmpAction extends ActionSupport {
	private Emp emp;
	private EmpService empService;
	private String set;
	private String msg;
	/**
	 * Ա����¼
	 * @return
	 */
	public String login(){
		emp=empService.login(emp);
		if (emp==null) {
			msg="�û��������벻ƥ�䣡";
			set=LOGIN;
		}else{
			//��Ա�����浽session��
			ActionContext ac=ActionContext.getContext();
			Map<String, Object> session=ac.getSession();
			session.put("emp", emp);
			if ("��ͨԱ��".equals(emp.getPosition().getPname())) {
				set="staff";
			}else if ("���ž���".equals(emp.getPosition().getPname())) {
				set="deptManager";
			}else if("�ܾ���".equals(emp.getPosition().getPname())){
				set="manager";
			}else if ("����".equals(emp.getPosition().getPname())) {
				set="cashier";
			}
		}
		return set;
	}
	/**
	 * ������Ҳ�淵����ҳ
	 * @return
	 */
	public String retrunIndex(){
		ActionContext ac=ActionContext.getContext();
		Map<String, Object> session=ac.getSession();
		emp=(Emp) session.get("emp");
		if ("��ͨԱ��".equals(emp.getPosition().getPname())) {
			set="staff";
		}else if ("���ž���".equals(emp.getPosition().getPname())) {
			set="deptManager";
		}else if("�ܾ���".equals(emp.getPosition().getPname())){
			set="manager";
		}else if ("����".equals(emp.getPosition().getPname())) {
			set="cashier";
		}
		return set;
	}
	
	//ע����¼
	public String exit(){
		ActionContext ac=ActionContext.getContext();
		Map<String, Object> session=ac.getSession();
		session.clear();
		return "login";
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

	public String getSet() {
		return set;
	}

	public void setSet(String set) {
		this.set = set;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	

	
}
