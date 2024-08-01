package com.example.app.constant;

public class JwtTokenError {
	
	private JwtTokenError() {
        throw new IllegalStateException("Constant JwtTokenError class");
    }
	
	public static final String ACCESS_DENIED = "Access Denied!";
	public static final String TOKEN_REQUIRED = "Token Required!";
	public static final String UNKNOW_ERROR = "Ops! Something went wrong!";

}
