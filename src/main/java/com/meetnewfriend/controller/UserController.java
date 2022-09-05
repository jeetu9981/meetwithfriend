package com.meetnewfriend.controller;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
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
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import com.meetnewfriend.CreateCaptcha;
import com.meetnewfriend.entities.FollowingEntity;
import com.meetnewfriend.entities.PostEntity;
import com.meetnewfriend.entities.RealFollowerEntity;
import com.meetnewfriend.entities.UserEntity;
import com.meetnewfriend.service.impl.FollowingServiceImpl;
import com.meetnewfriend.service.impl.PostServiceImpl;
import com.meetnewfriend.service.impl.RealFollowerServiceImpl;
import com.meetnewfriend.service.impl.UserServiceImpl;

import cn.apiclub.captcha.Captcha;

@RestController
@MultipartConfig
@RequestMapping("/user")
public class UserController {
	
	@Autowired
	private UserServiceImpl userServiceImpl;
	
	@Autowired
	private FollowingServiceImpl followingServiceImpl;
	
	@Autowired
	private RealFollowerServiceImpl realFollowerServiceImpl;
	
	@Autowired
	private PostServiceImpl postServiceImpl;
	
	private static String hidden="";
	
	//to setup captcha
	public void setupCaptcha(UserEntity user) throws IOException {
		Captcha cap=CreateCaptcha.createCaptch(420,50);
		user.setHidden(cap.getAnswer());
		hidden=cap.getAnswer();
		user.setCaptchImage(CreateCaptcha.createImage(cap));
	}
	
	//for redirect sign up
	@GetMapping("/")
	public ModelAndView test() throws IOException {
		ModelAndView md=new ModelAndView();
		UserEntity user=new UserEntity();
		setupCaptcha(user);
		
		md.addObject("hidden",user.getHidden());
		md.addObject("capImage",user.getCaptchImage());
		md.setViewName("home");
		return md;
	}
	

