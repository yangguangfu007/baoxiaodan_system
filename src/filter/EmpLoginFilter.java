package filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import entity.Emp;

public class EmpLoginFilter implements Filter {

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		System.err.println("���ٹ�����!");
	}

	@Override
	public void doFilter(ServletRequest arg0, ServletResponse arg1,
			FilterChain arg2) throws IOException, ServletException {
		// TODO Auto-generated method stub
		HttpServletRequest request = (HttpServletRequest) arg0;
		HttpServletResponse response = (HttpServletResponse) arg1;
		HttpSession session = request.getSession();
		Emp emp = (Emp) session.getAttribute("emp");
		if (emp == null
				&& !request.getRequestURI().equals("/baoxiaodan/index.jsp")) {
			request.setAttribute("msg", "���ȵ�¼!");
			request.getRequestDispatcher("index.jsp")
					.forward(request, response);
		}
		arg2.doFilter(arg0, arg1);
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub
		System.out.println("ִ�й�������֤�û��Ƿ��¼!");
	}

}
