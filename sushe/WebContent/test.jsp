<%@ page language="java" import="java.util.*,com.db.DBHelper,java.sql.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 5 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title></title>
    
 <meta http-equiv="pragma" content="no-cache">
 <meta http-equiv="cache-control" content="no-cache">
 <meta http-equiv="expires" content="0">    
 <meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
 <meta http-equiv="description" content="This is my page">

   <script type="text/javascript" language="javascript">
   
      
      var cache=[];
        function getLevel2(){
          // if(document.forms.form1.select1.selectedIndex==0){
          //        document.forms.form1.select2.length=1;
          //        return ;
          // }
           
           
           //if( !cache[document.forms.form1.selectedIndex]){
                        var xmlhttp;
                        try{
                                  xmlhttp=new ActiveXObject('Msxml2.XMLHTTP');
                     
                         }catch(e){
                                    try{
                                            xmlhttp = new ActiveXObject('Microsoft.XMLHTTP');
                                    }catch(e){
                                                    try{
                                                             xmlhttp= new XMLHttpRequest();
                                                     }catch(e){ }
                                    }
                        }
             
                         //alert("document.form1.select1.value="+(document.forms.form1.select1.selectedIndex-1));
                         
                         
                         xmlhttp.onreadystatechange = function(){
                                  if(xmlhttp.readystate==4){
                                              if(xmlhttp.status==200){                                               
                                                      var tt =xmlhttp.responseText;                                                 
                                                      var ttlength= tt.indexOf(',,');
                                                      var strtt = tt.slice(0,ttlength);//截取字符串
                                                      var stArray = strtt.split(',');//把它们放入数组
                                                   var sltCity = document.forms["form1"].elements["select2"];   
                                                     for(var i=0;i<stArray.length;i++){
                                                          sltCity[i+1]= new Option(stArray[i],stArray[i]);
                                                     }
                                                      
                                                      
                                              }else{
                                                      //alert(xmlhttp.status+"=xmlhttp.status-");
                                              }
                                 }else{
                                
                                    //     if(xmlhttp.readystate==0){
                                    //         alert("对象已经建立，没有初始化．．．");
                                   //      }
                                   //      if(xmlhttp.readystate==1){
                                  //           alert("open 的方法已经调用　但尚未调用send 方法");
                                   //      }
                                  //       if(xmlhttp.readystate==2){
                                  //            alert("send 的方法已经调用其他的未知");
                                 //        }                                         
                                  //       if(xmlhttp.readystate==3){
                                  //           alert("请求发送成功");
                                 //        }
                                 //        if(xmlhttp.readystate==4){
                                //             alert("数据接受成功");
                                //         }
                                       
                               //      alert(xmlhttp.readystate+"xmlhttp.readystate");
                                 } 
                       }
                       
                       //这个最好放在后面，必须加return 
                       xmlhttp.open("get","test2.jsp?id="+(document.forms.form1.select1.selectedIndex)+"&timed="+new Date() );
                 /*   "&timed="+new Date() */
                       xmlhttp.setRequestHeader('Content-type','application/x-www-form-urlencoded');
                       xmlhttp.send(null);
                       return ;
           
          // }           
        }
   </script>
  </head>
  
  <body>
    This is my JSP page <br>
    <form name="form1" >
     <select name="select1" onchange="getLevel2()">
        <option value="">请选择宿舍楼</option>
    <%
      DBHelper cdb = new DBHelper();
  Connection con = cdb.getConn();
  Statement stmt = null;
  String sql = "select * from building ";
  //String parentid = "2";
  //String sql = "select * from category where parentid="+parentid;
  ResultSet rs = null;
  
  try {
       stmt = con.createStatement();
       rs = stmt.executeQuery(sql);
       
       while(rs.next()){
          System.out.println("test++++++++++++++++++"); 
       System.out.println(rs.getInt(1));
       rs.getInt(1);
       %>
       <option value="<%=rs.getInt(1) %>"><%=rs.getString(2)%> </option>
       
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
      </select>
      <select name="select2" >         
         <option value="0" >请选择宿舍房间</option>
      </select>
      </form>
  </body>
</html>