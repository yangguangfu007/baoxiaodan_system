<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
  
    <title>报销单首页</title>
	
  </head>
  
  <body>
  <h3>用户登录</h3>
  ${msg }
   <s:form action="EmpAction_login" method="post">
   		<s:textfield name="emp.ename" label="用户名"></s:textfield>
   		<s:password name="emp.password" label="密码"></s:password>
   		<s:submit value="登录"></s:submit>
   </s:form>
  </body>
</html>
