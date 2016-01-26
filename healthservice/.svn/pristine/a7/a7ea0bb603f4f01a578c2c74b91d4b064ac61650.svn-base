<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ include file="/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<link rel="stylesheet" type="text/css"	href="${ctx }/themes/gray/easyui.css" />
<link rel="stylesheet" type="text/css" href="${ctx }/themes/icon.css" />
<style type="text/css">
#ysss{ font-size:12px;  margin:20px;}
.yz{font-size:12px;color: #F00;text-align:left;}
</style>
<script type="text/javascript" src="${ctx }/js/jquery-1.4.2.min.js"></script>
<script type="text/javascript" src="${ctx }/js/jquery.easyui.min.js"></script>
<script type="text/javascript" src="${ctx }/js/json2.js"></script>
<script type="text/javascript">
var windowWidth = $(window).width();
var tableHeight = $(window).height()-60-38-46-14;
$(function(){
	 
	  $('#table_data').datagrid({
			
		    height:tableHeight+110,
			nowrap: true,
			striped: true,
			url:"${ctx}/user!loadVersionInfo.action",
			idField:'id',
			columns:[[
			          {checkbox:true},
			
                {field:'title',title:'标题',width:220},
                {field:'urlpath',title:'网址',width:360},	
                {field:'urlNum',title:'版本内容',width:160},	
                {field:'type',title:'类型',width:100,formatter:function(value,rowdata,index){
					if(value==1){
						return 'IOS'; 
					}
					else if(value==2){
						return 'Android'; 
					}
					else
						return "";}}
				
			]],
			pagination:true,
			rownumbers:true,
			singleSelect:false,
			toolbar:[{
				text:'修改',
				iconCls:'icon-edit',
				handler:function(){
					updateShowDialog();
					
				}
			
			}]
			
		});
	  

	  var pp =  $('#table_data').datagrid("getPager");
	  pp.pagination({
      beforePageText:'第',
      afterPageText:'页，共{pages}页',
      displayMsg:'从{from}到{to}，共{total}&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;'
	});

	  




   $("#updateDiv").dialog({
		autoOpen: false,
		title:"修改版本更新",
		width : windowWidth,
		height : tableHeight+150,
		iconCls:'icon-save',
		modal:false,
		position:['left','top'],
		resizable:false,
		shadow: false,
		closed: true,
		buttons:[{
			text:'确定',
			iconCls:'icon-ok',
			handler:function(){
			updateDiv();	
			}
		},{
			text:'取消',
			handler:function(){
				$("#updateDiv").dialog('close');
			}
		}]
	}); 
 
   
});






 function updateShowDialog(){
	   
	    var rows = $('#table_data').datagrid("getSelections");
		 if(rows.length==0 || rows.length>1){
			 $.messager.alert('提示','请选择一条记录进行修改!','info');
		 }
		 else{
			  var row= rows[0];
			  
$('#versionId').val(row["id"]);
$('#updatetitle').val(row["title"]);
$('#updateurlpath').val(row["urlpath"]);
$('#updateurlNum').val(row["urlNum"]);
$("#updateDiv").dialog('open');
}
 }

 function updateDiv(){
$.ajax({
		type:"post",
		url:'${ctx}/user!updatpeVersion.action',
		data:{title:$('#updatetitle').val(),urlpath:$('#updateurlpath').val(),urlNum:$('#updateurlNum').val(),versionId:$('#versionId').val()},
		dataType:"json",
		success:function(msg,status){
			if(msg.status==200){
				alert(msg.msg);
				 $('#table_data').datagrid('reload');
				 $("#updateDiv").dialog('close');					
				 
			}
		}
	  });
}
			
		  
		  

	
	    


	</script>
</head>

<body>


<table id="table_data"></table>


	<div id="updateDiv">
		<form id="updateFrom" action="${ctx }/user!updatpeVersion.action"
			method="post" enctype="multipart/form-data">
			<input type="hidden" name="versionId" id="versionId" />
			<table cellspacing="0" cellpadding="2" border="0" id="ysss">
				<tr>
			   <td>标题</td><td><input type="text" id="updatetitle" name="title" size="60"/>
			   <td class="yz" id="uptitle"></td>
		      </tr>
		     
		      <tr>
			   <td>网址</td><td><input type="text" id="updateurlpath" name="urlpath" size="60"/>
			   <td class="yz" id="upurlpath"></td>
		      </tr>
		       <tr>
			   <td>版本内容</td><td><input type="text" id="updateurlNum" name="urlNum" size="60"/>
			   <td class="yz" id="upurlNum"></td>
		      </tr>
		      
			</table>
		</form>
	</div>	




</body>
</html>