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

import com.meetnewfriend.entity.Block;
import com.meetnewfriend.service.impl.BlockServiceImpl;

@RestController
@RequestMapping("/block")
public class BlockController {
	@Autowired
	private BlockServiceImpl blockServiceImpl;
	
	@GetMapping("/blockuser")
	public RedirectView blockUser(@RequestParam("blockuser") int blockUser,HttpServletRequest req) throws Exception {
		HttpSession session=req.getSession();
		RedirectView rd=new RedirectView();
		
		if(this.blockServiceImpl.blockUser(blockUser,(int)session.getAttribute("userId"))) {
			session.setAttribute("succMsg","Block SuccessFully");
		}
		else
			session.setAttribute("failMsg","Something went wrong...");
		rd.setUrl("/user/profile");
		return rd;
	}
	
	@GetMapping("/getAllBlocks")
	public ModelAndView getAllBlocks(HttpServletRequest req) {
		ModelAndView md=new ModelAndView();
		List<Block> users=this.blockServiceImpl.getBlockUsers((int)req.getSession().getAttribute("userId"));
		System.out.println(users);
		md.addObject("users", users);
		md.setViewName("blockuser");
		return md;
	}
	
	@GetMapping("/unblock")
	public RedirectView unblock(@RequestParam("userId") int blockUser,HttpServletRequest req){
		RedirectView rd=new RedirectView();
		if(this.blockServiceImpl.unblockUser(blockUser,(int)req.getSession().getAttribute("userId"))>0)
			req.getSession().setAttribute("succMsg","Unblock Successfully...");
		else
			req.getSession().setAttribute("failMsg","Something went wrong...");
		rd.setUrl("/block/getAllBlocks");
		return rd;
	}
}
























