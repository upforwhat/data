package com.action;

import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;
import com.bean.*;
import com.dao.*;


public class weiguiUpdate extends ActionSupport {

	//������Action�����ڷ�װ�û��������������
	private String weiguiid;
	private weiguiBean cnbean;
	private List<weiguiBean> list;
	//�����û������execute����
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
		
		//��ѯ����¥��
	
		//��ѯ
		cnbean=new weiguiDao().GetBean(Integer.parseInt(weiguiid));
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

	public String getWeiguiid() {
		return weiguiid;
	}

	public void setWeiguiid(String weiguiid) {
		this.weiguiid = weiguiid;
	}

	public weiguiBean getCnbean() {
		return cnbean;
	}

	public void setCnbean(weiguiBean cnbean) {
		this.cnbean = cnbean;
	}

	public List<weiguiBean> getList() {
		return list;
	}

	public void setList(List<weiguiBean> list) {
		this.list = list;
	}
	
}
