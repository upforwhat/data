<%@ page language="java" import="java.util.*,com.db.DBHelper,java.sql.*"
	pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 5 Transitional//EN">
<html>
<head>
<title>校园宿舍管理系统</title>
<base href="<%=basePath%>">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="Style/Style.css" rel="stylesheet" type="text/css" />
<link rel="stylesheet" type="text/css"
	href="jqueryeasyui/themes/default/easyui.css">
<link rel="stylesheet" type="text/css"
	href="jqueryeasyui/themes/icon.css">

<script type="text/javascript" src="jqueryeasyui/jquery.min.js"></script>
<script type="text/javascript" src="jqueryeasyui/jquery.easyui.min.js"></script>

<script type="text/javascript"
	src="jqueryeasyui/locale/easyui-lang-zh_CN.js"></script>
<script type="text/javascript">
	Date.prototype.Format = function(fmt) { //author: meizz 
		var o = {
			"M+" : this.getMonth() + 1, //月份 
			"d+" : this.getDate(), //日 
			"h+" : this.getHours(), //小时 
			"m+" : this.getMinutes(), //分 
			"s+" : this.getSeconds(), //秒 
			"q+" : Math.floor((this.getMonth() + 3) / 3), //季度 
			"S" : this.getMilliseconds()
		//毫秒 
		};
		if (/(y+)/.test(fmt))
			fmt = fmt.replace(RegExp.$1, (this.getFullYear() + "")
					.substr(4 - RegExp.$1.length));
		for ( var k in o)
			if (new RegExp("(" + k + ")").test(fmt))
				fmt = fmt.replace(RegExp.$1, (RegExp.$1.length == 1) ? (o[k])
						: (("00" + o[k]).substr(("" + o[k]).length)));
		return fmt;
	}
	var sysdate = new Date().Format("yyyy-MM-dd");
	var $obj;
	$(function() {
		$obj = $("#dg");
		$obj
				.datagrid({
					loadMsg : '数据加载中请稍后……',
					url : 'weiguiManager.action',
					//url : root + 'js/app/sysManagement/sysConfig.json',
					fitColumns : true,
					autoRowHeight : true,
					pagination : true,
					pagePosition : 'bottom',

					toolbar : '#caidan',
				

					border : false,
					singleSelect : true,
				
	idField:'weiguiid',
		columns : [ [ 
	{
				field : 'weiguiid',
				title : '违规id',
				hidden:true
			},
			{
				field : 'chulimiaoshu',
				title : '处理',
				hidden:true
			},
			{
				field : 'domitory_ID',
				title : '宿舍id',
				hidden:true
			},
			
			{
				field : 'building_Name',
				title : "宿舍楼",
				editor : 'text',
				width : 200,
				sortable : true
				
			},{
				field : 'domitory_Name',
				title : "宿舍名称",
				width : 200,
				editor : 'text',
				sortable : true
			}, {
				field : 'shijian',
				title : "时间",
				type:'datebox',
				editor : 'datebox',
				width : 200,
				sortable :true
			}, {
				field : 'yuanyin',
				title : "原因",
				editor : 'text',
				width : 200,
				sortable : false
			}, 

							{
								field : 'opt',
								title : "操作",
								width : 150,
								align : 'center',
								formatter : function(value, row, index) {
									if (row.editing) {
										var s = '<a href="javascript:void(0);" class="ope-save"   onclick="saverow('
												+ index + ',this)">保存</a> ';
										var c = '<a href="javascript:void(0);" class="ope-cancel" onclick="cancelrow('
												+ index + ',this)">取消</a>';
										return s + c;
									} else {
										var e = '<a href="javascript:void(0);" class="ope-edit"   onclick="editrow('
												+ index + ',this)">修改</a> ';
										var d = '<a href="javascript:void(0);" class="ope-remove"  onclick="deleterow('
												+ index + ',this)">删除</a>';
										return e + d;
									}
								}
							} ] ],
					onLoadSuccess : function(data) {

					},
					onBeforeEdit : function(index, row) {
						row.editing = true;
						$obj.datagrid('refreshRow', index);
					},
					onAfterEdit : function(index, row) {
						row.editing = false;
						$obj.datagrid('refreshRow', index);
					},
					onCancelEdit : function(index, row) {
						row.editing = false;
						$obj.datagrid('refreshRow', index);
					}
				});

	});

	function selectCurRow(obj) {
		var $a = $(obj);
		var $tr = $a.parent().parent().parent();
		var tmpId = $tr.find("td:eq(0)").text();
		$obj.datagrid('selectRecord', tmpId);
	}

	function getIndexAfterDel() {
		var selected = $obj.datagrid('getSelected');
		var index = $obj.datagrid('getRowIndex', selected);
		return index;
	}

	function editrow(index, obj) {
		selectCurRow(obj);

		var tmpIndex = getIndexAfterDel();
		$obj.datagrid('beginEdit', tmpIndex);
	}

	function deleterow(index, obj) {
		$.messager.confirm('Confirm', '确认删除?', function(r) {
			if (r) {
				selectCurRow(obj);
				var index = getIndexAfterDel();
				var node = $obj.datagrid('getSelected');
				/* 	alert("ID : "+node.shebeiId); */
				var id = node.weiguiid;
				$.ajax({
					url : 'weiguiDel.action?weiguiid=' + id,
					type : 'POST',
					timeout : 60000,
					success : function(data) {
						var msg = '删除';
						var result = JSON.parse(data);
						/* 	 alert(result.result); */
						/* 	var result=data.parseJSON();
							 alert(result.result); */

						/* 	 var result = eval('(' + data + ')');  
						
							alert(result.result); */
						if (result.result == "success") {
							$obj.datagrid('deleteRow', index);

							$.messager.alert('提示', msg + '成功！', 'info',
									function() {
									});
							$obj.datagrid('reload');
						} else {
							$.messager.alert('提示', msg + '失败！', 'error',
									function() {
										//window.location.href = root + 'esbService/initSysConfig.do';
									});
						}
					}
				});

			}
		});
	}
	function saverow(index, obj) {
		selectCurRow(obj);
		var tmpIndex = getIndexAfterDel();
		$obj.datagrid('endEdit', tmpIndex);
		var node = $obj.datagrid('getSelected');
		//var data = JSON.stringify(node);
		var json = {};
	
		json.weiguiid = node.weiguiid;
		json.Domitory_Name = node.domitory_Name;
		json.Domitory_ID = node.domitory_ID;
		json.shijian = node.shijian;
		json.yuanyin = node.yuanyin;
		json.chulimiaoshu = node.chulimiaoshu;
		
		$.ajax({
			url : 'weiguiUpdateSave.action',
			type : 'POST',
			data : json,
			timeout : 60000,
			success : function(data, textStatus, jqXHR) {
				var msg = '';
				var result=JSON.parse(data);
				if (result.result == "success") {
					$.messager.alert('提示', '保存成功！', 'info', function() {
						$obj.datagrid('refreshRow', tmpIndex);
					});
				} else {
					
						msg = "保存失败！";
				
					$.messager.alert('提示', msg, 'error', function() {
						$obj.datagrid('beginEdit', tmpIndex);
					});
				}

			}
		});

	}
	function cancelrow(index, obj) {
		selectCurRow(obj);
		var tmpIndex = getIndexAfterDel();
		$obj.datagrid('cancelEdit', tmpIndex);
	}

	function searchData() {
		$('#dg').datagrid('load', {
			select1 : $('#BuildingName').combobox('getValue'),
			select2 : $('#DomitoryName').combobox('getValue'), 
			
			
		});
	}
	function reject() {
		$('#dg').datagrid('rejectChanges');
		editIndex = undefined;
	}
