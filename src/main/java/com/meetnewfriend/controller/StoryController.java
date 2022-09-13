package com.meetnewfriend.controller;

import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import com.meetnewfriend.entity.Story;
import com.meetnewfriend.service.impl.StoryServiceImpl;


@RestController
@MultipartConfig
@RequestMapping("/story")
public class StoryController {
	@Autowired
	private StoryServiceImpl storyServiceImpl;
	
	@PostMapping("/addstory")
	public RedirectView addStory(@RequestParam("image1") MultipartFile image,@ModelAttribute Story story,HttpServletRequest req) {
		RedirectView md=new RedirectView();
		if(this.storyServiceImpl.addStory(story,image,(int)req.getSession().getAttribute("userId"))) {
			req.getSession().setAttribute("succMsg","Story Added...");
			md.setUrl("/user/dashboard");
		}
		return md;
	}
}
