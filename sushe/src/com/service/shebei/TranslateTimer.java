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
	            	
	                // ����  
	            /*	updateshebeistate instance=updateshebeistate.getInstance();*/
	            
	            	SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
	                System.out.println("--------------ϵͳʱ�䣺" + sdf.format(new Date())  
	                        + "------------------");  
	                count++;
	                System.out.println("������"+count+"��");
	               updateshebeistate upstate=new updateshebeistate();
	               upstate.updatestate();
	          
	            }  
	        };  
	        if (scheduler.isShutdown()) {  
	            scheduler = Executors.newScheduledThreadPool(1);  
	            scheduler.scheduleAtFixedRate(task, 0, 5, TimeUnit.DAYS);  
	           
				
	        } else {  
	            scheduler.scheduleAtFixedRate(task, 0, 5, TimeUnit.DAYS); // �ӳ�10�룬ÿ��30�뷭��һ��  
	
	        } 
	        
	    }  
	  
	 //ֹͣ���񣬲����ύ���������ύ��������ִ���������  
	  public void stop() {  
	        scheduler.shutdown();  
	  }  
}
