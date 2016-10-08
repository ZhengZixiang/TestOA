<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ include file="/common/common.jsp" %>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<link href="style/oa.css" rel="stylesheet" type="text/css">
<script src="script/public.js"></script>
<title>模块管理</title>
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
					bgColor=#dee7ff><font color=#f7f7f7>模块管理<font
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
					<c:if test="${ mytag:hasPermission(login.id, 'module', 0) }">
					<a href="#" onclick="openWin('module!addInput?parentId=<s:property value="parentId"/>','addModule',600,200);">添加模块信息</a>
					</c:if> 
					<s:if test="parentId!=0">
						 | <a href="module?parentId=<s:property value="ppid"/>">返回</a>
					</s:if>
				</td>
			</tr>
			<tr>
				<td height=28 colspan="2" align=right vAlign=center noWrap
					background=images/list_middle.jpg>&nbsp;&nbsp; <!-- 可以在这里插入分页导航条 -->
				</td>
			</tr>
		</tbody>
	</table>
	
	<table width="778" border="0" cellPadding="0" cellSpacing="1"
		bgcolor="#6386d6" style="margin: auto">
		<!-- 列表标题栏 -->
		<tr bgcolor="#EFF3F7" class="tableBody1">
			<td width="5%" height="37" align="center"><b>序号</b></td>
			<td width="15%" height="37" align="center"><b>模块名称</b></td>
			<td width="15%" height="37" align="center"><b>模块编号</b></td>
			<td width="15%" height="37" align="center"><b>父模块名称</b></td>
			<td width="20%" height="37" align="center"><b>模块地址</b></td>
			<td width="15%" height="37" align="center"><b>排序号</b></td>
			<td width="15%" height="37" align="center"><b>相关操作</b></td>
		</tr>
		<!-- 列表数据栏 -->
		
		<s:if test="%{pager.list!=null}">
			<s:iterator value="pager.list" id="module">
				<tr bgcolor="#EFF3F7" class="tableBody1"
					onmouseover="this.bgColor = '#DEE7FF';"
					onmouseout="this.bgColor='#EFF3F7';">
					<td align="center"><s:property value="#module.id"/></td>
					<td align="center"><a href="module?parentId=<s:property value="#module.id"/>"><s:property value="#module.name"/></a></td>
					<td align="center"><s:property value="#module.sn"/></td>
					<td align="center"><s:property value="#module.parent.name"/></td>
					<td align="center"><s:property value="#module.url"/></td>
					<td align="center"><s:property value="#module.orderNo"/></td>
					<td align="center">
						<c:if test="${ mytag:hasPermission(login.id, 'module', 2) }">
						<a href="#" onclick="openWin('module!updateInput?module.id=<s:property value="#module.id"/>','updateModule',600,200);">更新</a>
						</c:if>
						<c:if test="${ mytag:hasPermission(login.id, 'module', 3) }">
						 | <a href="#" onclick="del('module!delete?module.id=<s:property value="#module.id"/>');">删除</a>
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
	
	<table width="778" border=0 align=center cellPadding=0 cellSpacing=0
		borderColor=#ffffff style="font-size: 10pt">
		<tbody>
			<tr>
				<td height=28 align=right vAlign=center noWrap
					background=images/list_middle.jpg>&nbsp;&nbsp; 
					<!-- 可以在这里插入分页导航条 -->
					<pg:pager url="module" items="${ pager.total }" export="currentPage=pageNumber">
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
