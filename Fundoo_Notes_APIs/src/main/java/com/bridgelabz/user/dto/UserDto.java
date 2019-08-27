package com.bridgelabz.user.dto;

public class UserDto {

	private String name;
	private String emailId;
	private String mobileNo;
	private String password;

	public UserDto() {
	}

	public UserDto(String password, String emailId, String name, String mobileNo) {
		this.name = name;
		this.emailId = emailId;
		this.mobileNo = mobileNo;
		this.password = password;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmailId() {
		return emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getMobileNo() {
		return mobileNo;
	}

	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
	}

	@Override
	public String toString() {
		return "UserDto [emailId=" + emailId + ", name=" + name + ", mobileNo=" + mobileNo + ", password=" + password
				+ "]";
	}

}
