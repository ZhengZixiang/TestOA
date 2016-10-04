<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@include file="/common/common.jsp"%>

<!DOCTYPE html PUBLIC "-//W3C//Dtd HTML 4.01 transitional//EN" "http://www.w3.org/tr/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<link href="style/oa.css" rel="stylesheet" type="text/css">
<script language="javascript" src="script/public.js"></script>
<title>给用户分配新的角色</title>
</head>
<body>
	<center>
		<form action="user!saveURMapping" method="post">
			<input type="hidden" name="user.id" value="<s:property value="user.id"/>"/>
			<table class="tableEdit" border="0" cellspacing="1" cellpadding="0"
				style="width: 580px;">
				<tbody>
					<tr>
						<!-- 这里是添加、编辑界面的标题 -->
						<td align="center" class="tdEditTitle">请选择要分配给用户的角色</td>
					</tr>
					<tr>
						<td>
							<!-- 主输入域开始 -->
							<table width="100%" border="0" cellPadding="0" cellSpacing="1"
								bgcolor="#6386d6">
								<!-- 列表标题栏 -->
								<tr bgcolor="#EFF3F7" class="tableBody1">
									<td width="20%" height="37" align="center"><b>选择</b></td>
									<td width="80%" height="37" align="center"><b>角色名称</b></td>
								</tr>
								
								<!-- 列表数据栏 -->
								<s:if test="%{pager.list!=null}">
									<s:iterator value="pager.list" id="role">
										<tr bgcolor="#EFF3F7" class="tableBody1"
											onmouseover="this.bgColor = '#DEE7FF';"
											onmouseout="this.bgColor='#EFF3F7';">
											<td align="center"><input type="radio" name="role.id" value="<s:property value="#role.id"/>"/></td>
											<td align="center"><s:property value="#role.name"/></td>
										</tr>
									</s:iterator>
								</s:if>
							
								<!-- 在列表数据为空的时候，要显示的提示信息 -->
								<s:if test="%{pager.list==null || pager.list.size() == 0}">
									<tr>
										<td colspan="7" align="center" bgcolor="#EFF3F7"
											class="tableBody1" onmouseover="this.bgColor = '#DEE7FF';"
											onmouseout="this.bgColor='#EFF3F7';">没有找到相应的记录</td>
									</tr>
								</s:if>
							</table>
							
							<table width="100%" border=0 align=center cellPadding=0
								cellSpacing=0 borderColor=#ffffff style="FONT-SIZE: 10pt">
								<tbody>
									<tr>
										<td height=28 align="center" vAlign="middle" noWrap
											background=images/list_middle.jpg>&nbsp;&nbsp; <!-- 可以在这里插入分页导航条 -->
											请输入角色的优先级：<input type="text" name="orderNo">
										</td>
									</tr>
								</tbody>
							</table> <!-- 主输入域结束 -->
						</td>
					</tr>
				</tbody>
			</table>

			<table>
				<tr align="center">
					<td colspan="3" bgcolor="#EFF3F7"><input type="submit" class="MyButton" value="分配角色"> 
					<input type="button" class="MyButton" value="关闭窗口" onclick="window.close()"></td>
				</tr>
			</table>
		</form>
	</center>
</body>
</html>