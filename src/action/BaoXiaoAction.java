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
	private String comm;//批复意见
	/**
	 * 添加报销单明细
	 * 把报销明细存入session
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
	 * 添加报销单，存入数据库
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public String addBaoXiao(){
		set=(Set<BaoXiao_Detal>) session.get("detalset");
		//从数据库中查员工，打开session通道
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
		baoXiao.setStatus("待审核");
		createEmp.getBaoXiao1().add(baoXiao);
		dealEmp.getBaoXiao2().add(baoXiao);
		//添加审核结果表
		Result result=new Result();
		result.setCheck_emp(dealEmp.getEname());
		result.setBaoXiao(baoXiao);
		baoXiao.setResult(result);
		if (empService.addOrUpdateBaoXiao(baoXiao)) {
			//清空session，防止再次添加报销单时，把之前的又添加一次。
			session.remove("detalset");
			return SUCCESS;
		}
		return "fail";
	}
	
	/**
	 * 查找单个员工创建报销单，
	 * @return
	 */
	public String findAll(){
		//获得自己创建的报销单集合,查出员工，在jsp页面再查出员工创建的报销单即可
		emp=empService.findEmp(emp);
		if (emp.getBaoXiao1().size()==0) {
			msg="您还没有创建过报销单哦！";
			return "fail";
		}
		return SUCCESS;
	}
	
	/**
	 * 查找员工待审核的报销单,
	 * 从session中取出用户,查员工，并获得他要审核的报销单集合。
	 * @return
	 */
	public String findWaitCheckBX(){
		//获得要审核的报销单集合
		emp=empService.findEmp(emp);
		if (emp.getBaoXiao2().size()==0) {
			if ("财务".equals(emp.getPosition().getPname())) {
				msg="您没有要付款的报销单哦！";
			}else {
				msg="您没有要审核的报销单哦！";
			}
			return "fail";
		}
		return SUCCESS;
	}
	
	/**
	 * 查找报销单及其明细,根据不同员工跳不同地址
	 * @return
	 */
	public String findOneBX(){
		baoXiao=empService.findBaoXiao(baoXiao);
		emp=empService.findEmp(emp);
		if ("普通员工".equals(emp.getPosition().getPname())) {
			flag="update";//修改
		}else if ("部门经理".equals(emp.getPosition().getPname())
				||"总经理".equals(emp.getPosition().getPname())) {
			flag="check";//审核
		}
		return flag;
	}
	
	/**
	 * 员工修改报销单
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
		bx.setStatus("待审核");
		bx.getResult().setCheck_emp(dealEmp.getEname());
		bx.getResult().setComm(null);
		bx.setEvent(baoXiao.getEvent());
		bx.setDeal_empno(dealEmp);
		bx.setModify_time(new Date());
		empService.addOrUpdateBaoXiao(bx); 
		msg="修改成功！";
		return SUCCESS;
	}
	/**
	 * 部门经理审核报销单
	 * @return
	 */
	public String checkBX(){
		baoXiao=empService.findBaoXiao(baoXiao);
		if ("pass".equals(replay)) {
			baoXiao.getResult().setResult("通过");
			//根据报销单金额，修改报销单状态
			if (baoXiao.getTotal_account()<5000) {
				//注意，添加报销单时已经添加审核结果表，此处直接get出来修改即可
				baoXiao.setStatus("待付款");
				baoXiao.setDeal_empno(empService.findCashier());
			}else {
				baoXiao.setStatus("待总经理审核");
				baoXiao.setDeal_empno(empService.findGeneralManager());
			}
		}else if ("return".equals(replay)) {
			baoXiao.getResult().setResult("打回");
			baoXiao.setStatus("待修改");
			baoXiao.setDeal_empno(null);
		}else if("refuse".equals(replay)){
			baoXiao.getResult().setResult("拒绝");
			baoXiao.setStatus("终止");
			baoXiao.setDeal_empno(null);
		}
		baoXiao.setModify_time(new Date());
		baoXiao.getResult().setCheck_emp(emp.getEname());
		baoXiao.getResult().setCheck_date(new Date());
		baoXiao.getResult().setComm(comm);
		empService.addOrUpdateBaoXiao(baoXiao);
		msg="审核成功!";
		return SUCCESS;
		
	}
	
	/**
	 * 总经理审核报销单
	 * @return
	 */
	public String checkBX_manager(){
		baoXiao=empService.findBaoXiao(baoXiao);
		if ("pass".equals(replay)) {
			//注意，添加报销单时已经添加审核结果表，此处直接get出来修改即可
			baoXiao.getResult().setResult("通过");
			baoXiao.setStatus("待付款");
			baoXiao.setDeal_empno(empService.findCashier());
		}else if ("return".equals(replay)) {
			baoXiao.getResult().setResult("打回");
			baoXiao.setStatus("待修改");
			baoXiao.setDeal_empno(null);
		}else if("refuse".equals(replay)){
			baoXiao.getResult().setResult("拒绝");
			baoXiao.setStatus("终止");
			baoXiao.setDeal_empno(null);
		}
		baoXiao.setModify_time(new Date());
		baoXiao.getResult().setCheck_emp(emp.getEname());
		baoXiao.getResult().setCheck_date(new Date());
		baoXiao.getResult().setComm(comm);
		empService.addOrUpdateBaoXiao(baoXiao);
		msg="审核成功!";
		return SUCCESS;
	}
	
	/**
	 * 财务付款
	 * @return
	 */
	public String pay(){
		baoXiao=empService.findBaoXiao(baoXiao);
		//注意，添加报销单时已经添加审核结果表，此处直接get出来修改即可
		baoXiao.getResult().setResult("已付款");
		baoXiao.getResult().setCheck_emp(emp.getEname());
		baoXiao.getResult().setCheck_date(new Date());
		baoXiao.getResult().setComm(null);
		baoXiao.setStatus("终止");
		baoXiao.setDeal_empno(null);
		baoXiao.setModify_time(new Date());
		empService.addOrUpdateBaoXiao(baoXiao);
		msg="付款成功!";
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
