<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    
    <title>报销单明细</title>
	<style type="text/css">
		td{
			text-align: center;
			width: 150px;
		}
	</style>
  </head>
  
  <body>
  <h3>修改报销单</h3>
  	<s:form action="BaoXiaoAction_updateBX" method="post" theme="simple">
  		<s:iterator value="baoXiao.detals" var="b">
  			项目：<s:textfield name="item" value="%{#b.item}"></s:textfield>
  			金额<s:textfield name="account" value="%{#b.account}"></s:textfield>
  			费用说明：<s:textfield name="des" value="%{#b.des}"></s:textfield>
  			<hr/>
  		</s:iterator>
  		<s:hidden name="baoXiao.bxid" value="%{baoXiao.bxid}"></s:hidden>
  		事由：<s:textarea name="baoXiao.event" value="%{baoXiao.event}"></s:textarea>
  		<s:submit value="提交"></s:submit>
  	</s:form>
  <a href="retrunIndex">返回首页</a>
  </body>
</html>
