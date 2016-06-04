package com.action.weishengshuidianAction;

import java.io.PrintWriter;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;
import org.hibernate.Session;

import com.opensymphony.xwork2.ActionSupport;
import com.bean.*;
import com.dao.*;
import com.hibernate.model.BaseHibernateDAO;
import com.hibernate.model.Feilv;
import com.hibernate.model.FeilvDAO;
import com.hibernate.model.Weishengshuidian;
import com.hibernate.model.WeishengshuidianDAO;


public class WeishengshuidianAdd extends ActionSupport {

	//下面是Action内用于封装用户请求参数的属性
	private String select1;
	private String select2;
	private String newdiandushu;
	private String newshuidushu;
	private String newscore;
	private String newshijian;
	private List<Weishengshuidian>  Weishengshuidianlist;
	//处理用户请求的execute方法
	public String execute() throws Exception {
		Session sess=new BaseHibernateDAO().getSession();
		//解决乱码，用于页面输出
		HttpServletResponse response=null;
		response=ServletActionContext.getResponse();
		response.setContentType("text/html;charset=UTF-8");
		response.setCharacterEncoding("UTF-8");
		PrintWriter out = response.getWriter();
		
		//创建session对象
		HttpSession session = ServletActionContext.getRequest().getSession();
		//验证是否正常登录
		if(session.getAttribute("id")==null){
			out.print("<script language='javascript'>alert('请重新登录！');window.location='Login.jsp';</script>");
			out.flush();out.close();return null;
		}
		Feilv fl=new Feilv();
		String wssdBuildingName=new BuildingDao().GetBean(Integer.parseInt(select1)).getBuilding_Name();
		String wssdDomitoryName=new DomitoryDao().GetBean(Integer.parseInt(select2)).getDomitory_Name();
		Weishengshuidian wsd=new Weishengshuidian();
		WeishengshuidianDAO wd=new WeishengshuidianDAO();
		FeilvDAO fd=new FeilvDAO();
		fl=fd.findById(1);				
		wsd.setBuildingId(Integer.parseInt(select1));
		wsd.setDomitoryId(Integer.parseInt(select2));
		wsd.setShui(newshuidushu);
		wsd.setDian(newdiandushu);
		wsd.setScore(newscore);
		wsd.setShiijian(newshijian);
		wsd.setWssdBuildingName(wssdBuildingName);
		wsd.setWssdDomitoryName(wssdDomitoryName);
		BigDecimal nshuidushu=new BigDecimal(newshuidushu); 
		BigDecimal ndiandushu=new BigDecimal(newdiandushu); 
		BigDecimal diandanjia=fl.getDian();
		BigDecimal shuidanjia=fl.getShui();
//		BigDecimal s=nshuidushu.multiply(shuidanjia);
//		BigDecimal d=ndiandushu.multiply(diandanjia);
		String hql= "from Weishengshuidian as w where w.domitoryId = ? "
				+ " and w.shiijian <= ? order by  w.shiijian  desc";

		Object value[]={Integer.parseInt(select2),newshijian};
		Weishengshuidianlist=new WeishengshuidianDAO().findByMYProperty(hql, value);
		String lastmonthdiandushu=null;
		String lastmonthshuidushu=null;
		if(Weishengshuidianlist.size()>=1){
		Weishengshuidian []ws=new Weishengshuidian[Weishengshuidianlist.size()];
		for(int i=0;i<Weishengshuidianlist.size();i++){
			ws[i]=Weishengshuidianlist.get(i);
		}
	lastmonthdiandushu = ws[0].getDian();
    lastmonthshuidushu = ws[0].getShui();
		}
		if(isInvalid(lastmonthdiandushu)){
			lastmonthdiandushu="0";
			
		}
		if(isInvalid(lastmonthshuidushu)){
			lastmonthshuidushu="0";
			
		}
	
//		wsd.setJine(s.add(d).toString());
		BigDecimal oshuidushu=new BigDecimal(lastmonthshuidushu); 
		BigDecimal odiandushu=new BigDecimal(lastmonthdiandushu);
	if(nshuidushu.compareTo(oshuidushu)==-1||ndiandushu.compareTo(odiandushu)==-1){
		out.print("<script language='javascript'>alert('水电度数不正确！');history.back(-1);</script>");
		out.flush();out.close();
			return null;
		}
		BigDecimal shuiyongle=nshuidushu.subtract(oshuidushu);
		BigDecimal dianyongle=ndiandushu.subtract(odiandushu);
		BigDecimal shuijine=shuiyongle.multiply(shuidanjia);
		BigDecimal dianjine=dianyongle.multiply(diandanjia);
		BigDecimal zongjine=shuijine.add(dianjine);
	wsd.setDianjine(dianjine);
	wsd.setShuijine(shuijine);
	wsd.setZongjine(zongjine);
		wd.save(wsd);
		return "success";
		
	}
	
	//判断是否空值
	private boolean isInvalid(String value) {
		return (value == null || value.length() == 0);
	}
	
	//测试
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

	public String getNewdiandushu() {
		return newdiandushu;
	}

	public void setNewdiandushu(String newdiandushu) {
		this.newdiandushu = newdiandushu;
	}

	public String getNewshuidushu() {
		return newshuidushu;
	}

	public void setNewshuidushu(String newshuidushu) {
		this.newshuidushu = newshuidushu;
	}

	public String getNewscore() {
		return newscore;
	}

	public void setNewscore(String newscore) {
		this.newscore = newscore;
	}

	public String getNewshijian() {
		return newshijian;
	}

	public void setNewshijian(String newshijian) {
		this.newshijian = newshijian;
	}




	
}
