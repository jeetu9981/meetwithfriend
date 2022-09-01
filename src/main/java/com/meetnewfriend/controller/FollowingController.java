package com.meetnewfriend.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.view.RedirectView;

import com.meetnewfriend.entities.FollowerEntity;
import com.meetnewfriend.entities.UserEntity;
import com.meetnewfriend.service.FollowerService;
import com.meetnewfriend.service.FollowingService;
import com.meetnewfriend.service.RealFollowerSErvice;

@RestController
@RequestMapping("/following")
public class FollowingController {
	@Autowired
	private FollowingService followingService;
	
	@Autowired
	private FollowerService followerService;
	
	
	@Autowired
	private RealFollowerSErvice realFollowerService;
	
;	@GetMapping("/unfollow")
	public RedirectView unfollow(@RequestParam("following") int following,HttpServletRequest req) {
		RedirectView rd=new RedirectView();
		HttpSession session =req.getSession();
		
		//we decrease current user following
		int i=this.followingService.delete(following,(int)session.getAttribute("userId"));
		
	
		if(i>0) {
			//if both are not following each other then if execute in if from follow request we delete request from other user
			if(this.followerService.deleteRequest(following,(int)session.getAttribute("userId"))>0) {
				//delete follower from onother user
				this.realFollowerService.deleteRealFollower(following,(int)session.getAttribute("userId"));
			}else {
				//delete follower from other person
				this.realFollowerService.deleteRealFollower(following,(int)session.getAttribute("userId"));
				FollowerEntity follower = new FollowerEntity();
				UserEntity user = new UserEntity();
				user.setId(following);
				follower.setAccept(true);
				follower.setFollowBack(false);
				follower.setSendUserRequest(user);
				follower.setAcceptUser((int)session.getAttribute("userId"));
				this.followerService.addRequest(follower);
			}
			session.setAttribute("succMsg","Unfollow Successfully.....");
		}
		else {
			session.setAttribute("failMsg","Something went wrong........");
		}
		rd.setUrl("/user/profile");
		return rd;
	}
}
