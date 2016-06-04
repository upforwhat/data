package com.action.gonggaoAction;

import java.io.PrintWriter;
import java.sql.Timestamp;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;
import com.bean.*;
import com.dao.*;
import com.hibernate.model.Gongao;
import com.hibernate.model.GongaoDAO;
import com.hibernate.model.Weishengshuidian;
import com.hibernate.model.WeishengshuidianDAO;


public class gonggaoUpdateSave extends ActionSupport {

	//下面是Action内用于封装用户请求参数的属性
	private String gonggaoId;
	private Timestamp gonggaoTime;
	private String gonggaoConten;
	private String gonggaoShow;
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
		Gongao gg=new Gongao();
		GongaoDAO ggd=new GongaoDAO();
		gg=ggd.findById(Integer.parseInt(gonggaoId));
		gg.setGonggaoConten(gonggaoConten);
		gg.setGonggaoShow(gonggaoShow);
		gg.setGonggaoTime(gonggaoTime);
		ggd.save(gg);
		//跳转
		out.print("<script language='javascript'>alert('修改成功！');window.location='gonggaoManager.action';</script>");
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

	public String getGonggaoId() {
		return gonggaoId;
	}

	public void setGonggaoId(String gonggaoId) {
		this.gonggaoId = gonggaoId;
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

	public String getGonggaoShow() {
		return gonggaoShow;
	}

	public void setGonggaoShow(String gonggaoShow) {
		this.gonggaoShow = gonggaoShow;
	}

	
}
