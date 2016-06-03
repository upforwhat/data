package com.action.baoxiu;

import java.io.PrintWriter;
import java.sql.Time;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONObject;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;
import com.bean.*;
import com.dao.*;
import com.hibernate.model.Baoxiu;
import com.hibernate.model.BaoxiuDAO;



public class baoxiuShouli extends ActionSupport {

	//下面是Action内用于封装用户请求参数的属性
	private Integer baoxiuid;
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
		Baoxiu bx=null;
		JSONObject json=new JSONObject();
		int rs=0;
		bx=new BaoxiuDAO().findById(baoxiuid);
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
if(bx.getState().equals("已处理")){
	json.put("result", "yijingchuli");
}
		bx.setChulishijian(df.format(new Date()));
		bx.setState("已处理");
	rs=new BaoxiuDAO().save(bx);
	if(rs==0){
		json.put("result", "failed");
	}
	json.put("result", "success");
		response.getWriter().write(json.toString());
		out.flush();out.close();
		return null;
//		window.location='gonggaoManager.action';
	}
//	
	//判断是否空值
	private boolean isInvalid(String value) {
		return (value == null || value.length() == 0);
	}
	
	//测试
	public static void main(String[] args) {
		System.out.println();
	}
	public Integer getBaoxiuid() {
		return baoxiuid;
	}
	public void setBaoxiuid(Integer baoxiuid) {
		this.baoxiuid = baoxiuid;
	}




	
}