	//this api is for signup the user
	@PostMapping("/signup")
	public RedirectView signup(@ModelAttribute UserEntity userEntity,HttpServletRequest req) throws IOException {
		HttpSession session=req.getSession();
		RedirectView md = new RedirectView();
	
		//if generated captcha and user enter captcha is same then only save user
		if(userEntity.getCaptcha().equals(UserController.hidden)) {
			//signup user
			userEntity = this.userServiceImpl.addUser(userEntity);
			if (userEntity != null) {
				md.setUrl("/user/signinjsp");
				session.setAttribute("succMsg", "Signup Success......");
			} else {
				md.setUrl("/user/");
				session.setAttribute("failMsg", "SomeThing Went Wrong Please try again....");
			}
		}else {
			session.setAttribute("failMsg","Please Enter valid captch");
			setupCaptcha(userEntity);
			md.setUrl("/user/");
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
	public RedirectView signin(@ModelAttribute UserEntity userEntity,HttpServletRequest request) {
		HttpSession session=request.getSession();
		
		//here we call signin method
		userEntity=this.userServiceImpl.signin(userEntity);
		RedirectView rd=new RedirectView();
		
		
		if(userEntity!=null) {
			session.setAttribute("userName",userEntity.getUserName());
			session.setAttribute("userId",userEntity.getId());
			if(userEntity.isLoginFirst()!=true) {
				this.userServiceImpl.updateLoginFirst(userEntity.getId());
				rd.setUrl("rediredconsetimagejsp");
				return rd;
			}
			session.setAttribute("succMsg","Welcome to meet with new friends");
			rd.setUrl("profile");
		}else {
			session.setAttribute("failMsg","Invalid Password Or Email....");
			rd.setUrl("/");
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
	public RedirectView updateDeatail(@RequestParam("image") MultipartFile image,HttpServletRequest req) throws IOException {
		UserEntity userEntity=new UserEntity();
		HttpSession session=req.getSession();
		System.out.println("EMPTY : "+image.isEmpty());
		if(!image.isEmpty()) {
			//this peace of code use for upload image
			String fileName=image.getOriginalFilename().trim();
			InputStream is=image.getInputStream();
			String path="C:\\Users\\DELL\\Documents\\workspace-sts-3.9.10.RELEASE\\meetnewfriend\\src\\main\\webapp\\images\\"+fileName;
			int bytes=0;
			FileOutputStream fs=new FileOutputStream(path);
			while((bytes=is.read())!=-1)
				fs.write(bytes);
			fs.close();
			
			userEntity.setImage(fileName);
		}
		userEntity.setImage(null);
		userEntity.setFavouritBooks(req.getParameter("favouriteSongs"));
		userEntity.setFavouritePlaces(req.getParameter("favouritePlaces"));
		userEntity.setFavouritBooks(req.getParameter("favouritBooks"));
		
		this.userServiceImpl.updateUserDetail((int)session.getAttribute("userId"),userEntity);
		
		RedirectView rd=new RedirectView();
		rd.setUrl("profile");
		session.setAttribute("succMsg","Welcom to meet with new friends...");
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
		RestTemplate restTemplate=new RestTemplate();
		
		//here we get all posts of user 
		List<PostEntity> allposts=this.postServiceImpl.get((int)session.getAttribute("userId"));
		
		//here we get user particular user detail.
		UserEntity user=this.userServiceImpl.getUser((int)session.getAttribute("userId"));
		
		//here we count user followers
		int countFollower=this.realFollowerServiceImpl.countFollower((int)session.getAttribute("userId"));
		md.addObject("countFollower",countFollower);
		
		
		//here we count user following
		int countFollowing=this.followingServiceImpl.countFollowing((int)session.getAttribute("userId"));
		
		
		md.addObject("countFollowing",countFollowing);
		md.addObject("user",user);
		md.addObject("allposts", allposts);
		md.setViewName("profile");
		
		//here we will get user all followers
		List<RealFollowerEntity> followers=this.realFollowerServiceImpl.getFollower((int) session.getAttribute("userId"));
		md.addObject("followers",followers);
		
		//get user following
		List<FollowingEntity> following=this.followingServiceImpl.getFollwing((int) session.getAttribute("userId"));
		md.addObject("followings",following);
	
		return md;
	}
	
	
	//this user profile is diffrent from above this is our follower profile
	@GetMapping("/userprofile")
	public ModelAndView userProfile(@RequestParam("userId") int userId) {
		ModelAndView md=new ModelAndView();
		
		//get all post of users
		List<PostEntity> l=this.postServiceImpl.get(userId);
		UserEntity user=this.userServiceImpl.getUser(userId);
		
		
		//count followers
		int countFollower=this.realFollowerServiceImpl.countFollower(userId);
		md.addObject("countFollower",countFollower);
		
		
		//count following
		int countFollowing=this.followingServiceImpl.countFollowing(userId);
		md.addObject("countFollowing",countFollowing);
		md.addObject("user",user);
		md.addObject("allposts", l);
		md.setViewName("userprofile");
		
		//get all followers
		List<RealFollowerEntity> followers=this.realFollowerServiceImpl.getFollower(userId);
		md.addObject("followers",followers);
		
		
		//get all following
		List<FollowingEntity> following=this.followingServiceImpl.getFollwing(userId);
		md.addObject("followings",following);
		return md;
	}
	
	
	//this is dashboard api
	@GetMapping("/dashboard")
	public ModelAndView dash(HttpServletRequest req) {
		ModelAndView md=new ModelAndView();
		HttpSession session=req.getSession();
		
		
		//here we will get all followers
		List<RealFollowerEntity> users=this.realFollowerServiceImpl.getFollower((int)session.getAttribute("userId"));
		ArrayList<Integer> usersId=new ArrayList<Integer>();
		
		
		//get all followers id with the help of them id we can find them all posts
		for(RealFollowerEntity p:users) {
			usersId.add(p.getFollower().getId());
		}
		
		//here we will get our follower all posts
		List<PostEntity> posts=this.postServiceImpl.getAllPost(usersId);
		
		
		md.addObject("posts",posts);
		md.setViewName("dashboard");
		
		if(session.getAttribute("succMsg")!=null)
			session.setAttribute("succMsg",session.getAttribute("succMsg"));
		
		return md;
	}
	
	
	//search user by their name
	@PostMapping("/searchuser")
	public ModelAndView search(HttpServletRequest req){
		ModelAndView md=new ModelAndView();
		String name=req.getParameter("serachvalue");
		
		//get user by name
		List<UserEntity> users=this.userServiceImpl.search(name);
		
		md.addObject("users",users);
		md.setViewName("searchuser");
		return md;
	}
	
	
	//update user profile
	@PostMapping("/updateuser")
	public RedirectView update(@RequestParam("image") MultipartFile image,HttpServletRequest req) throws IOException {
		RedirectView rd=new RedirectView();
		HttpSession session=req.getSession();
		UserEntity userEntity=new UserEntity();
		
		userEntity.setName(req.getParameter("name"));
		userEntity.setEmail(req.getParameter("email"));
		userEntity.setFavouritBooks(req.getParameter("books"));
		userEntity.setFavouritePlaces(req.getParameter("places"));
		userEntity.setFavouriteSongs(req.getParameter("songs"));
		
		//get user for check details
		UserEntity user=this.userServiceImpl.getUser((int)session.getAttribute("userId"));
		
		//here we check which detail fill by user or which not
		if(image.isEmpty()) {
			userEntity.setImage(user.getImage());
		}else {
			String fileName=image.getOriginalFilename().trim();
			InputStream is=image.getInputStream();
			String path="C:\\Users\\DELL\\Documents\\workspace-sts-3.9.10.RELEASE\\meetnewfriend\\src\\main\\webapp\\images\\"+fileName;
			int bytes=0;
			FileOutputStream fs=new FileOutputStream(path);
			while((bytes=is.read())!=-1)
				fs.write(bytes);
			fs.close();
			userEntity.setImage(fileName);
		}
	
		int i=this.userServiceImpl.updateUser((int)session.getAttribute("userId"), userEntity);
		if(i>0)
			session.setAttribute("succMsg","updtaedSuccessfully");
		else
			session.setAttribute("failMsg","Something went wrong.......");
		rd.setUrl("/user/profile");
		return rd;
	}
	
	//Edit user profile
	@GetMapping("/edituserprofile")
	public ModelAndView edituserprofile(@RequestParam("userId") int userId) {
		ModelAndView md=new ModelAndView();
		UserEntity user=this.userServiceImpl.edituserprofile(userId);
		
		md.addObject("user",user);
		md.setViewName("edituserprofile");
		return md;
	}
}

































