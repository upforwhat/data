package com.action.gonggaoAction;

import java.io.PrintWriter;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.text.DecimalFormat;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;
import org.hibernate.Session;

import com.opensymphony.xwork2.ActionSupport;
import com.bean.*;
import com.dao.*;
import com.hibernate.model.BaseHibernateDAO;
import com.hibernate.model.Feilv;
import com.hibernate.model.FeilvDAO;
import com.hibernate.model.Gongao;
import com.hibernate.model.GongaoDAO;
import com.hibernate.model.Weishengshuidian;
import com.hibernate.model.WeishengshuidianDAO;


public class gonggaoAdd extends ActionSupport {

	//下面是Action内用于封装用户请求参数的属性
	private Timestamp gonggaoTime;
	private String gonggaoConten;
	private String show;
	//处理用户请求的execute方法
	public String execute() throws Exception {
		Session sess=new BaseHibernateDAO().getSession();
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
		Gongao gg=new Gongao();
		GongaoDAO ggd=new GongaoDAO();
		gg.setGonggaoConten(gonggaoConten);
		gg.setGonggaoTime(gonggaoTime);
		gg.setGonggaoShow(show);
		System.out.println(gonggaoTime);
		
		System.out.println(show);
		System.out.println(gonggaoConten);
		System.out.println("++++++++++++++++++++++++++s");
		ggd.save(gg);
		
		out.print("<script language='javascript'>alert('添加成功！');window.location='gonggaoManager.action';</script>");
		out.flush();out.close();return null;
		
	}
	
	//判断是否空值
	private boolean isInvalid(String value) {
		return (value == null || value.length() == 0);
	}
	
	//测试
	public static void main(String[] args) {
		System.out.println();
	}

	public Timestamp getGonggaoTime() {
		return gonggaoTime;
	}

	public void setGonggaoTime(Timestamp gonggaoTime) {
		this.gonggaoTime = gonggaoTime;
	}

	public String getGonggaoConten() {
		return gonggaoConten;
	}

	public void setGonggaoConten(String gonggaoConten) {
		this.gonggaoConten = gonggaoConten;
	}

	public String getShow() {
		return show;
	}

	public void setShow(String show) {
		this.show = show;
	}



	
}
