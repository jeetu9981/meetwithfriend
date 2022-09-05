package com.meetnewfriend.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.view.RedirectView;

@RestController
public class HomeController {
	@GetMapping("/")
	public RedirectView redirecToSignup() {
		RedirectView rd=new RedirectView();
		rd.setUrl("/user/");
		return rd;
	}
}
