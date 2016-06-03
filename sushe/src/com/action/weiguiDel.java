package com.action;

import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONObject;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;
import com.bean.*;
import com.dao.*;


public class weiguiDel extends ActionSupport {

	//������Action�����ڷ�װ�û��������������
	private String weiguiid ;


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
		
		
		//ɾ��
		
		int rs=0;
		rs=new weiguiDao().Delete("weiguiid="+Integer.parseInt(weiguiid));
		 JSONObject jsonObj=new JSONObject();
		 if(rs != 0){
		 jsonObj.put("result","success"); 
		 }else{ 
		 jsonObj.put("result","failed");
		 }
			response.getWriter().write(jsonObj.toString());
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

	public String getWeiguiid() {
		return weiguiid;
	}

	public void setWeiguiid(String weiguiid) {
		this.weiguiid = weiguiid;
	}
	
}
