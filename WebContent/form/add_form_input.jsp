<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/common.jsp" %>

<!DOCTYPE html PUBLIC "-//W3C//Dtd HTML 4.01 transitional//EN" "http://www.w3.org/tr/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<link href="style/oa.css" rel="stylesheet" type="text/css">
	<script language="javascript" src="script/public.js"></script>
	<title>设置流程表单</title>
</head>
<body>
<center>
	<form action="form!addForm" method="post">
		<input type="hidden" name="form.id" value="<s:property value="form.id"/>"/>
		<input type="hidden" name="workflow.id" value="<s:property value="workflow.id"/>"/>
		<table class="tableEdit" border="0" cellspacing="1" cellpadding="0" style="width:580px;">
			<tbody>
			<tr>
				<td align="center" class="tdEditTitle">请设置流程【<s:property value="workflow.name"/>】的表单</td>
			</tr>
			<tr>
				<td>
				<table class="tableEdit" style="width:580px;" cellspacing="0" border="0" cellpadding="0">
					<tr>
						<td class="tdEditLabel" >表单模板</td>			
						<td class="tdEditContent"><input type="text" name="form.template" value="<s:property value="form.template"/>"></td>
						<td class="tdEditLabel" ></td>			
						<td class="tdEditContent"></td>
					</tr>
				</table>
				
				<s:if test="%{form != null}">
				<hr>
				<table class="tableEdit" style="width:580px;" cellspacing="0" border="0" cellpadding="0">
					<tr bgcolor="#EFF3F7" class="tableBody1">
						<td width="20%"><b>标签</b></td>			
						<td width="20%"><b>名称</b></td>
						<td width="20%" ><b>类型</b></td>			
						<td width="20%"><b>输入形式</b></td>
						<td width="20%"><b>操作</b></td>
					</tr>
					<s:iterator value="%{form.fields}" id="field">
					<tr>
						<td><s:property value="#field.fieldLabel"/></td>			
						<td><s:property value="#field.fieldName"/></td>
						<td><s:property value="#field.fieldType.name"/></td>			
						<td><s:property value="#field.fieldInput.name"/></td>
						<td>
							<a href="#" onclick="del('form!deleteField?field.id=<s:property value="#field.id"/>')">删除</a>
							<a href="#" onclick="openWin('form!addItemInput?field.id=<s:property value="#field.id"/>','additem',700,600)">条目</a>
						</td>
					</tr>
					</s:iterator>
				</table>
				</s:if>
				</td>
			</tr>
			</tbody>
		</table>
		
		<table>
				<tr align="center">
					<td colspan="3" bgcolor="#EFF3F7">
					<input type="submit" class="MyButton" value="保存表单信息">
					<s:if test="%{form != null}">
					<input type="button" class="MyButton" value="添加表单域" onclick="openWin('form!addFieldInput?form.id=<s:property value="form.id"/>','addformfield',600,200)">
					</s:if>
					<input type="button" class="MyButton" value="关闭窗口" onclick="window.close()">
					</td>
				</tr>
		</table>
	</form>
</center>
</body>
</html>