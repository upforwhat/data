package com.action.stu;

import java.io.PrintWriter;
import java.lang.reflect.Array;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;
import com.bean.*;
import com.dao.*;
import com.hibernate.model.Feilv;
import com.hibernate.model.FeilvDAO;
import com.hibernate.model.Weishengshuidian;
import com.hibernate.model.WeishengshuidianDAO;


public class Stugerenshuidiandetail extends ActionSupport {

	//下面是Action内用于封装用户请求参数的属性
	private List<Weishengshuidian>  Weishengshuidianlist;//违规信息封装
//违规表字段
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
// 违规表字段
private String rows;// 每页显示的记录数
private String page;// 当前第几页
private	String sort;//easyui 传过来的排序参数
private	String order;//升或降序

	//处理用户请求的execute方法
	public String execute() throws Exception {
	   

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

		
		
		JSONObject jobj = new JSONObject();
//		temp=JSONArray.fromObject(Weishengshuidianlist);

		
		//需要的参数 根据学生id 查找到宿舍Id 
		String stuid= (String)session.getAttribute("id");
		StudentBean stu=new StudentDao().GetBean(Integer.parseInt(stuid));
		Integer did=stu.getStudent_DomitoryID();
		DomitoryBean db=new DomitoryDao().GetBean(did);
		
		String DomitoryName=db.getDomitory_Name();
		jobj.put("DomitoryName", DomitoryName);
		String stuname=stu.getStudent_Name();
		String stuxuehao=stu.getStudent_Username();
		jobj.put("stuname", stuname);
		jobj.put("stuxuehao", stuxuehao);
		//再根据宿舍id去查学生表查有多少人住宿
		String sql="select count(*) from student s where s.Student_DomitoryID=? and s.Student_State='入住'";
		int count= new StudentDao().getStuinDormitory(did, sql);
		//根据宿舍id和时间查找需要的两条数据 然后abs
		String hql= "from Weishengshuidian as w where w.domitoryId = ? "
				+ " and w.shiijian <= ? order by  w.shiijian  desc";
		
		SimpleDateFormat da=new SimpleDateFormat("yyyy-MM-dd");
		Date d1=da.parse(shiijian);
		String dd=da.format(d1);
		jobj.put("zhecishiijian", dd);//这次检查时间
		
		Object value[]={did,shiijian};
		Weishengshuidianlist=new WeishengshuidianDAO().findByMYProperty(hql, value);
		
		
		if(Weishengshuidianlist.size()>=1){
		Weishengshuidian []ws=new Weishengshuidian[Weishengshuidianlist.size()];
		for(int i=0;i<Weishengshuidianlist.size();i++){
			ws[i]=Weishengshuidianlist.get(i);
		}
		
	String thimothdiandushu = ws[0].getDian();
	String lastmothdiandushu=ws[1].getDian();
	String thimothshuidushu = ws[0].getShui();
	String lastmothshuidushu=ws[1].getShui();
	String shangcishiijian=ws[1].getShiijian();
	Date d2=da.parse(shangcishiijian);
	String ddd=da.format(d2);
	jobj.put("shancishiijian", ddd);//上次检查时间
	
	jobj.put("thimothdiandushu", thimothdiandushu);//这个月电电表数
	jobj.put("lastmothdiandushu", lastmothdiandushu);//上个月电电表数
	jobj.put("thimothshuidushu", thimothshuidushu);//这个月水表数
	jobj.put("lastmothshuidushu", lastmothshuidushu);//上个月水表数
	
	Double diandushu=Math.abs(Double.parseDouble(ws[0].getDian())-Double.parseDouble(ws[1].getDian()));
	Double shuidushu=Math.abs(Double.parseDouble(ws[0].getShui())-Double.parseDouble(ws[1].getShui()));
	jobj.put("dianyongle", diandushu);//这个月用电的度数
	jobj.put("shuiyongle", shuidushu);//这个月用水吨数
	//然后去查费率 //然后 计算平均值
		  Feilv fl=new FeilvDAO().findById(1);
		  BigDecimal diandushut=new BigDecimal(diandushu);
		  BigDecimal shuidushut=new BigDecimal(shuidushu);
		  BigDecimal dian=fl.getDian();
		  BigDecimal shui= fl.getShui();
		  BigDecimal shuijine= fl.getShui().multiply(shuidushut);
		  BigDecimal dianjine=dian.multiply(diandushut);
		  BigDecimal totaljine=shuijine.add(dianjine);
		  BigDecimal shuijinegeren=shuijine.divide(new BigDecimal(count),2,5);
		 /* BigDecimal shuijinegeren1=shuijine.divide(new BigDecimal(count),2,5);
		  System.out.println("个人水金额"+shuijinegeren1);*/
		  BigDecimal dianjinegeren=dianjine.divide(new BigDecimal(count),2,5);
		  BigDecimal totaljinegeren =shuijinegeren.add(dianjinegeren);
		  jobj.put("totaljinegeren", totaljinegeren);//共多少钱
		  jobj.put("shuiyonglejine", shuijine);//宿舍水共用了
		  jobj.put("dianjineyonglejine", dianjine);//宿舍电共用了
		  jobj.put("shuijinegeren", shuijinegeren);//个人应交水费
		  jobj.put("dianjinegeren", dianjinegeren);//个人应交电费
		  jobj.put("totaljinegeren", totaljinegeren);//个人共要交金额
		  jobj.put("ruzhurenshu", count);//宿舍入住人数
		  jobj.put("dian", dian);//电单价 
		  jobj.put("shui", shui);//水单价
		  jobj.put("found","true");//信息找到
		}
		else{
			  jobj.put("found","false");//信息没找到
			  
		}
		
		
response.getWriter().write(jobj.toString());
	
		//查询所有
	
		
		return null;
		
	}
	
	//判断是否空值
	private boolean isInvalid(String value) {
		return (value == null || value.length() == 0);
	}
	
	//测试
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

	public String getShiijian() {
		return shiijian;
	}

	public void setShiijian(String shiijian) {
		this.shiijian = shiijian;
	}

	
}
