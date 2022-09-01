package com.meetnewfriend.controller;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

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

import com.meetnewfriend.entities.FollowingEntity;
import com.meetnewfriend.entities.LikeEntity;
import com.meetnewfriend.entities.PostEntity;
import com.meetnewfriend.entities.RealFollowerEntity;
import com.meetnewfriend.entities.UserEntity;
import com.meetnewfriend.service.FollowingService;
import com.meetnewfriend.service.LikeService;
import com.meetnewfriend.service.PostService;
import com.meetnewfriend.service.RealFollowerSErvice;
import com.meetnewfriend.service.UserService;

@RestController
@MultipartConfig
@RequestMapping("/user")
public class UserController {
	@Autowired
	private UserService userService;
	
	@Autowired
	private FollowingService followingService;
	
	@Autowired
	private RealFollowerSErvice realFollowerSErvice;
	
	@Autowired
	private PostService postService;
	
	@Autowired
	private LikeService likeService;
	

	@PostMapping("/signup")
	public ModelAndView signup(@ModelAttribute UserEntity userEntity) {

		String userName = userEntity.getName().trim();

		if (userEntity.getName().length() < 5) {
			userEntity.setName("User " + userName);
		}
		userName = userName + new Random().nextInt(1000);
		userName = userName.substring(0, 1).toUpperCase() + userName.substring(1);
		userEntity.setUserName(userName);

		userEntity.setLoginFirst(false);
		userEntity = this.userService.addUser(userEntity);

		ModelAndView md = new ModelAndView();
		if (userEntity != null) {
			md.setViewName("signin");
			md.addObject("succMsg", "Signup Success......");
		} else {
			md.setViewName("home");
			md.addObject("failMsg", "SomeThing Went Wrong Please try again....");
		}

		return md;
	}

	@PostMapping("/signin")
	public RedirectView signin(@ModelAttribute UserEntity userEntity,HttpServletRequest request) {
		userEntity=this.userService.signin(userEntity);
		RedirectView rd=new RedirectView();
		if(userEntity!=null) {
			HttpSession session=request.getSession();
			session.setAttribute("userName",userEntity.getUserName());
			session.setAttribute("userId",userEntity.getId());
			if(userEntity.isLoginFirst()!=true) {
				this.userService.updateLoginFirst(userEntity.getId());
				rd.setUrl("rediredconsetimagejsp");
				return rd;
			}
			session.setAttribute("succMsg","Welcome to meet with new friends");;
		}else {
//			session.setAttribute("failMsg","Something went wrong.....");
		}
		rd.setUrl("profile");
		return rd;
	}
	
	@GetMapping("/rediredconsetimagejsp")
	public ModelAndView rediredconsetimagejsp() {
		ModelAndView md=new ModelAndView();
		md.setViewName("setimage");
		return md;
	}
	
	
	
	@PostMapping("setimage")
	public RedirectView updateDeatail(@RequestParam("image") MultipartFile image,HttpServletRequest req) throws IOException {
		UserEntity userEntity=new UserEntity();
		HttpSession session=req.getSession();
		if(req.getParameter("favouriteSongs") != null || req.getParameter("favouriteBooks") != null || req.getParameter("favouritePlaces") !=null || image !=null) {
			String fileName=image.getOriginalFilename().trim();
			InputStream is=image.getInputStream();
			String path="C:\\Users\\DELL\\Documents\\workspace-sts-3.9.10.RELEASE\\meetnewfriend\\src\\main\\webapp\\images\\"+fileName;
			System.out.println("Path : "+path);
			int bytes=0;
			FileOutputStream fs=new FileOutputStream(path);
			while((bytes=is.read())!=-1)
				fs.write(bytes);
			fs.close();
			
			userEntity.setImage(fileName);
			userEntity.setFavouritBooks(req.getParameter("favouriteSongs"));
			userEntity.setFavouritePlaces(req.getParameter("favouritePlaces"));
			userEntity.setFavouritBooks(req.getParameter("favouritBooks"));
			this.userService.updateUserDetail((int)session.getAttribute("userId"),userEntity);
			session.setAttribute("succMsg","Detailed update successsfully.........");
		}
		
		RedirectView rd=new RedirectView();
		rd.setUrl("profile");
		session.setAttribute("succMsg","Welcom to meet with new friends...");
		return rd;
	}
	
	
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
	
