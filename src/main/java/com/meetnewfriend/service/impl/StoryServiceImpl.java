package com.meetnewfriend.service.impl;

import java.io.FileOutputStream;
import java.io.InputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.meetnewfriend.entity.Story;
import com.meetnewfriend.entity.User;
import com.meetnewfriend.repository.StoryRepo;
import com.meetnewfriend.services.StoryService;

@Service
public class StoryServiceImpl implements StoryService{

	@Autowired
	private StoryRepo StoryRepo;
	
	
	@Override
	public boolean addStory(Story story, MultipartFile image, int userId) {
		if (!image.isEmpty()) {
			// this peace of code use for upload image
			String fileName = image.getOriginalFilename().trim();
			try {
				InputStream is = image.getInputStream();
				String path = "C:\\Users\\DELL\\Documents\\workspace-sts-3.9.10.RELEASE\\meetnewfriend\\src\\main\\webapp\\images\\"
						+ fileName;
				int bytes = 0;
				FileOutputStream fs = new FileOutputStream(path);
				while ((bytes = is.read()) != -1)
					fs.write(bytes);
				fs.close();

				story.setStory(fileName);
				
				User user=new User();
				user.setId(userId);
				story.setUser(user);
				
				
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				Calendar cal = Calendar.getInstance();
				cal.add(Calendar.DAY_OF_MONTH, 1);
				String dateAfter = sdf.format(cal.getTime());
				try {
					Date date = new SimpleDateFormat("yyyy-MM-dd").parse(dateAfter);
					story.setExpireDate(date);
				} catch (ParseException e) {
					e.printStackTrace();
				}
				
				if(this.StoryRepo.save(story)!=null)
					return true;
			} catch (Exception e) {
				return false;
			}
		}
		return false;
	}


	@Override
	public List<Story> getMystory(int userId) {
		User user=new User();
		user.setId(userId);
		return this.StoryRepo.findByUserId(user);
	}

	public List<Story> getAllStory(){
		return (List<Story>) this.StoryRepo.findAll();
	}
}
