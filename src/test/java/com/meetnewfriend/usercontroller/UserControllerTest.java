package com.meetnewfriend.usercontroller;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.meetnewfriend.entities.UserEntity;
import com.meetnewfriend.services.UserService;


@SpringBootTest
public class UserControllerTest {
	
	@Autowired
	private UserService userService;
	
	@Test
	public void emailAndPassword() {
		UserEntity user=new UserEntity();
		user.setEmail("jitupatil961@gmail");
		user.setPassword("1234");
		user=this.userService.signin(user);
		System.out.println(user);
		Assertions.assertNotNull(user);
	}
}
