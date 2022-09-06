package com.meetnewfriend.entity;

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
public class Comment {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="comment_id")
	private int id;
	
	@OneToOne
	@JoinColumn(name="user")
	private User user;
	
	@ManyToOne
	private Post post;
	
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
		return "Comment [id=" + id + ", user=" + user + ", post="  + ", realuser=" + realuser + ", comment="
				+ comment + "]";
	}

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

	public Post getPost() {
		return post;
	}

	public void setPost(Post post) {
		this.post = post;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}
	
	
	
}
