package com.hibernate.model;

/**
 * Student entity. @author MyEclipse Persistence Tools
 */

public class Student implements java.io.Serializable {

	// Fields

	private Integer studentId;
	private Integer studentDomitoryId;
	private String studentUsername;
	private String studentPassword;
	private String studentName;
	private String studentSex;
	private String studentClass;
	private String studentState;

	// Constructors

	/** default constructor */
	public Student() {
	}

	/** full constructor */
	public Student(Integer studentDomitoryId, String studentUsername,
			String studentPassword, String studentName, String studentSex,
			String studentClass, String studentState) {
		this.studentDomitoryId = studentDomitoryId;
		this.studentUsername = studentUsername;
		this.studentPassword = studentPassword;
		this.studentName = studentName;
		this.studentSex = studentSex;
		this.studentClass = studentClass;
		this.studentState = studentState;
	}

	// Property accessors

	public Integer getStudentId() {
		return this.studentId;
	}

	public void setStudentId(Integer studentId) {
		this.studentId = studentId;
	}

	public Integer getStudentDomitoryId() {
		return this.studentDomitoryId;
	}

	public void setStudentDomitoryId(Integer studentDomitoryId) {
		this.studentDomitoryId = studentDomitoryId;
	}

	public String getStudentUsername() {
		return this.studentUsername;
	}

	public void setStudentUsername(String studentUsername) {
		this.studentUsername = studentUsername;
	}

	public String getStudentPassword() {
		return this.studentPassword;
	}

	public void setStudentPassword(String studentPassword) {
		this.studentPassword = studentPassword;
	}

	public String getStudentName() {
		return this.studentName;
	}

	public void setStudentName(String studentName) {
		this.studentName = studentName;
	}

	public String getStudentSex() {
		return this.studentSex;
	}

	public void setStudentSex(String studentSex) {
		this.studentSex = studentSex;
	}

	public String getStudentClass() {
		return this.studentClass;
	}

	public void setStudentClass(String studentClass) {
		this.studentClass = studentClass;
	}

	public String getStudentState() {
		return this.studentState;
	}

	public void setStudentState(String studentState) {
		this.studentState = studentState;
	}

}