package com.bridgelabz.user.dto;



public class LoginDto {
	
	private String emailId;
	private String password ;
	
	
	public LoginDto(String emailId, String password) 
	{
		this.emailId = emailId;
		this.password = password;
	}
	
	public LoginDto()
	{
		
	}
	
	public String getEmailId() {
		return emailId;
	}
	
	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}
	
	public String getPassword() {
		return password;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}

	
}