package com.meetnewfriend.service.impl;

import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.meetnewfriend.entity.Post;
import com.meetnewfriend.entity.User;
import com.meetnewfriend.repository.PostRepo;
import com.meetnewfriend.services.PostService;

@Service
public class PostServiceImpl implements PostService{
	@Autowired
	private PostRepo postRepo;
	
	@Autowired
	private CommentServiceImpl commentServiceImpl;
	
	
	@Autowired
	private LikeServiceImpl likeServiceImpl;
	
	
	//this is for add post
	public Post addPost(Post post,MultipartFile image,int userId){
		//this peace if code use for upload image
		String fileName=image.getOriginalFilename().trim();
		try {
			InputStream is=image.getInputStream();
			String path="C:\\Users\\DELL\\Documents\\workspace-sts-3.9.10.RELEASE\\meetnewfriend\\src\\main\\webapp\\images\\"+fileName;
			int bytes=0;
			FileOutputStream fs=new FileOutputStream(path);
			while((bytes=is.read())!=-1)
				fs.write(bytes);
			fs.close();
			
			post.setImage(fileName);
			
			User user=new User();
			user.setId(userId);
			
			post.setUser(user);
			return this.postRepo.save(post);
		}
		catch(Exception e) {
			return null;
		}
	}
	
	
	//here we get all post of particular users
	public List<Post> get(int id){
		return this.postRepo.findByUserId(id);
	}
	
	
	//get all posts of our all followers
	public List<Post> getAllPost(ArrayList<Integer> usersId){
		ArrayList<Post> user=new ArrayList<Post>();
		for(int id:usersId) {
			List<Post> p=(List<Post>) this.postRepo.findByUserId(id);
			user.addAll(p);
		}
		return user;
	}
	
	//get for single post
	public Post getSinglePost(int postId) {
		return this.postRepo.findById(postId).get();
	}
	
	//delete post
	@Transactional
	public void deletePost(int postId,int userId){
		Post post=new Post();
		post.setId(postId);
		
		User user=new User();
		user.setId(userId);
		
		this.postRepo.deletePost(post,user);
	}
	
	@Transactional(rollbackFor = Exception.class)
	public void delete(int postId,int userId) {
		this.commentServiceImpl.deletePost(postId);
		this.likeServiceImpl.deletePost(postId);
		this.deletePost(postId,userId);
	}
}






















