package com.action;

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
import com.hibernate.model.BaoxiuDAO;


public class weiguiManager extends ActionSupport {

	//下面是Action内用于封装用户请求参数的属性
	private String select1;
	private String select2;
	private JSONObject jsonObj;
	private String rows;// 每页显示的记录数
	private String page;// 当前第几页
	private	String sort;
	private	String order;

	private List<weiguiBean>  weiguilist;
	


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
		/*//查询条件
	
		if(!isInvalid(select1))
		{
		
			strWhere+=" and w.Building_ID="+Integer.parseInt(select1);
		}
		if(!(isInvalid(select2)))
		{
			strWhere+=" and w.Domitory_ID="+select2;
		}
		
		//查询所有
		*/
		String parametersql="";
		if(!isInvalid(select1)){
			parametersql=" and w.Building_ID="+select1+"\t";
		}
		if(!isInvalid(select2)){
			parametersql=" and w.Domitory_ID="+select2+"\t";
		}
	 
	
		weiguilist=new weiguiDao().GetList(parametersql, sort, order);
		JSONObject result=new JSONObject();
		
		//查询所有
		
		List temp;
		temp=JSONArray.fromObject(weiguilist);
		System.out.println("所有数据"+weiguilist.size());
		System.out.println("格式是"+temp.toString());
		System.out.println("页数是"+this.getPage());
		System.out.println("行数是"+this.getRows());
		temp= temp.subList(rows*(page-1), (rows*(page-1)+rows>weiguilist.size()?weiguilist.size():rows*(page-1)+rows));
		result.put("total", weiguilist.size());
		result.put("rows", temp);
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
	public List<weiguiBean> getWeiguilist() {
		return weiguilist;
	}
	public void setWeiguilist(List<weiguiBean> weiguilist) {
		this.weiguilist = weiguilist;
	}
	public String getSelect1() {
		return select1;
	}
	public void setSelect1(String select1) {
		this.select1 = select1;
	}
	public String getSelect2() {
		return select2;
	}
	public void setSelect2(String select2) {
		this.select2 = select2;
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
