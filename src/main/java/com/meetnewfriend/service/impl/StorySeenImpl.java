package com.meetnewfriend.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.meetnewfriend.entity.Story;
import com.meetnewfriend.entity.StorySeen;
import com.meetnewfriend.entity.User;
import com.meetnewfriend.repository.StorySeenRepo;

@Service
public class StorySeenImpl {
	
	@Autowired
	private StorySeenRepo storySeenRepo;
	
	public boolean addSeen(int userId,int storyId){
		User user=new User();
		user.setId(userId);
		
		StorySeen seen=this.storySeenRepo.findByUser(user);
		
		if(seen!=null)
			return false;
		else
		{
			StorySeen st=new StorySeen();
			st.setSeen(true);
			st.setUser(user);
			
			Story sto=new Story();
			sto.setId(storyId);
			st.setStory(sto);
			if(this.storySeenRepo.save(st)!=null)
				return true;
		}
		return false;
	}
}
