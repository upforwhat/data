<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<link href="Style/Style.css" rel="stylesheet" type="text/css" />
<%-- <link rel="stylesheet" type="text/css"
	href="jqueryeasyui/themes/default/easyui.css">
<link rel="stylesheet" type="text/css"
	href="jqueryeasyui/themes/icon.css">

<script type="text/javascript" src="jqueryeasyui/jquery.min.js"></script>
<script type="text/javascript" src="jqueryeasyui/jquery.easyui.min.js"></script>
<div class="easyui-panel" style="padding:5px">

	<span><strong>系统选项</strong></span>
	<ul class="easyui-tree">

		<%
			if (session.getAttribute("type").toString().equals("1")) {
		%>
		<li data-options="state:'closed'">
		<span>角色模块</span>
			<ul>
				<li><span><a href="TeacherManager.action">管理员管理</a></span></li>
				<li><span><a href="StudentManagerview.action">学生录入</a></span></li>
			</ul>
		</li>
		<li data-options="state:'closed'">
		<span>宿舍楼模块</span>
		<ul>
		<li><a href="BuildingManager.action">宿舍楼管理</a></li>
		<li><a href="DomitoryManagerview.action">宿舍房间管理</a></li>

		<li><a href="shebeiManagerview.action">设备管理</a></li>
		</ul>
        </li>
        <li data-options="state:'closed'">
		<span>学生模块</span>
		<ul>
				<li><span><a href="baoxiuManagerview.action">报修管理</a></span></li>
			<li><span><a href="weiguiManagerview.action">违规管理</a></span></li>
		<li><span><a href="StudentRZ.action">学生入住登记</a></span></li>
				<li><span><a href="StudentTH.jsp">学生寝室调换</a></span></li>
				<li><span><a href="StudentQC.jsp">学生迁出登记</a></span></li>
				<li><span><a href="OutList.action">迁出记录</a></span></li>
				</ul>
        </li>
        <li><a href="WeishengshuidianManagerview">卫生水电管理</a></li>
     <li><a href="gonggaoManager.action">公告管理</a></li>
		<%}%>
		<%
			if (session.getAttribute("type").toString().equals("2")) {
		%>
		<li data-options="state:'closed'">
		<span>人员管理</span>
		<ul>
				<li><span><a href="TeacherManager.action">管理员管理</a></span></li>
				<li><span><a href="admin/student/StudentManager.jsp">学生管理</a></span></li>
				<a href="admin/student/StudentManager.jsp">学生管理</a>
				<a href="admin/weigui/weiguiManager.jsp">违规管理</a>
				<a href="seguan/Weishengshuidian/WeishengshuidianManager.jsp">卫生水电管理</a>
				<a href="admin/baoxiu/baoxiuManager.jsp">报修管理</a>
				<a href="admin/shebei/shebeiManager.jsp">设备管理</a>
				<a href="gonggaoManager.action">公告管理</a>
				<a href="StudentRZ.action">学生入住登记</a>
				<a href="StudentTH.jsp">学生寝室调换</a>
				<a href="StudentQC.jsp">学生迁出登记</a>
				<a href="OutList.action">迁出记录</a>
				
			</ul>
		</li>
			<%}%>
			<%
			if (session.getAttribute("type").toString().equals("3")) {
		%>
		<li data-options="state:'closed'">
		<span>人员管理</span>
		<ul>
				<li><span><a href="TeacherManager.action">管理员管理</a></span></li>
				<li><span><a href="admin/student/StudentManager.jsp">学生管理</a></span></li>
				<a href="stu/aboutme.jsp">我的信息</a>
				<a href="stu/shuidian/shuidianweisheng.jsp">水电卫生信息查看</a>
				<a href="stu/baoxiu/baoxiuStuManager.jsp">我的报修</a>
				<a href="stu/stuweigui.jsp">违规查询</a>
				<a href="baoxiuStuAdd.action">申请报修</a>
				
			</ul>
		</li>
			<%}%>
			<a href="PasswordUpdate.jsp">修改密码</a>
			<a href="Quit.action" onclick="return confirm('确定要退出系统吗？')">退出系统</a>
	</ul>
</div> --%>




