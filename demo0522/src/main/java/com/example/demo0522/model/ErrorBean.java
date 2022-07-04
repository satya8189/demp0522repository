package com.example.demo0522.model;

public class ErrorBean {
	private String error;
	private String message;
	private String status;
	
	
	public ErrorBean() {
	}
	
	
	public ErrorBean(String error, String message, String status) {
		super();
		this.error = error;
		this.message = message;
		this.status = status;
	}
	public String getError() {
		return error;
	}
	public void setError(String error) {
		this.error = error;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}

	
	
}
