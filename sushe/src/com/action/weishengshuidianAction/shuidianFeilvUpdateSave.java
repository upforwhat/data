package com.action.weishengshuidianAction;

import java.io.PrintWriter;
import java.math.BigDecimal;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;
import com.bean.*;
import com.dao.*;
import com.hibernate.model.Feilv;
import com.hibernate.model.FeilvDAO;
import com.hibernate.model.Weishengshuidian;
import com.hibernate.model.WeishengshuidianDAO;


public class shuidianFeilvUpdateSave extends ActionSupport {

	//������Action�����ڷ�װ�û��������������
	private String shui;
	private String dian;
	private Feilv fl;	
	private String msg=null;

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
		FeilvDAO fld=new FeilvDAO();
		fl=fld.findById(1);
		BigDecimal shuidushu=new BigDecimal(shui); 
		BigDecimal diandushu=new BigDecimal(dian); 
		fl.setDian(diandushu);
		fl.setShui(shuidushu);
		fld.save(fl);
		msg="�޸ĳɹ�";
		
/*		//��ת
		out.print("<script language='javascript'>alert('�޸ĳɹ���');window.location='weishengshuidianManager.action';</script>");
		out.flush();out.close();return null;
		*/
		return "success";
	}
	
	//�ж��Ƿ��ֵ
	private boolean isInvalid(String value) {
		return (value == null || value.length() == 0);
	}
	
	//����
	public static void main(String[] args) {
		System.out.println();
	}

	public String getShui() {
		return shui;
	}

	public void setShui(String shui) {
		this.shui = shui;
	}

	public String getDian() {
		return dian;
	}

	public void setDian(String dian) {
		this.dian = dian;
	}

	public Feilv getFl() {
		return fl;
	}

	public void setFl(Feilv fl) {
		this.fl = fl;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}


	
}