</script>
<script type="text/javascript">
	$(function() {
		// 下拉框选择控件，下拉框的内容是动态查询数据库信息  
		$('#BuildingName').combobox({
			url : 'GetBuilding.action',
			editable : false, //不可编辑状态  
			cache : false,
			panelHeight : 'auto',
			valueField : 'building_ID',
			textField : 'building_Name',
			required : false,
			width : "80",

			onHidePanel : function() {
				$("#DomitoryName").combobox("setValue", '');
				var id = $('#BuildingName').combobox('getValue');
				//alert(id);  

				$.ajax({
					type : "POST",
					url : 'GetDomitory.action?buildingid=' + id,
					cache : false,
					dataType : "json",
					success : function(data) {
						$("#DomitoryName").combobox("loadData", data);
					}
				});
			}
		});

		$('#DomitoryName').combobox({
			//url:'itemManage!categorytbl',   
			editable : false, //不可编辑状态  
			cache : false,
			panelHeight : 'auto',
			valueField : 'domitory_ID',
			textField : 'domitory_Name',
			required : false,
			width :80,
		});

	});
</script>
</head>
<body>
	<center>
		<table width="900" border="0" cellspacing="0" cellpadding="0">
			<tr>
				<td height="60" bgcolor="#E6F5FF"
					style="color:#06F; font-size:19px; font-weight:bolder; padding-left:50px;">校园宿舍管理系统</td>
			</tr>
			<tr>
				<td height="30" background="Images/MenuBg.jpg">&nbsp;</td>
			</tr>
			<tr>
				<td height="500" align="center" valign="top"><table width="900"
						border="0" cellspacing="0" cellpadding="0">
						<tr>
							<td width="191" height="500" align="center" valign="top"
								background="Images/leftbg.jpg"><jsp:include
									page="${path}/Left.jsp" /></td>
							<td width="709" align="center" valign="top" bgcolor="#F6F9FE"><table
									width="709" border="0" cellspacing="0" cellpadding="0">
									<tr>
										<td height="30" background="Images/mainMenuBg.jpg"
											style="padding-left:25px;">违规信息列表</td>
									</tr>
									<tr>
										<td height="470" align="center" valign="top" bgcolor="#F6F9FE">
											<table id="dg" class="easyui-datagrid" title="违规管理"></table>
											<div id="caidan">
												<div>
													<form name="form1" method="post">
														<table border="0" cellspacing="0" cellpadding="0">
															<tr>
																<td height="30" style="padding-left:20px; width: 152px">功能导航：
																	<a href="admin/weigui/weiguiAdd.jsp" class="easyui-linkbutton" iconCls="icon-add" plain="true">添加违规</a>
																</td>
																<td>查询： <input class="easyui-combobox"  id="BuildingName"
																name="select1"></td>

																<td><label>宿舍名称：</label> <input class="easyui-combobox" id="DomitoryName"
																name="select2"></td>
																<td><a href="javascript:searchData()"
																	class="easyui-linkbutton" iconCls="icon-search"
																	plain="true">搜索</a></td>
															</tr>

														</table>
													</form>
												</div>
												<div>
													<a href="javascript:void(0)" class="easyui-linkbutton"
														data-options="iconCls:'icon-undo',plain:true"
														onclick="reject()">取消</a>

												</div>

											</div>




										</td>
									</tr>
								</table></td>
						</tr>
					</table></td>
			</tr>
			<tr>
				<td height="35" background="Images/bootBg.jpg">&nbsp;</td>
			</tr>
		</table>

	</center>
</body>
</html>
