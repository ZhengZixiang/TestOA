<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/common.jsp" %>

<!DOCTYPE html PUBLIC "-//W3C//Dtd HTML 4.01 transitional//EN" "http://www.w3.org/tr/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<link href="style/oa.css" rel="stylesheet" type="text/css">
	<script language="javascript" src="script/public.js"></script>
	<title>流程表单定义</title>
</head>
<body bgcolor="#dee7ff" leftmargin="0" background="" topMargin="0" marginheight="0" marginwidth="0">
	<center>
		<table width="778" border="0" cellpadding="0" cellspacing="0" bordercolor="#ffffff" bgcolor="#dee7ff" style="font-size: 10pt">
			<tbody>
          		<tr height="35">
            		<td align="center" width="20" background="images/title_left.gif"  bgcolor="#dee7ff"></td>
            		<td align="center" width="120" background="images/title_left.gif"  bgcolor="#dee7ff"><font color="#f7f7f7">流程表单定义<font color="#FFFFFF">&nbsp;</font></font></td>
            		<td align="center" width="11" background="images/title_middle.gif" bgcolor="#dee7ff"><font color="#f7f7f7">&nbsp;</font> </td>
            		<td align="center" background="images/title_right.gif" bgcolor="#dee7ff"><font color="#f7f7f7">&nbsp;</font> </td>
          		</tr>
        	</tbody>
      	</table>
      	<table width="778" border="0" align="center" cellpadding="0" cellspacing="0" bordercolor="#ffffff" style="font-size: 10pt">
        	<tbody>
          		<tr>
            		<td width="82%" height="14" align="right" valign="middle" noWrap></td>
            		<td width="18%" align="right" valign="middle" noWrap></td>
          		</tr>
          		<tr>
            		<td height="14" align="right" valign="middle" noWrap></td>
            		<td height="14" align="left" valign="middle" noWrap></td>
          		</tr>
          		<tr>
            		<td height="28" colspan="2" align="right" vAlign="middle" noWrap background="images/list_middle.jpg">&nbsp;&nbsp;</td>
          		</tr>
        	</tbody>
      	</table>
      	
      	<table width="778" border="0" cellPadding="0" cellSpacing="1" bgcolor="#6386d6">
			<tr bgcolor="#EFF3F7" class="tablebody1">
				<td width="50%" height="37" align="center"><b>流程名称</b></td>
              	<td width="50%" height="37" align="center"><b>操作</b></td>
          	</tr>
          	
          	<s:if test="%{#workflows != null}">
          		<s:iterator value="%{#workflows}" id="workflow">
		      	<tr bgcolor="#EFF3F7" class="tablebody1" onmouseover="this.bgColor = '#DEE7FF';" onmouseout="this.bgColor='#EFF3F7';">
			      	<td align="center" valign="middle">
			      		<a href="workflow!viewImage?workflow.id=<s:property value="#workflow.id"/>" target="_blank"><s:property value="#workflow.name"/></a>
			      	</td>
		          	<td align="center" valign="middle">
			          	<a href="#" onclick="openWin('form!addFormInput?workflow.id=<s:property value="#workflow.id"/>','addform',600,500)">定义表单</a>
		          	</td>
	        	</tr>
        		</s:iterator>
			</s:if>
			
	    	<s:if test="%{#workflows == null || #workflows.size() == 0}">
	    	<tr>
	    		<td colspan="7" align="center" bgcolor="#EFF3F7" class="tablebody1" onmouseover="this.bgColor = '#DEE7FF';" onmouseout="this.bgColor='#EFF3F7';">
	    	没有找到相应的记录
	    		</td>
	    	</tr>
	    	</s:if>
      	</table>
      	
      	<table width="778" border="0" align="center" cellpadding="0" cellspacing="0" bordercolor="#ffffff" style="font-SIZE: 10pt">
        	<tbody>
          		<tr>
            		<td height="28" align="right" valign="middle" noWrap background="images/list_middle.jpg">&nbsp;&nbsp;</td>
          		</tr>
        	</tbody>
      	</table>
	</center>
</body>
</html>