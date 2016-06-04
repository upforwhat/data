package com.hibernate.model;

import java.sql.Timestamp;

/**
 * Gongao entity. @author MyEclipse Persistence Tools
 */

public class Gongao implements java.io.Serializable {

	// Fields

	private Integer gonggaoId;
	private Timestamp gonggaoTime;
	private String gonggaoConten;
	private String gonggaoShow;

	// Constructors

	/** default constructor */
	public Gongao() {
	}

	/** full constructor */
	public Gongao(Timestamp gonggaoTime, String gonggaoConten,
			String gonggaoShow) {
		this.gonggaoTime = gonggaoTime;
		this.gonggaoConten = gonggaoConten;
		this.gonggaoShow = gonggaoShow;
	}

	// Property accessors

	public Integer getGonggaoId() {
		return this.gonggaoId;
	}

	public void setGonggaoId(Integer gonggaoId) {
		this.gonggaoId = gonggaoId;
	}

	public Timestamp getGonggaoTime() {
		return this.gonggaoTime;
	}

	public void setGonggaoTime(Timestamp gonggaoTime) {
		this.gonggaoTime = gonggaoTime;
	}

	public String getGonggaoConten() {
		return this.gonggaoConten;
	}

	public void setGonggaoConten(String gonggaoConten) {
		this.gonggaoConten = gonggaoConten;
	}

	public String getGonggaoShow() {
		return this.gonggaoShow;
	}

	public void setGonggaoShow(String gonggaoShow) {
		this.gonggaoShow = gonggaoShow;
	}

}