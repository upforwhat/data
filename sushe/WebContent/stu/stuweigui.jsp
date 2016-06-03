
<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 5 Transitional//EN">
<html>
<head>
<title>校园宿舍管理系统</title>
<base href="<%=basePath%>">
<link href="Style/Style.css" rel="stylesheet" type="text/css" />
<link rel="stylesheet" type="text/css"
	href="jqueryeasyui/themes/default/easyui.css">
<link rel="stylesheet" type="text/css"
	href="jqueryeasyui/themes/icon.css">

</script>

<script type="text/javascript" src="jqueryeasyui/jquery.min.js"></script>
<script type="text/javascript" src="jqueryeasyui/jquery.easyui.min.js"></script>

<script type="text/javascript" src="jqueryeasyui/locale/easyui-lang-zh_CN.js"></script>
<script type="text/javascript">
Date.prototype.Format = function (fmt) { //author: meizz 
    var o = {
        "M+": this.getMonth() + 1, //月份 
        "d+": this.getDate(), //日 
        "h+": this.getHours(), //小时 
        "m+": this.getMinutes(), //分 
        "s+": this.getSeconds(), //秒 
        "q+": Math.floor((this.getMonth() + 3) / 3), //季度 
        "S": this.getMilliseconds() //毫秒 
    };
    if (/(y+)/.test(fmt)) fmt = fmt.replace(RegExp.$1, (this.getFullYear() + "").substr(4 - RegExp.$1.length));
    for (var k in o)
    if (new RegExp("(" + k + ")").test(fmt)) fmt = fmt.replace(RegExp.$1, (RegExp.$1.length == 1) ? (o[k]) : (("00" + o[k]).substr(("" + o[k]).length)));
    return fmt;
}
var sysdate = new Date().Format("yyyy-MM-dd");
var $obj;
   /*  function ww4(date){  
            var y = date.getFullYear();  
            var m = date.getMonth()+1;  
            var d = date.getDate();  
            var h = date.getHours();  
            return  y+'年'+(m<10?('0'+m):m)+'月'+(d<10?('0'+d):d)+'日'+(h<10?('0'+h):h)+'点';  
              
        }  */
       /*  function w4(s){  
            var reg=/[\u4e00\u9fa5]/  //利用正则表达式分隔  
            var ss = (s.split(reg));  
            var y = parseInt(ss[0],10);  
            var m = parseInt(ss[1],10);  
            var d = parseInt(ss[2],10);  
            
            if (!isNaN(y) && !isNaN(m) && !isNaN(d)){  
                return new Date(y,m-1,d);  
            } else {  
                return new Date();  
            }  
        }    */
