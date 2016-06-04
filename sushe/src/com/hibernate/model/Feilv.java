package com.hibernate.model;

import java.math.BigDecimal;

/**
 * Feilv entity. @author MyEclipse Persistence Tools
 */

public class Feilv implements java.io.Serializable {

	// Fields

	private Integer feilvId;
	private BigDecimal shui;
	private BigDecimal dian;
	private String shuidanwei;
	private String diandanwei;

	// Constructors

	/** default constructor */
	public Feilv() {
	}

	/** full constructor */
	public Feilv(BigDecimal shui, BigDecimal dian, String shuidanwei,
			String diandanwei) {
		this.shui = shui;
		this.dian = dian;
		this.shuidanwei = shuidanwei;
		this.diandanwei = diandanwei;
	}

	// Property accessors

	public Integer getFeilvId() {
		return this.feilvId;
	}

	public void setFeilvId(Integer feilvId) {
		this.feilvId = feilvId;
	}

	public BigDecimal getShui() {
		return this.shui;
	}

	public void setShui(BigDecimal shui) {
		this.shui = shui;
	}

	public BigDecimal getDian() {
		return this.dian;
	}

	public void setDian(BigDecimal dian) {
		this.dian = dian;
	}

	public String getShuidanwei() {
		return this.shuidanwei;
	}

	public void setShuidanwei(String shuidanwei) {
		this.shuidanwei = shuidanwei;
	}

	public String getDiandanwei() {
		return this.diandanwei;
	}

	public void setDiandanwei(String diandanwei) {
		this.diandanwei = diandanwei;
	}

}