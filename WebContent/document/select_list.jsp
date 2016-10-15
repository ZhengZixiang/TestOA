<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/common.jsp" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<link href="style/oa.css" rel="stylesheet" type="text/css">
	<script type="text/javascript" src="script/public.js"></script>
	<title>请选择要添加的公文类型</title>
</head>
<body>
	<center>
		<table class="tableEdit" border="0" cellspacing="1" cellpadding="0" style="width:580px;">
			<tbody>
				<tr>
					<td align="center" class="tdEditTitle">请选择要添加的公文类型</td>
				</tr>
				<s:iterator value="workflows" id="workflow">
				<tr><td align="center"><a href="document!addInput?workflowId=<s:property value="#workflow.id"/>"><s:property value="#workflow.name"/></a></td></tr>
				</s:iterator>
			</tbody>
		</table>
		
		<table>
			<tr align="center">
				<td colspan="3" bgcolor="#EFF3F7">
				<input type="button" class="MyButton" value="关闭窗口" onclick="window.close()">
				</td>
			</tr>
		</table>
	</center>
</body>
</html>