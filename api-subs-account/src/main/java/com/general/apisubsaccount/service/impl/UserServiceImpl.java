package com.general.apisubsaccount.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.general.apisubsaccount.beans.UserRequest;
import com.general.apisubsaccount.beans.UserResponse;
import com.general.apisubsaccount.entity.UserInfo;
import com.general.apisubsaccount.repo.UserRepository;
import com.general.apisubsaccount.service.IUserService;
import com.general.apisubsaccount.util.JwtService;


@Service
public class UserServiceImpl implements IUserService {
	
	private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

	
	@Autowired
	private UserRepository userRepo;
	
	@Autowired
	private PasswordEncoder encoder;
	
	@Autowired
    private JwtService jwtService; 
	
	@Override
	public String addUser(UserRequest userRq) {
		if (userRepo.countEmailUser(userRq.getEmail()) > 0) {
			return "Email Already Exists";
		}else {
			UserInfo userInfo = new UserInfo().builder()
					.email(userRq.getEmail())
					.name(userRq.getUsername())
					.password(encoder.encode(userRq.getPassword()))
					.build();
	        userRepo.save(userInfo); 
	        return "User Added Successfully"; 
		}
	}

	@Override
	public void updateLastLogin(String email) {
		try {
			UserInfo user = userRepo.findByEmail(email).orElseThrow();
			user.setLastLogin(new Date());
			
			userRepo.save(user);
			
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
	}

	@Override
	public UserResponse getUserDetail(String email, Boolean statusAuth) {
		UserResponse rs = new UserResponse();
		UserInfo users = new UserInfo();
		try {
			if (statusAuth) {
				UserInfo u = userRepo.findByEmail(email).orElseThrow();
				rs = UserResponse.builder()
				.email(u.getEmail())
				.name(u.getName())
				.lastLogin(u.getLastLogin())
				.isActive(u.getDeletedAt() != null ? 0  :1)
				.build();
				
				// untuk set token
				rs.setToken(jwtService.generateToken(email));
			}
			
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		return rs;
	}
	
	
	
	
}
