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
			nowrap: true,
			striped: true,
			url:"${ctx}/user!loadRecommendInfo.action",
			idField:'id',
			columns:[[
			          {checkbox:true},
				{field:'title',title:'标题',width:240,formatter:function(value,rowdata,index){
					if(value.length>18)
						return value.substr(0,18)+"...";
					else
						return value;
				}},
				
				{field:'introduction',title:'介绍',width:240,formatter:function(value,rowdata,index){
					if(value.length>18)
						return value.substr(0,18)+"...";
					else
						return value;
				}},
				{field:'content',title:'内容',width:340,formatter:function(value,rowdata,index){
					if(value.length>20)
						return value.substr(0,20)+"...";
					else
						return value;
				}},
				
                {field:'imgInfo',title:'列表图',width:120,formatter:function(value,rowdata,index){
					
					if(value !=null && $.trim(value).length>0){
						return '<img src="${ctx}/'+value+'" width="50px" height="50px"/>';  }
					else
						return "无图片!";
				}},
                {field:'imgDetail',title:'详情图',width:120,formatter:function(value,rowdata,index){
					
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
		title:"添加实用",
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
		title:"修改实用",
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

	 if($('#recommendfile').val()==""||$('#recommendfile').val()==null){
		 $('#addFile').html("必须放置文件"); 
	 }else if ($('#recommendfile1').val() == ""
			|| $('#recommendfile1').val() == null) {
			$('#addFile1').html("必须放置文件");
		}else if ($('#title').val() == "" || $('#title').val() == null) {
			$('#addtitle').html("必须填写标题");
		}else if ($('#introduction').val() == ""
				|| $('#introduction').val() == null) {
			$('#addintroduction').html("必须填写介绍");
		}else if ($('#content').val() == ""
				|| $('#content').val() == null) {
			$('#addcontent').html("必须填写内容");
		}
         else{
		
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
$('#recommendId').val(row["id"]);
$('#updaterecommendfile').val(row["recommendfile"]);
$('#updaterecommendfile1').val(row["recommendfile1"]);
$('#updatetitle').val(row["title"]);
$('#updateintroduction').val(row["introduction"]);
$('#updatecontent').val(row["content"]);
$("#updateDiv").dialog('open');
}
}


function updateDiv(){
if ($('#updatetitle').val() == ""
		|| $('#updatetitle').val() == null) {
		$('#uptitle').html("必须选择标题");
	}else if ($('#updatecontent').val() == ""
			|| $('#updatecontent').val() == null) {
		$('#upcontent').html("必须选择内容");
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
					     data:{ids:rt.join(","),table:"Recommend"},
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
		<form id="insertFrom" action="${ctx }/user!addRecommend.action"
			method="post" enctype="multipart/form-data">

			<table cellspacing="0" cellpadding="2" border="0" id="ysss">
				
				<tr>
					<td>标题 &nbsp;</td>
					<td><input type="text" id="title" name="title" size="140"/></td>
					<td class="yz" id="addtitle"></td>
				</tr>
				<tr>
					<td>介绍 &nbsp;</td>
					<td><input type="text" id="introduction" name="introduction"  size="140"/>
					</td>
					<td class="yz" id="addintroduction"></td>
				</tr>
				<tr>
					<td>内容 &nbsp;</td>
					<td><textarea name="content" id="content" cols="120" rows="15"></textarea></td>
					<td class="yz" id="addcontent"></td>
				</tr>
				
               <tr>
					<td>列表图 &nbsp;</td>
					<td><input type="file" id="recommendfile" name="recommendfile" /></td>
					<td class="yz" id="addFile"></td>
					<td class="yz">图片尺寸：137*100px</td>
				</tr>
				<tr>
					<td>详情图 &nbsp;</td>
					<td><input type="file" id="recommendfile1" name="recommendfile1" /></td>
					<td class="yz" id="addFile1"></td>
					<td class="yz">图片尺寸：640*320px</td>
				</tr>
				
			</table>
		</form>
	</div>

	<div id="updateDiv">
		<form id="updateFrom" action="${ctx }/user!updateRecommend.action"
			method="post" enctype="multipart/form-data">
			<input type="hidden" name="recommendId" id="recommendId" />
			<table cellspacing="0" cellpadding="2" border="0" id="ysss">
				
				<tr>
					<td>标题</td>
					<td><input type="text" id="updatetitle" name="title" size="140" />
					<td class="yz" id="uptitle"></td>
				</tr>
				<tr>
					<td>介绍</td>
					<td><input type="text" id="updateintroduction" name="introduction" size="140" />
					<td class="yz" id="upintroduction"></td>
				</tr>
				<tr>
					<td>内容</td>
					<td><textarea name="content" id="updatecontent" cols="120" rows="15"></textarea> </td>
					<td class="yz" id="upcontent"></td>
				</tr>
				<tr>
					<td>列表图 &nbsp;</td>
					<td><input type="file" id="updaterecommendfile" name="recommendfile" /></td>
					<td class="yz" id="uprecommendfile"></td>
					<td class="yz">图片尺寸：137*100px</td>
				</tr>		
				<tr>
					<td>详情图 &nbsp;</td>
					<td><input type="file" id="updaterecommendfile1" name="recommendfile1" /></td>
					<td class="yz" id="uprecommendfile1"></td>
					<td class="yz">图片尺寸：640*320px</td>
				</tr>	
				
			</table>
		</form>
	</div>	


</body>
</html>