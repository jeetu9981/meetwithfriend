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
import com.meetnewfriend.service.FollowerService;
import com.meetnewfriend.service.FollowingService;
import com.meetnewfriend.service.RealFollowerSErvice;

@RestController
@RequestMapping("/follower")
public class FollowerController {
	@Autowired
	private FollowerService followerService;

	@Autowired
	private FollowingService followingService;

	@Autowired
	RealFollowerSErvice realFollowerSErvice;

	@GetMapping("/followrequest")
	public RedirectView SendRequest(@RequestParam("userId") int userId, HttpServletRequest req) {
		RedirectView rd = new RedirectView();
		FollowerEntity follower = new FollowerEntity();
		HttpSession session = req.getSession();

		RealFollowerEntity realfollower = this.realFollowerSErvice.checkExistOrNot(userId,(int) session.getAttribute("userId"));

		FollowerEntity followerEntity = this.followerService.checkExixtOrNot(userId,(int) session.getAttribute("userId"));
		UserEntity user = new UserEntity();
		user.setId((int) session.getAttribute("userId"));
		if (realfollower == null && followerEntity==null) {
			follower.setAccept(false);
			follower.setFollowBack(false);
			follower.setSendUserRequest(user);
			follower.setAcceptUser(userId);

			this.followerService.addRequest(follower);
			session.setAttribute("succMsg", "Request Sent To The User...");
			rd.setUrl("../user/dashboard");
		} else {
			session.setAttribute("succMsg", "Alrady Sent Request...");
			rd.setUrl("../user/dashboard");
		}
		return rd;
	}

	@GetMapping("/checkrequest")
	public ModelAndView checkRequest(HttpServletRequest req) {
		ModelAndView md = new ModelAndView();
		HttpSession session = req.getSession();
		List<FollowerEntity> requests = this.followerService.getRequest((int) session.getAttribute("userId"));
		md.addObject("request", requests);
		session.setAttribute("succMsg", req.getAttribute("succMsg"));
		md.setViewName("allrequests");
		return md;
	}

	@GetMapping("/acceptrequest")
	public RedirectView acceptRequest(@RequestParam("userId") int userId, HttpServletRequest req) {
		RedirectView rd = new RedirectView();
		HttpSession session = req.getSession();
		int acceptUser = (int) session.getAttribute("userId");

		int i = this.followerService.accept(acceptUser, userId);
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

			if (this.followingService.addfollowing(followingEntity) != null
					&& this.realFollowerSErvice.addFollower(realFollowerEntity) != null) {
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

		if (this.followingService.addfollowing(followingEntity) != null
				&& this.realFollowerSErvice.addFollower(realFollowerEntity) != null) {
			this.followerService.deleteRequest(acceptUser, userId);
			session.setAttribute("succMsg", "Request Accepted...");
		} else {
			session.setAttribute("failMsg", "Something went wrong...");
		}

		rd.setUrl("checkrequest");
		return rd;
	}

}


























