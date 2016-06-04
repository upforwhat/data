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

	//������Action�����ڷ�װ�û��������������
	private String select1;
	private String select2;
	private JSONObject jsonObj;
	private String rows;// ÿҳ��ʾ�ļ�¼��
	private String page;// ��ǰ�ڼ�ҳ
	private	String sort;
	private	String order;

	private List<weiguiBean>  weiguilist;
	


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
		/*//��ѯ����
	
		if(!isInvalid(select1))
		{
		
			strWhere+=" and w.Building_ID="+Integer.parseInt(select1);
		}
		if(!(isInvalid(select2)))
		{
			strWhere+=" and w.Domitory_ID="+select2;
		}
		
		//��ѯ����
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
		
		//��ѯ����
		
		List temp;
		temp=JSONArray.fromObject(weiguilist);
		System.out.println("��������"+weiguilist.size());
		System.out.println("��ʽ��"+temp.toString());
		System.out.println("ҳ����"+this.getPage());
		System.out.println("������"+this.getRows());
		temp= temp.subList(rows*(page-1), (rows*(page-1)+rows>weiguilist.size()?weiguilist.size():rows*(page-1)+rows));
		result.put("total", weiguilist.size());
		result.put("rows", temp);
		response.getWriter().write(result.toString());// ת��ΪJSOn��ʽ
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
