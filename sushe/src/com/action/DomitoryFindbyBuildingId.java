package com.action;

import java.io.PrintWriter;
import java.util.List;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.struts2.ServletActionContext;
import com.opensymphony.xwork2.ActionSupport;

import com.bean.*;
import com.dao.*;


public class DomitoryFindbyBuildingId extends ActionSupport {

	//下面是Action内用于封装用户请求参数的属性
	private String BuildingID;
	private List<DomitoryBean> Domitory_list;
	//处理用户请求的execute方法
	public String execute() throws Exception {
		System.out.println("++++++++++"+BuildingID);
		
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
		//查询条件
		if(!isInvalid(BuildingID)){
			String strWhere="Building.Building_ID="+Integer.parseInt(BuildingID);
			Domitory_list=new DomitoryDao().GetList(strWhere,"Domitory_Name");
		}
		Domitory_list=new DomitoryDao().GetList("","Domitory_Name");
		
		//查询所有
		
	
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
	public String getBuildingID() {
		return BuildingID;
	}
	public void setBuildingID(String buildingID) {
		BuildingID = buildingID;
	}
	public List<DomitoryBean> getDomitory_list() {
		return Domitory_list;
	}
	public void setDomitory_list(List<DomitoryBean> domitory_list) {
		Domitory_list = domitory_list;
	}
	
}
