package com.example.app.common.response;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_NULL)
public class Header {

	private String status;
	private String respDateTime;
	private String errorMsg;

	public Header() {
		this.status = "S";
		this.respDateTime = this.getTimestamp();
	}

	public Header(String status, String errorMsg) {
		super();
		this.status = status;
		this.respDateTime = this.getTimestamp();
		this.errorMsg = errorMsg;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getRespDateTime() {
		return respDateTime;
	}

	public void setRespDateTime(String respDateTime) {
		this.respDateTime = respDateTime;
	}
	
	public String getErrorMsg() {
		return errorMsg;
	}

	public void setErrorMsg(String errorMsg) {
		this.errorMsg = errorMsg;
	}

	private String getTimestamp() {
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss", Locale.US);
		return sdf.format(Calendar.getInstance(Locale.US).getTime().getTime());
	}

}
