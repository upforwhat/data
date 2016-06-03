package com.action.baoxiu.stu;

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



public class baoxiuStuManager extends ActionSupport {

	//下面是Action内用于封装用户请求参数的属性

	private List<Baoxiu> stubaoxiulist;
public JSONObject getJsonObj() {
		return jsonObj;
	}

	public void setJsonObj(JSONObject jsonObj) {
		this.jsonObj = jsonObj;
	}

private String
	shoulistate;
private JSONObject jsonObj;
private String rows;// 每页显示的记录数
private String page;// 当前第几页
private	String sort;
private	String order;
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
 String stuid=(String)session.getAttribute("id");
 int stuid1=Integer.parseInt(stuid);
		BaoxiuDAO bxd=new BaoxiuDAO();
//		bxd.getTotal(parameter, appendhql);
			String where="";
		
			
			if(isInvalid(shoulistate)){
				this.setShoulistate("未处理");
				
			}
			where+="where model.baoxiuStudentId=? and model.state=?";
			Object parameter[]={stuid1,shoulistate};		
			stubaoxiulist=bxd.getList(where, parameter, page, rows, sort, order);
			Object Totalparameter[]={shoulistate};
			String appendhql=" where model.state=?";
			int total=bxd.getTotal(Totalparameter, appendhql);
			System.out.println("有条件的"+total);
			jsonObj=new JSONObject();
			jsonObj.put("total",total);
			jsonObj.put("rows", stubaoxiulist);
			response.getWriter().write(jsonObj.toString());
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

	public List<Baoxiu> getStubaoxiulist() {
		return stubaoxiulist;
	}

	public void setStubaoxiulist(List<Baoxiu> stubaoxiulist) {
		this.stubaoxiulist = stubaoxiulist;
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
