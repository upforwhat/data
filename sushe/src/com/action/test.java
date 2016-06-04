package com.action;

import javax.servlet.http.HttpSession;
import org.apache.struts2.ServletActionContext;
import com.dao.*;
import com.bean.*;

import com.opensymphony.xwork2.ActionSupport;

public class test extends ActionSupport {


	//处理用户请求的execute方法
	public String execute() throws Exception {
		
		return "success";
}
}