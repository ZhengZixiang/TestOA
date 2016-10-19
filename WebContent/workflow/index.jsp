<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ include file="/common/common.jsp" %>

<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
	<link href="style/oa.css" rel="stylesheet" type="text/css">
	<script src="script/public.js"></script>
	<title>流程管理</title>
</head>
<body bgcolor="#dee7ff" leftMargin=0 background="" topMargin="0" marginheight="0" marginwidth="0">
	<table border="0" cellpadding="0" cellspacing="0" bordercolor="#ffffff" bgcolor="#dee7ff" style="font-size: 10pt; margin: auto; width: 778; " >
		<tr height=35>
			<td align="center" width="20" background="images/title_left.gif" bgcolor="#dee7ff"></td>
			<td align="center" width="120" background="images/title_left.gif" bgColor="#dee7ff">
				<font color="#f7f7f7">流程管理<font color="#FFFFFF">&nbsp;</font></font>
			</td>
			<td align="center" width="11" background="images/title_middle.gif" bgcolor="#dee7ff">
				<font color="#f7f7f7">&nbsp;</font>
			</td>
			<td align="center" background="images/title_right.gif" bgcolor="#dee7ff"><font color="#f7f7f7">&nbsp;</font></td>
		</tr>
	</table>
	
	<table width="778" border="0" align="center" cellpadding="0" cellSpacing="0" bordercolor="#ffffff" style="font-size: 10pt; margin: auto">
		<tbody>
			<tr>
				<td width="82%" height="14" align="right" valign="middle" noWrap></td>
				<td width="18%" align="right" valign="middle" noWrap></td>
			</tr>
			<tr>
				<td height="14" align=right valign="middle" noWrap>
					<!-- 在这里插入查询表单 -->
				</td>
				<td height="14" align="left" valign="middle" noWrap>
				</td>
			</tr>
			<tr>
				<td height="28" colspan="2" align=right valign="middle" noWrap background="images/list_middle.jpg">&nbsp;&nbsp; <!-- 可以在这里插入分页导航条 -->
				</td>
			</tr>
		</tbody>
	</table>
	<table width="778" border="0" cellPadding="0" cellSpacing="1" bgcolor="#6386d6" style="margin: auto">
		<!-- 列表标题栏 -->
		<tr bgcolor="#EFF3F7" class="tableBody1">
		    <td width="10%" height="37" align="center"><b>序号</b></td>
		    <td width="40%" height="37" align="center"><b>流程名称</b></td>
			<td width="50%" height="37" align="center"><b>相关操作</b></td>
		</tr>
		<!-- 列表数据栏 -->
		<s:if test="%{#workflows != null}">
			<s:iterator value="%{#workflows}" id="workflow">
				<tr bgcolor="#EFF3F7" class="tableBody1"
					onmouseover="this.bgColor = '#DEE7FF';"
					onmouseout="this.bgColor='#EFF3F7';">
					<td align="center"><s:property value="#workflow.id"/></td>
					<td align="center">
						<a href="#" onclick="openWin('workflow!viewImage?workflow.id=<s:property value="#workflow.id"/>')"><s:property value="#workflow.name"/></a>
					</td>
					<td align="center">
						<c:if test="${ mytag:hasPermission(login.id, 'workflow', 3) }">
						<a href="#" onclick="del('user!delete?user.id=<s:property value="#workflow.id"/>');">删除流程</a>&nbsp;
						</c:if>
						<a href="#" onclick="openWin('form!addFormInput?workflow.id=<s:property value="#workflow.id"/>')">定义表单</a>
					</td>
				</tr>
			</s:iterator>
		</s:if>
		<!-- 在列表数据为空的时候，要显示的提示信息 -->
		<s:if test="%{#workflows==null || #workflows.size() == 0}">
			<tr>
				<td colspan="7" align="center" bgcolor="#EFF3F7" class="tableBody1"
					onmouseover="this.bgColor = '#DEE7FF';"
					onmouseout="this.bgColor='#EFF3F7';">没有找到相应的记录</td>
			</tr>
		</s:if>
	</table>
	
	<br/>
	<c:if test="${ mytag:hasPermission(login.id, 'workflow', 0) }">
	<form action="workflow!add" method="post"  enctype="multipart/form-data">
		<table border="1" align="center">
			<tr>
				<th align="center" colspan="2">添加流程信息</th>
			</tr>
			<tr>
				<td align="center">请选择要上传的流程定义文件：</td>
				<td align="center"><input type="file" name="processFile"></td>
			</tr>
			<tr>
				<td align="center">请选择要上传的流程定义图片：</td>
				<td align="center"><input type="file" name="processImage"></td>
			</tr>
			<tr><td colspan="2" align="center"><input type="submit" class="MyButton" value="上传流程"/></td></tr>
		</table>
	</form>
	</c:if>  
</body>

</html>
