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



<script type="text/javascript" src="jqueryeasyui/jquery.min.js"></script>
<script type="text/javascript" src="jqueryeasyui/jquery.easyui.min.js"></script>

<script type="text/javascript"
	src="jqueryeasyui/locale/easyui-lang-zh_CN.js"></script>
<script type="text/javascript">
	var $obj;
	$(function() {
		$obj = $("#dg");
		$obj.datagrid({
			loadMsg : '数据加载中请稍后……',
			url : 'weishengshuidianStudentManager.action?time='+new Date(),
			fitColumns : true,
			autoRowHeight : true,
			border : false,
			pagination : true,
			singleSelect : true,

			idField : 'weishengshuidianId',
			columns : [ [ {
				field : 'weishengshuidianId',
				title : "id",
				hidden : true
			}, {
				field : 'wssdBuildingName',
				title : "宿舍楼",
				width : 200,
				editor : 'text',
				sortable : false
			}, {
				field : 'wssdDomitoryName',
				title : "宿舍名称",
				width : 200,
				editor : 'text',
				sortable : false
			}, {
				field : 'shiijian',
				title : "时间",
				type : 'datebox',
			
				width : 200,
				sortable : true
			}, {
				field : 'shui',
				title : "水（吨）",
				editor : 'text',
				width : 200,
				sortable : false,
				hidden : true
			}, {
				field : 'dian',
				title : "电（度）",
				editor : 'text',
				width : 200,
				sortable : false,
				hidden : true,

			}, {
				field : 'jine',
				title : "总金额",
				width : 200,
				editor : 'text',
				sortable : false
			}, {
				field : 'score',
				title : "卫生评分",
				width : 200,
				editor : 'text',
				sortable : false
			}, {
				field : 'opt',
				title : "操作",
				width : 150,
				align : 'center',
				formatter : formattest,
			}

			] ],
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

	function formattest(value, row, index) {
		return '<a href="javascript:void(0);" onclick="showdetail(' + index
				+ ')">详细</a>';
	}
	function showdetail(index) {
		$('#dg').datagrid('selectRow', index);// 关键在这里  
		var row = $('#dg').datagrid('getSelected');
		if (row) {
			/*  $('#shoudetail').dialog('open').dialog('setTitle','个人卫生水电详情');   */
			var node = $obj.datagrid('getSelected', index);
			//var data = JSON.stringify(node);
			var jsond = node.shiijian;
			$('#tuitionUseListForm')
					.form(
							'submit',
							{
								url : 'Stugerenshuidiandetail.action',
								onSubmit : function(json) {
									json.shiijian = jsond;
								},
								success : function(data) {
									var result = JSON.parse(data);
									if (result.found == "false") {
										alert("信息没找到");
										$('#shoudetail').windo("close");
									} else {
										$('#shoudetail').dialog('open').dialog(
												'setTitle', '个人卫生水电详情');
										document.getElementById('susemingchen').innerHTML = result.DomitoryName;
										document.getElementById('ruzhurenshu').innerHTML = "入住人数:"
												+ result.ruzhurenshu;
										document
												.getElementById('shangcishuidushu').innerHTML = "上月水表度数:"
												+ result.lastmothshuidushu;
										document
												.getElementById('shangcidianbiaodushu').innerHTML = "上月电表度数:"
												+ result.lastmothdiandushu;
										document
												.getElementById('zhecishuibiaodushu').innerHTML = "这月水表度数:"
												+ result.thimothshuidushu;
										document
												.getElementById('zhecidianbiaodushu').innerHTML = "这月电表度数:"
												+ result.thimothdiandushu;

										document.getElementById('shuishiyong').innerHTML = "本月用水统计:"
												+ result.shuiyongle;
										document.getElementById('dianshiyong').innerHTML = "本月用电统计:"
												+ result.dianyongle;
										document.getElementById('shuidianheji').innerHTML = "个人应交总金额(元/人):"
												+ result.totaljinegeren;
										document.getElementById('shuigeren').innerHTML = "水费(元/人):"
												+ result.shuijinegeren;
										document.getElementById('diangeren').innerHTML = "电费(元/人):"
												+ result.dianjinegeren;
														document.getElementById('lasttimechaobiao').innerHTML = "上次查表时间:"
												+ result.shancishiijian;
															document.getElementById('zhecichaobiaoshijian').innerHTML = "这次查表时间:"
												+ result.zhecishiijian;
									}

								}
							});

		}
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
											style="padding-left: 25px;">水电卫生信息</td>
									</tr>
									<!--  -->

									<tr>
										<td height="470" align="center" valign="top" bgcolor="#F6F9FE">
											<table id="dg" class="easyui-datagrid">
											</table>
											<div id=shoudetail closed="true" class="easyui-dialog"
												align="justify">
												<form name="tuitionUseListForm" id=tuitionUseListForm
													method="post">
													<div align="center">
														<p class="font12">
														<table align="center" border="0" cellpadding="0"
															cellspacing="0" width="auto">
															<tbody>
																<tr>
																	<td height="32" valign="bottom" width="93%">
																		<div align="left">
																			<span class="title">水电费使用明细</span>
																		</div>
																	</td>
																	<td width="17%">
																		<div align="right">
																			<input name="cmdreturn" value=" 关 闭  "
																				onclick="$('#shoudetail').window('close');"
																				onmouseover="this.style.color='red'"
																				onmouseout="this.style.color='#1e7977'"
																				class="button" type="button">
																		</div>
																	</td>
																</tr>
															</tbody>
														</table>
														</p>
												
													
														<div align="right">(单位：水－吨，电－度)</div>
														<table align="center" border="1" cellpadding="0"
															cellspacing="0" width="640">
															<tbody>
																<tr>
																	<td class="font12" height="30" id=susemingchen></td>
																	<td class="font12" colspan="2" id=ruzhurenshu></td>
																</tr>
																<tr>
																	<td class="font12" height="30" id=lasttimechaobiao>
																	</td>
																	<td class="font12" id=shangcishuidushu></td>
																	<td class="font12" id=shangcidianbiaodushu></td>
																</tr>
																<tr>
																	<td class="font12" height="30" id=zhecichaobiaoshijian></td>
																	<td class="font12" id=zhecishuibiaodushu></td>
																	<td class="font12" id=zhecidianbiaodushu></td>
																</tr>
																<tr>
																	<td class="font12" height="30">&nbsp;</td>
																	<td class="font12" id=shuishiyong></td>
																	<td class="font12" id=dianshiyong></td>
																</tr>
															<!-- 	<tr>
																	<td class="font12" height="30">&nbsp;</td>
																	<td class="font12" id=shuidejiliang></td>
																	<td class="font12" id=diandejilian></td>
																</tr> -->
																<tr>
																	<td class="font12" height="30" id=shuidianheji></td>
																	<td class="font12" id=shuigeren></td>
																	<td class="font12" id=diangeren></td>
																</tr>

															</tbody>
														</table>

													</div>
												</form>
											</div>
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
