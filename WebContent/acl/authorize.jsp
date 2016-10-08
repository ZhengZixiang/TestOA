<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ include file="/common/common.jsp"%>

<!DOCTYPE html PUBLIC "-//W3C//Dtd HTML 4.01 transitional//EN" "http://www.w3.org/tr/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
	<link href="style/oa.css" rel="stylesheet" type="text/css">
	<script language="javascript" src="script/public.js"></script>
	<title>
	<s:if test="acl.principalType=='role'">
		请给角色【<s:property value="#role.name"/>】授权
	</s:if>
	<s:else>
		请给用户【<s:property value="#user.person.name"/>】授权
	</s:else>
	</title>
	<script src="dwr/engine.js"></script>
	<script src="dwr/util.js"></script>
	<script src="dwr/interface/aclManager.js"></script>
	<script>
	//授权
	function addOrUpdatePermission(v) {
		
		if(v.checked) {
			$(v.getAttribute("resourceId")+"_Q").checked = true;
			<s:if test="acl.principalType=='user'">
			$(v.getAttribute("resourceId")+"_E").checked = false;
			addOrUpdateExtends($(v.getAttribute("resourceId")+"_E"));
			</s:if>
		}
		
		aclManager.addOrUpdatePermission(
				"<s:property value="acl.principalType"/>", 
				<s:property value="acl.principalId"/>, 
				v.getAttribute("resourceId"),
				v.getAttribute("permission"),
				v.checked
				);
	}
	
	//继承
	function addOrUpdateExtends(v) {
		aclManager.addOrUpdateExtends(
 			<s:property value="acl.principalId"/>, 
			v.getAttribute("resourceId"),
			v.checked
			);
	}
	
	//启用
	function enable(v) {
		
		//同步
		dwr.engine.setAsync(false);
		
		if(v.checked) {
			addOrUpdatePermission($(v.getAttribute("resourceId")+"_C"));
			addOrUpdatePermission($(v.getAttribute("resourceId")+"_R"));
			addOrUpdatePermission($(v.getAttribute("resourceId")+"_U"));
			addOrUpdatePermission($(v.getAttribute("resourceId")+"_D"));
			<s:if test="acl.principalType=='user'">
			addOrUpdateExtends($(v.getAttribute("resourceId")+"_E"));
			</s:if>	
		} else {
			aclManager.deletePermission("<s:property value="acl.principalType"/>", <s:property value="acl.principalId"/>, v.getAttribute("resourceId"));
			$(v.getAttribute("resourceId")+"_C").checked = false;
			$(v.getAttribute("resourceId")+"_R").checked = false;
			$(v.getAttribute("resourceId")+"_U").checked = false;
			$(v.getAttribute("resourceId")+"_D").checked = false;
		}
	}
	
	//获得授权记录
	function init() {
		aclManager.searchAclRecord(
			"<s:property value="acl.principalType"/>",
			<s:property value="acl.principalId"/>,
			function(datas) {
 				for(var data in datas) {
 					
					var resourceId = datas[data].resourceId;
					var cState = datas[data].cState;
					var rState = datas[data].rState;
					var uState = datas[data].uState;
					var dState = datas[data].dState;
					var eState = datas[data].aclTriState;
					
					$(resourceId+"_C").checked = (cState == 0 ? false : true);
					$(resourceId+"_R").checked = (rState == 0 ? false : true);
					$(resourceId+"_U").checked = (uState == 0 ? false : true);
					$(resourceId+"_D").checked = (dState == 0 ? false : true);
					<s:if test="acl.principalType=='user'">
					$(resourceId+"_E").checked = (eState == 0 ? false : true);
					</s:if>	
					$(resourceId+"_Q").checked = true;
				}
			}
		);
	}
	</script>
</head>
<body onload="init()">
	<center>
		<table class="tableEdit" border="0" cellspacing="1" cellpadding="0" style="width: 580px;">
			<tbody>
				<tr>
					<!-- 这里是添加、编辑界面的标题 -->
					<td align="center" class="tdEditTitle">
						<s:if test="acl.principalType=='role'">
							请给角色【<s:property value="#role.name"/>】授权
						</s:if>
						<s:else>
							请给用户【<s:property value="#user.person.name"/>】授权
						</s:else>
					</td>
				</tr>
				<tr>
					<td>
						<table style="width: 580px;" cellspacing="0" border="1" cellpadding="0">
							<tr>
								<th align="center">顶级模块</th>
								<th align="center">二级模块</th>
								<th align="center">权限</th>
								<s:if test="acl.principalType=='user'">
									<th align="center">是否继承</th>
								</s:if>
								<th align="center">启用</th>
							</tr>
							
							
							<!-- 输出模块树 -->
							<s:iterator value="pager.list" id="module">
								<tr>
									<td align="center"><s:property value="#module.name"/></td>
									<td></td>
									<td align="center">
										<input type="checkbox" id="<s:property value="#module.id"/>_C" onclick="addOrUpdatePermission(this)" resourceId="<s:property value="#module.id"/>" permission="0"/>C &nbsp;
										<input type="checkbox" id="<s:property value="#module.id"/>_R" onclick="addOrUpdatePermission(this)" resourceId="<s:property value="#module.id"/>" permission="1"/>R &nbsp;
										<input type="checkbox" id="<s:property value="#module.id"/>_U" onclick="addOrUpdatePermission(this)" resourceId="<s:property value="#module.id"/>" permission="2"/>U &nbsp;
										<input type="checkbox" id="<s:property value="#module.id"/>_D" onclick="addOrUpdatePermission(this)" resourceId="<s:property value="#module.id"/>" permission="3"/>D
									</td>
									<s:if test="acl.principalType=='user'">
										<td align="center">
											<input type="checkbox" id="<s:property value="#module.id"/>_E" onclick="addOrUpdateExtends(this)" resourceId="<s:property value="#module.id"/>"/>
										</td>
									</s:if>
									<td align="center">
										<input type="checkbox" id="<s:property value="#module.id"/>_Q" onclick="enable(this)" resourceId="<s:property value="#module.id"/>"/>
									</td>
								</tr>
								<s:iterator value="#module.children" id="child">
								<tr>
									<td align="center"></td>
									<td align="center"><s:property value="#child.name"/></td>
									<td align="center">
										<input type="checkbox" id="<s:property value="#child.id"/>_C" onclick="addOrUpdatePermission(this)" resourceId="<s:property value="#child.id"/>" permission="0"/>C &nbsp;
										<input type="checkbox" id="<s:property value="#child.id"/>_R" onclick="addOrUpdatePermission(this)" resourceId="<s:property value="#child.id"/>" permission="1"/>R &nbsp;
										<input type="checkbox" id="<s:property value="#child.id"/>_U" onclick="addOrUpdatePermission(this)" resourceId="<s:property value="#child.id"/>" permission="2"/>U &nbsp;
										<input type="checkbox" id="<s:property value="#child.id"/>_D" onclick="addOrUpdatePermission(this)" resourceId="<s:property value="#child.id"/>" permission="3"/>D
									</td>
									<s:if test="acl.principalType=='user'">
										<td align="center">
											<input type="checkbox" id="<s:property value="#child.id"/>_E" onclick="addOrUpdateExtends(this)" resourceId="<s:property value="#child.id"/>"/>
										</td>
									</s:if>
									<td align="center">
										<input type="checkbox" id="<s:property value="#child.id"/>_Q" onclick="enable(this)" resourceId="<s:property value="#child.id"/>"/>
									</td>
								</tr>
								</s:iterator>

							</s:iterator>
						</table>
					</td>
				</tr>
			</tbody>
		</table>
	</center>
</body>
</html>