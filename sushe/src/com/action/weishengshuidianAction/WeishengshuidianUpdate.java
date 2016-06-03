package com.action.weishengshuidianAction;

import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;
import com.bean.*;
import com.dao.*;
import com.hibernate.model.Weishengshuidian;
import com.hibernate.model.WeishengshuidianDAO;


public class WeishengshuidianUpdate extends ActionSupport {

	//������Action�����ڷ�װ�û��������������
	private String weishengshuidian_ID;
private Weishengshuidian wssd;

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
		 wssd=new WeishengshuidianDAO().findById(Integer.parseInt(weishengshuidian_ID));
	
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

	public String getWeishengshuidian_ID() {
		return weishengshuidian_ID;
	}

	public void setWeishengshuidian_ID(String weishengshuidian_ID) {
		this.weishengshuidian_ID = weishengshuidian_ID;
	}

	public Weishengshuidian getWssd() {
		return wssd;
	}

	public void setWssd(Weishengshuidian wssd) {
		this.wssd = wssd;
	}


	
}
