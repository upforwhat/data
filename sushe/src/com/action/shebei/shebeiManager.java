package com.action.shebei;

import java.io.PrintWriter;
import java.sql.Timestamp;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONObject;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;
import com.hibernate.model.Gongao;
import com.hibernate.model.GongaoDAO;
import com.hibernate.model.Shebei;
import com.hibernate.model.ShebeiDAO;

public class shebeiManager extends ActionSupport {

	// 下面是Action内用于封装用户请求参数的属性
	private JSONObject jsonObj;
	private String rows;// 每页显示的记录数
	private String page;// 当前第几页
private	String sort;
private	String order;
	private String shebeiBuildingName;
	private List<Shebei> Shebeilist;
	ShebeiDAO sbd = new ShebeiDAO();

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
		if (!isInvalid(shebeiBuildingName)) {
			String hql="from Shebei as sb where sb.shebeiBuildingName='"+shebeiBuildingName+"'";
			if(!isInvalid(sort)){
				hql+=" order by "+" sb."+sort+" ";
				if(!isInvalid(order)){
					hql+=order;
				}
			}
			Shebeilist = sbd.findByShebeiBuildingNamePage(hql, page, rows);
			this.toBeJson(Shebeilist, sbd.getSearchTotal(shebeiBuildingName));
		} else {
String hql=" from Shebei as sb  ";
if(!isInvalid(sort)){
	hql+=" order by "+" sb."+sort+" ";
	if(!isInvalid(order)){
		hql+=order;
	}
}
System.out.println("hql语句"+hql+"按"+sort+"排序"+"升或降"+order);

			Shebeilist = sbd.getShebeiList(hql,page, rows);
			this.toBeJson(Shebeilist, sbd.getTotal());
		}
		// Shebeilist=sbd.findAll();
	

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

	public List<Shebei> getShebeilist() {
		return Shebeilist;
	}

	public void setShebeilist(List<Shebei> shebeilist) {
		Shebeilist = shebeilist;
	}

	public String getShebeiBuildingName() {
		return shebeiBuildingName;
	}

	public void setShebeiBuildingName(String shebeiBuildingName) {
		this.shebeiBuildingName = shebeiBuildingName;
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
