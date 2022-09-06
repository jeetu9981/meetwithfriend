package com.meetnewfriend.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="posts")
public class Post {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="post_id")
	private int id;
	private String description;
	private String image;
	
	
	@ManyToOne
	@JoinColumn(name="user_id")
	private User user;
	
	
	@OneToMany(mappedBy = "post")
	@JsonIgnore
	private List<Like> likes;
	
	
	@OneToMany(mappedBy = "post")
	@JsonIgnore
	private List<Comment> comments;	

	@Override
	public String toString() {
		return "Post [id=" + id + ", description=" + description + ", image=" + image + ", user=" + user
				+ ", likes=" + likes + ", comments=" + comments + "]";
	}


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public String getDescription() {
		return description;
	}


	public void setDescription(String description) {
		this.description = description;
	}


	public String getImage() {
		return image;
	}


	public void setImage(String image) {
		this.image = image;
	}


	public User getUser() {
		return user;
	}


	public void setUser(User user) {
		this.user = user;
	}


	public List<Like> getLikes() {
		return likes;
	}


	public void setLikes(List<Like> likes) {
		this.likes = likes;
	}


	public List<Comment> getComments() {
		return comments;
	}


	public void setComments(List<Comment> comments) {
		this.comments = comments;
	}


	
	
	
	
	
}
