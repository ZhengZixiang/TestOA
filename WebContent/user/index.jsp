<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ include file="/common/common.jsp" %>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<link href="style/oa.css" rel="stylesheet" type="text/css">
<script src="script/public.js"></script>
<title>用户管理</title>
</head>
<body bgColor=#dee7ff leftMargin=0 background="" topMargin="0" marginheight="0" marginwidth="0">
	<table border="0" cellPadding="0" cellSpacing="0" borderColor="#ffffff" bgColor="#dee7ff" style="font-size: 10pt; margin: auto; width: 778; " >
		<tbody>
			<tr height=35>
				<td align="center" width="20" background="images/title_left.gif" bgColor="#dee7ff"></td>
				<td align="center" width="120" background="images/title_left.gif" bgColor="#dee7ff">
					<font color="#f7f7f7">用户管理<font color="#FFFFFF">&nbsp;</font></font></td>
				<td align="center" width="11" background="images/title_middle.gif" bgColor="#dee7ff"><font color="#f7f7f7">&nbsp;</font></td>
				<td align="center" background="images/title_right.gif" bgColor="#dee7ff"><font color="#f7f7f7">&nbsp;</font></td>
			</tr>
		</tbody>
	</table>
	<table width="778" border="0" align="center" cellPadding="0" cellSpacing="0" borderColor=#ffffff style="font-size: 10pt; margin: auto">
		<tbody>
			<tr>
				<td width="82%" height="14" align="right" valign="middle" noWrap></td>
				<td width="18%" align="right" valign="middle" noWrap></td>
			</tr>
			<tr>
				<td height="14" align="right" valign="middle" noWrap>
					<!-- 在这里插入查询表单 -->
				</td>
			</tr>
			<tr>
				<td height="28" colspan="2" align="right" valign="middle" noWrap background="images/list_middle.jpg">&nbsp;&nbsp; <!-- 可以在这里插入分页导航条 -->
				</td>
			</tr>
		</tbody>
	</table>
	<table width="778" border="0" cellPadding="0" cellSpacing="1" bgcolor="#6386d6" style="margin: auto">
		<!-- 列表标题栏 -->
		<tr bgcolor="#EFF3F7" class="tableBody1">
		    <td width="5%" height="37" align="center"><b>序号</b></td>
		    <td width="10%" height="37" align="center"><B>姓名</B></td>
		    <td width="5%" height="37" align="center"><b>性别</b></td>
            <td width="10%" height="37" align="center"><b>所属机构</b></td>
            <td width="10%" height="37" align="center"><b>登录账号</b></td>
            <td width="15%" height="37" align="center"><b>失效时间</b></td>
			<td width="45%" height="37" align="center"><b>相关操作</b></td>
		</tr>
		<!-- 列表数据栏 -->
		<s:if test="%{pager.list!=null}">
			<s:iterator value="pager.list" id="person">
				<tr bgcolor="#EFF3F7" class="tableBody1"
					onmouseover="this.bgColor = '#DEE7FF';"
					onmouseout="this.bgColor='#EFF3F7';">
					<td align="center"><s:property value="#person.id"/></td>
					<td align="center"><s:property value="#person.name"/></td>
					<td align="center"><s:property value="#person.sex"/></td>
					<td align="center"><s:property value="#person.org.name"/></td>
					<td align="center"><s:property value="#person.user.username"/></td>
					<td align="center">
						<s:if test="#person.user.expireTime != null">
							<s:date name="#person.user.expireTime" format="yyyy-MM-dd"/>
						</s:if>
					</td>
					<td align="center">
						<c:if test="${ mytag:hasPermission(login.id, 'user', 0) }">
						<a href="#" onclick="openWin('user!addInput?person.id=<s:property value="#person.id"/>');">分配账号</a>&nbsp;
						</c:if>
						<c:if test="${ mytag:hasPermission(login.id, 'user', 3) }">
						<a href="#" onclick="del('user!delete?user.id=<s:property value="#person.user.id"/>');">删除账号</a>&nbsp;
						</c:if> 
						<c:if test="${ mytag:hasPermission(login.id, 'user', 2) }">
						<a href="#" onclick="openWin('user!updateInput?user.id=<s:property value="#person.user.id"/>&person.id=<s:property value="#person.id"/>');">修改账号</a>&nbsp;
						</c:if>
						<a href="#" onclick="openWin('user!listURMapping?user.id=<s:property value="#person.user.id"/>','listURMapiing',600,200);">分配角色</a>&nbsp;
						<c:if test="${ mytag:hasPermission(login.id, 'user', 2) }">
						<a href="#" onclick="openWin('acl?acl.principalType=user&acl.principalId=<s:property value="#person.user.id"/>')">用户授权</a>
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
	<table width="778" border="0" align="center" cellPadding="0" cellSpacing="0" borderColor="#ffffff" style="font-size: 10pt">
		<tbody>
			<tr>
				<td height="28" align="right" vAlign="center" noWrap background="images/list_middle.jpg">&nbsp;&nbsp; 
					<!-- 可以在这里插入分页导航条 -->
					<pg:pager url="user" items="${ pager.total }" export="currentPage=pageNumber">
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
