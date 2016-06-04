package com.service.shebei;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class StartTimerListener implements ServletContextListener {
	TranslateTimer tranTimer = null;  
	  
    /** 
     * 创建一个初始化监听器对象，一般由容器调用 
     */  
    public StartTimerListener() {  
        super();  
    }  
  
    /** 
     * 让Web程序运行的时候自动加载Timer 
     */  
    public void contextInitialized(ServletContextEvent e) {  
        System.out.println("-------------StartTimerListener.init-------------");  
        tranTimer = new TranslateTimer(); // 启动翻译数据定时器  
        tranTimer.executeTranslateTimer();  
    }  
  
    /** 
     * 该方法由容器调用 空实现 
     */  
    public void contextDestroyed(ServletContextEvent e) {  
    }


}
