package com.shiro.lh.demo;
 
import java.io.File;
 
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.config.IniSecurityManagerFactory;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.Factory;
import org.apache.shiro.util.ThreadContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
 
public class ShiroDemo
{
    //private static final Logger logger = LoggerFactory.getLogger(ShiroDemo.class);
 
    public static void main(String[] args)
    {
        // 初始化 SecurityManager
        Factory<SecurityManager> factory = new IniSecurityManagerFactory("classpath:shiro.ini");      
         
        SecurityManager securityManager = factory.getInstance();
        SecurityUtils.setSecurityManager(securityManager);
        // Now that a simple Shiro environment is set up, let's see what you can do:
 
        // get the currently executing user:
         
        Subject currentUser = SecurityUtils.getSubject();
        // Do some stuff with a Session (no need for a web or EJB container!!!)
        Session session = currentUser.getSession();
        session.setAttribute("userInfo", "瑞恩");
        String value = (String) session.getAttribute("userInfo");
        if (value.equals("rayn")) {
            ShiroDemo.info("Retrieved the correct value! [" + value + "]");
        }
 
        // let's login the current user so we can check against roles and permissions:
        UsernamePasswordToken token = null;
        if (!currentUser.isAuthenticated())
        {
            token = new UsernamePasswordToken("liu", "1231231", false);
            try {
                currentUser.login(token);
            } catch (UnknownAccountException uae) {
                ShiroDemo.info("不存在用户名为：" + token.getPrincipal());
            } catch (IncorrectCredentialsException ice) {
                ShiroDemo.info("用户：[" + token.getPrincipal() + "] 的密码错误");
            } catch (LockedAccountException lae) {
                ShiroDemo.info("该用户已经锁定 [" + token.getPrincipal() + "] 请联系管理员，进行解锁.");
            }
            // ... catch more exceptions here (maybe custom ones specific to
            // your application?
            catch (AuthenticationException ae) {
                // unexpected condition? error?
                ae.printStackTrace();
            }
        }
 
        // say who they are:
        // print their identifying principal (in this case, a username):
        ShiroDemo.info("用户 [" + currentUser.getPrincipal() + "] 登录成功！");
 
        currentUser = SecurityUtils.getSubject();
        // test a role:
        if (currentUser.hasRole("admin"))
        {
            ShiroDemo.info("该用户为admin.");
        }
        else
        {
            ShiroDemo.info("没有角色。");
        }
 
        // test a typed permission (not instance-level)
        if (currentUser.isPermitted("users:del"))
        {
            ShiroDemo.info("拥有：users:del");
        }
        else
        {
            ShiroDemo.info("对不起，你没有权限操作该模块！");
        }
 
        // a (very powerful) Instance Level permission:
        if (currentUser.isPermitted("users:create:del:upd"))
        {
            ShiroDemo.info("You are permitted to 'drive' the winnebago with license plate (id) 'eagle5'.  " + "Here are the keys - have fun!");
        }
        else
        {
            ShiroDemo.info("Sorry, you aren't allowed to drive the 'eagle5' winnebago!");
        }
 
        // all done - log out!
        currentUser.logout();
         
        ThreadContext.unbindSubject();
 
        System.exit(0);
    }
     
    public static void info(String s){
        System.out.println(s);
    }
}



机构名称 
   上级机构    
   机构编号    
   机构类型   
    机构级别    
    机构地址    
    公司邮编    
    机构负责人   
    机构电话    
    机构传真    
    机构邮箱    
    备注