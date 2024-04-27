package com.general.apisubsaccount.service.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.general.apisubsaccount.entity.UserInfo;
import com.general.apisubsaccount.repo.UserRepository;
import com.general.apisubsaccount.util.UserInfoDetails;


@Service
public class AuthService implements UserDetailsService  {

	@Autowired
	private UserRepository userRepo;
	
	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		Optional<UserInfo> userDetail = userRepo.findByEmail(email);
		
		return userDetail.map(UserInfoDetails::new)
				.orElseThrow(()-> new UsernameNotFoundException("User Not Found"));
	}
}
