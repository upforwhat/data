package com.action.BuildingAndDormitory;

import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;
import com.bean.*;
import com.dao.*;


public class BuildingAndDormitory extends ActionSupport {

	//下面是Action内用于封装用户请求参数的属性
	private List<BuildingBean> Buildinglist;
	private List<DomitoryBean> DomitoryByBuildinglist;
	private Integer buildingid;
	
	
	public String SearchBuilding() throws Exception {
		
		//解决乱码，用于页面输出
		HttpServletResponse response=null;
		response=ServletActionContext.getResponse();
		response.setContentType("text/html;charset=UTF-8");
		response.setCharacterEncoding("UTF-8");
		
		//创建session对象
		HttpSession session = ServletActionContext.getRequest().getSession();
Buildinglist=new BuildingDao().GetList("", "");	
	/*JSONObject json =new JSONObject();*/  
//{"buildings":[{"building_Name":"清风
	JSONArray ary=new JSONArray();   // [{"building_ID":1,"building_Introduction":"","building_Name":"红绵
	ary.addAll(Buildinglist);


	response.getWriter().write(ary.toString());
	response.getWriter().flush();
	response.getWriter().close();
		return null;
		
	}
public String SearchDomitory() throws Exception {
		
		//解决乱码，用于页面输出
		HttpServletResponse response=null;
		response=ServletActionContext.getResponse();
		response.setContentType("text/html;charset=UTF-8");
		response.setCharacterEncoding("UTF-8");
		PrintWriter out = response.getWriter();
		
		//创建session对象
		HttpSession session = ServletActionContext.getRequest().getSession();
		String parameter ="Building.Building_ID="+buildingid;
		DomitoryByBuildinglist= new DomitoryDao().GetList(parameter, "");
		JSONArray ary=new JSONArray();  
		ary.addAll(DomitoryByBuildinglist);
	
		response.getWriter().write(ary.toString());
		response.getWriter().flush();
		response.getWriter().close();
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
	public Integer getBuildingid() {
		return buildingid;
	}
	public void setBuildingid(Integer buildingid) {
		this.buildingid = buildingid;
	}
	
}
