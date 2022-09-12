package com.meetnewfriend.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import com.meetnewfriend.dto.MessageDto;
import com.meetnewfriend.entity.Following;
import com.meetnewfriend.service.impl.FollowingServiceImpl;
import com.meetnewfriend.service.impl.MessagesServiceImpl;
import com.meetnewfriend.services.MessagesService;

@RestController
@RequestMapping("/message")
public class MessagesController {
	@Autowired
	private MessagesServiceImpl messageServiceImpl;
	
	@Autowired
	private FollowingServiceImpl followingSreviceImpl;
	
	@GetMapping("/getmessages")
	public ModelAndView getMessages(HttpServletRequest req) {
		ModelAndView md=new ModelAndView();
		List<Following> following=this.followingSreviceImpl.getFollwing((int)req.getSession().getAttribute("userId"));
		md.addObject("followings",following);
		md.setViewName("allmessages");
		return md;
	}
	
	@GetMapping("/getSingleUserMessages")
	public MessageDto getSingleUserMessage(@RequestParam("recieverId") int recieverId,HttpServletRequest req) {
		return this.messageServiceImpl.getSingleUserMessages(recieverId,(int)req.getSession().getAttribute("userId"));
	}
	
	@PostMapping("/addmessage")
	public RedirectView add(HttpServletRequest req) {
		int recieverId=Integer.parseInt(req.getParameter("recieverId"));
		String message=req.getParameter("message");
		RedirectView md=new RedirectView();
		HttpSession session=req.getSession();
		if(this.messageServiceImpl.addMessage(recieverId,(int)req.getSession().getAttribute("userId"),message))
			session.setAttribute("succMsg","Msg Send Sucessfully");
		else
			session.setAttribute("failMsg","Something Went Wrong");
		
		md.setUrl("/message/getmessages");
		return md;
	}
}
