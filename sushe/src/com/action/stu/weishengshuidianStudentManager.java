package com.action.stu;

import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;
import com.bean.*;
import com.dao.*;
import com.hibernate.model.Weishengshuidian;
import com.hibernate.model.WeishengshuidianDAO;


public class weishengshuidianStudentManager extends ActionSupport {

	//下面是Action内用于封装用户请求参数的属性
	private List<Weishengshuidian>  Weishengshuidianlist;//违规信息封装
//违规表字段
	private Integer weishengshuidianId;
	private Integer domitoryId;
	private String score;
	private String shui;
	private String dian;
	private String jine;
	private String shiijian;
	private Integer buildingId;
	private String wssdBuildingName;
	private String wssdDomitoryName;
	private String gerenjine;
	public String getGerenjine() {
		return gerenjine;
	}

	public void setGerenjine(String gerenjine) {
		this.gerenjine = gerenjine;
	}

	private String total;
// 违规表字段
private String rows;// 每页显示的记录数
private String page;// 当前第几页
private	String sort;//easyui 传过来的排序参数
private	String order;//升或降序

	//处理用户请求的execute方法
	public String execute() throws Exception {
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
		//需要的参数 根据学生id 查找到宿舍Id 根据宿舍id查找卫生信息
		
		String stuid= (String)session.getAttribute("id");
		StudentBean stu=new StudentDao().GetBean(Integer.parseInt(stuid));
		Integer did=stu.getStudent_DomitoryID();
		Weishengshuidianlist=new WeishengshuidianDAO().StuFind(sort, order, did);
		
		//查询条件
		JSONObject jobj = new JSONObject();
		List temp;
//		temp=JSONArray.fromObject(Weishengshuidianlist);
		temp= Weishengshuidianlist.subList(rows*(page-1), (rows*(page-1)+rows>Weishengshuidianlist.size()?Weishengshuidianlist.size():rows*(page-1)+rows));
		jobj.put("total", Weishengshuidianlist.size());
		jobj.put("rows", temp);
		
response.getWriter().write(jobj.toString());
	
		//查询所有
	
		
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
	public List<Weishengshuidian> getWeishengshuidianlist() {
		return Weishengshuidianlist;
	}
	public void setWeishengshuidianlist(List<Weishengshuidian> weishengshuidianlist) {
		Weishengshuidianlist = weishengshuidianlist;
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
