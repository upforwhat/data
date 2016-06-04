<%@ page language="java" import="java.util.*,com.db.DBHelper,java.sql.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%@ taglib prefix="s" uri="/struts-tags"%>

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
</head>
<link href="Style/Style.css" rel="stylesheet" type="text/css" />
    <link rel="stylesheet" type="text/css"
	href="jqueryeasyui/themes/default/easyui.css">
<link rel="stylesheet" type="text/css"
	href="jqueryeasyui/themes/icon.css">

<script type="text/javascript" src="jqueryeasyui/jquery.min.js"></script>
<script type="text/javascript" src="jqueryeasyui/jquery.easyui.min.js"></script>

<script type="text/javascript" src="jqueryeasyui/locale/easyui-lang-zh_CN.js"></script>
<script language="JavaScript">

function mycheck(){
   if(isNull($('#BuildingName').combobox('getValue'))){  
   alert("请选择宿舍楼！"); 
   return false;
   }
   if(isNull($('#DomitoryName').combobox('getValue'))){
   alert("请输入选择宿舍！");
   return false;
   }
  
}

function isNull(str){
if ( str == "" ) return true;
var regu = "^[ ]+$";
var re = new RegExp(regu);
return re.test(str);
}

</script>
<script type="text/javascript">

	$(function() {
		// 下拉框选择控件，下拉框的内容是动态查询数据库信息  
		$('#BuildingName').combobox({
			url : 'GetBuilding.action',
			editable : false, //不可编辑状态  
			/* cache : false, */
			panelHeight : 'auto',
			valueField : 'building_ID',
			textField : 'building_Name',
			required:true,
   
			width : "80",

			onHidePanel : function() {
				$("#DomitoryName").combobox("setValue", '');
				var id = $('#BuildingName').combobox('getValue');
				//alert(id);  

				$.ajax({
					type : "POST",
					url : 'GetDomitory.action?buildingid=' + id,
					cache : true,
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
			  	required:true,
			width :80,
		});

	});
</script>
<body>
<center>
  <table width="900" border="0" cellspacing="0" cellpadding="0">
    <tr>
      <td height="60" bgcolor="#E6F5FF" style="color:#06F; font-size:19px; font-weight:bolder; padding-left:50px;">校园宿舍管理系统</td>
    </tr>
    <tr>
      <td height="30" background="Images/MenuBg.jpg">&nbsp;</td>
    </tr>
    <tr>
      <td height="500" align="center" valign="top"><table width="900" border="0" cellspacing="0" cellpadding="0">
        <tr>
          <td width="191" height="500" align="center" valign="top" background="Images/leftbg.jpg">
          <jsp:include  page="${path}/Left.jsp"/>
          </td>
          <td width="709" align="center" valign="top" bgcolor="#F6F9FE"><table width="709" border="0" cellspacing="0" cellpadding="0">
            <tr>
              <td height="30" background="Images/mainMenuBg.jpg" style="padding-left:25px;">添加违规</td>
            </tr>
            <tr>
              <td height="470" align="center" valign="top" bgcolor="#F6F9FE"><form name="form1" method="post"  onSubmit="return mycheck()" action="weiguiAddSave.action" >
                <table width="100%" border="0" cellspacing="0" cellpadding="0">
                  <tr>
                    <td width="33%" height="30" align="right">&nbsp;</td>
                    <td width="67%">&nbsp;<td></td>
                  </tr>
                  <tr>
                    <td height="30" align="right"><span style="color:red;">*</span>宿舍楼：</td>
                    <td>
        <input class=" easyui-validatebox easyui-combobox "  id="BuildingName"
																name="select1"  
>
 </td>
                  </tr>
                  <tr>
                    <td height="30" align="right"><span style="color:red;">*</span>宿舍名称：</td>
                    <td>   <input class=" easyui-validatebox  easyui-combobox "   id="DomitoryName" 
																name="select2"  
></td>
                  </tr>
                  <tr>
                    <td height="30" align="right"><span style="color:red;">*</span>时间：</td>
                    <td><input name="shijian" type="text" class="text2" id="shijian" onClick="WdatePicker()" class="easyui-validatebox" required="true" ></td>
                  </tr>
                  <tr>
                    <td height="30" align="right"><span style="color:red;">*</span>原因：</td>
                    <td><input name="yuanyin" type="text" class="text2" id="yuanyin"  class="easyui-validatebox" required="true" /></td>
                  </tr>
                 <%--  <tr>
                    <td height="30" align="right"><span style="color:red;">*</span>处理方式：</td>
                    <td><input name="chulimiaoshu" type="text" class="text2" id="Domitory_Tel"></td>
                  </tr> --%>
                  <tr>
                    <td height="30">&nbsp;</td>
                    <td><input type="submit"  name="button"  id="button"  value ="添加违规">
                      &nbsp;&nbsp;
                      <input type="button" name="button2" id="button2" value="返回上页" onClick="javascript:history.back(-1);"></td>
                  </tr>
                </table>
              </form></td>
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
