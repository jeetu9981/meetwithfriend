package com.meetnewfriend.services;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.meetnewfriend.entity.Story;

@Service
public interface StoryService {
	public boolean addStory(Story story,MultipartFile iamge,int userId);
	
	public List<Story> getMystory(int userId);
	
	public List<Story> getAllStory();
}
