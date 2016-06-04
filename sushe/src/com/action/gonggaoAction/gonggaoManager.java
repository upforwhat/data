package com.action.gonggaoAction;

import java.io.PrintWriter;
import java.sql.Timestamp;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;
import com.hibernate.model.Gongao;
import com.hibernate.model.GongaoDAO;



public class gonggaoManager extends ActionSupport {

	//下面是Action内用于封装用户请求参数的属性
	private Integer gonggaoId;
	private Timestamp gonggaoTime;
	private Timestamp searchtime;
	
	private String gonggaoConten;
	private String gonggaoShow;
	private List<Gongao> gongaolist;


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
	GongaoDAO ggd=new GongaoDAO();
	gongaolist=ggd.findAll();
		return SUCCESS;
	}
	
	//判断是否空值
	private boolean isInvalid(String value) {
		return (value == null || value.length() == 0);
	}
	
	//测试
	public static void main(String[] args) {
		System.out.println();
	}

	public Integer getGonggaoId() {
		return gonggaoId;
	}

	public void setGonggaoId(Integer gonggaoId) {
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


	public List<Gongao> getGongaolist() {
		return gongaolist;
	}

	public void setGongaolist(List<Gongao> gongaolist) {
		this.gongaolist = gongaolist;
	}

	public String getGonggaoShow() {
		return gonggaoShow;
	}

	public void setGonggaoShow(String gonggaoShow) {
		this.gonggaoShow = gonggaoShow;
	}

	public Timestamp getSearchtime() {
		return searchtime;
	}

	public void setSearchtime(Timestamp searchtime) {
		this.searchtime = searchtime;
	}



	
	

	
}
