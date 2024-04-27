package com.general.apisubsaccount.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.general.apisubsaccount.beans.Request;
import com.general.apisubsaccount.beans.Response;
import com.general.apisubsaccount.beans.UserRequest;
import com.general.apisubsaccount.beans.UserResponse;
import com.general.apisubsaccount.service.IUserService;

@RestController
@RequestMapping("/api") 
public class UserController {
	
	private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private IUserService userService;
    
  
    String pattern = "yyyy-MM-dd HH:mm:ss";
    
    @GetMapping("/welcome") 
    public String welcome() { 
        return "Welcome this endpoint is secure"; 
    } 
  
} 
