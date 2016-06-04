package com.action.weishengshuidianAction;

import java.io.PrintWriter;
import java.math.BigDecimal;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;
import com.bean.*;
import com.dao.*;
import com.hibernate.model.Feilv;
import com.hibernate.model.FeilvDAO;
import com.hibernate.model.Weishengshuidian;
import com.hibernate.model.WeishengshuidianDAO;


public class shuidianFeilvUpdateSave extends ActionSupport {

	//下面是Action内用于封装用户请求参数的属性
	private String shui;
	private String dian;
	private Feilv fl;	
	private String msg=null;

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
		FeilvDAO fld=new FeilvDAO();
		fl=fld.findById(1);
		BigDecimal shuidushu=new BigDecimal(shui); 
		BigDecimal diandushu=new BigDecimal(dian); 
		fl.setDian(diandushu);
		fl.setShui(shuidushu);
		fld.save(fl);
		msg="修改成功";
		
/*		//跳转
		out.print("<script language='javascript'>alert('修改成功！');window.location='weishengshuidianManager.action';</script>");
		out.flush();out.close();return null;
		*/
		return "success";
	}
	
	//判断是否空值
	private boolean isInvalid(String value) {
		return (value == null || value.length() == 0);
	}
	
	//测试
	public static void main(String[] args) {
		System.out.println();
	}

	public String getShui() {
		return shui;
	}

	public void setShui(String shui) {
		this.shui = shui;
	}

	public String getDian() {
		return dian;
	}

	public void setDian(String dian) {
		this.dian = dian;
	}

	public Feilv getFl() {
		return fl;
	}

	public void setFl(Feilv fl) {
		this.fl = fl;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}


	
}
