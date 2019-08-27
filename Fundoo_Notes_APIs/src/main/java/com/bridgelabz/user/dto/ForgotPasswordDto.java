package com.bridgelabz.user.dto;

public class ForgotPasswordDto
{
	
   private String password ;
	
   
   
    public ForgotPasswordDto() {}

    
	public ForgotPasswordDto(String password) 
	{
		this.password = password;
	}

	
	public String getPassword() 
	{
		return password;
	}

	public void setPassword(String password)
	{
		this.password = password;
	}

}
