package com.meetnewfriend.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="comments")
public class CommentEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="comment_id")
	private int id;
	
	@OneToOne
	@JoinColumn(name="user")
	private UserEntity user;
	
	@ManyToOne
//	@JoinColumn(name="post")
	private PostEntity post;
	
	private int realuser;
	
	private String comment;

	

	public int getRealuser() {
		return realuser;
	}

	public void setRealuser(int realuser) {
		this.realuser = realuser;
	}

	@Override
	public String toString() {
		return "CommentEntity [id=" + id + ", user=" + user + ", post="  + ", realuser=" + realuser + ", comment="
				+ comment + "]";
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public UserEntity getUser() {
		return user;
	}

	public void setUser(UserEntity user) {
		this.user = user;
	}

	public PostEntity getPost() {
		return post;
	}

	public void setPost(PostEntity post) {
		this.post = post;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}
	
	
	
}
