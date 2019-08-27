package com.bridgelabz.user.model;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;


@Document(collection = "User")
public class User {

	@Id
	private String userId;

	@Indexed(unique = true)
	private String emailId;
	private String name;
	private String password;
	private String mobileNo;
	private boolean isVerify;
	private String image;
	
	
	public User() {}

	public User(String emailId, String name, String password, String mobileNo, boolean isVerify) {

		this.emailId = emailId;
		this.name = name;
		this.password = password;
		this.mobileNo = mobileNo;
		this.isVerify = isVerify;
	}
	
	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
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

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getMobileNo() {
		return mobileNo;
	}

	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
	}

	public boolean isVerify() {
		return isVerify;
	}

	public void setVerify(boolean isVerify) {
		this.isVerify = isVerify;
	}

	@Override
	public String toString() {
		return "User [userId=" + userId + ", emailId=" + emailId + ", name=" + name + ", password=" + password
				+ ", mobileNo=" + mobileNo + ", isVerify=" + isVerify + "]";
	}

}
