<%@page language="java" import="java.util.*" pageEncoding="utf-8"%>
<% 
// 将 expire 时间置为很久以前的时间
response.setHeader("Expires", "Sat, 6 May 1995 12:00:00 GMT");

// 设置标准的 HTTP/1.1 no-cache 首部
response.setHeader("Cache-Control", "no-store, no-cache, must-revalidate");

// 设置 IE 扩展 HTTP/1.1 no-cache 首部（利用 addHeader）
response.addHeader("Cache-Control", "post-check=0, pre-check=0");

// 设置标准的 HTTP/1.0 no-cache 首部
response.setHeader("Pragma", "no-cache");
if (request.getSession(false) != null) {
	session.setAttribute("user","");
   session.invalidate();
} 
%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<base href="<%=basePath%>">
<title>健康教育后台管理系统</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<link rel="stylesheet" type="text/css" href="themes/default/easyui.css" />
<link rel="stylesheet" type="text/css" href="themes/icon.css">
<script type="text/javascript" src="js/jquery-1.4.2.min.js"></script>
<script type="text/javascript" src="js/jquery.easyui.min.js"></script>
<script type="text/javascript" src="http://j.maxmind.com/app/geoip.js"></script>
<script type="text/javascript">
  var contextPath="<%= request.getContextPath()%>";
  $(function(){
	  
	  
  });
  function logintest(){
	
	    var username =  $('#username').val();
		var pass =  $('#pass').val();
		if(username == "" || pass == ""){
			alert('账号和密码不为空');
			
			return;
		}
		$("<div id=\"dialog_div\" class=\"datagrid-mask-msg\"></div>").html("正在处理，请稍候...").appendTo("body").css({display:"block",left:($(document.body).outerWidth(true) - 190) / 2,top:($(window).height() - 45) / 2}); 


	    $.ajax({
	            type: "post",
	            url : contextPath+'/user!loginTest.action',
	            dataType:'json',
	            data:{account:username,password:pass},
	            timeout:10000,
	            success: function(msg,status){
	            	
	              $('#dialog_div').remove();
	          
	         	  if(msg["status"] == 200)
	         		  location.href="index.jsp";
	         	  else
	         		  alert(msg.msg);
	            }
	            
	      
	
	       });
  }
  
  function reset(){
	  $('#username').val('');
	  $('#pass').val('');
  }
  </script>

<style type="text/css">
<!--
body {
	margin-left: 0px;
	margin-top: 0px;
	margin-right: 0px;
	margin-bottom: 0px;
	overflow: auto;
	background: url(images/bottom.png) repeat;
}
.username {
	position: relative;
	margin: 0 0 0 582px;
}

.pass {
	position: relative;
	margin: 12px 0 0 582px;
}
.remember{
	position: relative;
	margin: 8px 0 0 560px;
}
.inputclass {
	color:#fff;
	border: 0px;
	padding: 0px 0 0 4px;
	height: 30px;
	line-height: 30px;
	width: 180px;
	background: url(images/input_box4.png) no-repeat;
	vertical-align: middle;
}
.main {
	margin: 0px auto;
	width: 1300px;
	height: 600px;
	padding-top: 280px;
	background: url(images/login_back.jpg) no-repeat;
}
.login {
	position: relative;
	margin: 50px 0 0 560px;
}
-->
</style>
</head>

<body>
	<div class="main">

		<div class="username">
			<input class="inputclass" type="text" id="username" value="admin"/>
		</div>
		<div class="pass">
			<input class="inputclass" type="password" id="pass" value="admin" />
		</div>
		<div class="login">
			<a href="javascript:void(0)"><img src="images/login.png"
				width="83" height="53" onclick="logintest()" border="0"></a> 
				<a	href="javascript:void(0)"><img src="images/reset.png"
				width="83" height="53" onclick="reset()" border="0"></a>
		</div>


	</div>


</body>
</html>