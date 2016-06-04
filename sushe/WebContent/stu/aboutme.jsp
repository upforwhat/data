
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
		url :'aboutme.action',
		fitColumns : true,
		autoRowHeight : true,
		border : false,
		singleSelect:true,
		idField:'student_ID',
		columns : [ [ 
	{
				field : 'student_ID',
				title : '学生id',
				hidden:true
			},{
				field : 'student_Username',
				title : "学号",
				width : 200,
				editor : 'text',
				sortable : false
			}, {
				field : 'student_Name',
				title : "姓名",
				type:'text',
				editor : 'text',
				width : 200,
				sortable : false
			}, {
				field : 'student_Sex',
				title : "性别",
				editor : 'text',
				width : 200,
				sortable : false
			}, {
				field : 'student_State',
				title : "住宿状态",
				editor : 'text', 
				width : 200,
				sortable : false,
				 
			}, 
			{
				field : 'building_Name',
				title : "所在宿舍楼",
				editor : 'text',
				width : 200,
				sortable : false
				
			},
			{
				field : 'domitory_Name',
				title : "宿舍名称",
				editor : 'text',
				width : 200,
				sortable : false
			},
			{
				field : 'student_Class',
				title : "班别",
				editor : 'text',
				width : 200,
				sortable : false
			}
			
			
		] ]
	});

});
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
											style="padding-left: 25px;">我的信息</td>
									</tr>
									<!--  -->

									<tr>
										<td height="470" align="center" valign="top" bgcolor="#F6F9FE">
											<table id="dg" title="我的信息" class="easyui-datagrid">
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
