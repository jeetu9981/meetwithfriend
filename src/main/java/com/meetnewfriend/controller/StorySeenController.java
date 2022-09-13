package com.meetnewfriend.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.meetnewfriend.service.impl.StorySeenImpl;
import com.meetnewfriend.service.impl.StoryServiceImpl;

@RestController
@RequestMapping("/storyseen")
public class StorySeenController {
	@Autowired
	private StorySeenImpl storySeenImpl;
	
	@GetMapping("/seen")
	public String seenStory(@RequestParam("storyId") int storyId,HttpServletRequest req) {
		if(this.storySeenImpl.addSeen((int)req.getSession().getAttribute("userId"),storyId)) {
			return "ok";
		}
		return "fail";
	}
}
