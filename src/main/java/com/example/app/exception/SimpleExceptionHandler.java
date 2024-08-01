package com.example.app.exception;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.example.app.common.response.Header;
import com.example.app.common.response.ResponseSimpleObject;

@ControllerAdvice
public class SimpleExceptionHandler extends ResponseEntityExceptionHandler {

	private final Logger logger = LogManager.getLogger(SimpleExceptionHandler.class);

	@ExceptionHandler(APIException.class)
	public ResponseEntity<Object> handleAPIException(APIException exception) {

		logger.error("handleAPIException");
		logger.error(ExceptionUtils.getStackTrace(exception));

		ResponseSimpleObject<Object> jsonObject = new ResponseSimpleObject<>();
		Header header = new Header("F", exception.getErrorMessage());
		jsonObject.setHeader(header);

		return this.buildResponseEntity(jsonObject, exception.getHttpStatus());

	}

	private ResponseEntity<Object> buildResponseEntity(ResponseSimpleObject<Object> response, HttpStatus httpStatus) {
		return new ResponseEntity<>(response, httpStatus);
	}
}
