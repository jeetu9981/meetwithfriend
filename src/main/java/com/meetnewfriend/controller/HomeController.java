package com.meetnewfriend.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
public class HomeController {
	@GetMapping("/")
	public ModelAndView test() {
		ModelAndView md=new ModelAndView();
		md.setViewName("home");
		return md;
	}
}
