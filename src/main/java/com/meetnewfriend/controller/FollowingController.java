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
import com.meetnewfriend.service.impl.FollowerServiceImpl;
import com.meetnewfriend.service.impl.FollowingServiceImpl;
import com.meetnewfriend.service.impl.RealFollowerServiceImpl;

@RestController
@RequestMapping("/following")
public class FollowingController {
	@Autowired
	private FollowingServiceImpl followingServiceImpl;
	
	@Autowired
	private FollowerServiceImpl followerServiceImpl;
	
	
	@Autowired
	private RealFollowerServiceImpl realFollowerService;
	
	
	//unfollow user
;	@GetMapping("/unfollow")
	public RedirectView unfollow(@RequestParam("following") int following,HttpServletRequest req) {
		RedirectView rd=new RedirectView();
		HttpSession session =req.getSession();
		
		//we decrease current user following
		int i=this.followingServiceImpl.delete(following,(int)session.getAttribute("userId"));
		
	
		if(i>0) {
			//if both are not following each other then if execute and inside if from follow request we delete request from other user
			if(this.followerServiceImpl.deleteRequest(following,(int)session.getAttribute("userId"))>0) {
				
				//delete follower from onother user
				this.realFollowerService.deleteRealFollower(following,(int)session.getAttribute("userId"));
				
			}else {
				//delete follower from other person and add this follower in follow back request
				this.realFollowerService.deleteRealFollower(following,(int)session.getAttribute("userId"));
				
				FollowerEntity follower = new FollowerEntity();
				UserEntity user = new UserEntity();
				user.setId(following);
				follower.setAccept(true);
				follower.setFollowBack(false);
				follower.setSendUserRequest(user);
				follower.setAcceptUser((int)session.getAttribute("userId"));
				
				//we add this request in follow back request only
				this.followerServiceImpl.addRequest(follower);
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
