<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
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
</head>

<body>
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
					fmt = fmt.replace(RegExp.$1,
							(RegExp.$1.length == 1) ? (o[k]) : (("00" + o[k])
									.substr(("" + o[k]).length)));
			return fmt;
		}
		var sysdate = new Date().Format("yyyy-MM-dd");
		var $obj;
		$(function() {
			$obj = $("#dg");
			$obj
					.datagrid({
						loadMsg : '数据加载中请稍后……',
						url : 'StudentManager.action',
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
						idField : 'student_ID',
						columns : [ [

								{
									field : 'student_ID',
									title : '学生id',
									hidden : true
								},
								{
									field : 'student_Username',
									title : "学号",
									width : 200,
									type : 'numberbox',
									editor : 'numberbox',
									required : true,
									sortable : true
								},
								{
									field : 'student_Name',
									title : "姓名",
									required : true,
									editor : 'text',
									width : 200,
									sortable : true
								},
								{
									field : 'student_Sex',
									title : "性别",
									required : true,

									width : 200,
									sortable : true,
									/* 	formatter : function(value, row) {
											return row.student_Sex;
										}, */
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
									field : 'student_Password',
									title : "密码",
									type : 'text',
									editor : 'text',
									width : 200,
									/* sortable : true, */
									formatter :  function (value) {
    return '******';
}

								},
								{
									field : 'student_Class',
									title : "系别",
									editor : 'text',
									width : 200,
									sortable : true,

								},
								{
									field : 'student_State',
									title : "状态",
									/* 		editor : 'text', */
									width : 200,
									sortable : true

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
					var id = node.student_ID;
					$.ajax({
						url : 'StudentDel.action?Student_ID=' + id,
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
			json.Student_ID = node.student_ID;
			
			json.Student_Password = node.student_Password;
			json.Student_Username = node.student_Username;
			json.Student_Name = node.student_Name;

			json.Student_State = node.student_State;

			json.Student_Class = node.student_Class;
			json.Student_Sex = node.student_Sex;
			$.ajax({
				url : 'StudentUpdateSave.action',
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
							msg = "该学号已存在！";
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
				SearchKey : $('#SearchKey').val(),
				SearchRow : $('#SearchRow').val(),
				State : $('#State').val(),
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
			window.open('template/stu.xls');
		}

		function uploadFile() {
			$('#uploadForm').form(
					'submit',
					{
						success : function(result) {
							var data = JSON.parse(result);
							if (data.errorMsg) {
								$.messager.alert("提示信息", data.errorMsg);
							} else {
								var success = data.success.length;
								var updateww = data.updatew.length;
								var a, b;
								a = data.failed;
								b = a.join("<br>");
								$.messager
										.alert("提示信息", "上传成功:" + "成功" + success
												+ "条" + "<br>" + "更新元数据"
												+ updateww + "条<br>" + "失败"
												+ data.failed.length + "条"
												+ '<br>' + b);
								$("#dlg2").dialog("close");
								$("#dg").datagrid("reload");
							}
						}
					});
		}
	</script>
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
											style="padding-left:25px;">学生管理</td>
									</tr>
									<tr>
										<td height="470" align="center" valign="top" bgcolor="#F6F9FE">

											<table id="dg" title="设备信息管理" class="easyui-datagrid">
											</table>
											<div id="caidan">
												<div>



													查询： <select name="State" id="State">
														<option value="" selected>请选择住宿状态</option>
														<option value="入住">入住</option>
														<option value="未入住">未入住</option>
														<option value="迁出">迁出</option>
													</select> <select name="SearchRow" id="SearchRow">
														<option value="" selected>请选择条件</option>
														<option value="student_Name">姓名</option>
														<option value="student_Username">学号</option>
														<option value="student_Class">系别</option>
													</select> <input name="SearchKey" type="text" class="text1"
														id="SearchKey" value=""> <a
														href="javascript:searchData()" class="easyui-linkbutton"
														iconCls="icon-search" plain="true">搜索</a>


												</div>
												<div>
													<a href="javascript:void(0);" class="easyui-linkbutton"
														iconCls="icon-add" plain="true" onclick="appendRow()">添加数据</a>
													<a href="javascript:void(0)" class="easyui-linkbutton"
														data-options="iconCls:'icon-undo',plain:true"
														onclick="reject()">取消</a> <a href="javascript:void(0)"
														class="easyui-linkbutton" iconCls="icon-import"
														plain="true" onclick="openUploadFileDialog()"><img
														src="Images/iconfont-congexceldaoru.png">批量导入数据</a>
												</div>

											</div>



											<div id="dlg2" class="easyui-dialog"
												style="width:400px;height:180px;padding:10px 20px"
												closed="true" buttons="#dlg-buttons2">
												<form id="uploadForm" action="stufileupload.action"
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
