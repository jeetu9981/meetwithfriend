package com.meetnewfriend.entity;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="story")
public class Story {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="story_id")
	private int id;
	
	@OneToOne
	private User user;
	
	private String story;
	
	private Date expireDate;
	
	private String description;
	
	
	@OneToMany(mappedBy = "story")
	private List<StorySeen> storySeen;


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public User getUser() {
		return user;
	}


	public void setUser(User user) {
		this.user = user;
	}


	public String getStory() {
		return story;
	}


	public void setStory(String story) {
		this.story = story;
	}


	public Date getExpireDate() {
		return expireDate;
	}


	public void setExpireDate(Date expireDate) {
		this.expireDate = expireDate;
	}


	public String getDescription() {
		return description;
	}


	public void setDescription(String description) {
		this.description = description;
	}


	public List<StorySeen> getStorySeen() {
		return storySeen;
	}


	public void setStorySeen(List<StorySeen> storySeen) {
		this.storySeen = storySeen;
	}
	
	
}
