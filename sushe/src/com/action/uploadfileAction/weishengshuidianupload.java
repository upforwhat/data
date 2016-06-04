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

	// 下面是Action内用于封装用户请求参数的属性
	private File userUploadFile;
	private List<Weishengshuidian> Weishengshuidianlist;

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
		/*
		 * Connection con = null; try { con = dbUtil.getCon(); Workbook wb = new
		 * HSSFWorkbook(); // 定义一个工作簿 String headers[] = { "编号", "姓名", "电话",
		 * "Email", "QQ" }; // 表的字段 ResultSet rs = userDao.userList(con, null,
		 * user); // 查询出的结果集 ExcelUtil.fillExcelData(rs, wb, headers);
		 * ResponseUtil.export(ServletActionContext.getResponse(), wb,
		 * "导出excel.xls"); // 将excel导出 } catch (Exception e) { // TODO
		 * Auto-generated catch block e.printStackTrace(); } finally { try {
		 * dbUtil.closeCon(con); } catch (Exception e) { // TODO Auto-generated
		 * catch block e.printStackTrace(); } }
		 */
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
		/*
		 * try { if(user == null){ user = new User(); } String s = new
		 * String(s_name.getBytes("iso-8859-1"),"utf-8"); user.setName(s); con =
		 * dbUtil.getCon(); System.out.println(user); ResultSet rs =
		 * userDao.userList(con, null, user); Workbook wb =
		 * ExcelUtil.fillExcelDataByTemplate(userDao.userList(con, null, user),
		 * "userExporTemplate.xls");
		 * ResponseUtil.export(ServletActionContext.getResponse(), wb,
		 * "通过模版导出的数据.xls"); } catch (Exception e) { e.printStackTrace(); }
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
	// 读取用户上传的文件并写进数据库
	@SuppressWarnings("unchecked")
	public String upload() throws IOException {
		POIFSFileSystem fs = new POIFSFileSystem(new FileInputStream(
				userUploadFile));
		HSSFWorkbook wb = new HSSFWorkbook(fs);
		HSSFSheet hssfSheet = wb.getSheetAt(0); // 获取第一个Sheet页
		HttpServletResponse response = ServletActionContext.getResponse();
		HttpServletRequest request = ServletActionContext.getRequest();
		response.setCharacterEncoding("utf-8");// 指定为utf-8
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
					err.add("第" + (rowNum + 1) + "行:请输入宿舍楼名");
					continue;
				}
				if (hssfRow.getCell(1) == null
						|| hssfRow.getCell(1).toString().length() == 0) {
					err.add("第" + (rowNum + 1) + "行:请输入宿舍名");
					continue;
				}
				if (hssfRow.getCell(2) == null
						|| hssfRow.getCell(2).toString().length() == 0) {
					err.add("第" + (rowNum + 1) + "行:请输入水表度数");
					continue;
				}
				if (hssfRow.getCell(3) == null
						|| hssfRow.getCell(3).toString().length() == 0) {
					err.add("第" + (rowNum + 1) + "行:请输入电表度数");
					continue;
				}
				if (hssfRow.getCell(5) == null
						|| hssfRow.getCell(5).toString().length() == 0) {
					err.add("第" + (rowNum + 1) + "行:请输入检查时间");
					continue;
				}
				// 宿舍楼是否存在
				List buildinglist = new ArrayList<BuildingBean>();

			
					buildinglist = buildingdao.GetList("Building_Name='"
							+ hssfRow.getCell(0).toString() + "'", "");
					if (buildinglist.size() == 0) {
						err.add("第" + (rowNum + 1) + "行:宿舍楼不存在");
						continue;
					}
				

				// 根据--宿舍名称--时间--来查找上一条记录
				// 先根据宿舍名称找到对应的ID

				db = dd.GetBean(hssfRow.getCell(1).toString());
				// 如果返回的宿舍为空 则宿舍不存在 不进行下一步操作

				if (db == null) {
					err.add("第" + (rowNum + 1) + "行:宿舍不存在");
					continue;
				}
				/*
				 * System.out.println("拿到的每行数据////////////////////////////////");
				 * System.out.println(hssfRow.getCell(0));// 宿舍楼
				 * System.out.println(hssfRow.getCell(1));// 宿舍名称
				 * System.out.println(hssfRow.getCell(2));// 水表度数
				 * System.out.println(hssfRow.getCell(3));// 电度数
				 * System.out.println(hssfRow.getCell(4));// 卫生分数
				 * System.out.println(hssfRow.getCell(5));// 时间
				 * System.out.println
				 * ("拿到的每行数据////////////////////////////////");
				 * System.out.println("数据库查询的////////////////////////////////");
				 * System.out.println(db.getBuilding_Name());
				 * System.out.println(db.getDomitory_BuildingID());
				 * System.out.println(db.getDomitory_ID());
				 * System.out.println(db.getDomitory_Name());
				 * System.out.println("数据库查询////////////////////////////////");
				 */
				String nshijian = hssfRow.getCell(5).toString();
				int suseid = db.getDomitory_ID();// 拿到宿舍ID

				int buildeingid = db.getDomitory_BuildingID();// 拿到宿舍楼ID

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

					System.out.println("数据库时间:" + d1);
					System.out.println("传进去的时间:" + d2);
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
						err.add("第" + (rowNum + 1) + "行:水电表的度数不正确");
						continue;
					}
					BigDecimal shuiyongle = nshuidushu.subtract(oshuidushu);
					BigDecimal dianyongle = ndiandushu.subtract(odiandushu);
					BigDecimal shuijine = shuiyongle.multiply(shuidanjia);
					BigDecimal dianjine = dianyongle.multiply(diandanjia);
					BigDecimal zongjine = shuijine.add(dianjine);

					if (d1.getTime() < d2.getTime()) {
						System.out.println("传进去的时间大");
						wsd.setDianjine(dianjine);
						wsd.setShuijine(shuijine);
						wsd.setZongjine(zongjine);
						wd.save(wsd);
						su.add("第" + (rowNum + 1));
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
								err.add("第" + (rowNum + 1) + "行:水电表的度数不正确");
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
							updatew.add("第" + (rowNum + 1));
							System.out.println("两个时间相等");
							System.out.println("那到的ID"
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
							updatew.add("第" + (rowNum + 1));
						}

					}

				} else {
					// 没有数据 传入的时间最小 更新数据
					wsd.setDianjine(null);
					wsd.setShuijine(null);
					wsd.setZongjine(null);
					wd.save(wsd);
					updatew.add("第" + (rowNum + 1));
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
