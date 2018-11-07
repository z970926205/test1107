package com.example.demo.jinx.general;

public class BaseEntity {
	private Integer id;
	private String userName;
	private String password;

	public BaseEntity() {
		super();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String toString() {
		return "BaseEntity [id=" + id + ", userName=" + userName
				+ ", password=" + password + "]";
	}

}
