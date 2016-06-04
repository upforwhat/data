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

	//������Action�����ڷ�װ�û��������������
	private List<BuildingBean> Buildinglist;
	private List<DomitoryBean> DomitoryByBuildinglist;
	private Integer buildingid;
	
	
	public String SearchBuilding() throws Exception {
		
		//������룬����ҳ�����
		HttpServletResponse response=null;
		response=ServletActionContext.getResponse();
		response.setContentType("text/html;charset=UTF-8");
		response.setCharacterEncoding("UTF-8");
		
		//����session����
		HttpSession session = ServletActionContext.getRequest().getSession();
Buildinglist=new BuildingDao().GetList("", "");	
	/*JSONObject json =new JSONObject();*/  
//{"buildings":[{"building_Name":"���
	JSONArray ary=new JSONArray();   // [{"building_ID":1,"building_Introduction":"","building_Name":"����
	ary.addAll(Buildinglist);


	response.getWriter().write(ary.toString());
	response.getWriter().flush();
	response.getWriter().close();
		return null;
		
	}
public String SearchDomitory() throws Exception {
		
		//������룬����ҳ�����
		HttpServletResponse response=null;
		response=ServletActionContext.getResponse();
		response.setContentType("text/html;charset=UTF-8");
		response.setCharacterEncoding("UTF-8");
		PrintWriter out = response.getWriter();
		
		//����session����
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
	
	
	//�ж��Ƿ��ֵ
	private boolean isInvalid(String value) {
		return (value == null || value.length() == 0);
	}
	
	//����
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
