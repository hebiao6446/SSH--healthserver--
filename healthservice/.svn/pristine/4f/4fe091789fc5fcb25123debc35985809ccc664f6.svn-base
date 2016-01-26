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
<script type="text/javascript">
var windowWidth = $(window).width();
var tableHeight = $(window).height()-60-38-46-14;
$(function(){
	 
	  $('#table_data').datagrid({
			
		    height:tableHeight+110,
			nowrap: true,
			striped: true,
			url:"${ctx}/user!loadBootpageInfo.action",
			idField:'id',
			columns:[[
			          {checkbox:true},
				{field:'bootPage',title:'图片',width:150 ,formatter:function(value,rowdata,index){
					
					if(value !=null && $.trim(value).length>0){
						return '<img src="${ctx}/'+value+'" width="50px" height="50px"/>';  }
					else
						return "无图片!";
				}}
				
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
	  		title:"添加引导页",
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
	  		title:"修改引导页",
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

	    



		   function insertDiv(){

		  	 if($('#homefile').val()==""||$('#homefile').val()==null){
		  		 $('#addFile').html("必须放置文件");
		  	 
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
	   $('#homeId').val(row["id"]);
	   $('#updatehomefile').val(row["homefile"]);
	   $("#updateDiv").dialog('open');
	  }
	     }

	   function updateDiv(){
	  	 if($('#updatehomefile').val()==""||$('#updatehomefile').val()==null){
	  		 $('#addFile').html("必须放置文件");
	  	 }else{
	  	  	$('#updateFrom').submit();
	  	 }
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
			  					     data:{ids:rt.join(","),table:"Bootpage"},
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
	  });

	  	</script>
	  </head>

	  <body>

	  <table id="table_data"></table>
	 
<div id="insertDiv">
	    <form id="insertFrom" action="${ctx }/user!addBootpageImge.action" method="post" enctype="multipart/form-data" >
	  	<table cellspacing="0" cellpadding="0" border="0">
	  		<tr>
	  			<td height="35" width="8"></td>
	  			<td colspan="3">添加图片</td>
	  			
	  		</tr>	
	  		<tr>
	  			<td height="35" width="8"></td>
	  			<td>图片</td>
	  			<td><input type="file" id="homefile" name="homefile"/></td>
	  			<td class="yz" id="addFile"></td>
	  			<td class="yz">图片尺寸：640*960px</td>
	  		</tr>
	  		
	  	</table>
	  	</form>
	  </div>

	  <div id="updateDiv">
	    <form id="updateFrom" action="${ctx }/user!updateBootpageimage.action" method="post" enctype="multipart/form-data" >
	  	<table cellspacing="0" cellpadding="0" border="0">
	  		<tr>
	  			<td height="35" width="8"></td>
	  			<td colspan="3">修改图片</td>
	  		</tr>
	 
	  	
	  		<tr>
	  			<td height="35" width="8"></td>
	  			<td>图片</td>
	  			<td><input type="file" id="updatehomefile" name="homefile"/><input type="hidden" id="homeId" name="homeId"/></td>
	  			<td class="yz">图片尺寸：640*960px</td>
	  		</tr>
	  	
	  	</table>
	  	</form>
	  </div>		
	  </body>
	  </html>