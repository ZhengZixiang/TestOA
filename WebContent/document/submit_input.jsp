<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/common.jsp" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<link href="style/oa.css" rel="stylesheet" type="text/css">
	<script type="text/javascript" src="script/public.js"></script>
	<title>请选择一个步骤进行提交</title>
</head>
<body>
	<center>
		<form action="document!submit" method="post">
			<input type="hidden" name="document.id" value="<s:property value="document.id"/>"/>
			<table class="tableEdit" border="0" cellspacing="1" cellpadding="0" style="width:580px;">
				<tbody>
					<tr><td align="center" class="tdEditTitle">请选择一个步骤进行提交</td></tr>
					<tr><td>
						<table class="tableEdit" style="width:580px;" cellspacing="0" border="0" cellpadding="0">
							<s:iterator value="steps" id="step">
							<tr>
								<td style="width:100%; align:center"><input type="radio" name="stepId" value="<s:property value="value"/>"><s:property value="key"/></td>		
							</tr>
							</s:iterator>
						</table>
					</td></tr>
				</tbody>
			</table>
			
			<table>
				<tr align="center">
					<td colspan="3" bgcolor="#EFF3F7">
						<input type="submit" value="提交"/>
						<input type="button" onclick="window.close()" value="关闭窗口"/>
					</td>
				</tr>
			</table>
		</form>
	</center>
</body>
</html>