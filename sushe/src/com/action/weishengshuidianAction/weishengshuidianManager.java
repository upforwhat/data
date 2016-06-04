package com.action.weishengshuidianAction;

import java.io.PrintWriter;
import java.math.BigDecimal;
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
import com.hibernate.model.Feilv;
import com.hibernate.model.FeilvDAO;
import com.hibernate.model.Weishengshuidian;
import com.hibernate.model.WeishengshuidianDAO;


public class weishengshuidianManager extends ActionSupport {

	//������Action�����ڷ�װ�û��������������
	private String select1;
	private String select2;
	private String total;
	private String rows;// ÿҳ��ʾ�ļ�¼��
	private String page;// ��ǰ�ڼ�ҳ
	private	String sort;//easyui ���������������
	private	String order;//������
	private BigDecimal shuijine;
	private BigDecimal dianjine;
	private BigDecimal zongjine;
	private List<Weishengshuidian>  Weishengshuidianlist;


	//�����û������execute����
	public String execute() throws Exception {
		
		//������룬����ҳ�����
		HttpServletResponse response=null;
		response=ServletActionContext.getResponse();
		response.setContentType("text/html;charset=UTF-8");
		response.setCharacterEncoding("UTF-8");
		PrintWriter out = response.getWriter();
		
		//����session����
		HttpSession session = ServletActionContext.getRequest().getSession();
		//��֤�Ƿ�������¼
		if(session.getAttribute("id")==null){
			out.print("<script language='javascript'>alert('�����µ�¼��');window.location='Login.jsp';</script>");
			out.flush();out.close();return null;
		}
		
	JSONObject jobj = new JSONObject();
	int total=0;
//	temp=JSONArray.fromObject(Weishengshuidianlist);
	Object parameter[]={};
	String appendhql=" where 1=1 ";
	if(!isInvalid(select1)){
		appendhql+=" and model.buildingId="+select1;
		
	}
	if(!isInvalid(select2)){
		appendhql+=" and model.domitoryId="+select2;
	}
total=new WeishengshuidianDAO().getTotal(parameter, appendhql);

Weishengshuidianlist=new WeishengshuidianDAO().getList(appendhql, parameter, page, rows, sort, order);
	SimpleDateFormat da=new SimpleDateFormat("yyyy-MM-dd");
	jobj.put("total",total);
	jobj.put("rows", Weishengshuidianlist);
response.getWriter().write(jobj.toString());
response.getWriter().flush();
response.getWriter().close();


	
		
		return null;
		
	}
	
	//�ж��Ƿ��ֵ
	private boolean isInvalid(String value) {
		return (value == null || value.length() == 0);
	}
	
	//����
	public static void main(String[] args) {
		System.out.println();
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
	public List<Weishengshuidian> getWeishengshuidianlist() {
		return Weishengshuidianlist;
	}
	public void setWeishengshuidianlist(List<Weishengshuidian> weishengshuidianlist) {
		Weishengshuidianlist = weishengshuidianlist;
	}

	public String getTotal() {
		return total;
	}

	public void setTotal(String total) {
		this.total = total;
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
