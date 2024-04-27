package com.general.apisubsaccount.beans;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_NULL)
public class Request<T> {
	
	@JsonProperty(value = "is_admin")
	private String isAdmin;
	
	@JsonProperty(value = "request_header")
	private RequsetHeader requestHeader;
	
	@JsonProperty(value = "request_payload")
	private T requestPayload;

	public String getIsAdmin() {
		return isAdmin;
	}

	public void setIsAdmin(String isAdmin) {
		this.isAdmin = isAdmin;
	}

	public RequsetHeader getRequestHeader() {
		return requestHeader;
	}

	public void setRequestHeader(RequsetHeader requestHeader) {
		this.requestHeader = requestHeader;
	}

	public T getRequestPayload() {
		return requestPayload;
	}

	public void setRequestPayload(T requestPayload) {
		this.requestPayload = requestPayload;
	}

	
}
