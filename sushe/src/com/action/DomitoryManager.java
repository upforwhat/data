package com.action;

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


public class DomitoryManager extends ActionSupport {

	//������Action�����ڷ�װ�û��������������
	private List<DomitoryBean> list;
	
	private String buildingid;
	private String domitoryid;
	private String Domitory_BuildingID;
	private List<BuildingBean> buildinglist;
	private String rows;// ÿҳ��ʾ�ļ�¼��
	private String page;// ��ǰ�ڼ�ҳ
	private	String sort;
	private	String order;
	
	//�����û������execute����
	public String execute() throws Exception {
		 int rows=Integer.parseInt(this.getRows());
		    if(rows==0)
		    {
		    	rows=10;
		    }
		    int page=Integer.parseInt(this.getPage());
		    
		    if(page==0)
		    {
		    	page=1;
		    }
		//������룬����ҳ�����
		
		JSONObject json=new JSONObject();
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
		String parametersql="";
		if(!isInvalid(buildingid))
		{
			parametersql+=" and Building_ID="+buildingid+"\t";
		}
		if(!isInvalid(domitoryid))
		{
			parametersql+=" and Domitory_ID="+domitoryid+"\t";
		}
		
		//��ѯ����¥��

		
		//��ѯ����
		list=new DomitoryDao().MyGetList(parametersql, sort, order);
		List temp;
		temp=JSONArray.fromObject(list);
//		System.out.println("��������"+list.size());
//		System.out.println("��ʽ��"+temp.toString());
//		System.out.println("ҳ����"+this.getPage());
//		System.out.println("������"+this.getRows());
		temp= temp.subList(rows*(page-1), (rows*(page-1)+rows>list.size()?list.size():rows*(page-1)+rows));
		json.put("total", list.size());
		json.put("rows", temp);
		response.getWriter().write(json.toString());
	
		return null;
		
	}
	
	//�ж��Ƿ��ֵ
	private boolean isInvalid(String value) {
		return (value == null || value.length() == 0);
	}
	
	public List<DomitoryBean> getList() {
		return list;
	}
	public void setList(List<DomitoryBean> list) {
		this.list = list;
	}
	
	
	public List<BuildingBean> getBuildinglist() {
		return buildinglist;
	}
	public void setBuildinglist(List<BuildingBean> buildinglist) {
		this.buildinglist = buildinglist;
	}

	public String getDomitory_BuildingID() {
		return Domitory_BuildingID;
	}
	public void setDomitory_BuildingID(String domitoryBuildingID) {
		Domitory_BuildingID = domitoryBuildingID;
	}
	public String getRows() {
		return rows;
	}

	public void setRows(String rows) {
		this.rows = rows;
	}

	public String getPage() {
		return page;
	}

	public void setPage(String page) {
		this.page = page;
	}

	public String getSort() {
		return sort;
	}

	public void setSort(String sort) {
		this.sort = sort;
	}

	public String getOrder() {
		return order;
	}

	public void setOrder(String order) {
		this.order = order;
	}

	public String getBuildingid() {
		return buildingid;
	}

	public void setBuildingid(String buildingid) {
		this.buildingid = buildingid;
	}

	public String getDomitoryid() {
		return domitoryid;
	}

	public void setDomitoryid(String domitoryid) {
		this.domitoryid = domitoryid;
	}

	//����
	public static void main(String[] args) {
		System.out.println();
	}
	
}
