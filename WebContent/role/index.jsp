<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ include file="/common/common.jsp" %>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<link href="style/oa.css" rel="stylesheet" type="text/css">
<script src="script/public.js"></script>
<title>角色管理</title>
</head>
<body bgColor=#dee7ff leftMargin=0 background="" topMargin=0
	marginheight="0" marginwidth="0">
	<table border="0" cellPadding="0" cellSpacing="0" borderColor="#ffffff" bgColor="#dee7ff" style="font-size: 10pt; margin: auto; width: 778; " >
		<tbody>
			<tr height=35>
				<td align=middle width=20 background=images/title_left.gif
					bgColor=#dee7ff></td>
				<td align=middle width=120 background=images/title_left.gif
					bgColor=#dee7ff><font color=#f7f7f7>角色管理<font
						color="#FFFFFF">&nbsp;</font></font></td>
				<td align=middle width=11 background=images/title_middle.gif
					bgColor=#dee7ff><font color=#f7f7f7>&nbsp;</font></td>
				<td align=middle background=images/title_right.gif bgColor=#dee7ff><font
					color=#f7f7f7>&nbsp;</font></td>
			</tr>
		</tbody>
	</table>
	<table width="778" border=0 align=center cellPadding=0 cellSpacing=0
		borderColor=#ffffff style="font-size: 10pt; margin: auto">
		<tbody>
			<tr>
				<td width="82%" height=14 align=right vAlign=center noWrap></td>
				<td width="18%" align=right vAlign=center noWrap></td>
			</tr>
			<tr>
				<td height=14 align=right vAlign=center noWrap>
				</td>
				<td height=14 align="left" vAlign=center noWrap>
					<c:if test="${ mytag:hasPermission(login.id, 'role', 0) }">
					<a href="#" onclick="openWin('role!addInput','addrole',600,200);">添加角色信息</a>
					</c:if>
				</td>
			</tr>
			<tr>
				<td height=28 colspan="2" align=right vAlign=center noWrap
					background=images/list_middle.jpg>&nbsp;&nbsp; 
				</td>
			</tr>
		</tbody>
	</table>
	<table width="778" border="0" cellPadding="0" cellSpacing="1" bgcolor="#6386d6" style="margin: auto">

		<tr bgcolor="#EFF3F7" class="tableBody1">
		    <td width="20%" height="37" align="center"><b>序号</b></td>
		    <td width="40%" height="37" align="center"><b>角色名称</b></td>
			<td width="40%" height="37" align="center"><b>相关操作</b></td>
		</tr>
		
		<!-- 列表数据栏 -->
		<s:if test="%{pager.list!=null}">
			<s:iterator value="pager.list" id="role">
				<tr bgcolor="#EFF3F7" class="tableBody1"
					onmouseover="this.bgColor = '#DEE7FF';"
					onmouseout="this.bgColor='#EFF3F7';">
					<td align="center"><s:property value="#role.id"/></td>
					<td align="center"><s:property value="#role.name"/></td>
					<td align="center">
						<c:if test="${ mytag:hasPermission(login.id, 'role', 2) }">
						<a href="#" onclick="openWin('acl?acl.principalType=role&acl.principalId=<s:property value="#role.id"/>');">角色授权</a>
						</c:if>
						<c:if test="${ mytag:hasPermission(login.id, 'role', 3) }">
						 | <a href="#" onclick="del('role!delete?role.id=<s:property value="#role.id"/>');">删除角色</a>
						</c:if>   
					</td>
				</tr>
			</s:iterator>
		</s:if>
		
		<!-- 在列表数据为空的时候，要显示的提示信息 -->
		<s:if test="%{pager.list==null || pager.list.size() == 0}">
			<tr>
				<td colspan="7" align="center" bgcolor="#EFF3F7" class="tableBody1"
					onmouseover="this.bgColor = '#DEE7FF';"
					onmouseout="this.bgColor='#EFF3F7';">没有找到相应的记录</td>
			</tr>
		</s:if>

	</table>
	<table width="778" border=0 align=center cellPadding=0 cellSpacing=0 borderColor=#ffffff style="font-size: 10pt">
		<tbody>
			<tr>
				<td height=28 align=right vAlign=center noWrap
					background=images/list_middle.jpg>&nbsp;&nbsp; 
					<!-- 可以在这里插入分页导航条 -->
					<pg:pager url="role" items="${ pager.total }" export="currentPage=pageNumber">
						<pg:param name="parentId"/>
						<pg:first>
							<a href="<%= pageUrl %>">首页</a>
						</pg:first>
						<pg:prev>
							<a href="<%= pageUrl %>">前一页</a>
						</pg:prev>
						<pg:pages>
							<% if(currentPage != pageNumber) { %>
								<a href="<%= pageUrl %>"><%= pageNumber %></a>
							<% } else { %>
								<b> <%= pageNumber %> </b>
							<% } %>
						</pg:pages>
						<pg:next>
							<a href="<%= pageUrl %>">后一页</a>
						</pg:next>
						<pg:last>
							<a href="<%= pageUrl %>">尾页</a>
						</pg:last>
					</pg:pager>
				</td>
			</tr>
		</tbody>
	</table>
</body>

</html>