<table width="155" border="0" cellspacing="0" cellpadding="0">
            <tr>
              <td height="31" align="center" background="Images/left1.jpg"><strong>系统选项</strong></td>
            </tr>
            <tr>
              <td height="50" align="center" valign="top"><table width="150" border="0" cellspacing="0" cellpadding="0">
                <tr>
                  <td height="5" align="center"><img src="Images/ic.gif" width="1" height="1"></td>
                </tr>
                <tr>
                  <td height="30" align="center" background="Images/left2.jpg" style="text-align:left; padding-left:40px;"><a href="Index.jsp">后台首页</a></td>
                </tr>
             <!--    <tr>
                  <td height="5" align="center"><img src="Images/ic.gif" width="1" height="1"></td>
                </tr> -->
                <%if(session.getAttribute("type").toString().equals("1")){%>


                <tr>
                  <td height="30" align="center" background="Images/left2.jpg" style="text-align:left; padding-left:40px;"><a href="TeacherManager.action">宿舍管理员管理</a></td>
                </tr>
            
                <tr>
                  <td height="30" align="center" background="Images/left2.jpg" style="text-align:left; padding-left:40px;"><a href="admin/student/StudentManager.jsp">学生管理</a></td>
                </tr>
             
                <tr>
                  <td height="30" align="center" background="Images/left2.jpg" style="text-align:left; padding-left:40px;"><a href="BuildingManager.action">宿舍楼管理</a></td>
                </tr>
            
                <tr>
                  <td height="30" align="center" background="Images/left2.jpg" style="text-align:left; padding-left:40px;"><a href="admin/domitory/DomitoryManager.jsp">宿舍房间管理</a></td>
                </tr>
                
                
                
              
                <tr>
                  <td height="30" align="center" background="Images/left2.jpg" style="text-align:left; padding-left:40px;"><a href="admin/weigui/weiguiManager.jsp">违规管理</a></td>
                </tr>
                
                
                <tr>
                  <td height="30" align="center" background="Images/left2.jpg" style="text-align:left; padding-left:40px;"><a href="admin/Weishengshuidian/WeishengshuidianManager.jsp">卫生水电管理</a></td>
                </tr>
                
             
                <tr>
                  <td height="30" align="center" background="Images/left2.jpg" style="text-align:left; padding-left:40px;"><a href="admin/baoxiu/baoxiuManager.jsp">报修管理</a></td>
                </tr>
             
                <tr>
                  <td height="30" align="center" background="Images/left2.jpg" style="text-align:left; padding-left:40px;"><a href="admin/shebei/shebeiManager.jsp">设备管理</a></td>
                </tr>
                
                
                
                
                <tr>
                  <td height="30" align="center" background="Images/left2.jpg" style="text-align:left; padding-left:40px;"><a href="gonggaoManager.action">公告管理</a></td>
                </tr>
                
                
                
                <tr>
                  <td height="30" align="center" background="Images/left2.jpg" style="text-align:left; padding-left:40px;"><a href="StudentRZ.action">学生入住登记</a></td>
                </tr>
             
                
                
                
                
                
                <tr>
                  <td height="30" align="center" background="Images/left2.jpg" style="text-align:left; padding-left:40px;"><a href="StudentTH.jsp">学生寝室调换</a></td>
                </tr>
            
                <tr>
                  <td height="30" align="center" background="Images/left2.jpg" style="text-align:left; padding-left:40px;"><a href="StudentQC.jsp">学生迁出登记</a></td>
                </tr>
              <!--   <tr>
                  <td height="5" align="center"><img src="Images/ic.gif" width="1" height="1"></td>
                </tr>
                <tr>
                  <td height="30" align="center" background="Images/left2.jpg" style="text-align:left; padding-left:40px;"><a href="AdminLog.action">学生缺寝记录</a></td>
                </tr> -->
              
                <tr>
                  <td height="30" align="center" background="Images/left2.jpg" style="text-align:left; padding-left:40px;"><a href="OutList.action">迁出记录</a></td>
                </tr>
           
                <%}%>
                <%if(session.getAttribute("type").toString().equals("2")){%>
                <tr>
                  <td height="30" align="center" background="Images/left2.jpg" style="text-align:left; padding-left:40px;"><a href="admin/student/StudentManager.jsp">学生管理</a></td>
                </tr>
              
                <tr>
                  <td height="30" align="center" background="Images/left2.jpg" style="text-align:left; padding-left:40px;"><a href="admin/weigui/weiguiManager.jsp">违规管理</a></td>
                </tr>
                
                
                <tr>
                  <td height="30" align="center" background="Images/left2.jpg" style="text-align:left; padding-left:40px;"><a href="seguan/Weishengshuidian/WeishengshuidianManager.jsp">卫生水电管理</a></td>
                </tr>
                
                  
                <tr>
                  <td height="30" align="center" background="Images/left2.jpg" style="text-align:left; padding-left:40px;"><a href="admin/baoxiu/baoxiuManager.jsp">报修管理</a></td>
                </tr>
                   
                <tr>
                  <td height="30" align="center" background="Images/left2.jpg" style="text-align:left; padding-left:40px;"><a href="admin/shebei/shebeiManager.jsp">设备管理</a></td>
                </tr>
                
                
                
                     
                <tr>
                  <td height="30" align="center" background="Images/left2.jpg" style="text-align:left; padding-left:40px;"><a href="gonggaoManager.action">公告管理</a></td>
                </tr>
                
                
                
                <tr>
                  <td height="30" align="center" background="Images/left2.jpg" style="text-align:left; padding-left:40px;"><a href="StudentRZ.action">学生入住登记</a></td>
                </tr>
               
                
                
                
                
                
                <tr>
                  <td height="30" align="center" background="Images/left2.jpg" style="text-align:left; padding-left:40px;"><a href="StudentTH.jsp">学生寝室调换</a></td>
                </tr>
               
                <tr>
                  <td height="30" align="center" background="Images/left2.jpg" style="text-align:left; padding-left:40px;"><a href="StudentQC.jsp">学生迁出登记</a></td>
                </tr>
              <!--   <tr>
                  <td height="5" align="center"><img src="Images/ic.gif" width="1" height="1"></td>
                </tr>
                <tr>
                  <td height="30" align="center" background="Images/left2.jpg" style="text-align:left; padding-left:40px;"><a href="AdminLog.action">学生缺寝记录</a></td>
                </tr> -->
                
                <tr>
                  <td height="30" align="center" background="Images/left2.jpg" style="text-align:left; padding-left:40px;"><a href="OutList.action">迁出记录</a></td>
                </tr>
               
                <%}%>
                <%if(session.getAttribute("type").toString().equals("3")){%>
               <!--  <tr>
                  <td height="30" align="center" background="Images/left2.jpg" style="text-align:left; padding-left:40px;"><a href="StudentLog.action">我的缺寝记录</a></td>
                </tr>
                <tr>
                  <td height="5" align="center"><img src="Images/ic.gif" width="1" height="1"></td>
                </tr> -->
                 <tr>
                  <td height="30" align="center" background="Images/left2.jpg" style="text-align:left; padding-left:40px;"><a href="stu/aboutme.jsp">我的信息</a></td>
                </tr>
              
                 <tr>
                  <td height="30" align="center" background="Images/left2.jpg" style="text-align:left; padding-left:40px;"><a href="stu/shuidian/shuidianweisheng.jsp">水电卫生信息查看</a></td>
                </tr>
               
                 <tr>
                  <td height="30" align="center" background="Images/left2.jpg" style="text-align:left; padding-left:40px;"><a href="stu/baoxiu/baoxiuStuManager.jsp">我的报修</a></td>
                </tr>
                
                   <tr>
                  <td height="30" align="center" background="Images/left2.jpg" style="text-align:left; padding-left:40px;"><a href="stu/stuweigui.jsp">违规查询</a></td>
                </tr>
               
                   <tr>
                  <td height="30" align="center" background="Images/left2.jpg" style="text-align:left; padding-left:40px;"><a href="baoxiuStuAdd.action">申请报修</a></td>
                </tr>
               
                <%}%>
                <tr>
                  <td height="30" align="center" background="Images/left2.jpg" style="text-align:left; padding-left:40px;"><a href="PasswordUpdate.jsp">修改密码</a></td>
                </tr>
              
                <tr>
                  <td height="30" align="center" background="Images/left2.jpg" style="text-align:left; padding-left:40px;"><a href="Quit.action" onclick="return confirm('确定要退出系统吗？')">退出系统</a></td>
                </tr>
              </table>
              </td>
            </tr>
          </table>