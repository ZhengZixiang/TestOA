<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/common/common.jsp" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>分页处理示例</title>
</head>
<body>
<pg:pager items="1001" export="currentPage=pageNumber">
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
</body>
</html>