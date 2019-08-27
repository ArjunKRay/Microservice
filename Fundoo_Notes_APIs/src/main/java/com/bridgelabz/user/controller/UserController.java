package com.bridgelabz.user.controller;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Arrays;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.bridgelabz.response.Response;
import com.bridgelabz.user.dto.LoginDto;
import com.bridgelabz.user.dto.UserDto;
import com.bridgelabz.user.service.UserService;

@RestController
@RequestMapping("/userservice")
@CrossOrigin(origins ="*",allowedHeaders = "*",exposedHeaders = {"tocken"})
public class UserController {
	@Autowired
	private UserService userService;
	@Autowired
	private RestTemplate restTemplate;


	@PostMapping("/register")
	public ResponseEntity<Response> userRegitration(@RequestBody UserDto userDto, HttpServletRequest request) {
		StringBuffer requestUrl = request.getRequestURL();
		System.out.println("userdetails==="+userDto.getName());
		Response response = userService.registration(userDto, requestUrl);
		return new ResponseEntity<Response>(response, HttpStatus.OK);

	}

	@PostMapping("/login")
	public ResponseEntity<?> loginUser(@RequestBody LoginDto loginDto, HttpServletResponse httpServletResponse)
			throws IllegalArgumentException, UnsupportedEncodingException {
		String token = userService.loginUser(loginDto);
		httpServletResponse.setHeader("Authorization", token);
		String url = "http://localhost:8080/noteservice/notes";
		HttpHeaders headers = new HttpHeaders();
		headers.set("token",token);
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		headers.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<String> entity = new HttpEntity<String>(headers);
		ResponseEntity<Object[]> responseEntity = restTemplate.exchange(url,HttpMethod.GET ,entity,Object[].class);
		Response response = new Response(200, "User logged in successfully", token);
		return new ResponseEntity<>(responseEntity.getBody(), HttpStatus.OK);
	}


	@PostMapping("/forgot")
	public ResponseEntity<Response> forgotPassword(@RequestParam String emailId) {
	
		Response response = userService.forgotPassword(emailId);
		return new ResponseEntity<Response>(response, HttpStatus.OK);
	}

	@PutMapping("/reset")
	public ResponseEntity<Response> reSetPassword(@RequestHeader(value="jwtTocken" ) String tocken, @RequestParam String password) {
		System.out.println("Password==="+password);
		Response response = userService.reSetPassword(tocken, password);
		return new ResponseEntity<Response>(response, HttpStatus.OK);

	}

	@GetMapping("/verification/{tocken}")
	public ResponseEntity<Response> validation(@PathVariable String tocken) {
		String message = userService.userValidation(tocken);
		Response response = new Response(HttpStatus.OK.value(), message, null);
		return new ResponseEntity<Response>(response, HttpStatus.OK);

	}

	

}
