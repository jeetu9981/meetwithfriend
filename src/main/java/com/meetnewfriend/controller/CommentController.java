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

import com.meetnewfriend.entities.CommentEntity;
import com.meetnewfriend.entities.PostEntity;
import com.meetnewfriend.entities.UserEntity;
import com.meetnewfriend.service.impl.CommentServiceImpl;

@RestController
@RequestMapping("/comment")
public class CommentController {
	@Autowired
	private CommentServiceImpl commentServiceImpl;
	
	
	//add comment to the post
	@GetMapping("/addcomment")
	public RedirectView addComment(HttpServletRequest req) {
		RedirectView md=new RedirectView();
		HttpSession session=req.getSession();
		
		UserEntity user=new UserEntity();
		user.setId((int)session.getAttribute("userId"));
		
		PostEntity post=new PostEntity();
		post.setId(Integer.parseInt(req.getParameter("postId")));
		
		CommentEntity comment=new CommentEntity();
		comment.setPost(post);
		comment.setUser(user);
		comment.setRealuser(Integer.parseInt(req.getParameter("commentUser")));
		comment.setComment(req.getParameter("comment"));
		
		//add comment
		comment=this.commentServiceImpl.addComment(comment);
		if(comment!=null)
			session.setAttribute("succMsg","comment added..");
		else
			session.setAttribute("failMsg","something went wrong..");
		md.setUrl("/user/dashboard");
		return md;
	}
}
