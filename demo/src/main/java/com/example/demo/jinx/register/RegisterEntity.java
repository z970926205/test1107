package com.example.demo.jinx.register;

public class RegisterEntity {

	private Integer id;
	private String userName;
	private String password;
	//sql有效行数
	private Integer count;
	public Integer getCount() {
		return count;
	}
	public void setCount(Integer count) {
		this.count = count;
	}
	public RegisterEntity() {
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
		return "LoginEntity [id=" + id + ", userName=" + userName
				+ ", password=" + password + ", count=" + count + "]";
	}
	
}
