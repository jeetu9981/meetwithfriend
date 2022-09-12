package com.meetnewfriend.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import com.meetnewfriend.CreateCaptcha;
import com.meetnewfriend.dto.DashboardDto;
import com.meetnewfriend.dto.ProfileDto;
import com.meetnewfriend.dto.SerachUserDto;
import com.meetnewfriend.entity.User;
import com.meetnewfriend.service.impl.UserServiceImpl;

import cn.apiclub.captcha.Captcha;

@RestController
@MultipartConfig
@RequestMapping("/user")
public class UserController {
	
	@Autowired
	private UserServiceImpl userServiceImpl;
	
	public static String hidden="";
	
	//to setup captcha
	public void setupCaptcha(User user) throws IOException {
		Captcha cap=CreateCaptcha.createCaptch(420,50);
		user.setHidden(cap.getAnswer());
		hidden=cap.getAnswer();
		user.setCaptchImage(CreateCaptcha.createImage(cap));
	}
	
	//for redirect sign up
	@GetMapping("/")
	public ModelAndView test() throws IOException {
		ModelAndView md=new ModelAndView();
		User user=new User();
		setupCaptcha(user);
		
		md.addObject("hidden",user.getHidden());
		md.addObject("capImage",user.getCaptchImage());
		md.setViewName("home");
		return md;
	}
	

	//this api is for signup the user
	@GetMapping("/signup")
	public RedirectView signup(@ModelAttribute User user,HttpServletRequest req) throws IOException{
		HttpSession session=req.getSession();
		RedirectView md = new RedirectView();
		System.out.println(user);
		String status = this.userServiceImpl.addUser(user);
		
		if(status.equals("success")) {
			md.setUrl("/user/signinjsp");
			session.setAttribute("succMsg", "Signup Success......");
		}else if(status.contentEquals("captchafail")) {
			session.setAttribute("failMsg", "Please Enter valid captch");
			setupCaptcha(user);
			md.setUrl("/user/");
		}else if(status.equals("invaliddata")) {
			md.setUrl("/user/");
			session.setAttribute("failMsg", "Please Enter Valid Email Or Password......");
		}else {
			md.setUrl("/user/");
			session.setAttribute("failMsg", "Something went wrong......");
		}
		return md;
	}
	
	
	@GetMapping("/signinjsp")
	public ModelAndView signinJsp() {
		ModelAndView md=new ModelAndView();
		md.setViewName("signin");
		return md;
	}

	//this api for signin the user
	@PostMapping("/signin")
	public RedirectView signin(@ModelAttribute User user,HttpServletRequest request) {
		HttpSession session=request.getSession();
		RedirectView rd=new RedirectView();
		
		//here we call signin method
		user=this.userServiceImpl.signin(user);
		
		if(user!=null) {
			session.setAttribute("userName",user.getUserName());
			session.setAttribute("userId",user.getId());
			
			if(user.isLoginFirst()!=true) {
				this.userServiceImpl.updateLoginFirst(user.getId());
				rd.setUrl("rediredconsetimagejsp");
				return rd;
			}
			session.setAttribute("succMsg","Welcome to meet with new friends");
			rd.setUrl("profile");
		}else {
			session.setAttribute("failMsg","Invalid Password Or Email....");
			rd.setUrl("/user/signinjsp");
		}
		return rd;
	}
	
