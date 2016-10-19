<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/common.jsp" %>

<!DOCTYPE html PUBLIC "-//W3C//Dtd HTML 4.01 transitional//EN" "http://www.w3.org/tr/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<link href="style/oa.css" rel="stylesheet" type="text/css">
	<script language="javascript" src="script/public.js"></script>
	<title>输入表单域条目</title>
</head>
<body>
	<center>
		<form action="form!addItem" method="post">
			<input type="hidden" name="field.id" value="<s:property value="field.id"/>"/>
			<table class="tableEdit" border="0" cellspacing="1" cellpadding="0" style="width:580px;">
				<tbody>
					<tr><td align="center" class="tdEditTitle">输入表单域条目</td></tr>
					<tr><td>
						<table class="tableEdit" style="width:580px;" cellspacing="0" border="0" cellpadding="0">
						<s:iterator begin="0" end="%{field.items.size() - 1}" var="i">
							<tr>
								<td class="tdEditLabel" >文本</td>
								<td class="tdEditContent"><input type="text" name="items[<s:property value="#i"/>].label" value="<s:property value="%{field.items[#i].label}"/>">
								</td>
								<td class="tdEditLabel" >值</td>			
								<td class="tdEditContent"><input type="text" name="items[<s:property value="#i"/>].value" value="<s:property value="%{field.items[#i].value}"/>"></td>
							</tr>
						</s:iterator>
						<s:iterator begin="%{field.items.size()}" end="20" var="i">
							<tr>
								<td class="tdEditLabel" >文本</td>
								<td class="tdEditContent"><input type="text" name="items[<s:property value="#i"/>].label" value="<s:property value=""/>">
								</td>
								<td class="tdEditLabel" >值</td>			
								<td class="tdEditContent"><input type="text" name="items[<s:property value="#i"/>].value" value="<s:property value=""/>"></td>
							</tr>
						</s:iterator>
						</table>
					</td></tr>
				</tbody>
			</table>
	
			<table>
				<tr align="center">
					<td colspan="3" bgcolor="#EFF3F7">
					<input type="submit" class="MyButton" value="保存表单域条目信息">
					<input type="button" class="MyButton" value="关闭窗口" onclick="window.close()">
					</td>
				</tr>
			</table>
		</form>
	</center>
</body>
</html>