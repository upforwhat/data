package com.action.baoxiu;

import java.io.PrintWriter;
import java.sql.Timestamp;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONObject;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;
import com.hibernate.model.Baoxiu;
import com.hibernate.model.BaoxiuDAO;
import com.hibernate.model.Gongao;
import com.hibernate.model.GongaoDAO;



public class baoxiuManager extends ActionSupport {

	//下面是Action内用于封装用户请求参数的属性

	private List<Baoxiu> baoxiulist;
	private String rows;// 每页显示的记录数
	private String page;// 当前第几页
	private	String sort;//easyui 传过来的排序参数
	private	String order;//升或降序
	private String shoulistate;
	//处理用户请求的execute方法 page sort order
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
		
		BaoxiuDAO bxd=new BaoxiuDAO();
		String appendhql="";  
		Object parameter[]={};
		int total=0;
		 if(!isInvalid(shoulistate)){
			 appendhql=" where  model.state='"+shoulistate+"'";
			 total=bxd.getTotal(parameter, appendhql);
		 }
		 total=bxd.getTotal(parameter, appendhql);
		if(!isInvalid(shoulistate)){
			baoxiulist=bxd.findByState(shoulistate);
		}else{
		
			baoxiulist=bxd.getList(appendhql, parameter, page, rows, sort, order);
		}
		JSONObject json =new JSONObject();
		json.put("rows", baoxiulist);
		json.put("total",total);
		response.getWriter().write(json.toString());
		response.getWriter().flush();
		response.getWriter().close();
		return null;
	}
	
	//判断是否空值
	private boolean isInvalid(String value) {
		return (value == null || value.length() == 0);
	}
	
	//测试
	public static void main(String[] args) {
		System.out.println();
	}

	public List<Baoxiu> getBaoxiulist() {
		return baoxiulist;
	}

	public void setBaoxiulist(List<Baoxiu> baoxiulist) {
		this.baoxiulist = baoxiulist;
	}

	public String getShoulistate() {
		return shoulistate;
	}

	public void setShoulistate(String shoulistate) {
		this.shoulistate = shoulistate;
	}

	

	public String getRows() {
		return rows;
	}

	public void setRows(String rows) {
		this.rows = rows;
	}

	public String getPage() {
		return page;
	}

	public void setPage(String page) {
		this.page = page;
	}

	public String getSort() {
		return sort;
	}

	public void setSort(String sort) {
		this.sort = sort;
	}

	public String getOrder() {
		return order;
	}

	public void setOrder(String order) {
		this.order = order;
	}





	
	

	
}
