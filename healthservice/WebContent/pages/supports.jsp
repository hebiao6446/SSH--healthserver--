<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ include file="/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<link rel="stylesheet" type="text/css"
	href="${ctx }/themes/gray/easyui.css" />
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
			nowrap: false,
			striped: true,
			url:"${ctx}/user!loadSupportsInfo.action",
			idField:'id',
			columns:[[
			          {checkbox:true},
			          {field:'title',title:'标题',width:240,formatter:function(value,rowdata,index){
							if(value.length>22)
								return value.substr(0,22)+"...";
							else
								return value;
						}},
						{field:'content',title:'内容',width:580}
			
			]],
			pagination:true,
			rownumbers:true,
			singleSelect:false,
			toolbar:[{
				text:'添加',
				iconCls:'icon-add',
				handler:function(){
					var rows= $('#table_data').datagrid("getRows");
					if(rows.length>=1){
						$.messager.alert('提示','亲,只能添加一条记录!','info');
					}
					else
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
					$("#update_div").dialog('move',{left:0,top:0});
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
		title:"添加技术支持",
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
		title:"修改技术支持",
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


function insertDiv(){
 $.ajax({
	type:"post",
	url:'${ctx}/user!addSupports.action',
	data:{title:$('#title').val(),content:$('#content').val()},
	dataType:"json",
	success:function(msg,status){
		if(msg.status==200){
			alert(msg.msg);
			 $('#table_data').datagrid('reload');
			 $("#insertDiv").dialog('close');
			
		}
	}
});
}





function updateShowDialog(){
	   
	    var rows = $('#table_data').datagrid("getSelections");
		 if(rows.length==0 || rows.length>1){
			 $.messager.alert('提示','请选择一条记录进行修改!','info');
		 }
		 else{
			  var row= rows[0];
			  
$('#supportsId').val(row["id"]);
$('#updatetitle').val(row["title"]);
$('#updatecontent').val(row["content"]);

$("#updateDiv").dialog('open');
}
}

function updateDiv(){
$.ajax({
		type:"post",
		url:'${ctx}/user!updatpeSupports.action',
		data:{title:$('#updatetitle').val(),content:$('#updatecontent').val(),supportsId:$('#supportsId').val()},
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
							     data:{ids:rt.join(","),table:"Supports"},
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
	<table cellspacing="0" cellpadding="2" border="0" id="ysss">
	<tr>
		<td>标题</td><td><input type="text" id="title" size="60"/></td>
		</tr>
		<tr>
		<td>内容</td><td><input type="text" id="content" size="60"/></td>
		</tr>
	</table>
</div>

<div id="updateDiv">
	<table cellspacing="0" cellpadding="2" border="0" id="ysss">
		
			<tr>
			<td>标题</td><td><input type="text" size="60" name="title" id="updatetitle"/><input type="hidden" id="supportsId"/></td>
		</tr>
		<tr>
			<td>内容</td><td><input type="text" size="60" name="content" id="updatecontent"/></td>
		</tr>
		
	</table>
</div>


</body>
</html>