	@GetMapping("/profile")
	public ModelAndView profile(HttpServletRequest req) {
		ModelAndView md=new ModelAndView();
		HttpSession session=req.getSession();
		List<PostEntity> l=this.postService.get((int)session.getAttribute("userId"));
		UserEntity user=this.userService.getUser((int)session.getAttribute("userId"));
		
		int countFollower=this.realFollowerSErvice.countFollower((int)session.getAttribute("userId"));
		md.addObject("countFollower",countFollower);
		
		int countFollowing=this.followingService.countFollowing((int)session.getAttribute("userId"));
		md.addObject("countFollowing",countFollowing);
		md.addObject("user",user);
		md.addObject("allposts", l);
		md.setViewName("profile");
		
		List<RealFollowerEntity> followers=this.realFollowerSErvice.getFollower((int) session.getAttribute("userId"));
		md.addObject("followers",followers);
		
		List<FollowingEntity> following=this.followingService.getFollwing((int) session.getAttribute("userId"));
		md.addObject("followings",following);
		return md;
	}
	
	
	@GetMapping("/userprofile")
	public ModelAndView userProfile(@RequestParam("userId") int userId) {
		ModelAndView md=new ModelAndView();
		List<PostEntity> l=this.postService.get(userId);
		UserEntity user=this.userService.getUser(userId);
		
		int countFollower=this.realFollowerSErvice.countFollower(userId);
		md.addObject("countFollower",countFollower);
		
		int countFollowing=this.followingService.countFollowing(userId);
		md.addObject("countFollowing",countFollowing);
		md.addObject("user",user);
		md.addObject("allposts", l);
		md.setViewName("userprofile");
		
		List<RealFollowerEntity> followers=this.realFollowerSErvice.getFollower(userId);
		md.addObject("followers",followers);
		
		List<FollowingEntity> following=this.followingService.getFollwing(userId);
		md.addObject("followings",following);
		return md;
	}
	
	@GetMapping("/dashboard")
	public ModelAndView dash(HttpServletRequest req) {
		ModelAndView md=new ModelAndView();
		HttpSession session=req.getSession();
		session.setAttribute("succMsg",session.getAttribute("succMsg"));
		List<RealFollowerEntity> users=this.realFollowerSErvice.getMyFollowerPosts((int)session.getAttribute("userId"));
		ArrayList<Integer> usersId=new ArrayList<Integer>();
		
		for(RealFollowerEntity p:users) {
			usersId.add(p.getFollower().getId());
		}
		
		List<PostEntity> posts=this.postService.getAllPost(usersId);
		md.addObject("posts",posts);
		md.setViewName("dashboard");
		return md;
	}
	
	@PostMapping("/searchuser")
	public ModelAndView search(HttpServletRequest req){
		ModelAndView md=new ModelAndView();
		String name=req.getParameter("serachvalue");
		List<UserEntity> users=this.userService.search(name);
		md.addObject("users",users);
		md.setViewName("searchuser");
		return md;
	}
	
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
		
		UserEntity user=this.userService.getUser((int)session.getAttribute("userId"));
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
		
		
		
		if(userEntity.getName().isEmpty())
			userEntity.setName(user.getName());
		
		if(userEntity.getEmail().isEmpty())
			userEntity.setEmail(user.getEmail());
		
		if(userEntity.getFavouritBooks().isEmpty())
			userEntity.setFavouritBooks(user.getFavouritBooks());
		
		if(userEntity.getFavouritePlaces().isEmpty())
			userEntity.setFavouritePlaces(user.getFavouritePlaces());
		
		if(userEntity.getFavouriteSongs().isEmpty())
			userEntity.setFavouriteSongs(user.getFavouriteSongs());
		
		int i=this.userService.updateUser((int)session.getAttribute("userId"), userEntity);
		if(i>0)
			session.setAttribute("succMsg","updtaedSuccessfully");
		else
			session.setAttribute("failMsg","Something went wrong.......");
		rd.setUrl("/user/profile");
		return rd;
	}
}

































