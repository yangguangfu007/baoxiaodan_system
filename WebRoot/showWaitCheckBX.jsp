<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    
    <title>待审核报销单</title>
	<style type="text/css">
		td{text-align: center;
		width: 120px;}
	</style>
  </head>
  
  <body>
  <h2>待审核报销单列表</h2>
  <h2>${msg }</h2>
  <table>
  	<tr>
  		<td>填报人</td>
  		<td>填报时间</td>
  		<td>修改时间</td>
  		<td>总金额</td>
  		<td>事由</td>
  		<td>状态</td>
  	</tr>
  	<s:iterator value="emp.baoXiao2" var="b">
  	<tr>
  		<td>${b.create_empno.ename }</td>
  		<td>${b.create_time }</td>
  		<td>${b.modify_time }</td>
  		<td>${b.total_account }</td>
  		<td>${b.event }</td>
  		<td>${b.status }</td>
  		<td>
  			<s:if test="#session.emp.position.pname=='财务'">
  				<a href="BaoXiaoAction_pay?baoXiao.bxid=${b.bxid }">付款</a>
  			</s:if>
  			<s:else>
  				<a href="BaoXiaoAction_findOneBX?baoXiao.bxid=${b.bxid }">批复</a>
  			</s:else>
  		</td>
  	</tr>
  	</s:iterator>
  </table>
  <h4><a href="retrunIndex">返回首页</a></h4>
  </body>
</html>
