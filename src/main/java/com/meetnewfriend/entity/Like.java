package com.meetnewfriend.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="likes")
public class Like {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="like_id")
	private int id;
	
	@OneToOne
//	@JoinColumn(name="user")
	private User user;
	
	@ManyToOne
	private Post post;
	
	private boolean status;

	
	private int realuser;

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	

	public int getRealuser() {
		return realuser;
	}

	public void setRealuser(int realuser) {
		this.realuser = realuser;
	}

	@Override
	public String toString() {
		return "Like [id=" + id + ", user=" + user + ", post=" + ", status=" + status + ", realuser="
				+ realuser + "]";
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
	
	
}
