<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>健康教育后台管理系统</title>
<link rel="stylesheet" type="text/css" href="${ctx }/themes/gray/easyui.css" />
<link rel="stylesheet" type="text/css" href="${ctx }/themes/icon.css" />
<script type="text/javascript" src="${ctx }/js/jquery-1.4.2.min.js"></script>
<script type="text/javascript" src="${ctx }/js/jquery.easyui.min.js"></script>
<script type="text/javascript">
	var menueList=[{title:"用户信息",url:"userinfo",child:[]},
	               {title:"首页",url:"firstpage",child:[]},
	               {title:"防治知识",url:"knowledge",child:[]},
	               {title:"在线测试",url:"onlinetest",child:[{title:"测试首页",url:"onlinefirst",child:[]},{title:"在线测试题",url:"onlinetest",child:[]}]},
	               {title:"引导页",url:"bootpage",child:[]},
	               {title:"关于我们",url:"aboutus",child:[]},
	               {title:"技术支持",url:"supports",child:[]},
	               {title:"版本更新",url:"version",child:[]},
	               {title:"反馈意见",url:"message",child:[]}
	
	];
	  $(function(){
			
          
           $(document.body).css({
            "overflow-y":"auto",
            "overflow-x":"hidden",
            "margin": "0px"
          });
          
           $('#tabs').css({
               "height":"500px"
             });
           initMenue();
           $('#tabs').tabs();
           tabCloseEvent();
                   
});
	  function initMenue(){
		  var htmlArray=[];
		  $.each(menueList,function(i,value){
			  var click='';
			  if(value["child"].length == 0){
				  click = ' onclick=addTab(\''+value["title"]+'\',\''+value["url"]+'\') ';
			  }
			  htmlArray.push('<li class="nav-1"><div '+click+'>'+value["title"]+'</div>');
			  if(value["child"].length > 0){
				  htmlArray.push('<ul class="nav-2">');
				  $.each(value["child"],function(j,obj){
					  var click = ' onclick=addTab(\''+obj["title"]+'\',\''+obj["url"]+'\') ';
					  htmlArray.push('<li><a href="javascript:void(0)"'+click+'>'+obj["title"]+'</a></li>');  
				  });
				  htmlArray.push('</ul>');
			  }
			 htmlArray.push('<li class="nav-1-menue-sep"><img src="${ctx}/images/menue_sep.png" width="5" height="33"/></li>'); 
			  
		  });
		  $('#nav').append(htmlArray.join(''));
		  
	  }
	 function addTab(title,url){
		  if($('#tabs').tabs('exists',title)){
			  $('#tabs').tabs('select',title);
		  }
		  else{
			  var content = createFrame("${ctx}/pages/"+url+".jsp");
			  $('#tabs').tabs('add',{
				  title:title,
				  content:content,
				  closable:true
			  });
			  
			  //未选项卡绑定右键
			  $(".tabs-inner").bind('contextmenu',function(e){
				  $('#mm').menu('show',{
					 left:e.pageX,
					 top:e.pageY
				  });
				  var subtitle = $(this).children(".tabs-closable").text();
				  $('#mm').data("currtab",subtitle);
				  $('#tabs').tabs("select",subtitle);
				  return false;
			  });
		  }
		  
	 }
	 function detailPage(title,url){
		  if($('#tabs').tabs('exists',title)){
			  $('#tabs').tabs('close',title);
		  }
		  
			  var content = createFrame(url);
			  $('#tabs').tabs('add',{
				  title:title,
				  content:content,
				  closable:true
			  });
			  
			  //未选项卡绑定右键
			  $(".tabs-inner").bind('contextmenu',function(e){
				  $('#mm').menu('show',{
					 left:e.pageX,
					 top:e.pageY
				  });
				  var subtitle = $(this).children(".tabs-closable").text();
				  $('#mm').data("currtab",subtitle);
				  $('#tabs').tabs("select",subtitle);
				  return false;
			  });
		  
		  
	 }
	 function createFrame(url){
		 return  "<iframe width='100%' height='100%' frameborder='0' src='"+ url +"' ></iframe>";
	 }
	 //绑定右键事件
	function tabCloseEvent(){
		//刷新
		$('#mm-tabupdate').click(function(){
			  var currtab = $('#tabs').tabs('getSelected');
			  var url = $(currtab.panel('options').content).attr('src');
			  if(url!=undefined){
				  $('#tabs').tabs('update',{
					  tab:currtab,
					  options:{
						  content:createFrame(url)
					  }
				  });
			  }
			  
		});
		//关闭当前
		$('#mm-tabclose').click(function(){
			var currtitle = $('#mm').data("currtab");
			 $('#tabs').tabs('close',currtitle);
		});
		//关闭全部
		$('#mm-tabcloseall').click(function(){
			$('.tabs-inner span').each(function(i,n){
				 $('#tabs').tabs('close',$(n).text());
			});
		});
		
		//关闭其它
		$('#mm-tabcloseother').click(function(){
			var preall = $('.tabs-selected').prevAll();
			var nextall = $('.tabs-selected').nextAll();
			if(preall.length > 0){
				preall.each(function(i,n){
					var t = $('a:eq(0) span',$(n)).text();
					 $('#tabs').tabs('close',t);
				});
			}
			if(nextall.length > 0){
				nextall.each(function(i,n){
					var t = $('a:eq(0) span',$(n)).text();
					 $('#tabs').tabs('close',t);
				});
			}
			return false;
		});
		
	}
	function logout(){
		if(confirm('您确定退出吗?')){
			top.location="login.jsp";
		}
		
	}
		
	</script>

