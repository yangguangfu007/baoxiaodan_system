<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
  
    <title>财务首页</title>
	
  </head>
  
  <body>
  <h3>欢迎您！${emp.ename }</h3>
  		<h3>${msg }</h3>
 		<h4><a href="BaoXiaoAction_findWaitCheckBX">查看待付款报销单</a></h4>
 		<h4><a href="EmpAction_exit">注销登录</a></h4>
  </body>
</html>
