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
import com.meetnewfriend.service.LikeService;

@RestController
@RequestMapping("/like")
public class LikeController {
	@Autowired
	private LikeService likeService;
	
	@GetMapping("/addlike")
	public RedirectView addLike(@RequestParam("likeUser") int likeUser,@RequestParam("postId") int postId,HttpServletRequest req) {
		RedirectView md=new RedirectView();
		HttpSession session=req.getSession();
		PostEntity post=new PostEntity();
		post.setId(postId);
		UserEntity user=new UserEntity();
		user.setId(likeUser);
		
		
		LikeEntity like=this.likeService.checkAlreadyLike(post,user,(int)session.getAttribute("userId"));
		System.out.println(like);
		if(like==null) {
			like =new LikeEntity();
			like.setUser(user);
			like.setPost(post);
			like.setStatus(true);
			like.setRealuser((int)session.getAttribute("userId"));
			
			
			like=this.likeService.addLike(like);
			if(like!=null)
				session.setAttribute("succMsg","Liked...");
			else
				session.setAttribute("failMsg","Somthing went wrong...");
		}else {
			session.setAttribute("succMsg","Already Like...");
		}
		md.setUrl("/user/dashboard");
		return md;
	}
	
	@GetMapping("/dislike")
	public RedirectView disLike(@RequestParam("likeUser") int likeUser,@RequestParam("postId") int postId,HttpServletRequest req) {
		RedirectView md=new RedirectView();
		HttpSession session=req.getSession();
		PostEntity post=new PostEntity();
		post.setId(postId);
		UserEntity user=new UserEntity();
		user.setId(likeUser);
		
		int i=this.likeService.delete(user,post);
		if(i>0)
			session.setAttribute("succMsg","Remove Like Successfully....");
		else
			session.setAttribute("failMsg", "Something went wrong.....");
		md.setUrl("/user/dashboard");
		return md;
	}
}





















