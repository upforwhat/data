package com.action.gonggaoAction;

import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;
import com.bean.*;
import com.dao.*;
import com.hibernate.model.GongaoDAO;



public class gonggaoDel extends ActionSupport {

	//������Action�����ڷ�װ�û��������������
	private Integer gonggaoId;
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
	new GongaoDAO().delete(new GongaoDAO().findById(gonggaoId));
		
		out.print("<script language='javascript'>alert('ɾ���ɹ���');window.location='gonggaoManager.action'</script>");
		out.flush();out.close();
		return null;
//		window.location='gonggaoManager.action';
	}
//	
	//�ж��Ƿ��ֵ
	private boolean isInvalid(String value) {
		return (value == null || value.length() == 0);
	}
	
	//����
	public static void main(String[] args) {
		System.out.println();
	}

	public Integer getGonggaoId() {
		return gonggaoId;
	}

	public void setGonggaoId(Integer gonggaoId) {
		this.gonggaoId = gonggaoId;
	}


	
}
