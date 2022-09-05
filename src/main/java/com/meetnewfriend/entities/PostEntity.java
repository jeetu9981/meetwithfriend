package com.meetnewfriend.entities;

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
public class PostEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="post_id")
	private int id;
	private String description;
	private String image;
	
	
	@ManyToOne
	@JoinColumn(name="user_id")
	private UserEntity user;
	
	
	@OneToMany(mappedBy = "post")
	@JsonIgnore
	private List<LikeEntity> likes;
	
	
	@OneToMany(mappedBy = "post")
	@JsonIgnore
	private List<CommentEntity> comments;	

	@Override
	public String toString() {
		return "PostEntity [id=" + id + ", description=" + description + ", image=" + image + ", user=" + user
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


	public UserEntity getUser() {
		return user;
	}


	public void setUser(UserEntity user) {
		this.user = user;
	}


	public List<LikeEntity> getLikes() {
		return likes;
	}


	public void setLikes(List<LikeEntity> likes) {
		this.likes = likes;
	}


	public List<CommentEntity> getComments() {
		return comments;
	}


	public void setComments(List<CommentEntity> comments) {
		this.comments = comments;
	}


	
	
	
	
	
}
