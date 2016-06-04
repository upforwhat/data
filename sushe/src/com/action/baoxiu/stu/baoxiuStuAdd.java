package com.action.baoxiu.stu;

import java.io.PrintWriter;
import java.sql.Timestamp;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;
import com.hibernate.model.Baoxiu;
import com.hibernate.model.BaoxiuDAO;
import com.bean.StudentBean;
import com.bean.DomitoryBean;
import com.dao.StudentDao;
import com.dao.DomitoryDao;




public class baoxiuStuAdd extends ActionSupport {

	//������Action�����ڷ�װ�û��������������

	private Baoxiu bx;
	private StudentBean stu;
	private String baoxiuBuildingName,baoxiuDomitoryName,baoxiuStudentName;


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
	String stuid=(String)session.getAttribute("id");
	
	int stuid1=Integer.parseInt(stuid);
		stu=new StudentDao().GetBean(stuid1);
		System.out.println(stuid1+"ѧ��id++++++++++++++++++++++++++");
		System.out.println(	stu.getBuilding_Name()+"¥��++++++++++++++++++++++++++++");
		System.out.println(stu.getDomitory_Name()+"������+++++++++++++++++++++++++++++++");
		System.out.println(stu.getStudent_Name()+"����++++++++++++++++++++++++++++");
		baoxiuBuildingName=stu.getBuilding_Name();
		baoxiuDomitoryName=stu.getDomitory_Name();
		baoxiuStudentName=stu.getStudent_Name();	
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

	public Baoxiu getBx() {
		return bx;
	}

	public void setBx(Baoxiu bx) {
		this.bx = bx;
	}

	public String getBaoxiuBuildingName() {
		return baoxiuBuildingName;
	}

//	public void setBaoxiuBuildingName(String baoxiuBuildingName) {
//		
//		this.baoxiuBuildingName = baoxiuBuildingName;
//	}

	public String getBaoxiuDomitoryName() {
		return baoxiuDomitoryName;
	}

//	public void setBaoxiuDomitoryName(String baoxiuDomitoryName) {
//		this.baoxiuDomitoryName = baoxiuDomitoryName;
//	}

	public String getBaoxiuStudentName() {
		return baoxiuStudentName;
	}

//	public void setBaoxiuStudentName(String baoxiuStudentName) {
//		this.baoxiuStudentName = baoxiuStudentName;
//	}

	
}
