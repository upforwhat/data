package com.hibernate.model;

/**
 * Baoxiu entity. @author MyEclipse Persistence Tools
 */

public class Baoxiu implements java.io.Serializable {

	// Fields

	private Integer baoxiuid;
	private String baoxiushijian;
	private String chulishijian;
	private String state;
	private Integer baoxiuDomitoryid;
	private Integer baoxiuStudentId;
	private String baoxiuStudentName;
	private String baoxiuDomitoryName;
	private String baoxiuXianmu;
	private String baoxiuBuildingName;
	private Integer baoxiuBuildingId;
	private String baoxiuOne;
	private String baoxiuTwo;
	private String baoxiuThreee;

	// Constructors

	/** default constructor */
	public Baoxiu() {
	}

	/** full constructor */
	public Baoxiu(String baoxiushijian, String chulishijian, String state,
			Integer baoxiuDomitoryid, Integer baoxiuStudentId,
			String baoxiuStudentName, String baoxiuDomitoryName,
			String baoxiuXianmu, String baoxiuBuildingName,
			Integer baoxiuBuildingId, String baoxiuOne, String baoxiuTwo,
			String baoxiuThreee) {
		this.baoxiushijian = baoxiushijian;
		this.chulishijian = chulishijian;
		this.state = state;
		this.baoxiuDomitoryid = baoxiuDomitoryid;
		this.baoxiuStudentId = baoxiuStudentId;
		this.baoxiuStudentName = baoxiuStudentName;
		this.baoxiuDomitoryName = baoxiuDomitoryName;
		this.baoxiuXianmu = baoxiuXianmu;
		this.baoxiuBuildingName = baoxiuBuildingName;
		this.baoxiuBuildingId = baoxiuBuildingId;
		this.baoxiuOne = baoxiuOne;
		this.baoxiuTwo = baoxiuTwo;
		this.baoxiuThreee = baoxiuThreee;
	}

	// Property accessors

	public Integer getBaoxiuid() {
		return this.baoxiuid;
	}

	public void setBaoxiuid(Integer baoxiuid) {
		this.baoxiuid = baoxiuid;
	}

	public String getBaoxiushijian() {
		return this.baoxiushijian;
	}

	public void setBaoxiushijian(String baoxiushijian) {
		this.baoxiushijian = baoxiushijian;
	}

	public String getChulishijian() {
		return this.chulishijian;
	}

	public void setChulishijian(String chulishijian) {
		this.chulishijian = chulishijian;
	}

	public String getState() {
		return this.state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public Integer getBaoxiuDomitoryid() {
		return this.baoxiuDomitoryid;
	}

	public void setBaoxiuDomitoryid(Integer baoxiuDomitoryid) {
		this.baoxiuDomitoryid = baoxiuDomitoryid;
	}

	public Integer getBaoxiuStudentId() {
		return this.baoxiuStudentId;
	}

	public void setBaoxiuStudentId(Integer baoxiuStudentId) {
		this.baoxiuStudentId = baoxiuStudentId;
	}

	public String getBaoxiuStudentName() {
		return this.baoxiuStudentName;
	}

	public void setBaoxiuStudentName(String baoxiuStudentName) {
		this.baoxiuStudentName = baoxiuStudentName;
	}

	public String getBaoxiuDomitoryName() {
		return this.baoxiuDomitoryName;
	}

	public void setBaoxiuDomitoryName(String baoxiuDomitoryName) {
		this.baoxiuDomitoryName = baoxiuDomitoryName;
	}

	public String getBaoxiuXianmu() {
		return this.baoxiuXianmu;
	}

	public void setBaoxiuXianmu(String baoxiuXianmu) {
		this.baoxiuXianmu = baoxiuXianmu;
	}

	public String getBaoxiuBuildingName() {
		return this.baoxiuBuildingName;
	}

	public void setBaoxiuBuildingName(String baoxiuBuildingName) {
		this.baoxiuBuildingName = baoxiuBuildingName;
	}

	public Integer getBaoxiuBuildingId() {
		return this.baoxiuBuildingId;
	}

	public void setBaoxiuBuildingId(Integer baoxiuBuildingId) {
		this.baoxiuBuildingId = baoxiuBuildingId;
	}

	public String getBaoxiuOne() {
		return this.baoxiuOne;
	}

	public void setBaoxiuOne(String baoxiuOne) {
		this.baoxiuOne = baoxiuOne;
	}

	public String getBaoxiuTwo() {
		return this.baoxiuTwo;
	}

	public void setBaoxiuTwo(String baoxiuTwo) {
		this.baoxiuTwo = baoxiuTwo;
	}

	public String getBaoxiuThreee() {
		return this.baoxiuThreee;
	}

	public void setBaoxiuThreee(String baoxiuThreee) {
		this.baoxiuThreee = baoxiuThreee;
	}

}