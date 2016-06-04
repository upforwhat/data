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

	// ������Action�����ڷ�װ�û��������������

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

		// ��ѯ�û����Ƿ����
		/*
		 * List<DomitoryBean> list=new
		 * DomitoryDao().GetList("Domitory_Name='"+Domitory_Name
		 * +"' and Domitory_BuildingID="
		 * +Domitory_BuildingID+" and Domitory_ID!="+Domitory_ID, "");
		 * if(list.size()>0) { out.print(
		 * "<script language='javascript'>alert('��¥�����Ѿ����ڸ����Һţ�');history.back(-1);</script>"
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
		System.out.println("�µ�ˮ������" + nshuidushu);
		BigDecimal ndiandushu = new BigDecimal(dian);
		System.out.println("�µĵ������" + ndiandushu);
		BigDecimal diandanjia = fl.getDian();
		System.out.println("�絥��" + diandanjia);
		BigDecimal shuidanjia = fl.getShui();
		System.out.println("ˮ����" + shuidanjia);

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

			lastmonthdiandushu = ws[1].getDian();// �������ݵĻ���Ϊ�����Ѿ����ڣ����õ�һ���Ƚ�û������
			System.out.println("�����õ������" + lastmonthdiandushu); // ����ֻ���õڶ��������Ա���Ҫȷ����һ��ԭʼ���ݣ���ʼʹ�õ�ʱ���Ķ�������׼ȷ�ԣ�
			// �ж������һ������δ�ո���0����ʾ���㿪ʼ
			lastmonthshuidushu = ws[1].getShui();
			//�޸�����֮ǰ��������,����Ҫ�ٲ���һ�α���ʱ����һ������,�Ѻ�һ���µ�ˮ�����
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
					json.put("result", "���������Ƿ���ȷ");
					response.getWriter().write(json.toString());
					response.getWriter().flush();
					response.getWriter().close();
					return null;

				} else {
					BigDecimal shuiyongle = backshuidushu.subtract(nshuidushu);
					System.out.println("��ˮ������" + shuiyongle);
					BigDecimal dianyongle = backdiandushu.subtract(ndiandushu);
					System.out.println("�õ������" + dianyongle);
					BigDecimal shuijine = shuidanjia.multiply(shuiyongle);
					System.out.println("��ˮ��" + shuijine);
					BigDecimal dianjine = diandanjia.multiply(dianyongle);
					System.out.println("�õ��" + dianjine);
					BigDecimal zongjine = shuijine.add(dianjine);
					nws.setDianjine(dianjine);
					nws.setShuijine(shuijine);
					nws.setZongjine(zongjine);
					wssdd.save(nws);
					}
			}
			
			System.out.println("������ˮ������" + lastmonthshuidushu);
			BigDecimal oshuidushu = new BigDecimal(lastmonthshuidushu);
			// System.out.println("�ĺ�+++++++++++������ˮ������"+oshuidushu);
			BigDecimal odiandushu = new BigDecimal(lastmonthdiandushu);
			// System.out.println("�ĺ�+++++++++++�����õ������"+odiandushu);

			// ���ڲ����Ǳ�����ֵ�������������ֻ�ܱ�Ԫ���ݴ�����
			// ���������ֵ �����ڷ������ݿ�����ӱ�����ֵ�ֶ� ������»�����ֵС����һ�ε�ֵ �����ֵ��ȥ��һ�ε�ֵ ������һ�ε�ֵ
			// ��Ϊ��������Ѿ�������ʼ���µ�һ�ּ���
			if (nshuidushu.compareTo(oshuidushu) == -1
					|| ndiandushu.compareTo(odiandushu) == -1) {
				json.put("result", "���������Ƿ���ȷ");
				response.getWriter().write(json.toString());
				response.getWriter().flush();
				response.getWriter().close();
				return null;

			} else {
				BigDecimal shuiyongle = nshuidushu.subtract(oshuidushu);
				System.out.println("��ˮ������" + shuiyongle);
				BigDecimal dianyongle = ndiandushu.subtract(odiandushu);
				System.out.println("�õ������" + dianyongle);
				BigDecimal shuijine = shuidanjia.multiply(shuiyongle);
				System.out.println("��ˮ��" + shuijine);
				BigDecimal dianjine = diandanjia.multiply(dianyongle);
				System.out.println("�õ��" + dianjine);
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
			// �����صĽ��ֻ��һ������û�е�ʱ�� �͸������ڵ���Ϣ��������������
			wssd.setScore(score);
			wssd.setShiijian(shiijian);
			wssd.setShui(shui);
			wssd.setDian(dian);
			wssd.setDianjine(null);
			wssd.setShuijine(null);
			wssd.setZongjine(null);
			wssdd.save(wssd);
			json.put("result", "��ʼ���ݸ��³ɹ�");
		}

		response.getWriter().write(json.toString());
		response.getWriter().flush();
		response.getWriter().close();
		return null;

	}

	// �ж��Ƿ��ֵ
	private boolean isInvalid(String value) {
		return (value == null || value.length() == 0);
	}

	// ����
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
