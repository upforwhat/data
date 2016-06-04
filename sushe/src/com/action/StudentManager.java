package com.action;

import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.struts2.ServletActionContext;

import com.util.JsonUtil;
import com.opensymphony.xwork2.ActionSupport;
import com.bean.*;
import com.dao.*;


public class StudentManager extends ActionSupport {

	//下面是Action内用于封装用户请求参数的属性
	private List<StudentBean> list;
	private String rows;// 每页显示的记录数
	private String page;// 当前第几页
private	String sort;
private	String order;
	public List<StudentBean> getList() {
		return list;
	}
	public void setList(List<StudentBean> list) {
		this.list = list;
	}
	private String SearchRow;
	private String SearchKey;
	private String State;
	public String getState() {
		return State;
	}
	public void setState(String state) {
		State = state;
	}
	public String getSearchRow() {
		return SearchRow;
	}
	public void setSearchRow(String searchRow) {
		SearchRow = searchRow;
	}
	public String getSearchKey() {
		return SearchKey;
	}
	public void setSearchKey(String searchKey) {
		SearchKey = searchKey;
	}
	//处理用户请求的execute方法
	public String execute() throws Exception {
		
		//解决乱码，用于页面输出
	    int rows=Integer.parseInt(this.getRows());
	    if(rows==0)
	    {
	    	rows=10;
	    }
	    int page=Integer.parseInt(this.getPage());
	    
	    if(page==0)
	    {
	    	page=1;
	    }
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
		//查询条件
		String strWhere="1=1";
		if(!(isInvalid(SearchKey)))
		{
			strWhere+=" and "+SearchRow+"='"+SearchKey+"'";
		}
		if(!(isInvalid(State)))
		{
			strWhere+=" and Student_State='"+State+"'";
		}
	
		JSONObject result=new JSONObject();
	
		//查询所有
		list=new StudentDao().GetAllList(strWhere,sort,order);
		
		List temp;
		temp=JSONArray.fromObject(list);
//		System.out.println("所有数据"+list.size());
//		System.out.println("格式是"+temp.toString());
//		System.out.println("页数是"+this.getPage());
//		System.out.println("行数是"+this.getRows());
		temp= temp.subList(rows*(page-1), (rows*(page-1)+rows>list.size()?list.size():rows*(page-1)+rows));
		result.put("total", list.size());
		result.put("rows", temp);
//		jobj.accumulate("total", total);// total代表一共有多少数据
//		jobj.accumulate("rows", list);// row是代表显示的页的数据
		response.getWriter().write(result.toString());// 转化为JSOn格式
		
	
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
