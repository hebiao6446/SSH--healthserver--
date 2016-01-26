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
			url:"${ctx}/user!loadOnlinetestInfo.action",
			idField:'id',
			columns:[[
			          {checkbox:true},
			          {field:'topic',title:'题目',width:300},
			          {field:'testa',title:'选项A',width:150},
			          {field:'testb',title:'选项B',width:150},
			          {field:'testc',title:'选项C',width:150},
			          {field:'testd',title:'选项D',width:150},
					  {field:'correctAnswer',title:'正确答案',width:80,formatter:function(value,rowdata,index){
							if(value==1){
								return 'A'; 
							}
							else if(value==2){
								return 'B'; 
							}
							else if(value==3){
								return 'C'; 
							}
							else if(value==4){
								return 'D'; 
							}
							else
								return "";}}
			
			]],
			pagination:true,
			rownumbers:true,
			singleSelect:false,
			toolbar:[{
				text:'添加',
				iconCls:'icon-add',
				handler:function(){
					$("#insert_div").dialog('move',{left:0,top:0});
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
		title:"添加在线测试",
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
		title:"修改在线测试",
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
	url:'${ctx}/user!addOnlinetest.action',
	data:{topic:$('#topic').val(),testa:$('#testa').val(),testb:$('#testb').val(),testc:$('#testc').val(),testd:$('#testd').val(),correctAnswer:$('#correctAnswer').val()},
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
			  
$('#onlinetestId').val(row["id"]);
$('#updatetopic').val(row["topic"]);
$('#updatetesta').val(row["testa"]);
$('#updatetestb').val(row["testb"]);
$('#updatetestc').val(row["testc"]);
$('#updatetestd').val(row["testd"]);
$('#updatecorrectAnswer').val(row["correctAnswer"]);

$("#updateDiv").dialog('open');
}
}

function updateDiv(){
$.ajax({
		type:"post",
		url:'${ctx}/user!updatpeOnlinetest.action',
		data:{topic:$('#updatetopic').val(),testa:$('#updatetesta').val(),testb:$('#updatetestb').val(),testc:$('#updatetestc').val(),testd:$('#updatetestd').val(),correctAnswer:$('#updatecorrectAnswer').val(),onlinetestId:$('#onlinetestId').val()},
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
							     data:{ids:rt.join(","),table:"Onlinetest"},
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
		<td>题目</td><td><input type="text" id="topic" size="60"/></td>
		</tr>
		<tr>
		<td>选项A</td><td><input type="text" id="testa" size="60"/></td>
		</tr>
		<tr>
		<td>选项B</td><td><input type="text" id="testb" size="60"/></td>
		</tr>
		<tr>
		<td>选项C</td><td><input type="text" id="testc" size="60"/></td>
		</tr>
		<tr>
		<td>选项D</td><td><input type="text" id="testd" size="60"/></td>
		</tr>
		<tr>
			 <td>答案</td><td> 
			  <select name="correctAnswer" id="correctAnswer">
						<option value="1">A</option>
						<option value="2">B</option>
						<option value="3">C</option>
						<option value="4">D</option>
					</select>
			
			</td>
		</tr>
	</table>
</div>

<div id="updateDiv">
	<table cellspacing="0" cellpadding="2" border="0" id="ysss">
		
			<tr>
			<td>题目</td><td><input type="text" size="60" name="topic" id="updatetopic"/><input type="hidden" id="onlinetestId"/></td>
		</tr>
		<tr>
			<td>选项A</td><td><input type="text" size="60" name="testa" id="updatetesta"/></td>
		</tr>
			<tr>
			<td>选项B</td><td><input type="text" size="60" name="testb" id="updatetestb"/></td>
		</tr>
			<tr>
			<td>选项C</td><td><input type="text" size="60" name="testc" id="updatetestc"/></td>
		</tr>
			<tr>
			<td>选项D</td><td><input type="text" size="60" name="testd" id="updatetestd"/></td>
		<tr>
		 	<td>答案</td>
		 	<td>
			           <select name="correctAnswer" id="updatecorrectAnswer">
						<option value="1">A</option>
						<option value="2">B</option>
						<option value="3">C</option>
						<option value="4">D</option>
					</select>	
			</td>
		</tr>
	</table>
</div>


</body>
</html>