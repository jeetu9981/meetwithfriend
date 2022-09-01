package com.meetnewfriend.controller;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import com.meetnewfriend.entities.PostEntity;
import com.meetnewfriend.entities.UserEntity;
import com.meetnewfriend.service.PostService;

@RestController
@MultipartConfig
@RequestMapping("/post")
public class PostController {
	@Autowired
	private PostService postService;
	
	@GetMapping("/addpost")
	public ModelAndView addPost() {
		ModelAndView md=new ModelAndView();
		md.setViewName("addpost");
		return md;
	}
	
	@PostMapping("/addpost")
	public RedirectView post(@RequestParam("image") MultipartFile image,HttpServletRequest req) throws IOException {
		RedirectView rd=new RedirectView();
		
		String fileName=image.getOriginalFilename().trim();
		InputStream is=image.getInputStream();
		String path="C:\\Users\\DELL\\Documents\\workspace-sts-3.9.10.RELEASE\\meetnewfriend\\src\\main\\webapp\\images\\"+fileName;
		System.out.println("Path : "+path);
		int bytes=0;
		FileOutputStream fs=new FileOutputStream(path);
		while((bytes=is.read())!=-1)
			fs.write(bytes);
		fs.close();
		
		PostEntity postEntity=new PostEntity();
		postEntity.setImage(fileName);
		
		HttpSession session=req.getSession();
		UserEntity user=new UserEntity();
		user.setId((int)session.getAttribute("userId"));
		
		postEntity.setUser(user);
		postEntity.setDescription(req.getParameter("description"));
		
		this.postService.addPost(postEntity);

		HttpSession se=req.getSession();
		session.setAttribute("succMsg","Post Added Successfully....");
		rd.setUrl("../user/profile");
		return rd;
	}
}
