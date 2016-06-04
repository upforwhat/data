<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
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
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="Style/Style.css" rel="stylesheet" type="text/css" />
</head>
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
					url : 'DomitoryManager.action?time='+sysdate,
					fitColumns : true,
					autoRowHeight : true,
					pagination : true,
					pagePosition : 'bottom',

					toolbar : '#caidan',
					/* 	rowStyler: function(index,row){
									if (row.student_Username < sysdate ){
										return 'background-color:#D61414;color:#fff;font-weight:bold;';
									}
								}, */

					border : false,
					singleSelect : true,
					idField : 'domitory_ID',
					columns : [ [
							{
								field : 'domitory_ID',
								title : 'id',
								hidden : true
							},
							{
								field : 'domitory_BuildingID',
								title : 'id',
								hidden : true
							},

							{
								field : 'building_Name',
								title : "宿舍楼",
								width : 200,

								sortable : true
							},
							{
								field : 'domitory_Name',
								title : "宿舍名称",

								editor : 'text',
								width : 200,
								sortable : true
							},
							{
								field : 'domitory_Number',
								title : "宿舍可住人数",
								editor : 'numberbox',
								width : 200,
								sortable : true
							},
							{
								field : 'domitory_Type',
								title : "宿舍类型",
								
								width : 200,
								sortable : true,
								editor : {
										type : 'combobox',
									
										options : {
											valueField : 'label',
											textField : 'value',
											data : [ {
												label : '男',
												value : '男'
											}, {
												label : '女',
												value : '女'
											} ],
												panelHeight : 'auto',
											required : true
										}
									},

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
				var id = node.domitory_ID;
				$.ajax({
					url : 'DomitoryDel.action?Domitory_ID=' + id,
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

		json.Domitory_ID = node.domitory_ID;
		json.Domitory_BuildingID = node.domitory_BuildingID;
		json.Domitory_Name = node.domitory_Name;

		json.Domitory_Type = node.domitory_Type;
		json.Domitory_Number = node.domitory_Number;

		$.ajax({
			url : 'DomitoryUpdateSave.action?time='+sysdate,
			type : 'POST',
			data : json,
			timeout : 60000,
			success : function(data, textStatus, jqXHR) {
				var msg = '';
				if (data == "success") {
					$.messager.alert('提示', '保存成功！', 'info', function() {
						$obj.datagrid('refreshRow', tmpIndex);
					});
				} else {
					if (data == "duplicate") {
						msg = "该宿舍已存在！";
					} else {
						msg = "保存失败！";
					}
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
			buildingid : $('#BuildingName').combobox('getValue'),
			domitoryid : $('#DomitoryName').combobox('getValue'),
		});

	}

	function appendRow() {
		$obj.datagrid('appendRow', {

			student_ID : '',
			student_Username : '',
			student_Name : '',
			student_Sex : '',
			student_Class : "",
			student_State : "",

			opt : ""
		});

		var length = $obj.datagrid("getRows").length;
		if (length > 0) {
			editIndex = length - 1;
		} else {
			editIndex = 0;
		}
		//$obj.datagrid("selectRow", editIndex);  
		$obj.datagrid("beginEdit", editIndex);
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
			width : 80,
		});

	});
</script>
<script>
	function exportUser() {

		window.open('user!export');
	}

	function exportUser2() {
		var s_name = $('#s_name').val();
		window.open('user!exportByTemplate?s_name=' + s_name);
	}

	function openUploadFileDialog() {
		$('#dlg2').dialog('open').dialog('setTitle', '批量添加');
	}

	function downloadTemplate() {
		window.open('template/domitory.xls');
	}

	function uploadFile() {
		$('#uploadForm').form('submit', {
			success : function(result) {
				var data = JSON.parse(result);
				if (data.errorMsg) {
							$.messager.alert("提示信息", data.errorMsg);
						} else {
							var success = data.success.length;
							var updateww= data.updatew.length;
							var a, b;
							a = data.failed;
							b = a.join("<br>");
							$.messager.alert("提示信息", "上传成功:" + "成功" + success
									+ "条" +"<br>"
									+"更新元数据"+updateww+"条<br>"
									+ "失败" + data.failed.length + "条"
									+ '<br>' + b);
							$("#dlg2").dialog("close");
							$("#dg").datagrid("reload");
				}
			}
		});
	}
</script>
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
											style="padding-left:25px;">宿舍管理</td>
									</tr>
									<tr>
										<td height="470" align="center" valign="top" bgcolor="#F6F9FE">
											<div id="caidan">
												<form name="form1" method="post" action="">
													<table width="100%%" border="0" cellspacing="0"
														cellpadding="0">
														<tr>
															功能导航：
															<a href="DomitoryAdd.action" class="easyui-linkbutton"
																iconCls="icon-add" plain="true">添加宿舍</a>
															<a href="javascript:void(0)" class="easyui-linkbutton"
																iconCls="icon-import" plain="true"
																onclick="openUploadFileDialog()"><img
																src="Images/iconfont-congexceldaoru.png">批量导入数据</a>
															查询： 宿舍楼：
															<input class="easyui-combobox" id="BuildingName"
																name=select1> 宿舍名称：
															<input class="easyui-combobox" id="DomitoryName"
																name=select2>
															<a href="javascript:searchData()"
																class="easyui-linkbutton" iconCls="icon-search"
																plain="true">搜索</a>
														</tr>
													</table>
												</form>
											</div>
											<table id="dg" title="设备信息管理" class="easyui-datagrid">

											</table>
											<div id="dlg2" class="easyui-dialog"
												style="width:400px;height:180px;padding:10px 20px"
												closed="true" buttons="#dlg-buttons2">
												<form id="uploadForm" action="domitoryupload.action"
													method="post" enctype="multipart/form-data">
													<table>
														<tr>
															<td>下载模版</td>
															<td><a href="javascript:void(0)"
																class="easyui-linkbutton" onclick="downloadTemplate()">下载模版</a>
															</td>
														</tr>
														<tr>
															<td>上传文件</td>
															<td><input type="file" name="userUploadFile">
															</td>
														</tr>
													</table>
												</form>
											</div>

											<div id="dlg-buttons2">
												<a href="javascript:void(0)" class="easyui-linkbutton"
													iconCls="icon-ok" onclick="uploadFile()">上传</a> <a
													href="javascript:void(0)" class="easyui-linkbutton"
													iconCls="icon-cancel"
													onclick="javascript:$('#dlg2').dialog('close')">关闭</a>
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
