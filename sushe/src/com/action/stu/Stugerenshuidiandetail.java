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

		
		
		JSONObject jobj = new JSONObject();
//		temp=JSONArray.fromObject(Weishengshuidianlist);

		
		//��Ҫ�Ĳ��� ����ѧ��id ���ҵ�����Id 
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
		//�ٸ�������idȥ��ѧ������ж�����ס��
		String sql="select count(*) from student s where s.Student_DomitoryID=? and s.Student_State='��ס'";
		int count= new StudentDao().getStuinDormitory(did, sql);
		//��������id��ʱ�������Ҫ���������� Ȼ��abs
		String hql= "from Weishengshuidian as w where w.domitoryId = ? "
				+ " and w.shiijian <= ? order by  w.shiijian  desc";
		
		SimpleDateFormat da=new SimpleDateFormat("yyyy-MM-dd");
		Date d1=da.parse(shiijian);
		String dd=da.format(d1);
		jobj.put("zhecishiijian", dd);//��μ��ʱ��
		
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
	jobj.put("shancishiijian", ddd);//�ϴμ��ʱ��
	
	jobj.put("thimothdiandushu", thimothdiandushu);//����µ�����
	jobj.put("lastmothdiandushu", lastmothdiandushu);//�ϸ��µ�����
	jobj.put("thimothshuidushu", thimothshuidushu);//�����ˮ����
	jobj.put("lastmothshuidushu", lastmothshuidushu);//�ϸ���ˮ����
	
	Double diandushu=Math.abs(Double.parseDouble(ws[0].getDian())-Double.parseDouble(ws[1].getDian()));
	Double shuidushu=Math.abs(Double.parseDouble(ws[0].getShui())-Double.parseDouble(ws[1].getShui()));
	jobj.put("dianyongle", diandushu);//������õ�Ķ���
	jobj.put("shuiyongle", shuidushu);//�������ˮ����
	//Ȼ��ȥ����� //Ȼ�� ����ƽ��ֵ
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
		  System.out.println("����ˮ���"+shuijinegeren1);*/
		  BigDecimal dianjinegeren=dianjine.divide(new BigDecimal(count),2,5);
		  BigDecimal totaljinegeren =shuijinegeren.add(dianjinegeren);
		  jobj.put("totaljinegeren", totaljinegeren);//������Ǯ
		  jobj.put("shuiyonglejine", shuijine);//����ˮ������
		  jobj.put("dianjineyonglejine", dianjine);//����繲����
		  jobj.put("shuijinegeren", shuijinegeren);//����Ӧ��ˮ��
		  jobj.put("dianjinegeren", dianjinegeren);//����Ӧ�����
		  jobj.put("totaljinegeren", totaljinegeren);//���˹�Ҫ�����
		  jobj.put("ruzhurenshu", count);//������ס����
		  jobj.put("dian", dian);//�絥�� 
		  jobj.put("shui", shui);//ˮ����
		  jobj.put("found","true");//��Ϣ�ҵ�
		}
		else{
			  jobj.put("found","false");//��Ϣû�ҵ�
			  
		}
		
		
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

	public String getShiijian() {
		return shiijian;
	}

	public void setShiijian(String shiijian) {
		this.shiijian = shiijian;
	}

	
}
