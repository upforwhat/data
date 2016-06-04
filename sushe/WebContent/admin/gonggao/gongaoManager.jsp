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
         <script language="javascript" type="text/javascript"
src="<%=request.getContextPath()%>/My97DatePicker/WdatePicker.js">	
</script>
</head>
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
          <jsp:include page ="${path}/Left.jsp"/>
          </td>
          <td width="709" align="center" valign="top" bgcolor="#F6F9FE"><table width="709" border="0" cellspacing="0" cellpadding="0">
            <tr>
              <td height="30" background="Images/mainMenuBg.jpg" style="padding-left:25px;">  公告信息列表</td>
            </tr>
            <tr>
              <td height="470" align="center" valign="top" bgcolor="#F6F9FE"><form name="form1" method="post" action="gonggaoManager.action">
                <table width="100%%" border="0" cellspacing="0" cellpadding="0" style="width: 614px; ">
                  <tr>
                    <td width="22%" height="30" style="padding-left:20px; width: 152px"> 功能导航： <a href="<%=path%>/admin/gonggao/gonggaoAdd.jsp">添加公告</a></td>
                   <!--  <td width="39%" style="width: 418px; ">按时间查询：<input id="d11"  type="text"  name="searchtime" onClick="WdatePicker()"/>
                      </td> -->
                      
                   
                      <!-- <td width="6%"><input type="submit" name="button" id="button" value="点击查询"/></td> -->
                      </tr>
                  
                </table>
              </form>
            
                <table width="100%" border="0" cellspacing="0" cellpadding="0">
                  <tr>
                  <td width="3%" style="display:none;">编号</td>
         <td bgcolor="#D5E4F4" align="center"><strong>内容 </strong></td>
                  
                    <td bgcolor="#D5E4F4"><strong>时间</strong></td>
                      <td bgcolor="#D5E4F4"><strong>是否显示</strong></td>
                  
                    <td bgcolor="#D5E4F4"><strong>操作</strong></td>
                  </tr>
                  <s:iterator id="adaa" value="gongaolist">
                  <tr>
                   <td  width="3%" style="display:none;" ><input id="gonggaoId" type="text" name="gonggaoId" value="${gonggaoId}"/></td>
              
       <td>${gonggaoConten}</td>
       <td>${gonggaoTime}</td>
         <td>${gonggaoShow}
   
      
      
         
  
      
         </td>
               
                      <td align="center"><a href="gonggaoUpdate.action?gonggaoId=${gonggaoId}">修改</a><a href="gonggaoDel.action?gonggaoId=${gonggaoId}" onClick="return confirm('确定要删除该宿舍吗？')">删除</a></td>
                    </tr>
                  </s:iterator>
                </table></td>
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
