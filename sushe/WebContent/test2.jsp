<%@ page language="java" import="java.util.*,com.db.DBHelper,java.sql.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 5 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'ajax.jsp' starting page</title>
    
 <meta http-equiv="pragma" content="no-cache">
 <meta http-equiv="cache-control" content="no-cache">
 <meta http-equiv="expires" content="0">    
 <meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
 <meta http-equiv="description" content="This is my page">
 <!--
 <link rel="stylesheet" type="text/css" href="styles.css">
 -->

  </head>
  
  <body>
    This is my JSP page. <br>    
    
     <%
        String parentid = request.getParameter("id");
        //String parentid="2";
        //int id = Integer.parseInt(parentid);
        System.out.println(parentid+"");
        DBHelper cdb = new DBHelper();
  Connection con = cdb.getConn();
  Statement stmt = null;
  String sql = "select * from domitory where Domitory_BuildingID="+parentid;
  ResultSet rs = null;
  
  try {
       stmt = con.createStatement();
       rs = stmt.executeQuery(sql);
      System.out.println("++++++++++++++宿舍楼id++++++++++++"+parentid);
    
       int j = 0;
       while(rs.next()){
       j++;
           System.out.println("++++++++++++++宿舍id++++++++++++"+rs.getString(1));
        System.out.println("++++++++++++++宿舍id++++++++++++"+rs.getString(3));
            
     /*   response.getWriter().print(rs.getString(1)); */
          response.getWriter().print(rs.getString(3));
       response.getWriter().print(',');
       //response.getWriter().print(',');
       }
       response.getWriter().print(',');
       
  } catch (SQLException e) {
      System.out.println("-------AAAAAAAAAA------");
   e.printStackTrace();
  }finally{
      // response.getWriter().print("work");
       
   try {
    rs.close();
    stmt.close();
    con.close();
   } catch (SQLException e) {
    
    e.printStackTrace();
   }
   
  }
     %>
   </body>
</html>