<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/common.jsp" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<link href="style/oa.css" rel="stylesheet" type="text/css">
	<script src="script/public.js"></script>
	<title>已审公文管理</title>
</head>
<body bgcolor="#dee7ff" leftMargin="0" background="" topMargin="0" marginheight="0" marginwidth="0">
	<table width="778" border="0" align="center" cellPadding="0" cellSpacing="0" borderColor="#ffffff" style="font-size: 10pt; margin: auto">
		<tr height=35>
			<td align="center" width="20" background="images/title_left.gif" bgcolor="#dee7ff"></td>
			<td align="center" width="120" background="images/title_left.gif" bgColor="#dee7ff">
				<font color="#f7f7f7">已审公文管理<font color="#FFFFFF">&nbsp;</font></font>
			</td>
			<td align="center" width="11" background="images/title_middle.gif" bgColor="#dee7ff"><font color="#f7f7f7">&nbsp;</font></td>
			<td align="center" background="images/title_right.gif" bgColor="#dee7ff"><font color="#f7f7f7">&nbsp;</font></td>
		</tr>
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
					<a href="document">我的公文</a>&nbsp;
					<a href="document!approvingList">待审公文</a>&nbsp;
					【已审公文】
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
			<td width="5%" height="37" align="center"><b>序号</b></td>
		    <td width="10%" height="37" align="center"><b>公文名称</b></td>
		    <td width="10%" height="37" align="center"><b>公文描述</b></td>
		    <td width="15%" height="37" align="center"><b>创建时间</b></td>
		    <td width="10%" height="37" align="center"><b>公文状态</b></td>
		    <td width="10%" height="37" align="center"><b>流程名称</b></td>
			<td width="40%" height="37" align="center"><b>相关操作</b></td>
		</tr>
		
		<s:if test="%{pager.list!=null}">
			<s:iterator value="pager.list" id="document">
				<tr bgcolor="#EFF3F7" class="tableBody1"
					onmouseover="this.bgColor = '#DEE7FF';"
					onmouseout="this.bgColor='#EFF3F7';">
					<td align="center"><s:property value="#document.id"/></td>
					<td align="center"><s:property value="#document.title"/></td>
					<td align="center"><s:property value="#document.description"/></td>
					<td align="center">
						<s:if test="#document.createTime != null">
						<s:date name="#document.createTime" format="yyyy-MM-dd"/>
						</s:if>
					</td>
					<td align="center"><s:property value="#document.status"/></td>
					<td align="center"><s:property value="#document.workflow.name"/></td>
					<td align="center">
						<s:if test="#document.content != null">
						<a href="#" onclick="openWin('document!download?document.id=<s:property value="#document.id"/>');">下载附件</a>
						</s:if>
						<a href="#" onclick="openWin('document!approveHistory?document.id=<s:property value="#document.id"/>');">查看审批历史</a>
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
					<pg:pager url="document" items="${ pager.total }" export="currentPage=pageNumber">
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