<%@ page language="java" import="java.util.*,com.db.DBHelper,java.sql.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
</script>
<marquee direction="left" height="10px" width="800px" id=m onmouseout=m.start() onMouseOver=m.stop() scrollamount=3 align="center">
<font size=2>
    <%
      DBHelper cdb = new DBHelper();
  Connection con = cdb.getConn();
  Statement stmt = null;
  String sql = "select * from gongao where gonggao_show='是'";
  //String parentid = "2";
  //String sql = "select * from category where parentid="+parentid;
  ResultSet rs = null;
  String gonggao="系统公告：";
  
  try {
       stmt = con.createStatement();
       rs = stmt.executeQuery(sql);
       
       while(rs.next()){
          System.out.println("gonggaotest++++++++++++++++++"); 
       System.out.println(rs.getString(2));
           System.out.println(rs.getString(3));
           gonggao+=rs.getString(2)+rs.getString(3);
           gonggao+="\t\t";
       %>
       
      <% }
       
       
  } catch (SQLException e) {
   // TODO Auto-generated catch block
   e.printStackTrace();
  }finally{
   try {
    rs.close();
    stmt.close();
    con.close();
   } catch (SQLException e) {
    // TODO Auto-generated catch block
    e.printStackTrace();
   }
   
  }
     %>
<p><%= gonggao%></p>
</font></marquee>


