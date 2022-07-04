package com.example.demo0522.model;

import java.util.List;

public class UserBean {
	
	private String firstName;
	private String lastName;
	private String qulification;
	private Long userId;
	private String status;
	private String  address;
	private List<CompanyBean > companyList;

	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getQulification() {
		return qulification;
	}
	public void setQulification(String qulification) {
		this.qulification = qulification;
	}
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	
	
	
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public List<CompanyBean> getCompanyList() {
		return companyList;
	}
	public void setCompanyList(List<CompanyBean> companyList) {
		this.companyList = companyList;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
}
