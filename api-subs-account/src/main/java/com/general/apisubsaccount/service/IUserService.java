package com.general.apisubsaccount.service;

import java.util.List;

import com.general.apisubsaccount.beans.UserRequest;
import com.general.apisubsaccount.beans.UserResponse;


public interface IUserService {

	public String addUser(UserRequest userRq);

	public void updateLastLogin(String userName);
	
	public UserResponse getUserDetail(String email, Boolean statusAuth);
	
}
