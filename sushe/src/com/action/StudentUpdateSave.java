package com.action;

import java.io.PrintWriter;
import java.util.List;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.struts2.ServletActionContext;
import com.opensymphony.xwork2.ActionSupport;

import com.bean.*;
import com.dao.*;

public class StudentUpdateSave extends ActionSupport {

	// 下面是Action内用于封装用户请求参数的属性
	private String Student_ID;
	private String Student_Username;
	private String Student_Password;
	private String Student_Name;
	private String Student_Sex;
	private String Student_Class;
	private String Student_State;

	public String getStudent_Username() {
		return Student_Username;
	}

	public void setStudent_Username(String studentUsername) {
		Student_Username = studentUsername;
	}

	public String getStudent_Password() {
		return Student_Password;
	}

	public void setStudent_Password(String studentPassword) {
		Student_Password = studentPassword;
	}

	public String getStudent_Name() {
		return Student_Name;
	}

	public void setStudent_Name(String studentName) {
		Student_Name = studentName;
	}

	public String getStudent_Sex() {
		return Student_Sex;
	}

	public void setStudent_Sex(String studentSex) {
		Student_Sex = studentSex;
	}

	public String getStudent_Class() {
		return Student_Class;
	}

	public void setStudent_Class(String studentClass) {
		Student_Class = studentClass;
	}

	// 处理用户请求的execute方法
	public String execute() throws Exception {

		// 解决乱码，用于页面输出
		HttpServletResponse response = null;
		response = ServletActionContext.getResponse();
		response.setContentType("text/html;charset=UTF-8");
		response.setCharacterEncoding("UTF-8");
		PrintWriter out = response.getWriter();

		// 创建session对象
		HttpSession session = ServletActionContext.getRequest().getSession();
		// 验证是否正常登录
		if (session.getAttribute("id") == null) {
			out.print("<script language='javascript'>alert('请重新登录！');window.location='Login.jsp';</script>");
			out.flush();
			out.close();
			return null;
		}
		System.out.println(this.getStudent_ID());
		// 查询已入住用户名是否存在

		if ( !isInvalid(Student_ID)) {
			
			if (new StudentDao().finddouble(Integer.parseInt(Student_ID), Student_Username)) {
				response.getWriter().write("duplicate");

				out.flush();
				out.close();
				return null;
			}
		} else {
			
			if (new StudentDao().finddouble(null, Student_Username)) {
				response.getWriter().write("duplicate");

				out.flush();
				out.close();
				return null;
			}
		}
		// 修改
		StudentBean cnbean;
		if (isInvalid(Student_ID)) {
			cnbean = new StudentBean();
			cnbean.setStudent_Class(Student_Class);
			cnbean.setStudent_Name(Student_Name);
			cnbean.setStudent_Sex(Student_Sex);
			cnbean.setStudent_State("未入住");
			cnbean.setStudent_Username(Student_Username);
			cnbean.setStudent_Password(Student_Username);
			new StudentDao().beforeAdd(cnbean);
		} else {
			cnbean = new StudentDao().GetAllBean(Integer.parseInt(Student_ID));
			cnbean.setStudent_Username(Student_Username);
			cnbean.setStudent_Name(Student_Name);
			cnbean.setStudent_Sex(Student_Sex);
			cnbean.setStudent_State(Student_State);
			cnbean.setStudent_Class(Student_Class);
			if (Student_Password != null) {
				cnbean.setStudent_Password(Student_Password);
			}
		}
		new StudentDao().Update(cnbean);

		response.getWriter().write("success");
		response.getWriter().flush();
		response.getWriter().close();
		return null;

	}

	// 判断是否空值
	private boolean isInvalid(String value) {
		return (value == null || value.length() == 0);
	}

	// 测试
	public static void main(String[] args) {
		System.out.println();
	}

	public String getStudent_ID() {
		return Student_ID;
	}

	public void setStudent_ID(String student_ID) {
		Student_ID = student_ID;
	}

	public String getStudent_State() {
		return Student_State;
	}

	public void setStudent_State(String student_State) {
		Student_State = student_State;
	}

}
