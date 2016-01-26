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
<script type="text/javascript">
var windowWidth = $(window).width();
var tableHeight = $(window).height()-60-38-46-14;
$(function(){
	 
	  $('#table_data').datagrid({
			
		   height:tableHeight+110,
			nowrap: true,
			striped: true,
			url:"${ctx}/user!loadMessageInfo.action",
			idField:'id',
			columns:[[
			    {checkbox:true},
			    {field:'phoneId',title:'手机标识符',width:250},
			    {field:'name',title:'姓名',width:150}, 
			    {field:'phone',title:'电话',width:150},
				{field:'address',title:'地址',width:250},
				{field:'content',title:'内容',width:320,formatter:function(value,rowdata,index){
					if(value.length>24)
						return value.substr(0,24)+"...";
					else
						return value;
				}},
				{field:'email',title:'邮箱',width:200},
				{field:'correct',title:'答对数',width:200},
				{field:'total',title:'总题数',width:200},
				{field:'timeSpace',title:'时间间隔',width:160},
				{field:'creatTime',title:'开始时间',width:160},
				{field:'endTime',title:'结束时间',width:160}
			]],
			pagination:true,
			rownumbers:true,
			singleSelect:false,
			toolbar:[{
				text:'删除',
				iconCls:'icon-cut',
				handler:function(){
					delet_fun();
				}
			}
			]
			
		});
	  

	  var pp =  $('#table_data').datagrid("getPager");
	  pp.pagination({
      beforePageText:'第',
      afterPageText:'页，共{pages}页',
      displayMsg:'从{from}到{to}，共{total}&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;'
	});

	  



		  
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
							     data:{ids:rt.join(","),table:"Message"},
							     success: function(msg,status){
							        if(msg.status==200){
							        	$('#table_data').datagrid("clearSelections");
							        	$('#table_data').datagrid("reload");
							        }
							        else
							        	$.messager.alert('提示', '删除失败','info');
							       
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

<table id="table_data"> </table>

</body>
</html>