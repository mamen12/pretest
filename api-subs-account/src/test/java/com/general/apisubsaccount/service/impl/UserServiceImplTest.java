package com.general.apisubsaccount.service.impl;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Date;
import java.util.UUID;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.general.apisubsaccount.entity.UserInfo;
import com.general.apisubsaccount.repo.UserRepository;

@SpringBootTest
class UserServiceImplTest {

	@Autowired
	private UserRepository repo;
	
	
	@Autowired
	private PasswordEncoder encoder;
	
//	@BeforeEach
//	void setUp() {
//		repo.deleteAll();
//	}
	
	@Test
	void testAddUser() {
		UserInfo user = new UserInfo().builder()
				.id(UUID.randomUUID().toString())
				.createdAt(new Date())
				.createdby("SYSTEM")
				.email("syahrulilham@gmail.com")
				.password(encoder.encode("password"))
				.name("syahrul")
				.build();
		
		repo.save(user);
	}

}
