package com.kanchankk.questionService.to;

public class ErrorMessage {
	private String statusCode;
	private String statusVal;
	private String msg;
	
	public String getStatusCode() {
		return statusCode;
	}
	public void setStatusCode(String statusCode) {
		this.statusCode = statusCode;
	}
	public String getStatusVal() {
		return statusVal;
	}
	public void setStatusVal(String statusVal) {
		this.statusVal = statusVal;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public ErrorMessage() {
		super();
		// TODO Auto-generated constructor stub
	}
	public ErrorMessage(String statusCode, String statusVal, String msg) {
		super();
		this.statusCode = statusCode;
		this.statusVal = statusVal;
		this.msg = msg;
	}
	@Override
	public String toString() {
		return "ErrorMessage [statusCode=" + statusCode + ", statusVal=" + statusVal + ", msg=" + msg + "]";
	}
	
	
}
