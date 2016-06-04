package com.action.shebei;

import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONObject;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;
import com.bean.*;
import com.dao.*;
import com.hibernate.model.Shebei;
import com.hibernate.model.ShebeiDAO;



public class shebeiDel extends ActionSupport {

	//������Action�����ڷ�װ�û��������������
	private String shebeiId;


	//�����û������execute����
	public String execute() throws Exception {
		
		//������룬����ҳ�����
		HttpServletResponse response=null;
		response=ServletActionContext.getResponse();
		response.setContentType("text/html;charset=UTF-8");
		response.setCharacterEncoding("UTF-8");
		PrintWriter out = response.getWriter();
		
		//����session����
		ShebeiDAO sbd=new ShebeiDAO();
		Shebei sb=null;
		HttpSession session = ServletActionContext.getRequest().getSession();
		//��֤�Ƿ�������¼
		if(session.getAttribute("id")==null){
			out.print("<script language='javascript'>alert('�����µ�¼��');window.location='Login.jsp';</script>");
			out.flush();out.close();return null;
		}
		if(!isInvalid(shebeiId)){
			sb=sbd.findById(Integer.parseInt(shebeiId));
sbd.delete(sb);
}


JSONObject jobj = new JSONObject();// newһ��JSON
jobj.accumulate("result", "success");//   ��������
jobj.put("result","success");
response.getWriter().write(jobj.toString());// ת��ΪJSOn��ʽ
response.getWriter().flush();  
response.getWriter().close(); 
		return null;
//		window.location='gonggaoManager.action';
	}
//	
	//�ж��Ƿ��ֵ
	private boolean isInvalid(String value) {
		return (value == null || value.length() == 0);
	}
	
	//����
	public static void main(String[] args) {
		System.out.println();
	}
	public String getShebeiId() {
		return shebeiId;
	}
	public void setShebeiId(String shebeiId) {
		this.shebeiId = shebeiId;
	}


	
}
