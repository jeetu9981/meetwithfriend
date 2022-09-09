//package com.meetnewfriend;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//
//import com.meetnewfriend.service.impl.UserServiceImpl;
//
//@Configuration
//@EnableWebSecurity
//public class SecurityConfig extends WebSecurityConfigurerAdapter{
//	
//	@Autowired
//	private UserServiceImpl customUserService;
//	
//	public void configure(HttpSecurity http) throws Exception{
//		http
//		.csrf().disable()
//		.authorizeRequests().antMatchers("/user/signup").permitAll()
//		.antMatchers("/","/user/","/user/signinjsp").permitAll()
//		.anyRequest().authenticated()
//		.and()
//		.formLogin()
//		.loginPage("/user/signinjsp")
////		.loginProcessingUrl("/user/signin")
//		.defaultSuccessUrl("/user/profile")
//		.failureUrl("/")
//		.permitAll();
//	}
////	
//	@Bean
//	public BCryptPasswordEncoder passwordEncoder() {
//		return new BCryptPasswordEncoder();
//	}
//	
//////	
//////	@Bean
//////	public DaoAuthenticationProvider authenticationProvider() {
//////		DaoAuthenticationProvider dao=new DaoAuthenticationProvider();
//////		dao.setUserDetailsService(userService);
//////		dao.setPasswordEncoder(passwordEncoder());
//////		return dao;
//////	}
//////	
//	public void configure(AuthenticationManagerBuilder auth) throws Exception {
//		auth.userDetailsService(customUserService).passwordEncoder(passwordEncoder());
//	}
//}
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
