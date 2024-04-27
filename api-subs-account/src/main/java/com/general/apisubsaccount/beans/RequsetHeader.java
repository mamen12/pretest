package com.general.apisubsaccount.beans;

import com.fasterxml.jackson.annotation.JsonProperty;

public class RequsetHeader {
	
	
	@JsonProperty(value = "request_id")
	private String requestId;
	
	
	@JsonProperty(value = "chanel")
	private String chanel
;

	public String getChanel() {
		return chanel;
	}

	public void setChanel(String chanel) {
		this.chanel = chanel;
	}

	public String getRequestId() {
		return requestId;
	}

	public void setRequestId(String requestId) {
		this.requestId = requestId;
	}
	
	
	
}
