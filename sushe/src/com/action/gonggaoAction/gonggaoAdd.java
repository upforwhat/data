package com.action.gonggaoAction;

import java.io.PrintWriter;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.text.DecimalFormat;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;
import org.hibernate.Session;

import com.opensymphony.xwork2.ActionSupport;
import com.bean.*;
import com.dao.*;
import com.hibernate.model.BaseHibernateDAO;
import com.hibernate.model.Feilv;
import com.hibernate.model.FeilvDAO;
import com.hibernate.model.Gongao;
import com.hibernate.model.GongaoDAO;
import com.hibernate.model.Weishengshuidian;
import com.hibernate.model.WeishengshuidianDAO;


public class gonggaoAdd extends ActionSupport {

	//������Action�����ڷ�װ�û��������������
	private Timestamp gonggaoTime;
	private String gonggaoConten;
	private String show;
	//�����û������execute����
	public String execute() throws Exception {
		Session sess=new BaseHibernateDAO().getSession();
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
		Gongao gg=new Gongao();
		GongaoDAO ggd=new GongaoDAO();
		gg.setGonggaoConten(gonggaoConten);
		gg.setGonggaoTime(gonggaoTime);
		gg.setGonggaoShow(show);
		System.out.println(gonggaoTime);
		
		System.out.println(show);
		System.out.println(gonggaoConten);
		System.out.println("++++++++++++++++++++++++++s");
		ggd.save(gg);
		
		out.print("<script language='javascript'>alert('��ӳɹ���');window.location='gonggaoManager.action';</script>");
		out.flush();out.close();return null;
		
	}
	
	//�ж��Ƿ��ֵ
	private boolean isInvalid(String value) {
		return (value == null || value.length() == 0);
	}
	
	//����
	public static void main(String[] args) {
		System.out.println();
	}

	public Timestamp getGonggaoTime() {
		return gonggaoTime;
	}

	public void setGonggaoTime(Timestamp gonggaoTime) {
		this.gonggaoTime = gonggaoTime;
	}

	public String getGonggaoConten() {
		return gonggaoConten;
	}

	public void setGonggaoConten(String gonggaoConten) {
		this.gonggaoConten = gonggaoConten;
	}

	public String getShow() {
		return show;
	}

	public void setShow(String show) {
		this.show = show;
	}



	
}
