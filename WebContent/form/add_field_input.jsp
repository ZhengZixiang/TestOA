<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/common/common.jsp" %>

<!DOCTYPE html PUBLIC "-//W3C//Dtd HTML 4.01 transitional//EN" "http://www.w3.org/tr/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="style/oa.css" rel="stylesheet" type="text/css">
<script language="javascript" src="script/public.js"></script>
<title>输入表单域</title>
</head>
<body>
	<center>
		<form action="form!addField" method="post">
			<input type="hidden" name="form.id" value="<s:property value="form.id"/>">
			<table class="tableEdit" border="0" cellspacing="1" cellpadding="0" style="width:580px;">
				<tbody>
					<tr>
						<td align="center" class="tdEditTitle">输入表单域</td>
					</tr>
					<tr>
						<td>
							<table class="tableEdit" style="width:580px;" cellspacing="0" border="0" cellpadding="0">
								<tr>
								<td class="tdEditLabel" >标签</td>			
								<td class="tdEditContent"><input type="text" name="field.fieldLabel"></td>
								<td class="tdEditLabel" >名称</td>
								<td class="tdEditContent"><input type="text" name="field.fieldName"></td>
								</tr>
								<tr>
								<td class="tdEditLabel" >类型</td>			
								<td class="tdEditContent">
									<select name="type.id">
										<s:iterator value="%{#types}" id="ft">
											<option value="<s:property value="#ft.id"/>"><s:property value="#ft.name"/></option>
										</s:iterator>
									</select>
								</td>
								<td class="tdEditLabel" >输入形式</td>			
								<td class="tdEditContent">
								<select name="input.id">
										<s:iterator value="%{#inputs}" id="fi">
											<option value="<s:property value="#fi.id"/>"><s:property value="#fi.name"/></option>
										</s:iterator>
								</select>		
								</td>
								</tr>
							</table>
						</td>
					</tr>
				</tbody>
			</table>
	
			<table>
				<tr align="center">
					<td colspan="3" bgcolor="#EFF3F7">
					<input type="submit" class="MyButton" value="保存表单域信息">
					<input type="button" class="MyButton" value="关闭窗口" onclick="window.close()">
					</td>
				</tr>
			</table>
		</form>
	</center>
</body>
</html>