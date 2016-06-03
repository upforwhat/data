package com.action.weishengshuidianAction;

import java.io.PrintWriter;
import java.math.BigDecimal;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONObject;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;
import com.bean.*;
import com.dao.*;
import com.hibernate.model.Feilv;
import com.hibernate.model.FeilvDAO;
import com.hibernate.model.Weishengshuidian;
import com.hibernate.model.WeishengshuidianDAO;

public class WeishengshuidianUpdateSave extends ActionSupport {

	// 下面是Action内用于封装用户请求参数的属性

	private List<Weishengshuidian> Weishengshuidianlist,nWeishengshuidianlist;
	private String weishengshuidianId;
	private String domitoryId;
	private String score;
	private String shui;
	private String dian;
	private String jine;
	private String shiijian;
	private String buildingId;
	private String wssdBuildingName;
	private String wssdDomitoryName;

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

		// 查询用户名是否存在
		/*
		 * List<DomitoryBean> list=new
		 * DomitoryDao().GetList("Domitory_Name='"+Domitory_Name
		 * +"' and Domitory_BuildingID="
		 * +Domitory_BuildingID+" and Domitory_ID!="+Domitory_ID, "");
		 * if(list.size()>0) { out.print(
		 * "<script language='javascript'>alert('该楼宇中已经存在该寝室号！');history.back(-1);</script>"
		 * ); out.flush();out.close();return null; }
		 */

		Weishengshuidian wssd = null;
		WeishengshuidianDAO wssdd = new WeishengshuidianDAO();
		wssd = wssdd.findById(Integer.parseInt(weishengshuidianId));

		Feilv fl = new Feilv();
		FeilvDAO fd = new FeilvDAO();
		fl = fd.findById(1);
		JSONObject json = new JSONObject();
		BigDecimal nshuidushu = new BigDecimal(shui);
		System.out.println("新的水度数：" + nshuidushu);
		BigDecimal ndiandushu = new BigDecimal(dian);
		System.out.println("新的电度数：" + ndiandushu);
		BigDecimal diandanjia = fl.getDian();
		System.out.println("电单价" + diandanjia);
		BigDecimal shuidanjia = fl.getShui();
		System.out.println("水单价" + shuidanjia);

		String hql = "from Weishengshuidian as w where w.domitoryId = ? "
				+ " and w.shiijian <= ? order by  w.shiijian  desc ";
		Integer id = wssd.getDomitoryId();
		Object value[] = { id, shiijian };
		Weishengshuidianlist = new WeishengshuidianDAO().findByMYProperty(hql,
				value);
		String lastmonthdiandushu = "0";
		String lastmonthshuidushu = "0";

