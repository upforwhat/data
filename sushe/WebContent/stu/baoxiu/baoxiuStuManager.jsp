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
<script type="text/javascript">
var $obj;
$(function() {
	$obj = $("#dg");
	$obj.datagrid({
		loadMsg : '数据加载中请稍后……',
		url :'baoxiuStuManager.action',
		//url : root + 'js/app/sysManagement/sysConfig.json',
		fitColumns : true,
		autoRowHeight : true,
		pagination : true,
		pagePosition : 'bottom',
		
		toolbar: '#toolba',
		
	
		border : false,
		singleSelect:true,
		idField:'baoxiuid',
		columns : [ [ 
	{
				field : 'baoxiuid',
				title : '设备id',
				hidden:true
			},{
				field : 'baoxiuBuildingName',
				title : "所在宿舍楼",
				width : 200,
				editor : 'text',
				sortable : false
			}, {
				field : 'baoxiuDomitoryName',
				title : "宿舍名称",
				type:'text',
				editor : 'text',
				width : 200,
				sortable : false
			}, {
				field : 'baoxiuStudentName',
				title : "申请人",
				editor : 'text',
				width : 200,
				sortable : false
			},
			 {
				field : 'baoxiuXianmu',
				title : "报修原因",
				editor : 'text',
				width : 200,
				sortable : false
			},
			 {
				field : 'baoxiushijian',
				title : "报修时间",
				editor : 'datebox', 
				width : 200,
				sortable : true,
				 
			}, 
			{
				field : 'state',
				title : "状态",
				editor : 'text',
				width : 200,
				sortable : false
				
			}
			
			
			
		 ] ],
		
	});

});

function searchData() {

		$('#dg').datagrid('load', {
			 shoulistate : $('#selectstate').val(),
		});
		
		
	}

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
											style="padding-left:25px;">报修信息列表</td>
									</tr>
									<tr><td> </td></tr>
									<tr>
										<td height="470" align="center" valign="top" bgcolor="#F6F9FE">
										
										
										<form
												name="form1" method="post" action="">
												<table border="0" cellspacing="0"
													cellpadding="0" style="width: 614px; ">
													<tr>
														<!--  <td width="22%" height="30" style="padding-left:20px; width: 152px"></td> -->
														<td width="39%" style="width: 418px; ">受理状态： <select
															name="shoulistate" id=selectstate>
																<option value="" selected>请选择</option>
																<option value="已处理">已处理</option>
																<option value="未处理">未处理</option>
														</select> 
														<a href="javascript:searchData()" class="easyui-linkbutton" iconCls="icon-search" plain="true">搜索</a>
														</td>
													</tr>

												</table>
											</form>
											<table id="dg" class="easyui-datagrid" title="我的报修信息">
											</table>

											<%-- <table width="100%" border="0" cellspacing="0"
												cellpadding="0">
												<tr>

													<td bgcolor="#D5E4F4" align="center"><strong>宿舍楼</strong></td>
													<td bgcolor="#D5E4F4" align="center"><strong>宿舍名称</strong></td>
													<td bgcolor="#D5E4F4" align="center"><strong>申报人</strong></td>
													<td bgcolor="#D5E4F4" align="center"><strong>报修原因</strong></td>
													<td bgcolor="#D5E4F4" align="center"><strong>申报时间</strong></td>

													<td bgcolor="#D5E4F4"><strong>状态</strong></td>



												</tr>
												<s:iterator id="adaa" value="stubaoxiulist">
													<tr>


														<td align="center">${baoxiuBuildingName}</td>
														<td align="center">${baoxiuDomitoryName}</td>
														<td align="center">${baoxiuStudentName}</td>
														<td align="center">${baoxiuXianmu}</td>
														<td align="center">${baoxiushijian}</td>
														<td>${state}</td>


													</tr>
												</s:iterator>
											</table> --%></td>
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
