<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/common.jsp" %>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html;charset=utf-8">
<title>Outlook Like Bar</title>
<style>
  div    {
         position:absolute;
         }
</style>

<script src="script/crossbrowser.js" type="text/javascript"></script>
<script src="script/outlook.js" type="text/javascript"></script>

<script type="text/javascript">

  //create OutlookBar
  var o = new createOutlookBar('Bar',0,0,screenSize.width,screenSize.height,'#606060','white')//'#000099') // OutlookBar
  
  <s:iterator value="modules" id="module">
  	<s:if test="#module.parent == null">
  		var p<s:property value="#module.id"/> = new createPanel('id_<s:property value="#module.id"/>', '<s:property value="#module.name"/>');
  		o.addPanel(p<s:property value="#module.id"/>);
  	</s:if>
  </s:iterator>

  <s:iterator value="modules" id="module">
	<s:if test="#module.parent != null">
		p<s:property value="#module.parent.id"/>.addButton('images/peditor.gif', '<s:property value="#module.name"/>', 'parent.main.location="<s:property value="#module.url"/>"');
	</s:if>
  </s:iterator>

  o.draw();         //draw the OutlookBar

//-----------------------------------------------------------------------------
//functions to manage window resize
//-----------------------------------------------------------------------------
//resize OP5 (test screenSize every 100ms)
function resize_op5() {
  if (bt.op5) {
    o.showPanel(o.aktPanel);
    var s = new createPageSize();
    if ((screenSize.width!=s.width) || (screenSize.height!=s.height)) {
      screenSize=new createPageSize();
      //need setTimeout or resize on window-maximize will not work correct!
      //ben�tige das setTimeout oder das Maximieren funktioniert nicht richtig
      setTimeout("o.resize(0,0,screenSize.width,screenSize.height)",100);
    }
    setTimeout("resize_op5()",100);
  }
}

//resize IE & NS (onResize event!)
function myOnResize() {
  if (bt.ie4 || bt.ie5 || 	bt.ns5) {
    var s=new createPageSize();
    o.resize(0,0,s.width,s.height);
  }
  else
    if (bt.ns4) location.reload();
}

</script>

</head>
<body onLoad="resize_op5();" onResize="myOnResize();">
</body>
</html>


