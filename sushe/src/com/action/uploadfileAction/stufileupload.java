package com.action.uploadfileAction;

import java.io.File;
import java.io.FileInputStream;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

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
import com.bean.StudentBean;
import com.dao.StudentDao;
import com.hibernate.model.Gongao;
import com.hibernate.model.GongaoDAO;
import com.hibernate.model.Shebei;
import com.hibernate.model.ShebeiDAO;

public class stufileupload extends ActionSupport {

	// 下面是Action内用于封装用户请求参数的属性
	private File userUploadFile;

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

		// Shebeilist=sbd.findAll();
	

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

		response.setCharacterEncoding("utf-8");// 指定为utf-8
		response.getWriter().write(jobj.toString());// 转化为JSOn格式

	}
	public String export() throws Exception {
	/*	Connection con = null;
		try {
			con = dbUtil.getCon();
			Workbook wb = new HSSFWorkbook(); // 定义一个工作簿
			String headers[] = { "编号", "姓名", "电话", "Email", "QQ" }; // 表的字段
			ResultSet rs = userDao.userList(con, null, user); // 查询出的结果集
			ExcelUtil.fillExcelData(rs, wb, headers);
			ResponseUtil.export(ServletActionContext.getResponse(), wb, "导出excel.xls"); // 将excel导出
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				dbUtil.closeCon(con);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}*/
		return null;
	}

	/**
	 * 利用模版导出数据
	 * 
	 * @return
	 * @throws Exception
	 */
	public String exportByTemplate() throws Exception {
		Connection con = null;
	/*	try {
			if(user == null){
				user = new User();
			}
			String s = new String(s_name.getBytes("iso-8859-1"),"utf-8");
			user.setName(s);
			con = dbUtil.getCon();
			System.out.println(user);
			ResultSet rs = userDao.userList(con, null, user);
			Workbook wb = ExcelUtil.fillExcelDataByTemplate(userDao.userList(con, null, user), "userExporTemplate.xls");
			ResponseUtil.export(ServletActionContext.getResponse(), wb, "通过模版导出的数据.xls");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				dbUtil.closeCon(con);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}*/
		return null;
	}

	/**
	 * @return
	 * @throws Exception
	 */
	//读取用户上传的文件并写进数据库
	public String upload() throws Exception {
		POIFSFileSystem fs = new POIFSFileSystem(new FileInputStream(userUploadFile));
		HSSFWorkbook wb = new HSSFWorkbook(fs);
		HSSFSheet hssfSheet = wb.getSheetAt(0); // 获取第一个Sheet页
		HttpServletResponse response = ServletActionContext.getResponse();
		HttpServletRequest request = ServletActionContext.getRequest();
		response.setCharacterEncoding("utf-8");// 指定为utf-8
		JSONObject result = new JSONObject();
		List successdata = new ArrayList();
		List errordata = new ArrayList();
		List updatedata = new ArrayList();

		if (hssfSheet != null) {
			for (int rowNum = 1; rowNum <= hssfSheet.getLastRowNum(); rowNum++) {
				HSSFRow hssfRow = hssfSheet.getRow(rowNum);
				if (hssfRow == null) {
					
					continue;
				}
				if(hssfRow.getCell(0)==null || hssfRow.getCell(0).toString().length() == 0){
					errordata.add("第" + (rowNum + 1) + "行:请输入学号");
					continue;
				}
				if(hssfRow.getCell(1)==null || hssfRow.getCell(1).toString().length() == 0){
					errordata.add("第" + (rowNum + 1) + "行:请输入姓名");
					continue;
				}
				if(hssfRow.getCell(4)==null || hssfRow.getCell(4).toString().length() == 0){
					errordata.add("第" + (rowNum + 1) + "行:请输入性别");
					continue;
				}
				
			/*	System.out.println(hssfRow.getCell(0));
				System.out.println(hssfRow.getCell(1));
				System.out.println(hssfRow.getCell(2));
				System.out.println(hssfRow.getCell(3));
				System.out.println(hssfRow.getCell(4));
				System.out.println(hssfRow.getCell(5));
				System.out.println(hssfRow.getCell(6));*/
				
				StudentBean user=new StudentBean();
				StudentDao sd = new StudentDao();
				//判断学生是否存在
				List stulist=sd.GetAllList(" Student_Username='"+hssfRow.getCell(0).toString()+"'", "", "");
				if(stulist.size()>0){
					errordata.add("第" + (rowNum + 1) + "行:学生学号已存在");
					continue;
				}
				user.setStudent_Username(ExcelUtil.formatCell(hssfRow.getCell(0)));
				user.setStudent_Name(ExcelUtil.formatCell(hssfRow.getCell(1)));
				if(isInvalid(ExcelUtil.formatCell(hssfRow.getCell(2)))){
					user.setStudent_Password(ExcelUtil.formatCell(hssfRow.getCell(0)));
				}else{
					
				user.setStudent_Password(ExcelUtil.formatCell(hssfRow.getCell(2)));
				}
				user.setStudent_Class(ExcelUtil.formatCell(hssfRow.getCell(3)));	
				user.setStudent_Sex(ExcelUtil.formatCell(hssfRow.getCell(4)));
				user.setStudent_State("未入住");
			
					sd.beforeAdd(user);
					successdata.add("第" + (rowNum + 1));
	
			}
		}

		result.put("success", successdata);
		result.put("failed", errordata);
		result.put("updatew", updatedata);
		System.out.println(result.toString());
		response.getWriter().write(result.toString());
		return null;
	}
	
	
	
	// 测试
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
