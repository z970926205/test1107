package com.example.demo.jinx.personal;

public class Page<T> {
	private Integer state;
	private String message;
	private T data;
	public Page() {
		super();
	}
	public Page(Integer state, String message) {
		super();
		this.state = state;
		this.message = message;
	}
	public Integer getState() {
		return state;
	}
	public void setState(Integer state) {
		this.state = state;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public T getData() {
		return data;
	}
	public void setData(T data) {
		this.data = data;
	}
	
}
