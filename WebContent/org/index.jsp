<%@ page language="java" contentType="text/html; charset=GB18030"
	pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<link href="style/oa.css" rel="stylesheet" type="text/css">
<script src="script/public.js"></script>
<title>机构管理</title>
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
					bgColor=#dee7ff><FONT color=#f7f7f7>机构管理<font
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
					<a href="#" onclick="openWin('org!addInput?parentId=<s:property value="parentId"/>','addOrg',600,200);">添加机构信息</a>
					<s:if test="parentId!=0">
						 | <a href="org?parentId=<s:property value="ppid"/>">返回</a>
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
			<td width="18%" height="37" align="center"><b>机构名称</b></td>
			<td width="18%" height="37" align="center"><b>机构编号</b></td>
			<td width="18%" height="37" align="center"><b>父机构名称</b></td>
			<td width="18%" height="37" align="center"><b>相关操作</b></td>
		</tr>
		<!-- 列表数据栏 -->
		<s:if test="%{orgs!=null}">
			<s:iterator value="orgs" id="org">
				<tr bgcolor="#EFF3F7" class="tableBody1"
					onmouseover="this.bgColor = '#DEE7FF';"
					onmouseout="this.bgColor='#EFF3F7';">
					<td align="center"><s:property value="#org.id"/></td>
					<td align="center"><a href="org?parentId=<s:property value="#org.id"/>"><s:property value="#org.name"/></a></td>
					<td align="center"><s:property value="#org.sn"/></td>
					<td align="center"><s:property value="#org.parent.name"/></td>
					<td align="center">
						<a href="#" onclick="del('org!delete?id=<s:property value="#org.id"/>');">删除</a> | 
						<a href="#" onclick="openWin('org!updateInput?id=<s:property value="#org.id"/>','updateOrg',600,200);">更新</a>
					</td>
				</tr>
			</s:iterator>
		</s:if>
		<!-- 在列表数据为空的时候，要显示的提示信息 -->
		<s:if test="%{orgs==null || orgs.size() == 0}">
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
					background=images/list_middle.jpg>&nbsp;&nbsp; 可以在这里插入分页导航条
				</td>
			</tr>
		</tbody>
	</table>
</body>

</html>
