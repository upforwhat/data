

var isUserSelf=false;
var flag=0;
var isLogin=false;
if(!isUserSelf) 
{
     
        if(document.getElementById('tag_bar_title')!=null && document.getElementById('tag_bar_left')!=null)
        {
         document.getElementById('tag_bar_title').innerHTML='联系主人';
         document.getElementById('tag_bar_left').innerHTML="<a href='http://message.hexun.com/Send.aspx?id=8490947' target='_blank' title='发送私信'>发送私信</a> | <a href='http://hexun.com/8490947' target='_blank'>进入Ta的个人门户</a> | <a href='http://t.hexun.com/8490947/default.html' target='_blank'>进入Ta的微博</a>";
        }
}

 if(document.getElementById("SelfTab_Login") !=null)
 {
     var login="";
     if(flag==1)
     {
        if (isLogin)
	    { 				
						
				login+="<a href='http://i.hexun.com/myhome.html' target=_blank>进入我的个人门户</a> | <a href='http://wenda.hexun.com/user/' target='_blank'>问达</a> | <a href='http://t.hexun.com/' target='_blank'>微博</a> | <a href='http://blog.hexun.com/' target='_blank'>和讯博客</a></span> | <span class='hexun_link'><a href='http://www.hexun.com/' target='_blank'>和讯首页</a> | <a  href='http://utility.tool.hexun.com/quit.aspx?gourl="+document.location.href+"'>退出</a>";
		}
		else
		{
				login+="<a href='javascript:GotoLogin()'>登录</a> | <a href='http://reg.hexun.com' target='_blank'>注册</a> | <a href='http://blog.hexun.com/' target='_blank'>和讯博客</a></span> | <span class='hexun_link'><a href='http://www.hexun.com/' target='_blank'>和讯首页</a>";
		}
     }
     else
     {
        if (isLogin)
	    { 				
						
			login+="<a href='http://i.hexun.com/myhome.html' target=_blank>进入我的个人门户</a> | <a href='http://wenda.hexun.com/user/' target='_blank'>问达</a> | <a href='http://t.hexun.com/' target='_blank'>微博</a> | <a href='http://blog.hexun.com/' target='_blank'>和讯博客</a></span> | <span class='hexun_link'><a href='http://www.hexun.com/' target='_blank'>和讯首页</a> | <a  href='http://utility.tool.hexun.com/quit.aspx?gourl="+document.location.href+"'>退出</a>";
		}
		else
		{
			login+="<a href='javascript:GotoLogin()'>登录</a> | <a href='http://reg.hexun.com' target='_blank'>注册</a> | <a href='http://blog.hexun.com/' target='_blank'>和讯博客</a></span> | <span class='hexun_link'><a href='http://www.hexun.com/' target='_blank'>和讯首页</a>";
		}
     }
     document.getElementById("SelfTab_Login").innerHTML=login;
 }

 function GotoLogin()
 {
    document.location.href='https://reg.hexun.com/login.aspx?gourl='+escape(window.document.location);
 }  
