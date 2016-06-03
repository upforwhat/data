package com.action.baoxiu;

import java.io.PrintWriter;
import java.sql.Timestamp;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONObject;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;
import com.hibernate.model.Baoxiu;
import com.hibernate.model.BaoxiuDAO;
import com.hibernate.model.Gongao;
import com.hibernate.model.GongaoDAO;



public class baoxiuManager extends ActionSupport {

	//������Action�����ڷ�װ�û��������������

	private List<Baoxiu> baoxiulist;
	private String rows;// ÿҳ��ʾ�ļ�¼��
	private String page;// ��ǰ�ڼ�ҳ
	private	String sort;//easyui ���������������
	private	String order;//������
	private String shoulistate;
	//�����û������execute���� page sort order
	public String execute() throws Exception {
		
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
		
		BaoxiuDAO bxd=new BaoxiuDAO();
		String appendhql="";  
		Object parameter[]={};
		int total=0;
		 if(!isInvalid(shoulistate)){
			 appendhql=" where  model.state='"+shoulistate+"'";
			 total=bxd.getTotal(parameter, appendhql);
		 }
		 total=bxd.getTotal(parameter, appendhql);
		if(!isInvalid(shoulistate)){
			baoxiulist=bxd.findByState(shoulistate);
		}else{
		
			baoxiulist=bxd.getList(appendhql, parameter, page, rows, sort, order);
		}
		JSONObject json =new JSONObject();
		json.put("rows", baoxiulist);
		json.put("total",total);
		response.getWriter().write(json.toString());
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

	public List<Baoxiu> getBaoxiulist() {
		return baoxiulist;
	}

	public void setBaoxiulist(List<Baoxiu> baoxiulist) {
		this.baoxiulist = baoxiulist;
	}

	public String getShoulistate() {
		return shoulistate;
	}

	public void setShoulistate(String shoulistate) {
		this.shoulistate = shoulistate;
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
