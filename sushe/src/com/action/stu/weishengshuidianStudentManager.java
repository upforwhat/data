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

	//������Action�����ڷ�װ�û��������������
	private List<Weishengshuidian>  Weishengshuidianlist;//Υ����Ϣ��װ
//Υ����ֶ�
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
// Υ����ֶ�
private String rows;// ÿҳ��ʾ�ļ�¼��
private String page;// ��ǰ�ڼ�ҳ
private	String sort;//easyui ���������������
private	String order;//������

	//�����û������execute����
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
		//��Ҫ�Ĳ��� ����ѧ��id ���ҵ�����Id ��������id����������Ϣ
		
		String stuid= (String)session.getAttribute("id");
		StudentBean stu=new StudentDao().GetBean(Integer.parseInt(stuid));
		Integer did=stu.getStudent_DomitoryID();
		Weishengshuidianlist=new WeishengshuidianDAO().StuFind(sort, order, did);
		
		//��ѯ����
		JSONObject jobj = new JSONObject();
		List temp;
//		temp=JSONArray.fromObject(Weishengshuidianlist);
		temp= Weishengshuidianlist.subList(rows*(page-1), (rows*(page-1)+rows>Weishengshuidianlist.size()?Weishengshuidianlist.size():rows*(page-1)+rows));
		jobj.put("total", Weishengshuidianlist.size());
		jobj.put("rows", temp);
		
response.getWriter().write(jobj.toString());
	
		//��ѯ����
	
		
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
