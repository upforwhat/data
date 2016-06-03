package com.hibernate.model;

/**
 * Shebei entity. @author MyEclipse Persistence Tools
 */

public class Shebei implements java.io.Serializable {

	// Fields

	private Integer shebeiId;
	private Integer shebeiBuildingId;
	private String shebeiName;
	private String shebeiState;
	private String shebeiGoumaishijian;
	private String shebeiYouxiaoshijian;
	private String shebeiZuijinjiachashijian;
	private String shebeiBianhao;
	private String shebeiBuildingName;
	private String shebeiThree;

	// Constructors

	/** default constructor */
	public Shebei() {
	}

	/** full constructor */
	public Shebei(Integer shebeiBuildingId, String shebeiName,
			String shebeiState, String shebeiGoumaishijian,
			String shebeiYouxiaoshijian, String shebeiZuijinjiachashijian,
			String shebeiBianhao, String shebeiBuildingName, String shebeiThree) {
		this.shebeiBuildingId = shebeiBuildingId;
		this.shebeiName = shebeiName;
		this.shebeiState = shebeiState;
		this.shebeiGoumaishijian = shebeiGoumaishijian;
		this.shebeiYouxiaoshijian = shebeiYouxiaoshijian;
		this.shebeiZuijinjiachashijian = shebeiZuijinjiachashijian;
		this.shebeiBianhao = shebeiBianhao;
		this.shebeiBuildingName = shebeiBuildingName;
		this.shebeiThree = shebeiThree;
	}

	// Property accessors

	public Integer getShebeiId() {
		return this.shebeiId;
	}

	public void setShebeiId(Integer shebeiId) {
		this.shebeiId = shebeiId;
	}

	public Integer getShebeiBuildingId() {
		return this.shebeiBuildingId;
	}

	public void setShebeiBuildingId(Integer shebeiBuildingId) {
		this.shebeiBuildingId = shebeiBuildingId;
	}

	public String getShebeiName() {
		return this.shebeiName;
	}

	public void setShebeiName(String shebeiName) {
		this.shebeiName = shebeiName;
	}

	public String getShebeiState() {
		return this.shebeiState;
	}

	public void setShebeiState(String shebeiState) {
		this.shebeiState = shebeiState;
	}

	public String getShebeiGoumaishijian() {
		return this.shebeiGoumaishijian;
	}

	public void setShebeiGoumaishijian(String shebeiGoumaishijian) {
		this.shebeiGoumaishijian = shebeiGoumaishijian;
	}

	public String getShebeiYouxiaoshijian() {
		return this.shebeiYouxiaoshijian;
	}

	public void setShebeiYouxiaoshijian(String shebeiYouxiaoshijian) {
		this.shebeiYouxiaoshijian = shebeiYouxiaoshijian;
	}

	public String getShebeiZuijinjiachashijian() {
		return this.shebeiZuijinjiachashijian;
	}

	public void setShebeiZuijinjiachashijian(String shebeiZuijinjiachashijian) {
		this.shebeiZuijinjiachashijian = shebeiZuijinjiachashijian;
	}

	public String getShebeiBianhao() {
		return this.shebeiBianhao;
	}

	public void setShebeiBianhao(String shebeiBianhao) {
		this.shebeiBianhao = shebeiBianhao;
	}

	public String getShebeiBuildingName() {
		return this.shebeiBuildingName;
	}

	public void setShebeiBuildingName(String shebeiBuildingName) {
		this.shebeiBuildingName = shebeiBuildingName;
	}

	public String getShebeiThree() {
		return this.shebeiThree;
	}

	public void setShebeiThree(String shebeiThree) {
		this.shebeiThree = shebeiThree;
	}

}