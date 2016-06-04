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

	// ������Action�����ڷ�װ�û��������������
	private JSONObject jsonObj;
	private String rows;// ÿҳ��ʾ�ļ�¼��
	private String page;// ��ǰ�ڼ�ҳ
private	String sort;
private	String order;
	private String shebeiBuildingName;
	private List<Shebei> Shebeilist;
	ShebeiDAO sbd = new ShebeiDAO();

	// �����û������execute����
	public String execute() throws Exception {

		// ������룬����ҳ�����
		HttpServletResponse response = null;
		response = ServletActionContext.getResponse();
		response.setContentType("text/html;charset=UTF-8");
		response.setCharacterEncoding("UTF-8");
		PrintWriter out = response.getWriter();

		// ����session����
		HttpSession session = ServletActionContext.getRequest().getSession();
		// ��֤�Ƿ�������¼
		if (session.getAttribute("id") == null) {
			out.print("<script language='javascript'>alert('�����µ�¼��');window.location='Login.jsp';</script>");
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
System.out.println("hql���"+hql+"��"+sort+"����"+"����"+order);

			Shebeilist = sbd.getShebeiList(hql,page, rows);
			this.toBeJson(Shebeilist, sbd.getTotal());
		}
		// Shebeilist=sbd.findAll();
	

		return null;
	}

	// �ж��Ƿ��ֵ
	private boolean isInvalid(String value) {
		return (value == null || value.length() == 0);
	}

	// ת��ΪJson��ʽ
	public void toBeJson(List list, int total) throws Exception {
		HttpServletResponse response = ServletActionContext.getResponse();
		HttpServletRequest request = ServletActionContext.getRequest();

		JSONObject jobj = new JSONObject();// newһ��JSON
		jobj.accumulate("total", total);// total����һ���ж�������
		jobj.accumulate("rows", list);// row�Ǵ�����ʾ��ҳ������

		response.setCharacterEncoding("utf-8");// ָ��Ϊutf-8
		response.getWriter().write(jobj.toString());// ת��ΪJSOn��ʽ

	}

	// ����
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
