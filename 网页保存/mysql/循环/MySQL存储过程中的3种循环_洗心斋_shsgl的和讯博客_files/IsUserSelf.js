

var isUserSelf=false;
var flag=0;
var isLogin=false;
if(!isUserSelf) 
{
     
        if(document.getElementById('tag_bar_title')!=null && document.getElementById('tag_bar_left')!=null)
        {
         document.getElementById('tag_bar_title').innerHTML='��ϵ����';
         document.getElementById('tag_bar_left').innerHTML="<a href='http://message.hexun.com/Send.aspx?id=8490947' target='_blank' title='����˽��'>����˽��</a> | <a href='http://hexun.com/8490947' target='_blank'>����Ta�ĸ����Ż�</a> | <a href='http://t.hexun.com/8490947/default.html' target='_blank'>����Ta��΢��</a>";
        }
}

 if(document.getElementById("SelfTab_Login") !=null)
 {
     var login="";
     if(flag==1)
     {
        if (isLogin)
	    { 				
						
				login+="<a href='http://i.hexun.com/myhome.html' target=_blank>�����ҵĸ����Ż�</a> | <a href='http://wenda.hexun.com/user/' target='_blank'>�ʴ�</a> | <a href='http://t.hexun.com/' target='_blank'>΢��</a> | <a href='http://blog.hexun.com/' target='_blank'>��Ѷ����</a></span> | <span class='hexun_link'><a href='http://www.hexun.com/' target='_blank'>��Ѷ��ҳ</a> | <a  href='http://utility.tool.hexun.com/quit.aspx?gourl="+document.location.href+"'>�˳�</a>";
		}
		else
		{
				login+="<a href='javascript:GotoLogin()'>��¼</a> | <a href='http://reg.hexun.com' target='_blank'>ע��</a> | <a href='http://blog.hexun.com/' target='_blank'>��Ѷ����</a></span> | <span class='hexun_link'><a href='http://www.hexun.com/' target='_blank'>��Ѷ��ҳ</a>";
		}
     }
     else
     {
        if (isLogin)
	    { 				
						
			login+="<a href='http://i.hexun.com/myhome.html' target=_blank>�����ҵĸ����Ż�</a> | <a href='http://wenda.hexun.com/user/' target='_blank'>�ʴ�</a> | <a href='http://t.hexun.com/' target='_blank'>΢��</a> | <a href='http://blog.hexun.com/' target='_blank'>��Ѷ����</a></span> | <span class='hexun_link'><a href='http://www.hexun.com/' target='_blank'>��Ѷ��ҳ</a> | <a  href='http://utility.tool.hexun.com/quit.aspx?gourl="+document.location.href+"'>�˳�</a>";
		}
		else
		{
			login+="<a href='javascript:GotoLogin()'>��¼</a> | <a href='http://reg.hexun.com' target='_blank'>ע��</a> | <a href='http://blog.hexun.com/' target='_blank'>��Ѷ����</a></span> | <span class='hexun_link'><a href='http://www.hexun.com/' target='_blank'>��Ѷ��ҳ</a>";
		}
     }
     document.getElementById("SelfTab_Login").innerHTML=login;
 }

 function GotoLogin()
 {
    document.location.href='https://reg.hexun.com/login.aspx?gourl='+escape(window.document.location);
 }  
