package com.action.gonggaoAction;

import java.io.PrintWriter;
import java.sql.Timestamp;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;
import com.hibernate.model.Gongao;
import com.hibernate.model.GongaoDAO;



public class getgonggao extends ActionSupport {

	//������Action�����ڷ�װ�û��������������
private String gonggaoString="�������ݣ�";

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
	GongaoDAO ggd=new GongaoDAO();
		 List<Gongao> gongaolist=ggd.findAll();
	for(Gongao g :gongaolist)
	{
		g.getGonggaoConten();
		g.getGonggaoTime();
		gonggaoString+=g.getGonggaoTime()+"___"+g.getGonggaoConten();
		gonggaoString+="\t\t";
		System.out.println(gonggaoString);
	}
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



	public String getGonggaoString() {
		return gonggaoString;
	}

	public void setGonggaoString(String gonggaoString) {
		this.gonggaoString = gonggaoString;
	}

	
	

	
}
