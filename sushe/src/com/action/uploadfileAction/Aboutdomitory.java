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

public class Aboutdomitory extends ActionSupport {

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
		String buildingname, domitoryname, keyizhuduoshao, leixing;
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date d1 = new Date();
		BuildingBean bb = null;
		DomitoryBean cnbea,newdomitory = null;
		BuildingDao buildingdao = new BuildingDao();
		DomitoryDao dd = new DomitoryDao();
		Integer domitoryBuildingID=null;

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
				List buildinglist;
				buildinglist = buildingdao.GetList("Building_Name='"
						+ hssfRow.getCell(0).toString() + "'", "");
				System.out.println(buildinglist+"\n\n\n\n\n\n");
				if (buildinglist.isEmpty()) {
					err.add("��" + (rowNum + 1) + "��:����¥������");
					continue;
				}
				else{
				domitoryBuildingID=((BuildingBean) buildinglist.get(0)).getBuilding_ID();
				
				}
			
			

				if (hssfRow.getCell(1) == null
						|| hssfRow.getCell(1).toString().length() == 0) {
					err.add("��" + (rowNum + 1) + "�У���������������");
					continue;
				}
				cnbea = dd.GetBean(hssfRow.getCell(1).toString());
				if (cnbea != null) {
					err.add("��" + (rowNum + 1) + "�У������Ѵ���");
					continue;
				}

				if (hssfRow.getCell(2) == null
						|| hssfRow.getCell(2).toString().length() == 0) {
					err.add("��" + (rowNum + 1) + "�У������������ס����");
					continue;
				}
				if (hssfRow.getCell(3) == null
						|| hssfRow.getCell(3).toString().length() == 0) {
					err.add("��" + (rowNum + 1) + "�У���������������");
					continue;
				}
				newdomitory=new DomitoryBean();
				buildingname = hssfRow.getCell(0).toString();
				domitoryname = hssfRow.getCell(1).toString();
				keyizhuduoshao = hssfRow.getCell(2).toString();
				leixing = hssfRow.getCell(3).toString();
				newdomitory.setBuilding_Name(buildingname);
				newdomitory.setDomitory_BuildingID(domitoryBuildingID);
				newdomitory.setDomitory_Name(domitoryname);
				newdomitory.setDomitory_Number(keyizhuduoshao);
				newdomitory.setDomitory_Type(leixing);
				dd.Add(newdomitory);
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
