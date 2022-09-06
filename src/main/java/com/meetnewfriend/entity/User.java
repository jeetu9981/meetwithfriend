package com.meetnewfriend.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name="user")
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="userId")
	private int id;
	
	@Column(name="name")
	private String name;
	
	@Column(name="email")
	private String email;
	
	@Column(name="password")
	private String password;
	
	@Column(name="loginFirst")
	private boolean loginFirst;
	
	@Column(name="image")
	private String image;
	
	@Column(name="userName")
	private String userName;
	
	@Transient
	private String captchImage;
	
	@Transient
	private String captcha;
	
	@Transient
	private String hidden;
	
	private String favouritBooks;
	private String favouriteSongs;
	private String favouritePlaces;
	
	
	
	public int getId() {
		return id;
	}
	
	

	public String getCaptchImage() {
		return captchImage;
	}



	public void setCaptchImage(String captchImage) {
		this.captchImage = captchImage;
	}



	public String getCaptcha() {
		return captcha;
	}



	public void setCaptcha(String captcha) {
		this.captcha = captcha;
	}



	public String getHidden() {
		return hidden;
	}



	public void setHidden(String hidden) {
		this.hidden = hidden;
	}



	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public boolean isLoginFirst() {
		return loginFirst;
	}

	public void setLoginFirst(boolean loginFirst) {
		this.loginFirst = loginFirst;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getFavouritBooks() {
		return favouritBooks;
	}

	public void setFavouritBooks(String favouritBooks) {
		this.favouritBooks = favouritBooks;
	}

	public String getFavouriteSongs() {
		return favouriteSongs;
	}

	public void setFavouriteSongs(String favouriteSongs) {
		this.favouriteSongs = favouriteSongs;
	}

	public String getFavouritePlaces() {
		return favouritePlaces;
	}

	public void setFavouritePlaces(String favouritePlaces) {
		this.favouritePlaces = favouritePlaces;
	}

	public User() {
	}
	@Override
	public String toString() {
		return "User [id=" + id + ", name=" + name + ", email=" + email + ", password=" + password
				+ ", loginFirst=" + loginFirst + ", image=" + image + ", userName=" + userName + ", favouritBooks="
				+ favouritBooks + ", favouriteSongs=" + favouriteSongs + ", favouritePlaces=" + favouritePlaces + "]";
	}

	
	
	
	
}
