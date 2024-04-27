package com.general.apisubsaccount.beans;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.general.apisubsaccount.constant.ApiResponse;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(Include.NON_NULL)
@JsonPropertyOrder({"code", "message", "message_en", "data"})
public class Response<T>{
	
	@JsonProperty(value = "code")
	private String status;
	
	@JsonProperty(value = "message")
	private String message;
	
	@JsonProperty(value = "message_en")
	private String messageEn;
	
	private T data;
	
	public void setStatusResponse(ApiResponse rs) {
		this.status = rs.getCode();
		this.messageEn = rs.getEn();
		this.message = rs.getId();
	}
}