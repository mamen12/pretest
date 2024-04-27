package com.general.apisubsaccount.beans;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@JsonInclude(Include.NON_NULL)
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserResponse {
	
	@JsonProperty(value = "id")
	private String idUser;
	
	@JsonProperty(value = "name")
	private String name;

	@JsonProperty(value = "email")
	private String email;

	@JsonProperty(value = "active")
	private Integer isActive;
	
	private Boolean status;
	
	private String message;
	
	@JsonProperty(value = "last_login")
	private Date lastLogin;
	
	private String token;
}
