package com.hibernate.model;

import java.math.BigDecimal;

/**
 * Weishengshuidian entity. @author MyEclipse Persistence Tools
 */

public class Weishengshuidian implements java.io.Serializable {

	// Fields

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
	private BigDecimal shuijine;
	private BigDecimal dianjine;
	private BigDecimal zongjine;
	
	// Constructors

	/** default constructor */
	public Weishengshuidian() {
	}


	// Property accessors

	public Weishengshuidian(Integer weishengshuidianId, Integer domitoryId,
			String score, String shui, String dian, String jine,
			String shiijian, Integer buildingId, String wssdBuildingName,
			String wssdDomitoryName, BigDecimal shuijine, BigDecimal dianjine,
			BigDecimal zongjine) {
		super();
		this.weishengshuidianId = weishengshuidianId;
		this.domitoryId = domitoryId;
		this.score = score;
		this.shui = shui;
		this.dian = dian;
		this.jine = jine;
		this.shiijian = shiijian;
		this.buildingId = buildingId;
		this.wssdBuildingName = wssdBuildingName;
		this.wssdDomitoryName = wssdDomitoryName;
		this.shuijine = shuijine;
		this.dianjine = dianjine;
		this.zongjine = zongjine;
	}


	public Integer getWeishengshuidianId() {
		return this.weishengshuidianId;
	}

	public void setWeishengshuidianId(Integer weishengshuidianId) {
		this.weishengshuidianId = weishengshuidianId;
	}

	public Integer getDomitoryId() {
		return this.domitoryId;
	}

	public void setDomitoryId(Integer domitoryId) {
		this.domitoryId = domitoryId;
	}

	public String getScore() {
		return this.score;
	}

	public void setScore(String score) {
		this.score = score;
	}

	public String getShui() {
		return this.shui;
	}

	public void setShui(String shui) {
		this.shui = shui;
	}

	public String getDian() {
		return this.dian;
	}

	public void setDian(String dian) {
		this.dian = dian;
	}

	public String getJine() {
		return this.jine;
	}

	public void setJine(String jine) {
		this.jine = jine;
	}

	public String getShiijian() {
		return this.shiijian;
	}

	public void setShiijian(String shiijian) {
		this.shiijian = shiijian;
	}

	public Integer getBuildingId() {
		return this.buildingId;
	}

	public void setBuildingId(Integer buildingId) {
		this.buildingId = buildingId;
	}

	public String getWssdBuildingName() {
		return this.wssdBuildingName;
	}

	public void setWssdBuildingName(String wssdBuildingName) {
		this.wssdBuildingName = wssdBuildingName;
	}

	public String getWssdDomitoryName() {
		return this.wssdDomitoryName;
	}

	public void setWssdDomitoryName(String wssdDomitoryName) {
		this.wssdDomitoryName = wssdDomitoryName;
	}

	public BigDecimal getShuijine() {
		return shuijine;
	}

	public void setShuijine(BigDecimal shuijine) {
		this.shuijine = shuijine;
	}

	public BigDecimal getDianjine() {
		return dianjine;
	}

	public void setDianjine(BigDecimal dianjine) {
		this.dianjine = dianjine;
	}

	public BigDecimal getZongjine() {
		return zongjine;
	}

	public void setZongjine(BigDecimal zongjine) {
		this.zongjine = zongjine;
	}

}