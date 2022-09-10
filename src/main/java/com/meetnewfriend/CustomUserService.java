//package com.meetnewfriend;
//
//import java.util.ArrayList;
//import java.util.Collection;
//
//import org.springframework.security.core.GrantedAuthority;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//
//import com.meetnewfriend.entity.User;
//
//public class CustomUserService implements UserDetails{
//
//	
//	private BCryptPasswordEncoder bcrypt=new BCryptPasswordEncoder();
//	User user;
//	
//	public CustomUserService(User user) {
//		this.user=user;
//	}
//	
//	public CustomUserService() {}
//	
//	@Override
//	public Collection<? extends GrantedAuthority> getAuthorities() {
//		// TODO Auto-generated method stub
//		return new ArrayList<GrantedAuthority>();
//	}
//
//	@Override
//	public String getPassword() {
//		// TODO Auto-generated method stub
//		return this.user.getPassword();
//	}
//
//	@Override
//	public String getUsername() {
//		// TODO Auto-generated method stub
//		return user.getEmail();
//	}
//
//	@Override
//	public boolean isAccountNonExpired() {
//		// TODO Auto-generated method stub
//		return true;
//	}
//
//	@Override
//	public boolean isAccountNonLocked() {
//		// TODO Auto-generated method stub
//		return true;
//	}
//
//	@Override
//	public boolean isCredentialsNonExpired() {
//		// TODO Auto-generated method stub
//		return true;
//	}
//
//	@Override
//	public boolean isEnabled() {
//		// TODO Auto-generated method stub
//		return true;
//	}
//
//}
