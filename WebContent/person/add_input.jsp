<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ include file="/common/common.jsp"%>

<!DOCTYPE html PUBLIC "-//W3C//Dtd HTML 4.01 transitional//EN" "http://www.w3.org/tr/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=GB18030">
<link href="style/oa.css" rel="stylesheet" type="text/css">
<script language="javascript" src="script/public.js"></script>
<title>添加人员信息</title>
</head>
<body>
	<center>
		<form action="person!save" method="post">
			<table class="tableEdit" border="0" cellspacing="1" cellpadding="0"
				style="width: 580px;">
				<tbody>
					<tr>
						<!-- 这里是添加、编辑界面的标题 -->
						<td align="center" class="tdEditTitle">添加人员信息</td>
					</tr>
					<tr>
						<td>
							<table class="tableEdit" style="width: 580px;" cellspacing="0"
								border="0" cellpadding="0">
								<tr>
									<td class="tdEditLabel">所属机构</td>
									<td class="tdEditContent">
										<input type="text" id="orgNameId" disabled="disabled">
										<input type="hidden" name="orgId" id="orgIdId">
										<input type="button" value="选择" onclick="openWin('org?select=true','selectOrgs',800,600,1);">
									</td>
									<td class="tdEditLabel">姓名</td>
									<td class="tdEditContent">
										<input type="text" name="person.name">
									</td>
								</tr>
								<tr>
									<td class="tdEditLabel">性别</td>
									<td class="tdEditContent"><input type="text" name="person.sex"></td>
									<td class="tdEditLabel">职务</td>
									<td class="tdEditContent"><input type="text" name="person.duty"></td>
								</tr>
								<tr>
									<td class="tdEditLabel">电话</td>
									<td class="tdEditContent"><input type="text" name="person.phone"></td>
								</tr>
							</table>
						</td>
					</tr>
				</tbody>
			</table>

			<table>
				<tr align="center">
					<td colspan="3" bgcolor="#EFF3F7"><input type="submit"
						class="MyButton" value="保存人员信息"> <input type="button"
						class="MyButton" value="关闭窗口" onclick="window.close()"></td>
				</tr>
			</table>
		</form>
	</center>
</body>
</html>