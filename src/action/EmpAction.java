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
	 * 员工登录
	 * @return
	 */
	public String login(){
		emp=empService.login(emp);
		if (emp==null) {
			msg="用户名与密码不匹配！";
			set=LOGIN;
		}else{
			//将员工保存到session中
			ActionContext ac=ActionContext.getContext();
			Map<String, Object> session=ac.getSession();
			session.put("emp", emp);
			if ("普通员工".equals(emp.getPosition().getPname())) {
				set="staff";
			}else if ("部门经理".equals(emp.getPosition().getPname())) {
				set="deptManager";
			}else if("总经理".equals(emp.getPosition().getPname())){
				set="manager";
			}else if ("财务".equals(emp.getPosition().getPname())) {
				set="cashier";
			}
		}
		return set;
	}
	/**
	 * 从其他也面返回首页
	 * @return
	 */
	public String retrunIndex(){
		ActionContext ac=ActionContext.getContext();
		Map<String, Object> session=ac.getSession();
		emp=(Emp) session.get("emp");
		if ("普通员工".equals(emp.getPosition().getPname())) {
			set="staff";
		}else if ("部门经理".equals(emp.getPosition().getPname())) {
			set="deptManager";
		}else if("总经理".equals(emp.getPosition().getPname())){
			set="manager";
		}else if ("财务".equals(emp.getPosition().getPname())) {
			set="cashier";
		}
		return set;
	}
	
	//注销登录
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
