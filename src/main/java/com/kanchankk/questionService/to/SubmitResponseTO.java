package com.kanchankk.questionService.to;

public class SubmitResponseTO {
	private Integer id;
	private String response;
	public SubmitResponseTO(Integer id, String response) {
		super();
		this.id = id;
		this.response = response;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getResponse() {
		return response;
	}
	public void setResponse(String response) {
		this.response = response;
	}
	public SubmitResponseTO() {
		super();
		// TODO Auto-generated constructor stub
	}
	
}
