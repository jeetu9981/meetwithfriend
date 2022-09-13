package com.meetnewfriend.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.meetnewfriend.entity.Like;
import com.meetnewfriend.repository.LikeRepo;
import com.meetnewfriend.service.impl.LikeServiceImpl;

@RestController
@RequestMapping("/like")
public class LikeController {
	@Autowired
	private LikeServiceImpl likeServiceImpl;
	
	@Autowired
	private LikeRepo likeRepo;
	
	
	//this api for add like
	@GetMapping("/addlike")
	public String addLike(@RequestParam("likeUser") int likeUser,@RequestParam("postId") int postId,HttpServletRequest req) {
		HttpSession session=req.getSession();
		
		// add like on particular post bu particular user
		if(this.likeServiceImpl.addLike(likeUser,postId,(int)session.getAttribute("userId")))
			return "success";
		else
			return "fail";
	}
	
	
	//when user dislike post
	@GetMapping("/dislike")
	public String disLike(@RequestParam("likeUser") int likeUser,@RequestParam("postId") int postId,@RequestParam("realUser") int realUser,HttpServletRequest req) {
		HttpSession session=req.getSession();	
		
		//delete like which like already
		if(this.likeServiceImpl.delete((int)session.getAttribute("userId"),postId,realUser,likeUser)>0)
			session.setAttribute("succMsg", "Dislike.....");
		else
			session.setAttribute("failMsg", "Something went wrong.....");
		return "success";
	}
	
	@GetMapping("/getlike")
	public Like getLikes(@RequestParam("likeId") int likeId) {
		return this.likeRepo.findById(likeId).get();
	}
}





















