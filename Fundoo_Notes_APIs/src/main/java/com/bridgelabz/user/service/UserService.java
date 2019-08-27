package com.bridgelabz.user.service;

import com.bridgelabz.response.Response;
import com.bridgelabz.user.dto.LoginDto;
import com.bridgelabz.user.dto.UserDto;

public interface UserService {

	Response registration(UserDto userDto, StringBuffer requestUrl);

	String loginUser(LoginDto loginDto);

	Response forgotPassword(String emailId);

	Response reSetPassword(String tocken, String password);

	String userValidation(String tocken);
}
