package com.service.shebei;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class TranslateTimer {
	int count=0;
	  private  ScheduledExecutorService scheduler = Executors  
	            .newScheduledThreadPool(1); 
	  
	  
	    public void executeTranslateTimer() {  
	    	
	        Runnable task = new Runnable() {  
	        
	            public void run() {  
	            	
	                // 翻译  
	            /*	updateshebeistate instance=updateshebeistate.getInstance();*/
	            
	            	SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
	                System.out.println("--------------系统时间：" + sdf.format(new Date())  
	                        + "------------------");  
	                count++;
	                System.out.println("运行了"+count+"次");
	               updateshebeistate upstate=new updateshebeistate();
	               upstate.updatestate();
	          
	            }  
	        };  
	        if (scheduler.isShutdown()) {  
	            scheduler = Executors.newScheduledThreadPool(1);  
	            scheduler.scheduleAtFixedRate(task, 0, 5, TimeUnit.DAYS);  
	           
				
	        } else {  
	            scheduler.scheduleAtFixedRate(task, 0, 5, TimeUnit.DAYS); // 延迟10秒，每隔30秒翻译一次  
	
	        } 
	        
	    }  
	  
	 //停止任务，不再提交新任务，已提交任务会继续执行以致完成  
	  public void stop() {  
	        scheduler.shutdown();  
	  }  
}
