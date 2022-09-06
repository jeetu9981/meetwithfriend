package com.meetnewfriend.usercontroller;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.meetnewfriend.entity.User;
import com.meetnewfriend.services.UserService;


@SpringBootTest
public class UserControllerTest {
	
	@Autowired
	private UserService userService;
	
	@Test
	public void emailAndPassword() {
		User user=new User();
		user.setEmail("jitupatil961@gmail");
		user.setPassword("1234");
		user=this.userService.signin(user);
		System.out.println(user);
		Assertions.assertNotNull(user);
	}
}
