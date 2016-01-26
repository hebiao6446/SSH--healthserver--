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
			url:"${ctx}/user!loadUserinfo.action",
			idField:'id',
			columns:[[
			          {checkbox:true},
				{field:'account',title:'账号',width:120},
				{field:'passWord',title:'密码',width:120}
			
				
			]],
			pagination:true,
			rownumbers:true,
			singleSelect:false,
			toolbar:[{
				text:'添加',
				iconCls:'icon-add',
				handler:function(){
					$("#insertDiv").dialog('open');
				}
			},{
				text:'删除',
				iconCls:'icon-cut',
				handler:function(){
					delet_fun();
				}
			},'-',{
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

	  



$("#insertDiv").dialog({
		autoOpen: false,
		title:"添加用户",
		width: 420,
		height: 280,
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
			insertDiv();	
			}
		},{
			text:'取消',
			handler:function(){
				$("#insertDiv").dialog('close');
			}
		}]

	});

   $("#updateDiv").dialog({
		autoOpen: false,
		title:"修改用户",
		width: 420,
		height: 280,
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



 
 
 function insertDiv(){

 	 if($('#account').val()==""||$('#account').val()==null){
 		 
 		 $('#addaccount').html("必须填写用户");
 	 
 	 }else if ($('#passWord').val() == "" || $('#passWord').val() == null) {
			$('#addpassWord').html("必须填写密码");
		}else{
 		
 	  	$('#insertFrom').submit();
 	 }
 }





 function updateShowDialog(){
	   
	    var rows = $('#table_data').datagrid("getSelections");
		 if(rows.length==0 || rows.length>1){
			 $.messager.alert('提示','请选择一条记录进行修改!','info');
		 }
		 else{
			  var row= rows[0];
			  
$('#userId').val(row["id"]);
$('#updateaccount').val(row["account"]);
$('#updatepassWord').val(row["passWord"]);
$("#updateDiv").dialog('open');
}
 }

 function updateDiv(){
$.ajax({
		type:"post",
		url:'${ctx}/user!updateuserinfo.action',
		data:{account:$('#updateaccount').val(),passWord:$('#updatepassWord').val(),userId:$('#userId').val()},
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
			
		  
		  function delet_fun(){
				var rt=[];
				var rows= $('#table_data').datagrid("getSelections");
				
				$.each(rows,function(index,value){
					rt.push(value["id"]);
				});
				if (rt.length == 0)
					$.messager.alert('提示', '至少选中一项!','info');
				else {
					$.messager.confirm('提示', '确定删除这些记录吗!', function(r){
						if (r){
							
							 $.ajax({
							     type: "post",
							     url : '${ctx}/user!deleteBaseInfo.action',
							     dataType:'json',
							     data:{ids:rt.join(","),table:"User"},
							     success: function(msg,status){
							        if(msg.status==200){
							        	alert('删除成功');
							        	$('#table_data').datagrid("clearSelections");
							        	$('#table_data').datagrid("reload");
							        }
							        else
							        	alert('删除失败');
							       
							     } 
							});
						}
					});
				}
					
			}


	
	    


	</script>
</head>

<body>

<table id="table_data"></table>
<div id="insertDiv">
	    <form id="insertFrom" action="${ctx }/user!addUserinfo.action" method="post" enctype="multipart/form-data" >
	  	<table cellspacing="0" cellpadding="5" border="0">
	  		
	  		<tr>
			<td>账户</td><td><input type="text" id="account" name="account"/></td>
			<td class="yz" id="addaccount"></td>
		</tr>
		<tr>
			<td>密码</td><td><input type="text" id="passWord" name="passWord" /></td>
			<td class="yz" id="addpassWord"></td>
		</tr>	
   
	  	</table>
	  	</form>
	  </div>
	  



<div id="updateDiv">
	<table cellspacing="0" cellpadding="2" border="0" id="ysss">
		<tr>
			<td>账户</td><td><input type="text" id="updateaccount"/><input type="hidden" id="userId"/></td>
		</tr>
		<tr>
			<td>密码</td><td><input type="text" id="updatepassWord" /></td>
		</tr>
		
	</table>
</div>


</body>
</html>