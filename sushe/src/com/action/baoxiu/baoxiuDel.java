package com.action.baoxiu;

import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONObject;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;
import com.bean.*;
import com.dao.*;
import com.hibernate.model.BaoxiuDAO;



public class baoxiuDel extends ActionSupport {

	//������Action�����ڷ�װ�û��������������
	private Integer baoxiuid;

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
		int rs=0;


		JSONObject json=new JSONObject();
 rs=new BaoxiuDAO().delete(new BaoxiuDAO().findById(baoxiuid));
	if(rs==0){
		json.put("result", "failed");
	}else
	{json.put("result","success");
		
	}
	response.getWriter().write(json.toString());
		out.flush();out.close();
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
	public Integer getBaoxiuid() {
		return baoxiuid;
	}
	public void setBaoxiuid(Integer baoxiuid) {
		this.baoxiuid = baoxiuid;
	}



	
}
