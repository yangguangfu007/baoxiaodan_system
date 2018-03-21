<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
  
    <title>添加报销单</title>
	
  </head>
  
  <body>
   <h3>添加报销单明细</h3>
 	<s:form action="BaoXiaoAction_addDetal" method="post">
 		<s:textfield label="项目" name="detal.item"></s:textfield>
 		<s:textfield label="金额" name="detal.account"></s:textfield>
 		<s:textfield label="事由" name="detal.des"></s:textfield>
 		<s:submit value="提交"></s:submit>
 	</s:form>
 	
  </body>
</html>
