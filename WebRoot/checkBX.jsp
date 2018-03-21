<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>

<title>报销单明细</title>
<style type="text/css">
td {
	text-align: center;
	width: 150px;
}
</style>
</head>

<body>
	<h3>审批报销单</h3>
	<table>
		<tr>
			<td>项目</td>
			<td>金额</td>
			<td>费用说明</td>
		</tr>
		<s:iterator value="baoXiao.detals" var="b">
			<tr>
				<td>${b.item }</td>
				<td>${b.account }</td>
				<td>${b.des }</td>
			</tr>
		</s:iterator>
	</table>
	<h4>总金额：${baoXiao.total_account }</h4>
	<s:if test="#session.emp.position.pid==8"><!--部门经理  -->
		<!--使用set存值时，value里是双引号套单引号，再放字符串  -->
		<s:set id="actionUrl" value="'BaoXiaoAction_checkBX'"></s:set>
	</s:if>
	<s:elseif test="#session.emp.position.pid==7">
		<s:set id="actionUrl" value="'BaoXiaoAction_checkBX_manager'"></s:set>
	</s:elseif>
	<s:form action="%{actionUrl}" method="post">
		<s:hidden name="baoXiao.bxid" value="%{baoXiao.bxid}"></s:hidden>
		<s:select list="#{'pass':'通过','return':'打回','refuse':'拒绝'}"
			name="replay" label="批复"></s:select>
		<br>
		<s:textarea name="comm" label="批复意见" value="同意"></s:textarea>
		<s:submit value="提交"></s:submit>
	</s:form>
	<a href="retrunIndex">返回首页</a>
</body>
</html>