	//this api is to basically redirect on set some detail if user first time ligin then onle this api will execute
	@GetMapping("/rediredconsetimagejsp")
	public ModelAndView rediredconsetimagejsp() {
		ModelAndView md=new ModelAndView();
		md.setViewName("setimage");
		return md;
	}
	
	
	//this api use for set detail whe user login first time login
	@PostMapping("setimage")
	public RedirectView updateDeatail(@RequestParam("image1") MultipartFile image,@ModelAttribute User user,HttpServletRequest req)  {
		HttpSession session=req.getSession();
		RedirectView rd=new RedirectView();
		String status=this.userServiceImpl.updateUserDetail((int)session.getAttribute("userId"),user,image);
		if(status.equals("success")) {
			rd.setUrl("profile");
			session.setAttribute("succMsg","Welcom to meet with new friends...");
		}else {
			rd.setUrl("profile");
			session.setAttribute("failMsg","Something went wrong...");
		}
		return rd;
	}
	
	
	//this api use for logout user
	@GetMapping("/logout")
	public ModelAndView logout(HttpServletRequest req) {
		HttpSession session=req.getSession();
		session.removeAttribute("userId");
		session.removeAttribute("UserName");
		
		ModelAndView md=new ModelAndView();
		md.setViewName("signin");
		session.setAttribute("succMsg","Logout successfully..");
		return md;
	}
	
	
	//it is profile api where we get complete data about user profile
	@GetMapping("/profile")
	public ModelAndView profile(HttpServletRequest req) {
		ModelAndView md=new ModelAndView();
		HttpSession session=req.getSession();
		ProfileDto profile=this.userServiceImpl.getProfile((int)session.getAttribute("userId"));
		md.addObject("profile",profile);
		md.setViewName("profile");
		return md;
	}
	
	
	//this user profile is diffrent from above this is our follower profile
	@GetMapping("/userprofile")
	public ModelAndView userProfile(@RequestParam("userId") int userId) {
		ModelAndView md=new ModelAndView();
		ProfileDto profile=this.userServiceImpl.getProfile(userId);
		md.addObject("profile",profile);
		md.setViewName("userprofile");
		return md;
	}
	
	
	//this is dashboard api
	@GetMapping("/dashboard")
	public ModelAndView dash(HttpServletRequest req) {
		ModelAndView md=new ModelAndView();
		HttpSession session=req.getSession();
		DashboardDto dashboard=this.userServiceImpl.getDashboard((int)session.getAttribute("userId"));
		md.addObject("posts",dashboard);
		md.setViewName("dashboard");
		
		if(session.getAttribute("succMsg")!=null)
			session.setAttribute("succMsg",session.getAttribute("succMsg"));
		
		return md;
	}
	
	
	//search user by their name
	@GetMapping("/searchuser")
	public ModelAndView search(HttpServletRequest req){
		ModelAndView md=new ModelAndView();
		String name=req.getParameter("serachvalue");
		//get user by name
		List<SerachUserDto> users=this.userServiceImpl.search(name,(int)req.getSession().getAttribute("userId"));

		if(!users.isEmpty())
			md.addObject("users",users);
		else
			md.addObject("users", null);
		md.setViewName("searchuser");
		return md;
	}
	
	
	//update user profile
	@PostMapping("/updateuser")
	public RedirectView update(@RequestParam("image1") MultipartFile image,@ModelAttribute User user,HttpServletRequest req) throws IOException {
		RedirectView rd=new RedirectView();
		HttpSession session=req.getSession();
		String status=this.userServiceImpl.updateUserDetail((int)session.getAttribute("userId"),user,image);
		if(status.equals("success"))
			session.setAttribute("succMsg","updtaedSuccessfully");
		else if(status.equals("nameinvalid")) {
			rd.setUrl("/user/edituserprofile?userId="+user.getId());
			session.setAttribute("failMsg","Name or email can't be null.......");
			return rd;
		}
		else if(status.equals("fail"))
			session.setAttribute("failMsg","Something went wrong.......");
		rd.setUrl("/user/profile");
		return rd;
	}
	
	//Edit user profile
	@GetMapping("/edituserprofile")
	public ModelAndView edituserprofile(@RequestParam("userId") int userId) {
		ModelAndView md=new ModelAndView();
		User user=this.userServiceImpl.edituserprofile(userId);
		
		md.addObject("user",user);
		md.setViewName("edituserprofile");
		return md;
	}
	
	@PostMapping("/")
	public ModelAndView test1() throws IOException {
		ModelAndView md=new ModelAndView();
		User user=new User();
		setupCaptcha(user);
		
		md.addObject("hidden",user.getHidden());
		md.addObject("capImage",user.getCaptchImage());
		md.setViewName("home");
		return md;
	}
}

































