package com.example.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.app.annotation.JwtAuthorizationFilterEnable;
import com.example.app.common.response.ResponseSimpleObject;
import com.example.app.models.UserInfoResponse;
import com.example.app.service.UserInfoService;

@RestController
@RequestMapping("/api")
public class UserInfoController {
	
	@Autowired
	private UserInfoService userInfoService;
	
	@JwtAuthorizationFilterEnable
	@GetMapping("/userInfo/{userId}")
    public ResponseSimpleObject<UserInfoResponse> userInfo(@PathVariable String userId) {
		ResponseSimpleObject<UserInfoResponse> response = new ResponseSimpleObject<>();
		UserInfoResponse data = userInfoService.getUserInfo();
		response.setData(data);
		return response;
    }

}
