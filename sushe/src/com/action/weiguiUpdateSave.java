package com.action;

import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONObject;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;
import com.bean.*;
import com.dao.*;


public class weiguiUpdateSave extends ActionSupport {

	//下面是Action内用于封装用户请求参数的属性
	private String weiguiid;
	private String Domitory_Name;
	private String Domitory_ID;
	private String shijian;
	private String yuanyin;
	private String chulimiaoshu;
	//处理用户请求的execute方法
	public String execute() throws Exception {
		
		//解决乱码，用于页面输出
		HttpServletResponse response=null;
		response=ServletActionContext.getResponse();
		response.setContentType("text/html;charset=UTF-8");
		response.setCharacterEncoding("UTF-8");
		PrintWriter out = response.getWriter();
		
		//创建session对象
		HttpSession session = ServletActionContext.getRequest().getSession();
		//验证是否正常登录
		if(session.getAttribute("id")==null){
			out.print("<script language='javascript'>alert('请重新登录！');window.location='Login.jsp';</script>");
			out.flush();out.close();return null;
		}
		
	
		weiguiBean cnbean=new weiguiBean();
		cnbean=new weiguiDao().GetBean(Integer.parseInt(weiguiid));
		cnbean.setDomitory_Name(Domitory_Name);
		cnbean.setChulimiaoshu(chulimiaoshu);
		cnbean.setYuanyin(yuanyin);
		cnbean.setShijian(shijian);
		cnbean.setDomitory_ID(Integer.parseInt(Domitory_ID));
		cnbean.setWeiguiid(Integer.parseInt(weiguiid));
		int rs=0;
		rs=new weiguiDao().Update(cnbean);
		JSONObject jsonObj=new JSONObject();
		 if(rs != 0){
		 jsonObj.put("result","success"); 
		 }else{ 
		 jsonObj.put("result","failed");
		 }
			response.getWriter().write(jsonObj.toString()); 
	return null;
		
	}
	
	//判断是否空值
	private boolean isInvalid(String value) {
		return (value == null || value.length() == 0);
	}
	
	//测试
	public static void main(String[] args) {
		System.out.println();
	}

	public String getWeiguiid() {
		return weiguiid;
	}

	public void setWeiguiid(String weiguiid) {
		this.weiguiid = weiguiid;
	}

	public String getDomitory_Name() {
		return Domitory_Name;
	}

	public void setDomitory_Name(String domitory_Name) {
		Domitory_Name = domitory_Name;
	}

	public String getDomitory_ID() {
		return Domitory_ID;
	}

	public void setDomitory_ID(String domitory_ID) {
		Domitory_ID = domitory_ID;
	}

	public String getShijian() {
		return shijian;
	}

	public void setShijian(String shijian) {
		this.shijian = shijian;
	}

	public String getYuanyin() {
		return yuanyin;
	}

	public void setYuanyin(String yuanyin) {
		this.yuanyin = yuanyin;
	}

	public String getChulimiaoshu() {
		return chulimiaoshu;
	}

	public void setChulimiaoshu(String chulimiaoshu) {
		this.chulimiaoshu = chulimiaoshu;
	}
	
}
