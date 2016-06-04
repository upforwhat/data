package com.action.gonggaoAction;

import java.io.PrintWriter;
import java.sql.Timestamp;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;
import com.bean.*;
import com.dao.*;
import com.hibernate.model.Gongao;
import com.hibernate.model.GongaoDAO;
import com.hibernate.model.Weishengshuidian;
import com.hibernate.model.WeishengshuidianDAO;

public class gonggaoUpdate extends ActionSupport {

	// ������Action�����ڷ�װ�û��������������
	private Integer gonggaoId;

	public Integer getGonggaoId() {
		return gonggaoId;
	}

	public void setGonggaoId(Integer gonggaoId) {
		this.gonggaoId = gonggaoId;
	}

	private Gongao gg;

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
		GongaoDAO ggd = new GongaoDAO();
		gg = ggd.findById(gonggaoId);
		return "success";

	}

	// �ж��Ƿ��ֵ
	private boolean isInvalid(String value) {
		return (value == null || value.length() == 0);
	}

	// ����
	public static void main(String[] args) {
		System.out.println();
	}

	public Gongao getGg() {
		return gg;
	}

	public void setGg(Gongao gg) {
		this.gg = gg;
	}

}
