<%@ page language="java" contentType="text/html; charset=GB18030"
	pageEncoding="utf-8"%>
<%@ include file="/common/common.jsp" %>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<link href="style/oa.css" rel="stylesheet" type="text/css">
<script src="script/public.js"></script>
<title>人员管理</title>
</head>
<body bgColor=#dee7ff leftMargin=0 background="" topMargin=0
	marginheight="0" marginwidth="0">
	<table border=0 cellPadding=0 cellSpacing=0
		borderColor=#ffffff bgColor=#dee7ff style="font-size: 10pt; margin: auto; width: 778; " >
		<tbody>
			<tr height=35>
				<td align=middle width=20 background=images/title_left.gif
					bgColor=#dee7ff></td>
				<td align=middle width=120 background=images/title_left.gif
					bgColor=#dee7ff><FONT color=#f7f7f7>人员管理<font
						color="#FFFFFF">&nbsp;</font></FONT></td>
				<td align=middle width=11 background=images/title_middle.gif
					bgColor=#dee7ff><FONT color=#f7f7f7>&nbsp;</FONT></td>
				<td align=middle background=images/title_right.gif bgColor=#dee7ff><FONT
					color=#f7f7f7>&nbsp;</FONT></td>
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
					<!-- 在这里插入查询表单 -->
				</td>
				<td height=14 align="left" vAlign=center noWrap>
					<%
						/**
						* 在这里定义“添加”，“查询”等按钮
						* <input type="image" name="find" value="find" src="images/cz.gif">
						* &nbsp;&nbsp;&nbsp;&nbsp; 
						* <a href="#" onClick="openWin('document.do?method=addInput','470')">
						* <img src="images/addpic.gif" border=0 align=absMiddle style="CURSOR: hand"></a>
						*/
					%>
					<a href="#" onclick="openWin('person!addInput','addPerson',600,200);">添加人员信息</a>
				</td>
			</tr>
			<tr>
				<td height=28 colspan="2" align=right vAlign=center noWrap
					background=images/list_middle.jpg>&nbsp;&nbsp; <!-- 可以在这里插入分页导航条 -->
				</td>
			</tr>
		</tbody>
	</table>
	<table width="778" border="0" cellPadding="0" cellSpacing="1" bgcolor="#6386d6" style="margin: auto">
		<!-- 列表标题栏 -->
		<tr bgcolor="#EFF3F7" class="tableBody1">
		    <td width="5%" height="37" align="center"><b>序号</b></td>
		    <td width="18%" height="37" align="center"><B>姓名</B></td>
		    <td width="13%" height="37" align="center"><b>性别</b></td>
		    <td width="18%" height="37" align="center"><b>职位</b></td>
            <td width="18%" height="37" align="center"><b>电话</b></td>
            <td width="18%" height="37" align="center"><b>所属机构</b></td>
			<td width="18%" height="37" align="center"><b>相关操作</b></td>
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
					<td align="center"><s:property value="#person.duty"/></td>
					<td align="center"><s:property value="#person.phone"/></td>
					<td align="center"><s:property value="#person.org.id"/></td>
					<td align="center">
						<a href="#" onclick="del('person!delete?person.id=<s:property value="#person.id"/>');">删除</a> | 
						<a href="#" onclick="openWin('person!updateInput?person.id=<s:property value="#person.id"/>','updatePerson',600,200);">更新</a>
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
					<pg:pager url="person" items="${ pager.total }" export="currentPage=pageNumber">
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
