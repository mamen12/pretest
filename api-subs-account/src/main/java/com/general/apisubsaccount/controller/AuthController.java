package com.general.apisubsaccount.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.general.apisubsaccount.beans.Request;
import com.general.apisubsaccount.beans.Response;
import com.general.apisubsaccount.beans.UserRequest;
import com.general.apisubsaccount.beans.UserResponse;
import com.general.apisubsaccount.constant.ApiResponse;
import com.general.apisubsaccount.service.IUserService;
import com.general.apisubsaccount.util.JwtService;


@RestController
@RequestMapping("/auth")
public class AuthController {
	
    @Autowired
    private IUserService userService;
    
    @Autowired
    private AuthenticationManager authenticationManager; 
	
	@RequestMapping(path = "/login", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public Response<UserResponse> loginUser(@RequestBody Request<UserRequest> rq){
    	Response<UserResponse> rs = new Response<>();
    	Boolean statusAuthenticated = false;
    	try {
    		Authentication authentication = authenticationManager.authenticate(
        			new UsernamePasswordAuthenticationToken(rq.getRequestPayload().getEmail(), rq.getRequestPayload().getPassword()));
    		if (authentication.isAuthenticated()) {
    			statusAuthenticated = true;
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
    	
    	if (statusAuthenticated) { 
    		UserResponse ur = userService.getUserDetail(rq.getRequestPayload().getEmail(), statusAuthenticated);
    		rs.setData(ur);
    		if (rs.getData() != null) {
				userService.updateLastLogin(rq.getRequestPayload().getEmail());
			}
    		rs.setStatusResponse(ApiResponse.SUCCESS);
    	} else { 
    		rs.setStatusResponse(ApiResponse.UNAUTHORIZED);
    	} 
    	return rs;
    }
}
