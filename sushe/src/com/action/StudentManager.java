package com.action;

import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.struts2.ServletActionContext;

import com.util.JsonUtil;
import com.opensymphony.xwork2.ActionSupport;
import com.bean.*;
import com.dao.*;


public class StudentManager extends ActionSupport {

	//������Action�����ڷ�װ�û��������������
	private List<StudentBean> list;
	private String rows;// ÿҳ��ʾ�ļ�¼��
	private String page;// ��ǰ�ڼ�ҳ
private	String sort;
private	String order;
	public List<StudentBean> getList() {
		return list;
	}
	public void setList(List<StudentBean> list) {
		this.list = list;
	}
	private String SearchRow;
	private String SearchKey;
	private String State;
	public String getState() {
		return State;
	}
	public void setState(String state) {
		State = state;
	}
	public String getSearchRow() {
		return SearchRow;
	}
	public void setSearchRow(String searchRow) {
		SearchRow = searchRow;
	}
	public String getSearchKey() {
		return SearchKey;
	}
	public void setSearchKey(String searchKey) {
		SearchKey = searchKey;
	}
	//�����û������execute����
	public String execute() throws Exception {
		
		//������룬����ҳ�����
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
		String strWhere="1=1";
		if(!(isInvalid(SearchKey)))
		{
			strWhere+=" and "+SearchRow+"='"+SearchKey+"'";
		}
		if(!(isInvalid(State)))
		{
			strWhere+=" and Student_State='"+State+"'";
		}
	
		JSONObject result=new JSONObject();
	
		//��ѯ����
		list=new StudentDao().GetAllList(strWhere,sort,order);
		
		List temp;
		temp=JSONArray.fromObject(list);
//		System.out.println("��������"+list.size());
//		System.out.println("��ʽ��"+temp.toString());
//		System.out.println("ҳ����"+this.getPage());
//		System.out.println("������"+this.getRows());
		temp= temp.subList(rows*(page-1), (rows*(page-1)+rows>list.size()?list.size():rows*(page-1)+rows));
		result.put("total", list.size());
		result.put("rows", temp);
//		jobj.accumulate("total", total);// total����һ���ж�������
//		jobj.accumulate("rows", list);// row�Ǵ�����ʾ��ҳ������
		response.getWriter().write(result.toString());// ת��ΪJSOn��ʽ
		
	
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
	
}
