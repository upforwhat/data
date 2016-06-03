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

	// 下面是Action内用于封装用户请求参数的属性
	private String shebeiId;
	private String shebeiName;
	private String shebeiState;
	private String shebeiGoumaishijian;
	private String shebeiYouxiaoshijian;
	private String shebeiZuijinjiachashijian;
	private String shebeiBianhao;
	private String shebeiBuildingName;
	ShebeiDAO sbd = new ShebeiDAO();

	// 处理用户请求的execute方法
	public String execute() throws Exception {

		// 解决乱码，用于页面输出
		HttpServletResponse response = null;
		response = ServletActionContext.getResponse();
		response.setContentType("text/html;charset=UTF-8");
		response.setCharacterEncoding("UTF-8");
		PrintWriter out = response.getWriter();

		// 创建session对象
		HttpSession session = ServletActionContext.getRequest().getSession();
		// 验证是否正常登录
		if (session.getAttribute("id") == null) {
			out.print("<script language='javascript'>alert('请重新登录！');window.location='Login.jsp';</script>");
			out.flush();
			out.close();
			return null;
		}
		//先判断是否传过来的名称宿舍楼是否存在(去宿舍楼表查。。。)
		String strwhere=" Building_Name='"+shebeiBuildingName+"'";
		BuildingDao bd=new  BuildingDao();
		bd.GetList(strwhere, "");
		if(bd.GetList(strwhere, "").isEmpty()){
			response.getWriter().write("宿舍楼找不到");
			response.getWriter().flush();
			response.getWriter().close();
			return null;
		}
if(isInvalid(shebeiId)){
	//设备id为空就是添加
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
	//设备不为空就是更新数据
	

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

	// 判断是否空值
	private boolean isInvalid(String value) {
		return (value == null || value.length() == 0);
	}

	// 转化为Json格式
	public void toBeJson(List list, int total) throws Exception {
		HttpServletResponse response = ServletActionContext.getResponse();
		HttpServletRequest request = ServletActionContext.getRequest();

		JSONObject jobj = new JSONObject();// new一个JSON
		jobj.accumulate("total", total);// total代表一共有多少数据
		jobj.accumulate("rows", list);// row是代表显示的页的数据

		response.getWriter().write(jobj.toString());// 转化为JSOn格式

	}

	// 测试
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
