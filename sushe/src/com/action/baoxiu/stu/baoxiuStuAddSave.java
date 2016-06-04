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

	//下面是Action内用于封装用户请求参数的属性

	private Baoxiu bx;
	private DomitoryBean db;
	private StudentBean stu;
	private String baoxiuBuildingName,baoxiuDomitoryName,baoxiuStudentName,baoxiuXianmu;
	//处理用户请求的execute方法
	public String execute() throws Exception {
		
		//解决乱码，用于页面输出
		HttpServletResponse response=null;
		response=ServletActionContext.getResponse();
		response.setContentType("text/html;charset=UTF-8");
		response.setCharacterEncoding("UTF-8");
		PrintWriter out = response.getWriter();
		
		//创建session对象
		HttpSession session = ServletActionContext.getRequest().getSession();
		//验证是否正常登录
		if(session.getAttribute("id")==null){
			out.print("<script language='javascript'>alert('请重新登录！');window.location='Login.jsp';</script>");
			out.flush();out.close();return null;
		}
		Date date = new Date();
		System.out.println(date);
		//注意format的格式要与日期String的格式相匹配
//		DateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		DateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		
		System.out.println("格式化后的日期"+sdf.format(date));
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
		bx.setState("未处理");
		BaoxiuDAO bxd=new BaoxiuDAO();
		bxd.save(bx);
		/*out.print("<script language='javascript'>alert('申请报修成功！');</script>");
		out.flush();out.close();*/
		return SUCCESS;
	}
	
	//判断是否空值
	private boolean isInvalid(String value) {
		return (value == null || value.length() == 0);
	}
	
	//测试
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
