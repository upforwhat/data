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

	//������Action�����ڷ�װ�û��������������
	private String BuildingID;
	private List<DomitoryBean> Domitory_list;
	//�����û������execute����
	public String execute() throws Exception {
		System.out.println("++++++++++"+BuildingID);
		
		//������룬����ҳ�����
		HttpServletResponse response=null;
		response=ServletActionContext.getResponse();
		response.setContentType("text/html;charset=UTF-8");
		response.setCharacterEncoding("UTF-8");
		PrintWriter out = response.getWriter();
		
		//����session����
		HttpSession session = ServletActionContext.getRequest().getSession();
		//��֤�Ƿ�������¼
		if(session.getAttribute("id")==null){
			out.print("<script language='javascript'>alert('�����µ�¼��');window.location='Login.jsp';</script>");
			out.flush();out.close();return null;
		}
		//��ѯ����
		if(!isInvalid(BuildingID)){
			String strWhere="Building.Building_ID="+Integer.parseInt(BuildingID);
			Domitory_list=new DomitoryDao().GetList(strWhere,"Domitory_Name");
		}
		Domitory_list=new DomitoryDao().GetList("","Domitory_Name");
		
		//��ѯ����
		
	
		return SUCCESS;
		
	}
	
	//�ж��Ƿ��ֵ
	private boolean isInvalid(String value) {
		return (value == null || value.length() == 0);
	}
	
	//����
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
