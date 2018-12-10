package com.example.demo.jinx.singleUser;

public class SingleUserEntity {
	
	private Integer id;
	private Integer userId;
	private String registerDate;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public String getRegisterDate() {
		return registerDate;
	}
	public void setRegisterDate(String registerDate) {
		this.registerDate = registerDate;
	}
	@Override
	public String toString() {
		return "SingleUserEntity [id=" + id + ", userId=" + userId
				+ ", registerDate=" + registerDate + "]";
	}
	
}
