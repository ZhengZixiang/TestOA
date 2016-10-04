<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ include file="/common/common.jsp" %>

<!DOCTYPE html PUBLIC "-//W3C//Dtd HTML 4.01 transitional//EN" "http://www.w3.org/tr/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=GB18030">
<link href="style/oa.css" rel="stylesheet" type="text/css">
<script language="javascript" src="script/public.js"></script>
<title>更新人员信息</title>
</head>
<body>
	<center>
		<form action="person!update" method="post">
			<input type="hidden" name="person.id" value="<s:property value="person.id"/>"/>
			<table class="tableEdit" border="0" cellspacing="1" cellpadding="0"
				style="width: 580px;">
				<tbody>
					<tr>
						<!-- 这里是更新、编辑界面的标题 -->
						<td align="center" class="tdEditTitle">更新人员信息</td>
					</tr>
					<tr>
						<td>
							<table class="tableEdit" style="width: 580px;" cellspacing="0"
								border="0" cellpadding="0">
								<tr>
									<td class="tdEditLabel">所属机构</td>
									<td class="tdEditContent">
										<input type="text" id="orgNameId" disabled="disabled" value="<s:property value="person.org.name"/>">
										<input type="hidden" name="orgId" id="orgIdId" value="<s:property value="person.org.id"/>">
										<input type="button" value="选择" onclick="openWin('org?select=true','selectOrgs',800,600,1);">
									</td>
									<td class="tdEditLabel">姓名</td>
									<td class="tdEditContent">
										<input type="text" name="person.name" value="<s:property value="person.name"/>">
									</td>
								</tr>
								<tr>
									<td class="tdEditLabel">性别</td>
									<td class="tdEditContent"><input type="text" name="person.sex" value="<s:property value="person.sex"/>"></td>
									<td class="tdEditLabel">职务</td>
									<td class="tdEditContent"><input type="text" name="person.duty" value="<s:property value="person.duty"/>"></td>
								</tr>
								<tr>
									<td class="tdEditLabel">电话</td>
									<td class="tdEditContent"><input type="text" name="person.phone" value="<s:property value="person.phone"/>"></td>
								</tr>
							</table> <!-- 主输入域结束 -->
						</td>
					</tr>
				</tbody>
			</table>

			<table>
				<tr align="center">
					<td colspan="3" bgcolor="#EFF3F7">
						<input type="submit" class="MyButton" value="更新人员信息">
						<input type="button" class="MyButton" value="关闭窗口" onclick="window.close()">
					</td>
				</tr>
			</table>
		</form>
	</center>
</body>
</html>