package com.meetnewfriend.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import com.meetnewfriend.entities.FollowerEntity;
import com.meetnewfriend.entities.FollowingEntity;
import com.meetnewfriend.entities.RealFollowerEntity;
import com.meetnewfriend.entities.UserEntity;
import com.meetnewfriend.service.impl.FollowerServiceImpl;
import com.meetnewfriend.service.impl.FollowingServiceImpl;
import com.meetnewfriend.service.impl.RealFollowerServiceImpl;

@RestController
@RequestMapping("/follower")
public class FollowerController {
	@Autowired
	private FollowerServiceImpl followerServiceImpl;

	@Autowired
	private FollowingServiceImpl followingServiceImpl;

	@Autowired
	RealFollowerServiceImpl realFollowerServiceImpl;

	//follow request from one user to the other user
	@GetMapping("/followrequest")
	public RedirectView SendRequest(@RequestParam("userId") int userId, HttpServletRequest req) {
		RedirectView rd = new RedirectView();
		FollowerEntity follower = new FollowerEntity();
		HttpSession session = req.getSession();

		//check already user following or not
		RealFollowerEntity realfollower = this.realFollowerServiceImpl.checkExistOrNot(userId,(int) session.getAttribute("userId"));

		//check user sent before request or not in follower requets
		FollowerEntity followerEntity = this.followerServiceImpl.checkExixtOrNot(userId,(int) session.getAttribute("userId"));
		
		UserEntity user = new UserEntity();
		user.setId((int) session.getAttribute("userId"));
		
		if (realfollower == null && followerEntity==null) {
			follower.setAccept(false);
			follower.setFollowBack(false);
			follower.setSendUserRequest(user);
			follower.setAcceptUser(userId);

			//add follow request
			this.followerServiceImpl.addRequest(follower);
			session.setAttribute("succMsg", "Request Sent To The User...");
			rd.setUrl("../user/dashboard");
		} else {
			session.setAttribute("succMsg", "Alrady Sent Request...");
			rd.setUrl("../user/dashboard");
		}
		return rd;
	}

	//get all follow request to the user
	@GetMapping("/checkrequest")
	public ModelAndView checkRequest(HttpServletRequest req) {
		ModelAndView md = new ModelAndView();
		HttpSession session = req.getSession();
		
		//get all follow request to the user
		List<FollowerEntity> requests = this.followerServiceImpl.getRequest((int) session.getAttribute("userId"));
		
		md.addObject("request", requests);
		session.setAttribute("succMsg", req.getAttribute("succMsg"));
		md.setViewName("allrequests");
		return md;
	}

	//when user accept follow request if other user
	@GetMapping("/acceptrequest")
	public RedirectView acceptRequest(@RequestParam("userId") int userId, HttpServletRequest req) {
		RedirectView rd = new RedirectView();
		HttpSession session = req.getSession();
		
		int acceptUser = (int) session.getAttribute("userId");
		
		//when user accept request we update accept field in database with the help of we know user accept request but does't not provide till follow back
		int i = this.followerServiceImpl.accept(acceptUser, userId);
		if (i > 0) {

			RealFollowerEntity realFollowerEntity = new RealFollowerEntity();
			UserEntity user = new UserEntity();
			user.setId(userId);
			realFollowerEntity.setFollower(user);
			realFollowerEntity.setUser_id(acceptUser);

			FollowingEntity followingEntity = new FollowingEntity();
			UserEntity user1 = new UserEntity();
			user1.setId((int) session.getAttribute("userId"));
			
			followingEntity.setFollowing(user1);
			followingEntity.setUser_id(userId);
			
			//here we add following of one user and add increase follower of one user
			if (this.followingServiceImpl.addfollowing(followingEntity) != null
					&& this.realFollowerServiceImpl.addFollower(realFollowerEntity) != null) {
				session.setAttribute("succMsg", "Request Accepted...");
			} else {
				session.setAttribute("failMsg", "Something went wrong...");
			}
		} else {
			session.setAttribute("failMsg", "Something went wrong...");
		}
		rd.setUrl("checkrequest");
		return rd;
	}

	//give followback to the user
	@GetMapping("/followback")
	public RedirectView followBack(@RequestParam("userId") int userId, HttpServletRequest req) {
		RedirectView rd = new RedirectView();
		HttpSession session = req.getSession();
		
		int acceptUser = (int) session.getAttribute("userId");
		RealFollowerEntity realFollowerEntity = new RealFollowerEntity();
		UserEntity user = new UserEntity();
		user.setId(acceptUser);
		realFollowerEntity.setFollower(user);
		realFollowerEntity.setUser_id(userId);

		FollowingEntity followingEntity = new FollowingEntity();
		UserEntity user1 = new UserEntity();
		user1.setId(userId);
		followingEntity.setFollowing(user1);
		followingEntity.setUser_id(acceptUser);

		//here we add following of one user and add increase follower of one user
		if (this.followingServiceImpl.addfollowing(followingEntity) != null
				&& this.realFollowerServiceImpl.addFollower(realFollowerEntity) != null) {
			this.followerServiceImpl.deleteRequest(acceptUser, userId);
			session.setAttribute("succMsg", "Request Accepted...");
		} else {
			session.setAttribute("failMsg", "Something went wrong...");
		}

		rd.setUrl("checkrequest");
		return rd;
	}
	
	
	//when user decline follow request
	@GetMapping("/declinerequest")
	public RedirectView declineRequest(@RequestParam("userId") int requestUser,HttpServletRequest req) {
		RedirectView rd=new RedirectView();
		HttpSession session=req.getSession();
		
		//user can delete follow request of other user
		int i=this.followerServiceImpl.deleteRequest((int)session.getAttribute("userId"), requestUser);
		if(i>0)
			session.setAttribute("succMsg","Request Decline Successsfully.....");
		else
			session.setAttribute("failMsg","Something went wrong...");
		rd.setUrl("/user/dashboard");
		return rd;
	}

}


























