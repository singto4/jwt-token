package com.example.app.aspect;

import javax.servlet.http.HttpServletRequest;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

import com.example.app.configuration.JwtTokenProviderCenter;

@Aspect
@Component
public class JwtTokenAspect {
	
	private static final Logger logger = LogManager.getLogger(JwtTokenAspect.class);

	@Autowired
	private JwtTokenProviderCenter jwtTokenProviderCenter;

	@Autowired
	private HttpServletRequest request;

	
	@Before("@annotation(com.example.app.annotation.JwtAuthorizationFilterEnable)")
	public void validateJwt(JoinPoint joinPoint) throws Exception {
		
		logger.debug("Class Name : {}.{} ", joinPoint.getSignature().getDeclaringTypeName(), joinPoint.getSignature().getName());
		
		String header = request.getHeader("Authorization");
		if (header == null || !header.startsWith("Bearer ")) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Missing or invalid Authorization header");
        }
		
		String token = header.substring(7);
        if (!jwtTokenProviderCenter.validateToken(token)) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Invalid JWT token");
        }
		
	}

}
