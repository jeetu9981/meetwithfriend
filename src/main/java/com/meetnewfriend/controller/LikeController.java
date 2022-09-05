package com.meetnewfriend.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import com.meetnewfriend.entities.LikeEntity;
import com.meetnewfriend.entities.PostEntity;
import com.meetnewfriend.entities.UserEntity;
import com.meetnewfriend.service.impl.LikeServiceImpl;

@RestController
@RequestMapping("/like")
public class LikeController {
	@Autowired
	private LikeServiceImpl likeServiceImpl;
	
	
	//this api for add like
	@GetMapping("/addlike")
	public String addLike(@RequestParam("likeUser") int likeUser,@RequestParam("postId") int postId,HttpServletRequest req) {
//		RedirectView md=new RedirectView();
		HttpSession session=req.getSession();
		
		PostEntity post=new PostEntity();
		post.setId(postId);
		UserEntity user=new UserEntity();
		user.setId((int)session.getAttribute("userId"));
		
		//this api ceck if it already like or not
		LikeEntity like=this.likeServiceImpl.checkAlreadyLike(post,user,(int)session.getAttribute("userId"));
		
		if(like==null) {
			like =new LikeEntity();
			like.setUser(user);
			like.setPost(post);
			like.setStatus(true);
			like.setRealuser(likeUser);
			
			// add like on particular post bu particular user
			like=this.likeServiceImpl.addLike(like);
			
			
			if(like==null)
				session.setAttribute("failMsg","Somthing went wrong...");
		}else {
			session.setAttribute("succMsg","Already Like...");
		}
//		md.setUrl("/user/dashboard");
		return "success";
	}
	
	
	//when user dislike post
	@GetMapping("/dislike")
	public String disLike(@RequestParam("likeUser") int likeUser,@RequestParam("postId") int postId,@RequestParam("realUser") int realUser,HttpServletRequest req) {
//		RedirectView md=new RedirectView();
		HttpSession session=req.getSession();
		System.out.println("LikeUser : "+likeUser);
		System.out.println("realUser : "+realUser);
		System.out.println("postId : "+postId);
		PostEntity post=new PostEntity();
		post.setId(postId);
		UserEntity user=new UserEntity();
		user.setId(likeUser);
		
		
		//delete like which like already
		int i=this.likeServiceImpl.delete(user,post,realUser);
		if(i==0)
			session.setAttribute("failMsg", "Something went wrong.....");
		/* md.setUrl("/user/dashboard"); */
		return "success";
	}
}





















