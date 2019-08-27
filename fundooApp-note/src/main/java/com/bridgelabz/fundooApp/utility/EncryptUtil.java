package com.bridgelabz.fundooApp.utility;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;


public class EncryptUtil {

	public String encryptPassword(String password) {
		return new BCryptPasswordEncoder().encode(password);
	}

	

}
