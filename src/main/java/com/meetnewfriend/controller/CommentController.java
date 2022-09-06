package com.meetnewfriend.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.view.RedirectView;

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
		
		//add comment
		boolean status=this.commentServiceImpl.addComment((int)session.getAttribute("userId"),Integer.parseInt(req.getParameter("postId")),Integer.parseInt(req.getParameter("commentUser")),req.getParameter("comment"));
		if(status)
			session.setAttribute("succMsg","comment added..");
		else
			session.setAttribute("failMsg","Please Add Proper Comment.");
		md.setUrl("/user/dashboard");
		return md;
	}
}
