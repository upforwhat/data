package com.dao;

import com.db.DBHelper;
import com.bean.weiguiBean;

import java.util.*;
import java.sql.*;

public class weiguiDao {
	
	//获取列表
	
	
	
	public List<weiguiBean> GetList(String parametersql,String sort,String order){
		String sql="select * from weigui w,domitory d,building b where w.Domitory_ID=d.Domitory_ID and d.Domitory_BuildingID=b.Building_ID ";
		String defaultsort="shijian";
		String defaultorder="desc";
		if(!(isInvalid(parametersql)))
		{
			sql+=parametersql;
		}
		if(!(isInvalid(sort)))
		{ if(sort.equals("building_Name")){
			defaultsort=" b."+sort;	
		}
		else
			defaultsort=sort;	
		}
		sql+=" order by " +defaultsort;
		if(!(isInvalid(order)))
		{  defaultorder=order;
			
		}
		sql+=" "+defaultorder;
		
		System.out.println("违规查找语句+++++++++++++++++++++"+sql+"+++++++++++++++++");
		Statement stat = null;
		ResultSet rs = null;
		Connection conn = new DBHelper().getConn();
		List<weiguiBean> list=new ArrayList<weiguiBean>();
		try{
			stat = conn.createStatement();
			rs = stat.executeQuery(sql);
			System.out.println("+++++++++++++"+sql);
			while(rs.next()){
				weiguiBean cnbean=new weiguiBean();
				cnbean.setDomitory_ID(rs.getInt("Domitory_ID"));
				cnbean.setBuilding_ID(rs.getInt("Building_ID"));
				cnbean.setWeiguiid(rs.getInt("weiguiid"));
				cnbean.setChulimiaoshu(rs.getString("chulimiaoshu"));
				cnbean.setShijian(rs.getString("shijian"));
				cnbean.setYuanyin(rs.getString("yuanyin"));
				cnbean.setDomitory_Name(rs.getString("Domitory_Name"));
				cnbean.setBuilding_Name(rs.getString(15));
				list.add(cnbean);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (conn != null)
					conn.close();
				if (stat != null)
					stat.close();
				if (rs != null)
					rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return list;
	}
	
	//学生违规查询sort, order
	public List<weiguiBean> StuGetList(String did,String sort,String order){
		String sql="select * from weigui w,domitory d,building b where w.Domitory_ID=d.Domitory_ID and b.Building_ID=d.Domitory_BuildingID and d.Domitory_ID="+did+" ";
		String defaultsort="shijian";
		String defaultorder="desc";
		if(!(isInvalid(sort)))
		{ defaultsort=sort;	
		}
		sql+="order by " +defaultsort;
		if(!(isInvalid(order)))
		{  defaultorder=order;
			
		}
		sql+=" "+defaultorder;
		System.out.println("违规查找语句+++++++++++++++++++++"+sql+"+++++++++++++++++");
		Statement stat = null;
		ResultSet rs = null;
		Connection conn = new DBHelper().getConn();
		List<weiguiBean> list=new ArrayList<weiguiBean>();
		try{
			stat = conn.createStatement();
			rs = stat.executeQuery(sql);
			
			System.out.println(rs.toString());
			System.out.println("+++++++++++++"+sql);
			while(rs.next()){
				weiguiBean cnbean=new weiguiBean();
				cnbean.setDomitory_ID(rs.getInt("Domitory_ID"));
				cnbean.setBuilding_ID(rs.getInt("Building_ID"));
				cnbean.setWeiguiid(rs.getInt("weiguiid"));
				cnbean.setChulimiaoshu(rs.getString("chulimiaoshu"));
				cnbean.setShijian(rs.getString("shijian"));
				cnbean.setYuanyin(rs.getString("yuanyin"));
				cnbean.setDomitory_Name(rs.getString("Domitory_Name"));
				cnbean.setBuilding_Name(rs.getString(15));
				System.out.println("宿舍楼名称"+rs.getString(15));
				list.add(cnbean);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (conn != null)
					conn.close();
				if (stat != null)
					stat.close();
				if (rs != null)
					rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return list;
	}
	
	//获取指定ID的实体Bean
	public weiguiBean GetBean(int id){
		String sql="select * from weigui w,domitory d  where  w.Domitory_ID=d.Domitory_ID and w.weiguiid="+id;
		Statement stat = null;
		ResultSet rs = null;
		Connection conn = new DBHelper().getConn();
		weiguiBean cnbean=new weiguiBean();
		try{
			stat = conn.createStatement();
			rs = stat.executeQuery(sql);
			while(rs.next()){
				
				cnbean.setBuilding_ID(rs.getInt("Building_ID"));
				cnbean.setDomitory_ID(rs.getInt("Domitory_ID"));
				cnbean.setWeiguiid(rs.getInt("weiguiid"));
				cnbean.setBuilding_Name(rs.getString("Building_Name"));
				cnbean.setChulimiaoshu(rs.getString("chulimiaoshu"));
				cnbean.setDomitory_Name(rs.getString("Domitory_Name"));
				cnbean.setShijian(rs.getString("shijian"));
				cnbean.setYuanyin(rs.getString("yuanyin"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (conn != null)
					conn.close();
				if (stat != null)
					stat.close();
				if (rs != null)
					rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return cnbean;
	}
	
	//添加
/*	private String Building_ID ;
    private String Domitory_Name ;
    private String shijian ;
    private String yuanyin ;
    private String chulimiaoshu ;*/
	public void Add(weiguiBean cnbean){
		String sql="insert into weigui (";
		sql+="Building_ID,Domitory_ID,shijian,yuanyin,chulimiaoshu";
		sql+=") values(";
		sql+=""+cnbean.getBuilding_ID()+",'"+cnbean.getDomitory_ID()+"','"+cnbean.getShijian()+"','"+cnbean.getYuanyin()+"','"+cnbean.getChulimiaoshu()+"'";
		sql+=")";
		Statement stat = null;
		ResultSet rs = null;
		Connection conn = new DBHelper().getConn();
		try{
			stat = conn.createStatement();
			stat.executeUpdate(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (conn != null)
					conn.close();
				if (stat != null)
					stat.close();
				if (rs != null)
					rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	//修改
	
/*	cnbean.setBuilding_ID(rs.getInt("Building_ID"));
	cnbean.setDomitory_ID(rs.getInt("Domitory_ID"));
	cnbean.setWeiguiid(rs.getInt("weiguiid"));
	cnbean.setBuilding_Name(rs.getString("Building_Name"));
	cnbean.setChulimiaoshu(rs.getString("chulimiaoshu"));
	cnbean.setDomitory_Name(rs.getString("Domitory_Name"));
	cnbean.setShijian(rs.getString("shijian"));
	cnbean.setYuanyin(rs.getString("yuanyin"));*/
	public int Update(weiguiBean cnbean){
		String sql="update weigui set ";
		sql+="chulimiaoshu='"+cnbean.getChulimiaoshu()+"',";
//		sql+="Domitory_Name='"+cnbean.getDomitory_Name()+"',";
		sql+="Domitory_ID="+cnbean.getDomitory_ID()+",";
		sql+="shijian='"+cnbean.getShijian()+"',";
		sql+="yuanyin='"+cnbean.getYuanyin()+"'";
		
		sql+=" where weiguiid="+cnbean.getWeiguiid();
		Statement stat = null;
		ResultSet rs = null;
		Connection conn = new DBHelper().getConn();
		int i=0;
		try{
			stat = conn.createStatement();
			i=stat.executeUpdate(sql);
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (conn != null)
					conn.close();
				if (stat != null)
					stat.close();
				if (rs != null)
					rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return i;
	}
	//删除
	public int Delete(String strwhere){
		String sql="delete from weigui where ";
		sql+=strwhere;
		System.out.println("+++++++++++++++++"+sql);
		Statement stat = null;
		ResultSet rs = null;
		Connection conn = new DBHelper().getConn();
		int rss=0;
		try{
			stat = conn.createStatement();
			
			rss=stat.executeUpdate(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (conn != null)
					conn.close();
				if (stat != null)
					stat.close();
				if (rs != null)
					rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return rss;
	}

	
	//判断是否空值
	private boolean isInvalid(String value) {
		return (value == null || value.length() == 0);
	}
	
	//测试
	public static void main(String[] args) {
		System.out.println("");
	}
	
}

