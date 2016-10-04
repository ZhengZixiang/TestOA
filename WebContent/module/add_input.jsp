<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ include file="/common/common.jsp" %>

<!DOCTYPE html PUBLIC "-//W3C//Dtd HTML 4.01 transitional//EN" "http://www.w3.module/tr/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=GB18030">
<link href="style/oa.css" rel="stylesheet" type="text/css">
<script language="javascript" src="script/public.js"></script>
<title>添加模块信息</title>
</head>
<body>
	<center>
		<form action="module!save" method="post">
			<input type="hidden" name="parentId" value="<s:property value="parentId"/>"/>
			<table class="tableEdit" border="0" cellspacing="1" cellpadding="0"
				style="width: 580px;">
				<tbody>
					<tr>
						<!-- 这里是添加、编辑界面的标题 -->
						<td align="center" class="tdEditTitle">添加模块信息</td>
					</tr>
					<tr>
						<td>
							<table class="tableEdit" style="width: 580px;" cellspacing="0"
								border="0" cellpadding="0">
								<tr>
									<td class="tdEditLabel">模块名称</td>
									<td class="tdEditContent"><input type="text" name="module.name"/></td>
									<td class="tdEditLabel">模块编号</td>
									<td class="tdEditContent"><input type="text" name="module.sn"></td>
								</tr>
								<tr>
									<td class="tdEditLabel">模块地址</td>
									<td class="tdEditContent"><input type="text" name="module.url"/></td>
									<td class="tdEditLabel">排序号</td>
									<td class="tdEditContent"><input type="text" name="module.orderNo"></td>
								</tr>
							</table> <!-- 主输入域结束 -->
						</td>
					</tr>
				</tbody>
			</table>

			<table>
				<tr align="center">
					<td colspan="3" bgcolor="#EFF3F7">
						<input type="submit" class="MyButton" value="保存模块信息">
						<input type="button" class="MyButton" value="关闭窗口" onclick="window.close()">
					</td>
				</tr>
			</table>
		</form>
	</center>
</body>
</html>