package com.meetnewfriend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.meetnewfriend.service.impl.RealFollowerServiceImpl;

@RestController
@RequestMapping("/realfollower")
public class RealFollowerController {
	@Autowired
	private RealFollowerServiceImpl realFollowerServiceImpl;
}
