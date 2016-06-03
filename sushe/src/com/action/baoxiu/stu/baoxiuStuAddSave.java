package com.action.baoxiu.stu;

import java.io.PrintWriter;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;
import com.hibernate.model.Baoxiu;
import com.hibernate.model.BaoxiuDAO;
import com.hibernate.model.Gongao;
import com.hibernate.model.GongaoDAO;
import com.hibernate.model.Baoxiu;
import com.hibernate.model.BaoxiuDAO;
import com.bean.StudentBean;
import com.bean.DomitoryBean;
import com.dao.StudentDao;
import com.dao.DomitoryDao;


public class baoxiuStuAddSave extends ActionSupport {

	//������Action�����ڷ�װ�û��������������

	private Baoxiu bx;
	private DomitoryBean db;
	private StudentBean stu;
	private String baoxiuBuildingName,baoxiuDomitoryName,baoxiuStudentName,baoxiuXianmu;
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
		Date date = new Date();
		System.out.println(date);
		//ע��format�ĸ�ʽҪ������String�ĸ�ʽ��ƥ��
//		DateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		DateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		
		System.out.println("��ʽ���������"+sdf.format(date));
		String stuid=(String) session.getAttribute("id");
		int stuid1=Integer.parseInt(stuid);
		stu=new StudentDao().GetBean(stuid1);
		stu.getBuilding_Name();
		stu.getDomitory_Name();
		stu.getStudent_Name();
		stu.getStudent_DomitoryID();
		db=new DomitoryDao().GetBean(stu.getStudent_DomitoryID());
		db.getDomitory_BuildingID();
		db.getDomitory_ID();
		bx=new Baoxiu();
		bx.setBaoxiuBuildingId(db.getDomitory_BuildingID());
		bx.setBaoxiuBuildingName(stu.getBuilding_Name());
		bx.setBaoxiuDomitoryid(db.getDomitory_ID());
		bx.setBaoxiuDomitoryName(stu.getDomitory_Name());
		bx.setBaoxiushijian(sdf.format(date));
		bx.setBaoxiuStudentId(stuid1);
		bx.setBaoxiuStudentName(stu.getStudent_Name());
		bx.setBaoxiuXianmu(baoxiuXianmu);
		bx.setChulishijian(null);
		bx.setState("δ����");
		BaoxiuDAO bxd=new BaoxiuDAO();
		bxd.save(bx);
		/*out.print("<script language='javascript'>alert('���뱨�޳ɹ���');</script>");
		out.flush();out.close();*/
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

	public String getBaoxiuBuildingName() {
		return baoxiuBuildingName;
	}

	public void setBaoxiuBuildingName() {
		this.baoxiuBuildingName = stu.getBuilding_Name();
	}

	public String getBaoxiuDomitoryName() {
		return baoxiuDomitoryName;
	}

	public void setBaoxiuDomitoryName() {
		this.baoxiuDomitoryName =stu.getDomitory_Name();
	}

	public String getBaoxiuStudentName() {
		return baoxiuStudentName;
	}

	public void setBaoxiuStudentName() {
		this.baoxiuStudentName =stu.getStudent_Name();
	}

	public String getBaoxiuXianmu() {
		return baoxiuXianmu;
	}

	public void setBaoxiuXianmu(String baoxiuXianmu) {
		this.baoxiuXianmu = baoxiuXianmu;
	}





	
	

	
}
