package com.example.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.app.common.response.ResponseSimpleObject;
import com.example.app.models.LoginRequest;
import com.example.app.models.LoginResponse;
import com.example.app.service.LoginService;

@RestController
@RequestMapping("/api")
public class LoginController {
	
	@Autowired
	private LoginService loginService;
	
    @PostMapping("/login")
    public ResponseSimpleObject<LoginResponse> login(@RequestBody LoginRequest login) throws Exception {
    	ResponseSimpleObject<LoginResponse> response = new ResponseSimpleObject<>();
    	LoginResponse data = loginService.validateLogin(login);
    	response.setData(data);
    	return response;
    }
}