		if (Weishengshuidianlist.size() > 1) {
			Weishengshuidian[] ws = new Weishengshuidian[Weishengshuidianlist
					.size()];
			for (int i = 0; i < Weishengshuidianlist.size(); i++) {
				ws[i] = Weishengshuidianlist.get(i);
			}

			lastmonthdiandushu = ws[1].getDian();// 更新数据的话因为数据已经存在，再拿第一条比较没有意义
			System.out.println("上月用电度数：" + lastmonthdiandushu); // 所以只能拿第二条，所以必须要确保第一条原始数据（开始使用的时候表的度数）的准确性，
			// 判断如果第一条数据未空给赋0；表示从零开始
			lastmonthshuidushu = ws[1].getShui();
			//修改数据之前还有数据,就需要再查找一次比这时间大的一条数据,把后一个月的水电更新
			String nhql = "from Weishengshuidian as w where w.domitoryId = ? "
					+ " and w.shiijian > ? order by  w.shiijian  asc ";
			nWeishengshuidianlist = new WeishengshuidianDAO().findByMYProperty(nhql,
					value);
			if(!nWeishengshuidianlist.isEmpty()){
				Weishengshuidian nws= new Weishengshuidian();
				nws=nWeishengshuidianlist.get(0);
				BigDecimal backshuidushu = new BigDecimal(nws.getShui());
				BigDecimal backdiandushu = new BigDecimal(nws.getDian());
				if (backshuidushu.compareTo(nshuidushu) == -1
						|| backdiandushu.compareTo(ndiandushu) == -1) {
					json.put("result", "请检查数据是否正确");
					response.getWriter().write(json.toString());
					response.getWriter().flush();
					response.getWriter().close();
					return null;

				} else {
					BigDecimal shuiyongle = backshuidushu.subtract(nshuidushu);
					System.out.println("用水度数：" + shuiyongle);
					BigDecimal dianyongle = backdiandushu.subtract(ndiandushu);
					System.out.println("用电度数：" + dianyongle);
					BigDecimal shuijine = shuidanjia.multiply(shuiyongle);
					System.out.println("用水金额：" + shuijine);
					BigDecimal dianjine = diandanjia.multiply(dianyongle);
					System.out.println("用电金额：" + dianjine);
					BigDecimal zongjine = shuijine.add(dianjine);
					nws.setDianjine(dianjine);
					nws.setShuijine(shuijine);
					nws.setZongjine(zongjine);
					wssdd.save(nws);
					}
			}
			
			System.out.println("上月用水度数：" + lastmonthshuidushu);
			BigDecimal oshuidushu = new BigDecimal(lastmonthshuidushu);
			// System.out.println("改后+++++++++++上月用水度数："+oshuidushu);
			BigDecimal odiandushu = new BigDecimal(lastmonthdiandushu);
			// System.out.println("改后+++++++++++上月用电度数："+odiandushu);

			// 现在不考虑表的最大值，插入的新数据只能比元数据大或等于
			// 若考虑最大值 可以在费率数据库表增加表的最大值字段 如果更新或插入的值小于上一次的值 就最大值减去上一次的值 加上这一次的值
			// 因为表的数据已经重启开始了新的一轮计算
			if (nshuidushu.compareTo(oshuidushu) == -1
					|| ndiandushu.compareTo(odiandushu) == -1) {
				json.put("result", "请检查数据是否正确");
				response.getWriter().write(json.toString());
				response.getWriter().flush();
				response.getWriter().close();
				return null;

			} else {
				BigDecimal shuiyongle = nshuidushu.subtract(oshuidushu);
				System.out.println("用水度数：" + shuiyongle);
				BigDecimal dianyongle = ndiandushu.subtract(odiandushu);
				System.out.println("用电度数：" + dianyongle);
				BigDecimal shuijine = shuidanjia.multiply(shuiyongle);
				System.out.println("用水金额：" + shuijine);
				BigDecimal dianjine = diandanjia.multiply(dianyongle);
				System.out.println("用电金额：" + dianjine);
				BigDecimal zongjine = shuijine.add(dianjine);
				wssd.setScore(score);
				wssd.setShiijian(shiijian);
				wssd.setShui(shui);
				wssd.setDian(dian);
				wssd.setDianjine(dianjine);
				wssd.setShuijine(shuijine);
				wssd.setZongjine(zongjine);
				wssdd.save(wssd);

				json.put("result", "success");
			}

		} else {
			// 当返回的结果只有一条或者没有的时候 就更新现在的信息，不做其他处理
			wssd.setScore(score);
			wssd.setShiijian(shiijian);
			wssd.setShui(shui);
			wssd.setDian(dian);
			wssd.setDianjine(null);
			wssd.setShuijine(null);
			wssd.setZongjine(null);
			wssdd.save(wssd);
			json.put("result", "初始数据更新成功");
		}

		response.getWriter().write(json.toString());
		response.getWriter().flush();
		response.getWriter().close();
		return null;

	}

	// 判断是否空值
	private boolean isInvalid(String value) {
		return (value == null || value.length() == 0);
	}

	// 测试
	public static void main(String[] args) {
		System.out.println();
	}

	public String getWeishengshuidianId() {
		return weishengshuidianId;
	}

	public void setWeishengshuidianId(String weishengshuidianId) {
		this.weishengshuidianId = weishengshuidianId;
	}

	public String getDomitoryId() {
		return domitoryId;
	}

	public void setDomitoryId(String domitoryId) {
		this.domitoryId = domitoryId;
	}

	public String getScore() {
		return score;
	}

	public void setScore(String score) {
		this.score = score;
	}

	public String getShui() {
		return shui;
	}

	public void setShui(String shui) {
		this.shui = shui;
	}

	public String getDian() {
		return dian;
	}

	public void setDian(String dian) {
		this.dian = dian;
	}

	public String getJine() {
		return jine;
	}

	public void setJine(String jine) {
		this.jine = jine;
	}

	public String getShiijian() {
		return shiijian;
	}

	public void setShiijian(String shiijian) {
		this.shiijian = shiijian;
	}

	public String getBuildingId() {
		return buildingId;
	}

	public void setBuildingId(String buildingId) {
		this.buildingId = buildingId;
	}

	public String getWssdBuildingName() {
		return wssdBuildingName;
	}

	public void setWssdBuildingName(String wssdBuildingName) {
		this.wssdBuildingName = wssdBuildingName;
	}

	public String getWssdDomitoryName() {
		return wssdDomitoryName;
	}

	public void setWssdDomitoryName(String wssdDomitoryName) {
		this.wssdDomitoryName = wssdDomitoryName;
	}

}
