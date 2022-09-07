package com.meetnewfriend.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="block")
public class Block {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="block_id")
	private int id;
	
	@OneToOne
	private User blockUser;
	
	@OneToOne
	private User realUser;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public User getBlockUser() {
		return blockUser;
	}

	public void setBlockUser(User blockUser) {
		this.blockUser = blockUser;
	}

	public User getRealUser() {
		return realUser;
	}

	public void setRealUser(User realUser) {
		this.realUser = realUser;
	}
	
	
}
