<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/common.jsp" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<link href="style/oa.css" rel="stylesheet" type="text/css">
	<script src="script/public.js"></script>
	<title>查看流程【<s:property value="workflow.name"/>】图片</title>
</head>
<body>
	<center>
		<table class="tableEdit" border="0"	cellspacing="1" cellpadding="0" style="width:580px">
			<tr>
				<td align="center">
				【查看流程图片】&nbsp;
				<a href="workflow!viewCode?workflow.id=<s:property value="workflow.id"/>">查看流程代码</a>
				</td>
			</tr>
			
			<tr>
				<td align="center">
				<img src="workflow!showImage?workflow.id=<s:property value="workflow.id"/>"/>
				</td>
			</tr>
		</table>
		
		<table>
			<tr align="center">
				<td colspan="3" bgcolor="#EFF3F7">
					<input type="button" value="关闭窗口" onclick="window.close()">
				</td>
			</tr>
		</table>
	</center>
</body>
</html>