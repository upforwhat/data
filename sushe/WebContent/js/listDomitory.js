var xmlHttp;
function showCustomer(str) 
{ 
 xmlHttp=GetXmlHttpObject(); 
if (xmlHttp==null) 
 { 
 alert ("您的浏览器不支持AJAX！"); 
 return; 
 } 
var url="getcustomer.asp"; 
url=url+"?q="+str; 
url=url+"&sid="+Math.random(); 
xmlHttp.onreadystatechange=stateChanged; 
xmlHttp.open("GET",url,true); xmlHttp.send(null); 
} 
function stateChanged() 
{ 
   if (xmlHttp.readyState==4) 
  { 
        document.getElementByIdx_x_x("txtHint").innerHTML=xmlHttp.responseText; 
  } 
} 
  function GetXmlHttpObject() 
{ 
    var xmlHttp=null; 
     try { // Firefox, Opera 8.0+, Safari 
      xmlHttp=new XMLHttpRequest(); 
    } catch (e) { // Internet Explorer 
     try { 
                xmlHttp=new ActiveXObject("Msxml2.XMLHTTP"); 
      } catch (e) 
      { 
               xmlHttp=new ActiveXObject("Microsoft.XMLHTTP"); 
        } 
     } return xmlHttp; 
}