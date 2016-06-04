package com.action.uploadfileAction;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;
import com.util.ExcelUtil;
import com.bean.BuildingBean;
import com.bean.DomitoryBean;
import com.bean.StudentBean;
import com.dao.BuildingDao;
import com.dao.DomitoryDao;
import com.dao.StudentDao;
import com.hibernate.model.Feilv;
import com.hibernate.model.FeilvDAO;
import com.hibernate.model.Gongao;
import com.hibernate.model.GongaoDAO;
import com.hibernate.model.Shebei;
import com.hibernate.model.ShebeiDAO;
import com.hibernate.model.Weishengshuidian;
import com.hibernate.model.WeishengshuidianDAO;

public class weishengshuidianupload extends ActionSupport {

	// ������Action�����ڷ�װ�û��������������
	private File userUploadFile;
	private List<Weishengshuidian> Weishengshuidianlist;

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

		response.setCharacterEncoding("utf-8");// ָ��Ϊutf-8
		response.getWriter().write(jobj.toString());// ת��ΪJSOn��ʽ

	}

	public String export() throws Exception {
		/*
		 * Connection con = null; try { con = dbUtil.getCon(); Workbook wb = new
		 * HSSFWorkbook(); // ����һ�������� String headers[] = { "���", "����", "�绰",
		 * "Email", "QQ" }; // ����ֶ� ResultSet rs = userDao.userList(con, null,
		 * user); // ��ѯ���Ľ���� ExcelUtil.fillExcelData(rs, wb, headers);
		 * ResponseUtil.export(ServletActionContext.getResponse(), wb,
		 * "����excel.xls"); // ��excel���� } catch (Exception e) { // TODO
		 * Auto-generated catch block e.printStackTrace(); } finally { try {
		 * dbUtil.closeCon(con); } catch (Exception e) { // TODO Auto-generated
		 * catch block e.printStackTrace(); } }
		 */
		return null;
	}

	/**
	 * ����ģ�浼������
	 * 
	 * @return
	 * @throws Exception
	 */
	public String exportByTemplate() throws Exception {
		Connection con = null;
		/*
		 * try { if(user == null){ user = new User(); } String s = new
		 * String(s_name.getBytes("iso-8859-1"),"utf-8"); user.setName(s); con =
		 * dbUtil.getCon(); System.out.println(user); ResultSet rs =
		 * userDao.userList(con, null, user); Workbook wb =
		 * ExcelUtil.fillExcelDataByTemplate(userDao.userList(con, null, user),
		 * "userExporTemplate.xls");
		 * ResponseUtil.export(ServletActionContext.getResponse(), wb,
		 * "ͨ��ģ�浼��������.xls"); } catch (Exception e) { e.printStackTrace(); }
		 * finally { try { dbUtil.closeCon(con); } catch (Exception e) {
		 * e.printStackTrace(); } }
		 */
		return null;
	}

	/**
	 * @return
	 * @throws IOException
	 * @throws Exception
	 */
	// ��ȡ�û��ϴ����ļ���д�����ݿ�
	@SuppressWarnings("unchecked")
	public String upload() throws IOException {
		POIFSFileSystem fs = new POIFSFileSystem(new FileInputStream(
				userUploadFile));
		HSSFWorkbook wb = new HSSFWorkbook(fs);
		HSSFSheet hssfSheet = wb.getSheetAt(0); // ��ȡ��һ��Sheetҳ
		HttpServletResponse response = ServletActionContext.getResponse();
		HttpServletRequest request = ServletActionContext.getRequest();
		response.setCharacterEncoding("utf-8");// ָ��Ϊutf-8
		JSONObject result = new JSONObject();
		JSONArray detailcode = new JSONArray();
		List su = new ArrayList();
		List err = new ArrayList();
		List updatew = new ArrayList();

		DomitoryBean db = null;
		BuildingBean bb = null;
		BuildingDao buildingdao = new BuildingDao();
		DomitoryDao dd = new DomitoryDao();

		if (hssfSheet != null) {
			for (int rowNum = 1; rowNum <= hssfSheet.getLastRowNum(); rowNum++) {
				HSSFRow hssfRow = hssfSheet.getRow(rowNum);

				if (hssfRow == null) {
					continue;
				}

				if (hssfRow.getCell(0) == null
						|| hssfRow.getCell(0).toString().length() == 0) {
					err.add("��" + (rowNum + 1) + "��:����������¥��");
					continue;
				}
				if (hssfRow.getCell(1) == null
						|| hssfRow.getCell(1).toString().length() == 0) {
					err.add("��" + (rowNum + 1) + "��:������������");
					continue;
				}
				if (hssfRow.getCell(2) == null
						|| hssfRow.getCell(2).toString().length() == 0) {
					err.add("��" + (rowNum + 1) + "��:������ˮ�����");
					continue;
				}
				if (hssfRow.getCell(3) == null
						|| hssfRow.getCell(3).toString().length() == 0) {
					err.add("��" + (rowNum + 1) + "��:�����������");
					continue;
				}
				if (hssfRow.getCell(5) == null
						|| hssfRow.getCell(5).toString().length() == 0) {
					err.add("��" + (rowNum + 1) + "��:��������ʱ��");
					continue;
				}
				// ����¥�Ƿ����
				List buildinglist = new ArrayList<BuildingBean>();

			
					buildinglist = buildingdao.GetList("Building_Name='"
							+ hssfRow.getCell(0).toString() + "'", "");
					if (buildinglist.size() == 0) {
						err.add("��" + (rowNum + 1) + "��:����¥������");
						continue;
					}
				

				// ����--��������--ʱ��--��������һ����¼
				// �ȸ������������ҵ���Ӧ��ID

				db = dd.GetBean(hssfRow.getCell(1).toString());
				// ������ص�����Ϊ�� �����᲻���� ��������һ������

				if (db == null) {
					err.add("��" + (rowNum + 1) + "��:���᲻����");
					continue;
				}
				/*
				 * System.out.println("�õ���ÿ������////////////////////////////////");
				 * System.out.println(hssfRow.getCell(0));// ����¥
				 * System.out.println(hssfRow.getCell(1));// ��������
				 * System.out.println(hssfRow.getCell(2));// ˮ�����
				 * System.out.println(hssfRow.getCell(3));// �����
				 * System.out.println(hssfRow.getCell(4));// ��������
				 * System.out.println(hssfRow.getCell(5));// ʱ��
				 * System.out.println
				 * ("�õ���ÿ������////////////////////////////////");
				 * System.out.println("���ݿ��ѯ��////////////////////////////////");
				 * System.out.println(db.getBuilding_Name());
				 * System.out.println(db.getDomitory_BuildingID());
				 * System.out.println(db.getDomitory_ID());
				 * System.out.println(db.getDomitory_Name());
				 * System.out.println("���ݿ��ѯ////////////////////////////////");
				 */
				String nshijian = hssfRow.getCell(5).toString();
				int suseid = db.getDomitory_ID();// �õ�����ID

				int buildeingid = db.getDomitory_BuildingID();// �õ�����¥ID

				String shijian = hssfRow.getCell(5).toString();

				Feilv fl = new Feilv();
				String wssdBuildingName = hssfRow.getCell(0).toString();
				String wssdDomitoryName = hssfRow.getCell(1).toString();
				Weishengshuidian wsd = new Weishengshuidian();
				WeishengshuidianDAO wd = new WeishengshuidianDAO();
				FeilvDAO fd = new FeilvDAO();
				fl = fd.findById(1);
				wsd.setBuildingId(buildeingid);
				wsd.setDomitoryId(suseid);
				wsd.setShui(hssfRow.getCell(2).toString());
				wsd.setDian(hssfRow.getCell(3).toString());
				wsd.setScore(hssfRow.getCell(4).toString());
				wsd.setShiijian(hssfRow.getCell(5).toString());
				wsd.setWssdBuildingName(wssdBuildingName);
				wsd.setWssdDomitoryName(wssdDomitoryName);
				BigDecimal nshuidushu = new BigDecimal(hssfRow.getCell(2)
						.toString());
				BigDecimal ndiandushu = new BigDecimal(hssfRow.getCell(3)
						.toString());
				BigDecimal diandanjia = fl.getDian();
				BigDecimal shuidanjia = fl.getShui();
				// BigDecimal s=nshuidushu.multiply(shuidanjia);
				// BigDecimal d=ndiandushu.multiply(diandanjia);
				String hql = "from Weishengshuidian as w where w.domitoryId = ? "
						+ " and w.shiijian <= ? order by  w.shiijian  desc";

				Object value[] = { suseid, shijian };
				Weishengshuidianlist = new WeishengshuidianDAO()
						.findByMYProperty(hql, value);
				String lastmonthdiandushu = null;
				String lastmonthshuidushu = null;

				if (Weishengshuidianlist.size() > 0) {
					Weishengshuidian[] ws = new Weishengshuidian[Weishengshuidianlist
							.size()];
					for (int i = 0; i < Weishengshuidianlist.size(); i++) {
						ws[i] = Weishengshuidianlist.get(i);
					}
					lastmonthdiandushu = ws[0].getDian();

					lastmonthshuidushu = ws[0].getShui();
					String panduantime = ws[0].getShiijian();
					SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
					Date d1 = new Date();
					Date d2 = new Date();
					try {
						d1 = sdf.parse(panduantime);
						d2 = sdf.parse(shijian);
					} catch (ParseException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

					System.out.println("���ݿ�ʱ��:" + d1);
					System.out.println("����ȥ��ʱ��:" + d2);
					if (isInvalid(lastmonthdiandushu)) {
						lastmonthdiandushu = "0";

					}
					if (isInvalid(lastmonthshuidushu)) {
						lastmonthshuidushu = "0";

					}

					// wsd.setJine(s.add(d).toString());
					BigDecimal oshuidushu = new BigDecimal(lastmonthshuidushu);
					BigDecimal odiandushu = new BigDecimal(lastmonthdiandushu);
					if (nshuidushu.compareTo(oshuidushu) == -1
							|| ndiandushu.compareTo(odiandushu) == -1) {
						err.add("��" + (rowNum + 1) + "��:ˮ���Ķ�������ȷ");
						continue;
					}
					BigDecimal shuiyongle = nshuidushu.subtract(oshuidushu);
					BigDecimal dianyongle = ndiandushu.subtract(odiandushu);
					BigDecimal shuijine = shuiyongle.multiply(shuidanjia);
					BigDecimal dianjine = dianyongle.multiply(diandanjia);
					BigDecimal zongjine = shuijine.add(dianjine);

					if (d1.getTime() < d2.getTime()) {
						System.out.println("����ȥ��ʱ���");
						wsd.setDianjine(dianjine);
						wsd.setShuijine(shuijine);
						wsd.setZongjine(zongjine);
						wd.save(wsd);
						su.add("��" + (rowNum + 1));
					} else {

						if (Weishengshuidianlist.size() > 1) {
							ws = new Weishengshuidian[Weishengshuidianlist
									.size()];
							for (int i = 0; i < Weishengshuidianlist.size(); i++) {
								ws[i] = Weishengshuidianlist.get(i);
							}
							lastmonthdiandushu = ws[1].getDian();

							lastmonthshuidushu = ws[1].getShui();
							panduantime = ws[1].getShiijian();
							oshuidushu = new BigDecimal(lastmonthshuidushu);
							odiandushu = new BigDecimal(lastmonthdiandushu);
							if (nshuidushu.compareTo(oshuidushu) == -1
									|| ndiandushu.compareTo(odiandushu) == -1) {
								err.add("��" + (rowNum + 1) + "��:ˮ���Ķ�������ȷ");
								continue;
							}
							shuiyongle = nshuidushu.subtract(oshuidushu);
							dianyongle = ndiandushu.subtract(odiandushu);
							shuijine = shuiyongle.multiply(shuidanjia);
							dianjine = dianyongle.multiply(diandanjia);
							zongjine = shuijine.add(dianjine);
							wsd = wd.findById(ws[0].getWeishengshuidianId());

							wsd.setDianjine(dianjine);
							wsd.setShuijine(shuijine);
							wsd.setZongjine(zongjine);
							wsd.setShui(hssfRow.getCell(2).toString());
							wsd.setDian(hssfRow.getCell(3).toString());
							wsd.setScore(hssfRow.getCell(4).toString());
							wd.save(wsd);
							updatew.add("��" + (rowNum + 1));
							System.out.println("����ʱ�����");
							System.out.println("�ǵ���ID"
									+ ws[0].getWeishengshuidianId());
						} else {
							wsd = wd.findById(ws[0].getWeishengshuidianId());

							wsd.setDianjine(null);
							wsd.setShuijine(null);
							wsd.setZongjine(null);
							wsd.setShui(hssfRow.getCell(2).toString());
							wsd.setDian(hssfRow.getCell(3).toString());
							wsd.setScore(hssfRow.getCell(4).toString());
							wd.save(wsd);
							updatew.add("��" + (rowNum + 1));
						}

					}

				} else {
					// û������ �����ʱ����С ��������
					wsd.setDianjine(null);
					wsd.setShuijine(null);
					wsd.setZongjine(null);
					wd.save(wsd);
					updatew.add("��" + (rowNum + 1));
				}
			}

		}
		result.put("success", su);
		result.put("failed", err);
		result.put("updatew", updatew);
		System.out.println(result.toString());

		response.getWriter().write(result.toString());
		response.getWriter().flush();
		response.getWriter().close();
		return null;
	}

	// ����
	public static void main(String[] args) {
		System.out.println();
	}

	public File getUserUploadFile() {
		return userUploadFile;
	}

	public void setUserUploadFile(File userUploadFile) {
		this.userUploadFile = userUploadFile;
	}

}
