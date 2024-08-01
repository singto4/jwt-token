package com.example.app.service;

import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.example.app.configuration.JwtTokenProviderCenter;
import com.example.app.exception.APIException;
import com.example.app.models.LoginRequest;
import com.example.app.models.LoginResponse;
import com.example.app.models.UserProfile;

@Service
public class LoginService {

	private static final Logger logger = LogManager.getLogger(LoginService.class);

	@Autowired
	private JwtTokenProviderCenter jwtTokenProviderCenter;

	public LoginResponse validateLogin(LoginRequest login) throws Exception {
		
		LoginResponse loginResponse = new LoginResponse();

		if (StringUtils.isEmpty(login.getUsername()) || StringUtils.isEmpty(login.getPassword())) {
			throw new APIException(HttpStatus.UNPROCESSABLE_ENTITY, "Username or password invalid");
		}

		if ("test".equals(login.getUsername()) && "pass".equals(login.getPassword())) {
			UserProfile profile = UserProfile.builder().userId("1000").username(login.getUsername()).build();
			String token = jwtTokenProviderCenter.genarateToken(profile);
			loginResponse.setToken(token);
			logger.info("Login success token : {}", token);
		} else {
			throw new APIException(HttpStatus.UNPROCESSABLE_ENTITY, "Username or password invalid");
		}

		return loginResponse;
	}

}
