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
<script language="javascript" type="text/javascript"
	src="<%=request.getContextPath()%>/My97DatePicker/WdatePicker.js">
	
</script>
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
					url : 'baoxiuManager.action?time='+new Date(),
					//url : root + 'js/app/sysManagement/sysConfig.json',
					fitColumns : true,
					autoRowHeight : true,
					pagination : true,
					pagePosition : 'bottom',

					toolbar : '#caidan',

					border : false,
					singleSelect : true,

					idField : 'baoxiuid',
					columns : [ [
							{
								field : 'baoxiuid',
								title : 'id',
								hidden : true
							},
							{
								field : 'buildingId',
								title : 'id',
								hidden : true
							},
							{
								field : 'domitoryId',
								title : 'id',
								hidden : true
							},
							{
								field : 'baoxiuBuildingName',
								title : '宿舍楼',
								hidden : false,
								sortable : true
							},
							{
								field : 'baoxiuDomitoryName',
								title : '宿舍名称',
								hidden : false,
								sortable : true
							},
							{
								field : 'baoxiuStudentName',
								title : '申请人',
								hidden : false,
								sortable : false
							},

							{
								field : 'baoxiushijian',
								title : "申请时间",
							width :130,
								sortable : true

							},
							{
								field : 'baoxiuXianmu',
								title : '申请原因',
							width :"100",
							autoRowHeight :true,
								hidden : false,
						
								sortable : false
							},
							{
								field : 'state',
								title : "状态",
								width :"auto",
								
								sortable : false,
							},
							
							{
								field : 'chulishijian',
								title : '处理时间',
								hidden : false,
								width :130,
								sortable : true
							},
							
							{
								field : 'opt',
								title : "操作",
								width : 150,
								align : 'center',
								formatter : function(value, row, index) {
								
										var e = '<a href="javascript:void(0);" class="ope-edit"   onclick="saverow('
												+ index + ',this)">受理</a> ';
										var d = '<a href="javascript:void(0);" class="ope-remove"  onclick="deleterow('
												+ index + ',this)">删除</a>';
										return e + d;
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
				var id = node.baoxiuid;
				$.ajax({
					url : 'baoxiuDel.action?baoxiuid=' + id,
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
		//处理用户请求的execute方法
		json.baoxiuBuildingId = node.baoxiuBuildingId;
		json.baoxiuBuildingName = node.baoxiuBuildingName;
		json.baoxiuDomitoryName = node.baoxiuDomitoryName;
		json.baoxiuDomitoryid = node.baoxiuDomitoryid;
		json.baoxiuStudentId = node.baoxiuStudentId;
		json.baoxiuStudentName = node.baoxiuStudentName;
		json.baoxiuXianmu = node.baoxiuXianmu;
		json.baoxiuid = node.baoxiuid;
		json.baoxiushijian = node.baoxiushijian;
		json.chulishijian = node.chulishijian;
		json.state = node.state;

		$.ajax({
			url : 'baoxiuShouli.action',
			type : 'POST',
			data : json,
			timeout : 60000,
			success : function(data, textStatus, jqXHR) {
				var msg = '';
				var result = JSON.parse(data);
				if (result.result == "success") {
					$.messager.alert('提示', '已处理成功！', 'info', function() {
						$obj.datagrid('reload');
					});
				} else if (result.result == "yijingchuli") {
					$.messager.alert('提示', '未做更改', 'info', function() {
						$obj.datagrid('reload');
					});

				} else {

					msg = "处理失败";
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
			shoulistate : $('#shoulistate').val(),
			
		});
	}
	function reject() {
		$('#dg').datagrid('rejectChanges');
		editIndex = undefined;
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
											style="padding-left:25px;">报修信息列表</td>
									</tr>
									<tr>
										<td height="470" align="center" valign="top" bgcolor="#F6F9FE">


											<table id="dg" class="easyui-datagrid" title="报修信息列表"></table>

											<div id="caidan">
												<form name="form1" method="post"
													action="baoxiuManager.action">
													<table>
														<tr>

															<td>受理状态： <select name="shoulistate" id=shoulistate>
																	<option value="">请选择</option>
																	<option value="已处理">已处理</option>
																	<option value="未处理">未处理</option>
															</select>
															<td><a href="javascript:searchData()"
																class="easyui-linkbutton" iconCls="icon-search"
																plain="true">搜索</a></td>
														</tr>

													</table>
												</form>
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
