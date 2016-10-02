<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ include file="/common/common.jsp" %>

<!DOCTYPE html PUBLIC "-//W3C//Dtd HTML 4.01 transitional//EN" "http://www.w3.org/tr/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=GB18030">
<link href="style/oa.css" rel="stylesheet" type="text/css">
<script language="javascript" src="script/public.js"></script>
<title>更新机构信息</title>
</head>
<body>
	<center>
		<form action="org!update" method="post">
			<input type="hidden" name="org.id" value="<s:property value="org.id"/>"/>
			<input type="hidden" name="org.sn" value="<s:property value="org.sn"/>"/>
			<input type="hidden" name="parentId" value="<s:property value="org.parent.id"/>"/>
			<table class="tableEdit" border="0" cellspacing="1" cellpadding="0"
				style="width: 580px;">
				<tbody>
					<tr>
						<!-- 这里是更新、编辑界面的标题 -->
						<td align="center" class="tdEditTitle">更新机构信息</td>
					</tr>
					<tr>
						<td>
							<table class="tableEdit" style="width: 580px;" cellspacing="0"
								border="0" cellpadding="0">
								<tr>
									<td class="tdEditLabel">机构名称</td>
									<td class="tdEditContent"><input type="text" name="org.name" value="<s:property value="org.name"/>"/></td>
									<td class="tdEditLabel">机构描述</td>
									<td class="tdEditContent"><input type="text" name="org.description" value="<s:property value="org.description"/>"/></td>
								</tr>
							</table> <!-- 主输入域结束 -->
						</td>
					</tr>
				</tbody>
			</table>

			<table>
				<tr align="center">
					<td colspan="3" bgcolor="#EFF3F7">
						<input type="submit" class="MyButton" value="更新机构信息">
						<input type="button" class="MyButton" value="关闭窗口" onclick="window.close()">
					</td>
				</tr>
			</table>
		</form>
	</center>
</body>
</html>