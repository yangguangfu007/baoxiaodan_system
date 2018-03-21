<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    
    <title>显示报销单</title>
	<style type="text/css">
		td{text-align: center;
		width: 120px;}
	
	</style>
  </head>
  
  <body>
  <h3>报销单列表</h3>
  <h2>${msg }</h2>
  <table>
  	<tr>
  		<td>填报人</td>
  		<td>填报时间</td>
  		<td>审核人</td>
  		<td>修改时间</td>
  		<td>总金额</td>
  		<td>事由</td>
  		<td>状态</td>
  		<td>审核结果</td>
  		<td>审核意见</td>
  	</tr>
  	<s:iterator value="emp.baoXiao1" var="b">
  	<tr>
  		<td>${b.create_empno.ename }</td>
  		<td>${b.create_time }</td>
  		<td>${b.result.check_emp }</td>
  		<td>${b.modify_time }</td>
  		<td>${b.total_account }</td>
  		<td>${b.event }</td>
  		<td>${b.status }</td>
  		<td>${b.result.result }</td>
  		<td>${b.result.comm }</td>
  		<td>
  			<s:if test="#b.status=='待修改'">
  				<a href="BaoXiaoAction_findOneBX?baoXiao.bxid=${b.bxid }">修改</a>
  			</s:if>
  		</td>
  	</tr>
  	</s:iterator>
  </table>
  <h4><a href="retrunIndex">返回首页</a></h4>
  </body>
</html>
