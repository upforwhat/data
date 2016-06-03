<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
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
</head>
<link rel="stylesheet" type="text/css"
	href="jqueryeasyui/themes/default/easyui.css">
<link rel="stylesheet" type="text/css"
	href="jqueryeasyui/themes/icon.css">

<script type="text/javascript" src="jqueryeasyui/jquery.min.js"></script>
<script type="text/javascript" src="jqueryeasyui/jquery.easyui.min.js"></script>

<script type="text/javascript"
	src="jqueryeasyui/locale/easyui-lang-zh_CN.js"></script>
<script language="JavaScript">


function mycheck(){
  /*  if(isNull(form1.Domitory_BuildingID.value)){  
   alert("请选择楼宇！"); 
   return false;
   } */
   if(isNull(form1.shui.value)){
   alert("请输入水单价！");
   return false;
   }
   if(isNull(form1.dian.value)){
   alert("请输入电单价！");
   return false;
   }
   if(isNull(form1.Domitory_Number.value)){
   alert("请输入人数！");
   return false;
   }
   if(isNull(form1.Domitory_Tel.value)){
   alert("请输入电话！");
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
          <jsp:include page="${path}/Left.jsp"/>
          </td>
          <td width="709" align="center" valign="top" bgcolor="#F6F9FE"><table width="709" border="0" cellspacing="0" cellpadding="0">
            <tr>
              <td height="30" background="Images/mainMenuBg.jpg" style="padding-left:25px;">修改水电单价信息</td>
            </tr>
            <tr>
              <td height="470" align="center" valign="top" bgcolor="#F6F9FE"><form name="form1" method="post" action="shuidianFeilvUpdateSave.action" onSubmit="return mycheck()" >
                <table width="100%" border="0" cellspacing="0" cellpadding="0">
                       <%if(request.getAttribute("msg")!=null){%> <tr>
                    <td width="100%" height="30" align="center">  
                    <%=request.getAttribute("msg")%>
                    </td>
                   
                  </tr><%}%>
                     <tr>
                    <td height="30" align="left">当前的水电单价为</td>
                   
                  </tr>
                  <tr >
                
                    <td ><input name="feilvId" type="text" style="display: none;" id="feilvId" value="${fl.feilvId}"></td>
                  </tr>
                  
           
                  <tr>
                    <td height="30" align="right"><span style="color:red;">*</span>水单价：</td>
                    <td style="width: 151px; "><input name="shui" type="text" class=" easyui-numberbox "   data-options="min:0.1,max:100,precision:2"  id="shui" value="<s:property value='fl.shui'/>"></td>
                  <td height="30" align="left"><input name="shuidanwei" type="text" disabled="true" class="text2" id="shuidanwei" value="<s:property value='fl.shuidanwei'/>" style="height: 30px; width: 59px; "></td>
                  </tr>
                  <tr>
                    <td height="30" align="right"><span style="color:red;">*</span>电单价：</td>
                    <td><input name="dian" type="text" class=" easyui-numberbox "   data-options="min:0.1,max:100,precision:2" id="dian" value="<s:property value='fl.dian'/>"></td>
                   <td height="30" align="left"><input name="diandanwei" type="text" class="text2"  disabled="true" id="diandanwei" value="<s:property value='fl.diandanwei'/>" style="height: 28px; width: 62px; "></td></tr>
             
                  <tr>
                    <td height="30">&nbsp;</td>
                    <td><input type="submit" name="button" id="button" value="修改">
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
