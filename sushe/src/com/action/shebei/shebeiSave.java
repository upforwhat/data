package com.action.shebei;

import java.io.PrintWriter;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONObject;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;
import com.dao.BuildingDao;
import com.hibernate.model.Gongao;
import com.hibernate.model.GongaoDAO;
import com.hibernate.model.Shebei;
import com.hibernate.model.ShebeiDAO;

public class shebeiSave extends ActionSupport {

	// ������Action�����ڷ�װ�û��������������
	private String shebeiId;
	private String shebeiName;
	private String shebeiState;
	private String shebeiGoumaishijian;
	private String shebeiYouxiaoshijian;
	private String shebeiZuijinjiachashijian;
	private String shebeiBianhao;
	private String shebeiBuildingName;
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
		//���ж��Ƿ񴫹�������������¥�Ƿ����(ȥ����¥��顣����)
		String strwhere=" Building_Name='"+shebeiBuildingName+"'";
		BuildingDao bd=new  BuildingDao();
		bd.GetList(strwhere, "");
		if(bd.GetList(strwhere, "").isEmpty()){
			response.getWriter().write("����¥�Ҳ���");
			response.getWriter().flush();
			response.getWriter().close();
			return null;
		}
if(isInvalid(shebeiId)){
	//�豸idΪ�վ������
	Shebei sb=new Shebei();
	sb.setShebeiBianhao(shebeiBianhao);
	sb.setShebeiBuildingName(shebeiBuildingName);
	sb.setShebeiGoumaishijian(shebeiGoumaishijian);
	sb.setShebeiName(shebeiName);
	sb.setShebeiState(shebeiState);
	sb.setShebeiYouxiaoshijian(shebeiYouxiaoshijian);
	if(isInvalid(shebeiZuijinjiachashijian)){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date d1 = new Date();
		shebeiZuijinjiachashijian=sdf.format(d1);
	}
	else{
	sb.setShebeiZuijinjiachashijian(shebeiZuijinjiachashijian);
	}
	sbd.save(sb);
}
else{
	//�豸��Ϊ�վ��Ǹ�������
	

	Shebei sb=sbd.findById(Integer.parseInt(shebeiId));
	sb.setShebeiBianhao(shebeiBianhao);
	sb.setShebeiBuildingName(shebeiBuildingName);
	sb.setShebeiGoumaishijian(shebeiGoumaishijian);
	sb.setShebeiName(shebeiName);
	sb.setShebeiState(shebeiState);
	sb.setShebeiYouxiaoshijian(shebeiYouxiaoshijian);
	if(isInvalid(shebeiZuijinjiachashijian)){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date d1 = new Date();
		shebeiZuijinjiachashijian=sdf.format(d1);
	}
	else{
	sb.setShebeiZuijinjiachashijian(shebeiZuijinjiachashijian);
	}
	sbd.save(sb);

}
response.getWriter().write("success");
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

		response.getWriter().write(jobj.toString());// ת��ΪJSOn��ʽ

	}

	// ����
	public static void main(String[] args) {
		System.out.println();
	}


	public String getShebeiName() {
		return shebeiName;
	}

	public void setShebeiName(String shebeiName) {
		this.shebeiName = shebeiName;
	}

	public String getShebeiState() {
		return shebeiState;
	}

	public void setShebeiState(String shebeiState) {
		this.shebeiState = shebeiState;
	}

	public String getShebeiGoumaishijian() {
		return shebeiGoumaishijian;
	}

	public void setShebeiGoumaishijian(String shebeiGoumaishijian) {
		this.shebeiGoumaishijian = shebeiGoumaishijian;
	}

	public String getShebeiYouxiaoshijian() {
		return shebeiYouxiaoshijian;
	}

	public void setShebeiYouxiaoshijian(String shebeiYouxiaoshijian) {
		this.shebeiYouxiaoshijian = shebeiYouxiaoshijian;
	}

	public String getShebeiZuijinjiachashijian() {
		return shebeiZuijinjiachashijian;
	}

	public void setShebeiZuijinjiachashijian(String shebeiZuijinjiachashijian) {
		this.shebeiZuijinjiachashijian = shebeiZuijinjiachashijian;
	}

	public String getShebeiBianhao() {
		return shebeiBianhao;
	}

	public void setShebeiBianhao(String shebeiBianhao) {
		this.shebeiBianhao = shebeiBianhao;
	}

	public String getShebeiBuildingName() {
		return shebeiBuildingName;
	}

	public void setShebeiBuildingName(String shebeiBuildingName) {
		this.shebeiBuildingName = shebeiBuildingName;
	}

	public String getShebeiId() {
		return shebeiId;
	}

	public void setShebeiId(String shebeiId) {
		this.shebeiId = shebeiId;
	}


}
