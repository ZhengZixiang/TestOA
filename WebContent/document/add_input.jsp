<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ include file="/common/common.jsp" %>
<%@ page import="com.opensymphony.xwork2.ActionContext" %>

<!DOCTYPE html PUBLIC "-//W3C//Dtd HTML 4.01 transitional//EN" "http://www.w3.role/tr/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<link href="style/oa.css" rel="stylesheet" type="text/css">
<script language="javascript" src="script/public.js"></script>
<title>添加公文信息</title>
</head>
<body>
	<center>
		<form action="document!add" method="post" enctype="multipart/form-data">
			<input type="hidden" name="workflowId" value="<s:property value="workflowId"/>"/>
			<table class="tableEdit" border="0" cellspacing="1" cellpadding="0"
				style="width: 580px;">
				<tbody>
					<tr>
						<td align="center" class="tdEditTitle">添加公文信息</td>
					</tr>
					<tr>
						<td>
							<table class="tableEdit" style="width: 580px;" cellspacing="0"
								border="0" cellpadding="0">
								<tr>
									<td class="tdEditLabel">公文名称</td>
									<td class="tdEditContent"><input type="text" name="document.title"/></td>
									<td class="tdEditLabel">公文描述</td>
									<td class="tdEditContent"><input type="text" name="document.description"/></td>
								</tr>
								<tr>
									<td class="tdEditLabel">公文附件</td>
									<td class="tdEditContent"><input type="file" name="attachment"/></td>
								</tr>
							</table>
							${mytag:form(wfid)}
						</td>
					</tr>
				</tbody>
			</table>

			<table>
				<tr align="center">
					<td colspan="3" bgcolor="#EFF3F7">
						<input type="submit" class="MyButton" value="保存公文信息">
						<input type="button" class="MyButton" value="关闭窗口" onclick="window.close()">
					</td>
				</tr>
			</table>
		</form>
	</center>
</body>
</html>