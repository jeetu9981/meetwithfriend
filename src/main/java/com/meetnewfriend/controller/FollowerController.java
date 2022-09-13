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

import com.meetnewfriend.entity.Follower;
import com.meetnewfriend.service.impl.FollowerServiceImpl;
import com.meetnewfriend.service.impl.RealFollowerServiceImpl;

@RestController
@RequestMapping("/follower")
public class FollowerController {
	@Autowired
	private FollowerServiceImpl followerServiceImpl;

	@Autowired
	RealFollowerServiceImpl realFollowerServiceImpl;

	//follow request from one user to the other user
	@GetMapping("/followrequest")
	public RedirectView SendRequest(@RequestParam("userId") int userId, HttpServletRequest req) {
		RedirectView rd = new RedirectView();
		HttpSession session = req.getSession();
		String status=this.followerServiceImpl.addRequest(userId,(int) session.getAttribute("userId"));

		if(status.equals("success")) 
			session.setAttribute("succMsg", "Request Sent To The User..."); 
		else if(status.equals("already")) 
			session.setAttribute("succMsg", "Alrady Sent Request...");
		else if(status.equals("fail")) 
			session.setAttribute("failMsg","Something went wrong.....");;
		
		rd.setUrl("../user/dashboard");
		return rd;
	}

	//get all follow request to the user
	@GetMapping("/checkrequest")
	public ModelAndView checkRequest(HttpServletRequest req) {
		ModelAndView md = new ModelAndView();
		HttpSession session = req.getSession();
		
		//get all follow request to the user
		List<Follower> requests = this.followerServiceImpl.getRequest((int) session.getAttribute("userId"));
		
		md.addObject("request", requests);
		session.setAttribute("succMsg", req.getAttribute("succMsg"));
		md.setViewName("allrequests");
		return md;
	}

	//when user accept follow request if other user
	@GetMapping("/acceptrequest")
	public RedirectView acceptRequest(@RequestParam("userId") int userId, HttpServletRequest req) throws Exception {
		RedirectView rd = new RedirectView();
		HttpSession session = req.getSession();
		
		int acceptUser = (int) session.getAttribute("userId");
		
		//when user accept request we update accept field in database with the help of we know user accept request but does't not provide till follow back
		if(this.followerServiceImpl.accept(acceptUser, userId)>0)
			session.setAttribute("succMsg","request Accepted..");
		else
			session.setAttribute("failMsg","Something went wrong...");
		
		rd.setUrl("checkrequest");
		return rd;
	}

	//give followback to the user
	@GetMapping("/followback")
	public RedirectView followBack(@RequestParam("userId") int userId, HttpServletRequest req) throws Exception {
		RedirectView rd = new RedirectView();
		HttpSession session = req.getSession();
		
		if(!this.followerServiceImpl.saveFollower((int) session.getAttribute("userId"),userId))
			session.setAttribute("failMsg","Please First Accept Request...");
		rd.setUrl("checkrequest");
		return rd;
	}
	
	
	//when user decline follow request
	@GetMapping("/declinerequest")
	public RedirectView declineRequest(@RequestParam("userId") int requestUser,HttpServletRequest req) throws Exception {
		RedirectView rd=new RedirectView();
		HttpSession session=req.getSession();
		
		//user can delete follow request of other user
		int i=this.followerServiceImpl.deleteRequest((int)session.getAttribute("userId"), requestUser);
		if(i>0)
		{
			session.setAttribute("succMsg","Request Decline Successsfully.....");
		}
		else
			session.setAttribute("failMsg","Something went wrong...");
		
		rd.setUrl("/user/dashboard");
		return rd;
	}

}


























