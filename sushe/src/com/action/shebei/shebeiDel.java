package com.action.shebei;

import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONObject;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;
import com.bean.*;
import com.dao.*;
import com.hibernate.model.Shebei;
import com.hibernate.model.ShebeiDAO;



public class shebeiDel extends ActionSupport {

	//下面是Action内用于封装用户请求参数的属性
	private String shebeiId;


	//处理用户请求的execute方法
	public String execute() throws Exception {
		
		//解决乱码，用于页面输出
		HttpServletResponse response=null;
		response=ServletActionContext.getResponse();
		response.setContentType("text/html;charset=UTF-8");
		response.setCharacterEncoding("UTF-8");
		PrintWriter out = response.getWriter();
		
		//创建session对象
		ShebeiDAO sbd=new ShebeiDAO();
		Shebei sb=null;
		HttpSession session = ServletActionContext.getRequest().getSession();
		//验证是否正常登录
		if(session.getAttribute("id")==null){
			out.print("<script language='javascript'>alert('请重新登录！');window.location='Login.jsp';</script>");
			out.flush();out.close();return null;
		}
		if(!isInvalid(shebeiId)){
			sb=sbd.findById(Integer.parseInt(shebeiId));
sbd.delete(sb);
}


JSONObject jobj = new JSONObject();// new一个JSON
jobj.accumulate("result", "success");//   返回数据
jobj.put("result","success");
response.getWriter().write(jobj.toString());// 转化为JSOn格式
response.getWriter().flush();  
response.getWriter().close(); 
		return null;
//		window.location='gonggaoManager.action';
	}
//	
	//判断是否空值
	private boolean isInvalid(String value) {
		return (value == null || value.length() == 0);
	}
	
	//测试
	public static void main(String[] args) {
		System.out.println();
	}
	public String getShebeiId() {
		return shebeiId;
	}
	public void setShebeiId(String shebeiId) {
		this.shebeiId = shebeiId;
	}


	
}
