<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
  
    <title>添加报销单信息</title>
	
  </head>
  
  <body>
   <h3>报销单信息完善</h3>
 	<s:form action="BaoXiaoAction_addBaoXiao" method="post">
 		<s:textfield label="报销事由" name="baoXiao.event"></s:textfield>
 		<s:submit value="提交审核"></s:submit>
 	</s:form>
 	
 	
  </body>
</html>