$(function() {
	$obj = $("#dg");
	$obj.datagrid({
		loadMsg : '数据加载中请稍后……',
		url :'stuweigui.action',
		fitColumns : true,
		autoRowHeight : true,
		pagination :true, 
		border : false,
		singleSelect:true,
	
	idField:'weiguiid',
		columns : [ [ 
	{
				field : 'weiguiid',
				title : '违规id',
				hidden:true
			},
			{
				field : 'building_Name',
				title : "宿舍楼",
				editor : 'text',
				width : 200,
				sortable : false
				
			},{
				field : 'domitory_Name',
				title : "宿舍名称",
				width : 200,
				editor : 'text',
				sortable : false
			}, {
				field : 'shijian',
				title : "时间",
				type:'datebox',
				editor : 'text',
				width : 200,
				sortable :true
			}, {
				field : 'yuanyin',
				title : "原因",
				editor : 'text',
				width : 200,
				sortable : false
			}, 
			
		
			
			
		] ]
		
	});

});

	function selectCurRow(obj){
		var $a = $(obj);
		var $tr = $a.parent().parent().parent();
		var tmpId = $tr.find("td:eq(0)").text();
		$obj.datagrid('selectRecord', tmpId);
	}
	
	function getIndexAfterDel(){
		var selected = $obj.datagrid('getSelected');
		var index = $obj.datagrid('getRowIndex', selected);
		return index;
	}
	
	function editrow(index,obj){
		selectCurRow(obj);
		
		var tmpIndex = getIndexAfterDel();	
	    $obj.datagrid('beginEdit', tmpIndex);
	}
	
	function deleterow(index,obj){
	$.messager.confirm('Confirm','确认删除?',function(r){
		if (r){	        	
			selectCurRow(obj);
			var index = getIndexAfterDel();
			var node = $obj.datagrid('getSelected');
		/* 	alert("ID : "+node.shebeiId); */
			var id = node.shebeiId; 
			$.ajax({
				url :'shebeiDel?shebeiId='+id,
				type : 'POST',	 		
				timeout : 60000,
				success : function(data) {	
					var msg = '删除';
					var result=JSON.parse(data);
				/* 	 alert(result.result); */
				/* 	var result=data.parseJSON();
					 alert(result.result); */
					
				/* 	 var result = eval('(' + data + ')');  
				
					alert(result.result); */
					if ( result.result == "success") {
						$obj.datagrid('deleteRow', index);
					
						$.messager.alert('提示', msg + '成功！', 'info', function() {});
	    					$obj.datagrid('reload');
					} else {
						$.messager.alert('提示', msg + '失败！', 'error', function() {
	    						//window.location.href = root + 'esbService/initSysConfig.do';
	    					});
					}
				}
			}); 
			
		}
	});
}
	function saverow(index,obj){
	    selectCurRow(obj);
		var tmpIndex = getIndexAfterDel();	
		$obj.datagrid('endEdit', tmpIndex);
	    var node = $obj.datagrid('getSelected');
	    //var data = JSON.stringify(node);
	    var json = {};
	    json.shebeiId = node.shebeiId;
	    json.shebeiBuildingName = node.shebeiBuildingName;
	    json.shebeiBianhao = node.shebeiBianhao;
	    json.shebeiGoumaishijian = node.shebeiGoumaishijian;
	     json.shebeiZuijinjiachashijian = node.shebeiZuijinjiachashijian;
	      json.shebeiState  = node.shebeiState;
	        json.shebeiYouxiaoshijian = node.shebeiYouxiaoshijian;
	      json.shebeiName = node.shebeiName;
	    $.ajax({
			url :'shebeiSave.action',
			type : 'POST',
			data : json,
			timeout : 60000,
			success : function(data, textStatus, jqXHR) {	
				var msg = '';
				if (data == "success") {
					$.messager.alert('提示', '保存成功！', 'info', function() {
						$obj.datagrid('refreshRow', tmpIndex);
					});
				} else{
				 if(data == "duplicate"){
						msg = "该标识已存在！";
					}else{
						msg = "保存失败！";
					}
					$.messager.alert('提示', msg , 'error', function() {
						$obj.datagrid('beginEdit', tmpIndex);
					});
				}					

			}
		});
	    
	}
	function cancelrow(index,obj){
	    selectCurRow(obj);
		var tmpIndex = getIndexAfterDel();	
	    $obj.datagrid('cancelEdit', tmpIndex);
	}
	
	
		function searchData() {
		$('#dg').datagrid('load', {
			shebeiBuildingName : $('#shebeiBuildingName').val(),
		});
	}
	
	
	 function appendRow(){  
        $obj.datagrid('appendRow',{  
        shebeiId:'',
          
          shebeiBuildingName:'',
            shebeiBianhao: '',
             shebeiName:'',  
            shebeiGoumaishijian: "",  
            shebeiYouxiaoshijian: "",  
            shebeiZuijinjiachashijian:"",  
            shebeiState:"",
            opt:""  
        });  
          
        var length = $obj.datagrid("getRows").length;  
        if(length > 0){  
            editIndex = length - 1;  
        }else{  
            editIndex = 0;  
        }         
        //$obj.datagrid("selectRow", editIndex);  
        $obj.datagrid("beginEdit", editIndex);  
    }  
    function reject(){
			$('#dg').datagrid('rejectChanges');
			editIndex = undefined;
		}
	
</script>

</head>
<body>
	<center>
		<table width="900" border="0" cellspacing="0" cellpadding="0">
			<tr>
				<td height="60" bgcolor="#E6F5FF"
					style="color: #06F; font-size: 19px; font-weight: bolder; padding-left: 50px;">校园宿舍管理系统</td>
			</tr>
			<tr>
				<td height="30" background="Images/MenuBg.jpg">&nbsp;</td>
			</tr>
			<tr>
				<td height="500" align="center" valign="top">
					<table width="900" border="0" cellspacing="0" cellpadding="0">
						<tr>
							<td width="191" height="500" align="center" valign="top"
								background="Images/leftbg.jpg"><jsp:include
									page="${path}/Left.jsp" /></td>


							<td width="709" align="center" valign="top" bgcolor="#F6F9FE">

								<table width="709" border="0" cellspacing="0" cellpadding="0">
									<tr>
										<td height="30" background="Images/mainMenuBg.jpg"
											style="padding-left: 25px;">宿舍违规信息</td>
									</tr>
									<!--  -->

									<tr>
										<td height="470" align="center" valign="top" bgcolor="#F6F9FE">
											<table id="dg" title="违规信息" class="easyui-datagrid">
											</table>
											
										</td>


									</tr>
								</table>
							</td>
						</tr>
					</table>
				</td>
			</tr>
		</table>

	</center>
</body>
</html>
