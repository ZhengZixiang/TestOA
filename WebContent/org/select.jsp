<%@ page language="java" contentType="text/html; charset=GB18030"
	pageEncoding="GB18030"%>
<%@ include file="/common/common.jsp"%>

<!DOCTYPE html PUBLIC "-//W3C//Dtd HTML 4.01 transitional//EN" "http://www.w3.org/tr/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=GB18030">
<link href="style/oa.css" rel="stylesheet" type="text/css">
<script language="javascript" src="script/public.js"></script>
<title>请选择机构</title>
<script type="text/javascript">
	function selectOrg(oid, oname) {
		if (window.opener) {
			window.opener.document.all.orgIdId.value = oid;
			window.opener.document.all.orgNameId.value = oname;
			window.close();
		}
	}
</script>
</head>
<body>
	<center>
		<table class="tableEdit" border="0" cellspacing="1" cellpadding="0"
			style="width: 580px;">
			<tbody>
				<tr>
					<!-- 这里是添加、编辑界面的标题 -->
					<td align="center" class="tdEditTitle">请选择机构</td>
				</tr>
				<tr>
					<td>
						<!-- 主输入域开始 -->
						<table width="778" border=0 align=center cellPadding=0
							cellSpacing=0 borderColor=#ffffff style="FONT-SIZE: 10pt">
							<tbody>
								<tr>
									<td width="82%" height=14 align=right vAlign=center noWrap>
									</td>
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
											* <a href="#" onClick="BeginOut('document.do?method=addInput','470')">
											* <img src="images/addpic.gif" border=0 align=absMiddle style="CURSOR: hand"></a>
											*/
										%> <a href="org?parentId=${ppid }&select=true">返回</a>
									</td>
								</tr>
								<tr>
									<td height=28 colspan="2" align=right vAlign=center noWrap
										background=images/list_middle.jpg>&nbsp;&nbsp; <!-- 可以在这里插入分页导航条 -->
									</td>
								</tr>
							</tbody>
						</table>
						
						<table width="100%" border="0" cellPadding="0" cellSpacing="1"
							bgcolor="#6386d6">
							<!-- 列表标题栏 -->
							<tr bgcolor="#EFF3F7" class="tableBody1">
								<td width="20%" height="37" align="center"><b>选择</b></td>
								<td width="20%" height="37" align="center"><B>机构名称</B></td>
								<td width="20%" height="37" align="center"><b>机构编号</b></td>
								<td width="20%" height="37" align="center"><b>父机构名称</b></td>
							</tr>

							<!-- 列表数据栏 -->
							<s:if test="%{pager.list!=null}">
								<s:iterator value="pager.list" id="org">
									<tr bgcolor="#EFF3F7" class="tableBody1"
										onmouseover="this.bgColor = '#DEE7FF';"
										onmouseout="this.bgColor='#EFF3F7';">
										<td align="center"><input type="radio" name="orgId"
											onclick="selectOrg(<s:property value="#org.id"/>, <s:property value="#org.name"/>)" /></td>
										<td align="center"><a href="org?parentId=<s:property value="#org.id"/>&select=true"><s:property value="#org.name" /></a></td>
										<td align="center"><s:property value="#org.sn" /></td>
										<td align="center"><s:property value="#org.parent.name" /></td>
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

						<table width="778" border=0 align=center cellPadding=0
							cellSpacing=0 borderColor=#ffffff style="font-size: 10pt">
							<tbody>
								<tr>
									<td height=28 align=right noWrap
										background=images/list_middle.jpg>&nbsp;&nbsp; <!-- 可以在这里插入分页导航条 -->
										<pg:pager url="org" items="${ pager.total }"
											export="currentPage=pageNumber">
											<pg:param name="parentId" />
											<pg:param name="select" value="true"/>
											<pg:first>
												<a href="<%=pageUrl%>">首页</a>
											</pg:first>
											<pg:prev>
												<a href="<%=pageUrl%>">前一页</a>
											</pg:prev>
											<pg:pages>
												<%
													if (currentPage != pageNumber) {
												%>
												<a href="<%=pageUrl%>"><%=pageNumber%></a>
												<%
													} else {
												%>
												<b> <%=pageNumber%>
												</b>
												<%
													}
												%>
											</pg:pages>
											<pg:next>
												<a href="<%=pageUrl%>">后一页</a>
											</pg:next>
											<pg:last>
												<a href="<%=pageUrl%>">尾页</a>
											</pg:last>
										</pg:pager>
									</td>
								</tr>
							</tbody>
						</table>
					</td>
				</tr>
			</tbody>
		</table>

	</center>
</body>
</html>