<style type="text/css">
body {
	margin: 0px;
	font: 12px/20px "微软雅黑", "宋体", Aria;
}

.main {
	width: 100%;
	height: 100%;
	margin: 0px auto 0px auto;
	overflow: hidden;
}

.header {
	height: 40px;
	background:#eee;
}

.footer {
	height: 38px;
	background:#eee;
	vertical-align: middle;
	padding-top:10px;
}

.mainContent {
	clear:both;
	margin-top:0px;
	width:100%;
}

#popimg{
margin:0 auto;
float:left;
margin-left:60px;
}
.menue{
 height:30px;
 background:url(${ctx}/images/menue_mainbg.png) repeat-x;
 
}
.menue ul { 
	margin-left:60px;
	padding: 0;
	list-style: none;
	
}

.nav-1{
  position:relative;
  float:left;
  width:80px;
  height:30px;
  margin-top:2px;
}
.nav-1-menue-sep{
 position:relative;
 float:left;
 width:5px;
 height:33px;
 margin-top:0px;
}
.nav-1  div{
	width:80px;
	height:24px;
	padding-top:2px;
	margin-top:0px;
	text-align: center;
	cursor:pointer;
}
.nav-1 :hover{
margin-top:1px;
background-color:#2e64fe;
color:#fff;
}
.menue li ul { 
	position:absolute;
	z-index:9999999;
	display: none;
	margin-top:0px;
	margin-left:0px;
	list-style:none;
	background-color:#2e64ee;
	
}
#nav .nav-1 li :hover{
	background-color:#2e64fe;
}
.menue li:hover ul, li.over ul {
	display: block;
}
.nav-2{
	background-color:#819dd7;
	border-bottom:1px solid #819ff7;
}
.nav-2  li a{
	display:block;
	border: 1px solid #819ff7;
	border-bottom:0px;
	padding:5px 5px; 
	text-decoration: none;
	color:#200;
	font-family:微软雅黑;
}

.nav-2  li{
	position:relative;
	float: left;
	width:80px;
}
.menue ul li a:hover{
	background-color:#fff;
	color:#fff;
}
</style>
</head>
<body>

	<div class="main">

		<div class="header">
			<div style="padding-left:26px;margin-top:6px;float:left;font-size:20px;">
				健康教育后台管理系统
			</div>
			<div style="padding:4px 6px;float:right;">
			  欢迎 &nbsp;&nbsp;<span>${user.account }</span>&nbsp;&nbsp;&nbsp;&nbsp;[<a href="javascript:void(0)" onclick="logout(0)">注销</a>]&nbsp;&nbsp;&nbsp;
			</div>
		</div>
		
		<div class="menue">
		  <div class="div_title">
		     <div id="poping">
		        <ul id="nav">
		        <li class="nav-1-menue-sep"><img src="${ctx}/images/menue_sep.png" width="5" height="33"/></li>
		        </ul>
		     </div>
		  </div>
		</div>
		
		
		
		<div class="mainContent" id="tabs">
		  
		</div>
		<div   id="mm" class="easyui-menu" style="width:120px">
		  <div id="mm-tabupdate">刷新</div>
		  <div class="menu-sep"></div>
		  <div id="mm-tabclose">关闭</div> 
		  <div id="mm-tabcloseother">关闭其它</div>
		  <div id="mm-tabcloseall">关闭全部</div>
		</div>
		
	    <div id="footer_div" align="center" class="footer">
			<div>2012-版权所有:上海民之智能科技有限公司</div>
		</div>
	</div>



</body>
</html>