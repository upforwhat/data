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

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;
import com.bean.StudentBean;
import com.bean.weiguiBean;
import com.dao.StudentDao;
import com.dao.weiguiDao;
import com.hibernate.model.Gongao;
import com.hibernate.model.GongaoDAO;
import com.hibernate.model.Shebei;
import com.hibernate.model.ShebeiDAO;

public class stuweigui extends ActionSupport {

	// 下面是Action内用于封装用户请求参数的属性
	private JSONObject jsonObj;
	private StudentBean stu;
	private String rows;// 每页显示的记录数
	private String page;// 当前第几页
private	String sort;
private	String order;
	private List<weiguiBean> weiguilist;
	// 处理用户请求的execute方法
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
		Integer did=stu.getStudent_DomitoryID();
		String didt=String.valueOf(did);
		weiguiDao wd=new weiguiDao();
		weiguilist=wd.StuGetList(didt,sort, order);
List temp;
temp=JSONArray.fromObject(weiguilist);
JSONObject result=new JSONObject();
//System.out.println("所有数据"+list.size());
//System.out.println("格式是"+temp.toString());
//System.out.println("页数是"+this.getPage());
//System.out.println("行数是"+this.getRows());
temp= temp.subList(rows*(page-1), (rows*(page-1)+rows>weiguilist.size()?weiguilist.size():rows*(page-1)+rows));
result.put("total", weiguilist.size());
result.put("rows", temp);
response.getWriter().write(result.toString());
	
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

	public List<weiguiBean> getWeiguilist() {
		return weiguilist;
	}

	public void setWeiguilist(List<weiguiBean> weiguilist) {
		this.weiguilist = weiguilist;
	}






}
