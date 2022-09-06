package com.meetnewfriend.controller;

import java.io.IOException;

import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import com.meetnewfriend.dto.PostDto;
import com.meetnewfriend.entity.Post;
import com.meetnewfriend.service.impl.PostServiceImpl;

@RestController
@MultipartConfig
@RequestMapping("/post")
public class PostController {
	@Autowired
	private PostServiceImpl postServiceImpl;

	
	//this api use for unavigate on user add post jsp.
	@GetMapping("/addpost")
	public ModelAndView addPost() {
		ModelAndView md=new ModelAndView();
		md.setViewName("addpost");
		return md;
	}
	
	
	//this api is use for add user post
	@PostMapping("/addpost")
	public RedirectView post(@RequestParam("image1") MultipartFile image,@ModelAttribute Post post,HttpServletRequest req) throws IOException {
		RedirectView rd=new RedirectView();
		HttpSession session=req.getSession();
		
		//here we can save post
		post=this.postServiceImpl.addPost(post,image,(int)session.getAttribute("userId"));
		if(post!=null)
			session.setAttribute("succMsg","Post Added Successfully....");
		else
			session.setAttribute("failMsf","Something went wrongy....");
		rd.setUrl("../user/profile");
		return rd;
	}
	
	
	//get particular post comments
	@GetMapping("/getPostComment")
	public ResponseEntity<PostDto> getPost(@RequestParam("postId") int postId,HttpServletRequest req) {
		HttpSession session=req.getSession();
		PostDto postDto=new PostDto();
		
		Post post=this.postServiceImpl.getSinglePost(postId);
		postDto.setUser(post.getUser());
		postDto.setLikes(post.getLikes());
		postDto.setComments(post.getComments());
		postDto.setId(post.getId());

		session.setAttribute("singlepost",post);
		return ResponseEntity.ok(postDto);
	}
	
	
	//delete particular user post
	@GetMapping("/deletepost")
	public String deletePost(@RequestParam("postId") int postId,HttpServletRequest req) {
		HttpSession session=req.getSession();
		this.postServiceImpl.delete(postId,(int)session.getAttribute("userId"));
		return "success";
	}
}





















