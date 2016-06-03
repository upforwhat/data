package com.action.stu;

import java.io.PrintWriter;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javassist.expr.NewArray;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONObject;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;
import com.bean.StudentBean;
import com.dao.StudentDao;
import com.hibernate.model.Gongao;
import com.hibernate.model.GongaoDAO;
import com.hibernate.model.Shebei;
import com.hibernate.model.ShebeiDAO;

public class aboutme extends ActionSupport {

	// 下面是Action内用于封装用户请求参数的属性
	private JSONObject jsonObj;
	private StudentBean stu;

	private List<StudentBean> aboutmelist;


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
		String stuid=(String) session.getAttribute("id");
		int stuid1=Integer.parseInt(stuid);
		stu=new StudentDao().GetBean(stuid1);
		System.out.println("aboutme"+stu.getStudent_ID());
//		List<StudentBean> aboutmelist;
		aboutmelist=new ArrayList<StudentBean>();
		aboutmelist.add(stu);
	
		JSONObject jobj = new JSONObject();
		jobj.put("rows", aboutmelist);
response.getWriter().write(jobj.toString());
	
		return null;
	}
	
	// 判断是否空值
	private boolean isInvalid(String value) {
		return (value == null || value.length() == 0);
	}

	// 转化为Json格式
	public void toBeJson(List list, int total) throws Exception {
		HttpServletResponse response = ServletActionContext.getResponse();
		HttpServletRequest request = ServletActionContext.getRequest();

		JSONObject jobj = new JSONObject();// new一个JSON
		jobj.accumulate("total", total);// total代表一共有多少数据
		jobj.accumulate("rows", list);// row是代表显示的页的数据

		response.setCharacterEncoding("utf-8");// 指定为utf-8
		response.getWriter().write(jobj.toString());// 转化为JSOn格式
		response.getWriter().flush();  
		response.getWriter().close(); 
	}

	// 测试
	public static void main(String[] args) {
		System.out.println();
	}

	public JSONObject getJsonObj() {
		return jsonObj;
	}

	public void setJsonObj(JSONObject jsonObj) {
		this.jsonObj = jsonObj;
	}


	public List<StudentBean> getAboutmelist() {
		return aboutmelist;
	}

	public void setAboutmelist(List<StudentBean> aboutmelist) {
		this.aboutmelist = aboutmelist;
	}



}
