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

	//������Action�����ڷ�װ�û��������������

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
private String rows;// ÿҳ��ʾ�ļ�¼��
private String page;// ��ǰ�ڼ�ҳ
private	String sort;
private	String order;
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
 String stuid=(String)session.getAttribute("id");
 int stuid1=Integer.parseInt(stuid);
		BaoxiuDAO bxd=new BaoxiuDAO();
//		bxd.getTotal(parameter, appendhql);
			String where="";
		
			
			if(isInvalid(shoulistate)){
				this.setShoulistate("δ����");
				
			}
			where+="where model.baoxiuStudentId=? and model.state=?";
			Object parameter[]={stuid1,shoulistate};		
			stubaoxiulist=bxd.getList(where, parameter, page, rows, sort, order);
			Object Totalparameter[]={shoulistate};
			String appendhql=" where model.state=?";
			int total=bxd.getTotal(Totalparameter, appendhql);
			System.out.println("��������"+total);
			jsonObj=new JSONObject();
			jsonObj.put("total",total);
			jsonObj.put("rows", stubaoxiulist);
			response.getWriter().write(jsonObj.toString());
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
