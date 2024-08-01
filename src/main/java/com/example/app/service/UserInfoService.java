package com.example.app.service;

import org.springframework.stereotype.Service;

import com.example.app.models.UserInfoResponse;

@Service
public class UserInfoService {
	
	public UserInfoResponse getUserInfo() {

		UserInfoResponse userInfoResponse = new UserInfoResponse();
		userInfoResponse.setUsername("test");
		userInfoResponse.setUserCode("1000");
		return userInfoResponse;
		
	}

}
