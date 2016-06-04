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

public class shebeiupload extends ActionSupport {

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
		 * "Email", "QQ" }; // �����ֶ� ResultSet rs = userDao.userList(con, null,
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
		String buildingname, shebeibianhao, shebeiname, goumaishijian, youxiaoshijian, zuijinjianchashijian, zhuantai;
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date d1 = new Date();
		Shebei shebei = null;
		ShebeiDAO sbd = new ShebeiDAO();
		BuildingBean bb = null;
		BuildingDao buildingdao = new BuildingDao();

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
				// ����¥�Ƿ����
				List buildinglist = new ArrayList<BuildingBean>();
				buildinglist = buildingdao.GetList("Building_Name='"
						+ hssfRow.getCell(0).toString() + "'", "");
				if (buildinglist.size() == 0) {
					err.add("��" + (rowNum + 1) + "��:����¥������");
					continue;
				}
				bb = (BuildingBean) buildinglist.get(0);

				if (hssfRow.getCell(1) == null
						|| hssfRow.getCell(1).toString().length() == 0) {
					err.add("��" + (rowNum + 1) + "�У��������豸���");
					continue;
				}
				if (hssfRow.getCell(2) == null
						|| hssfRow.getCell(2).toString().length() == 0) {
					err.add("��" + (rowNum + 1) + "�У��������豸����");
					continue;
				}
				if (hssfRow.getCell(3) == null
						|| hssfRow.getCell(3).toString().length() == 0) {
					err.add("��" + (rowNum + 1) + "�У���������Чʱ��");
					continue;
				}
				if (hssfRow.getCell(4) == null
						|| hssfRow.getCell(4).toString().length() == 0) {
					err.add("��" + (rowNum + 1) + "��:�����빺��ʱ��");
					continue;
				}
				
				buildingname = hssfRow.getCell(0).toString();
				shebeibianhao = hssfRow.getCell(1).toString();
				shebeiname = hssfRow.getCell(2).toString();
				goumaishijian = sdf.format(hssfRow.getCell(4).getDateCellValue());
				youxiaoshijian = sdf.format(hssfRow.getCell(3).getDateCellValue());
				System.out.println(goumaishijian+"����ʱ��");
				
					if(hssfRow.getCell(3).getDateCellValue().getTime()<hssfRow.getCell(4).getDateCellValue().getTime()||
							hssfRow.getCell(3).getDateCellValue().getTime()<d1.getTime()){
						err.add("��" + (rowNum + 1) + "��:��ȷ��ʱ��");
						continue;
					}
			
				if (hssfRow.getCell(5) == null
						|| hssfRow.getCell(5).toString().length() == 0) {
					zuijinjianchashijian = sdf.format(d1);
				} else {
					zuijinjianchashijian = hssfRow.getCell(5).toString();
				}
				if (hssfRow.getCell(6) == null
						|| hssfRow.getCell(6).toString().length() == 0) {
					zhuantai = "����";
				} else {
					zhuantai = hssfRow.getCell(6).toString();
				}
				shebei = new Shebei();
				shebei.setShebeiBuildingId(bb.getBuilding_ID());
				shebei.setShebeiBuildingName(buildingname);
				shebei.setShebeiBianhao(shebeibianhao);
				shebei.setShebeiGoumaishijian(goumaishijian);
				shebei.setShebeiYouxiaoshijian(youxiaoshijian);
				shebei.setShebeiZuijinjiachashijian(zuijinjianchashijian);
				shebei.setShebeiName(shebeiname);
				shebei.setShebeiState(zhuantai);
				sbd.save(shebei);
				su.add("��" + (rowNum + 1));

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