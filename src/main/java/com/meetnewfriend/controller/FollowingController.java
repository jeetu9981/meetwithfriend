package com.meetnewfriend.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.view.RedirectView;

import com.meetnewfriend.entity.User;
import com.meetnewfriend.service.impl.FollowingServiceImpl;

@RestController
@RequestMapping("/following")
public class FollowingController {
	@Autowired
	private FollowingServiceImpl followingServiceImpl;
	
	
	
	
	//unfollow user
;	@GetMapping("/unfollow")
	public RedirectView unfollow(@RequestParam("following") int following,HttpServletRequest req) throws Exception {
		RedirectView rd=new RedirectView();
		HttpSession session =req.getSession();
		
		//we decrease current user following
		if(this.followingServiceImpl.delete(following,(int)session.getAttribute("userId"))>0)
			session.setAttribute("succMsg","Unfollow Successfully.....");
		else
			session.setAttribute("failMsg","Something Went wrong......");
		
		rd.setUrl("/user/profile");
		return rd;
	}


	@GetMapping("/mutualfriends")
	public List<User> mutual(@RequestParam("userId") int userId,HttpServletRequest req){
		return this.followingServiceImpl.getMutualFriends(userId,(int)req.getSession().getAttribute("userId"));
	}
}
