//package com.meetnewfriend;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
//import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
//
//import com.meetnewfriend.services.UserService;
//
//@Configuration
//@EnableWebSecurity
//public class SecurityConfig extends WebSecurityConfigurerAdapter{
//	
//	@Autowired
//	private UserService userService;
//	
//	public void configure(HttpSecurity http) throws Exception{
//		http.csrf().disable()
//		.authorizeRequests().antMatchers("/","/user/","/user/signinjsp","/user/signup","/user/signin").permitAll()
//		.anyRequest().authenticated()
//		.and()
//		.formLogin()
//		.loginPage("/views/signin.jsp")
//		.defaultSuccessUrl("/user/profile")
//		.permitAll();
//	}
//	
//	@Bean
//	public BCryptPasswordEncoder passwordEncoder() {
//		return new BCryptPasswordEncoder();
//	}
//	
//	@Bean
//	public DaoAuthenticationProvider authenticationProvider() {
//		DaoAuthenticationProvider dao=new DaoAuthenticationProvider();
//		dao.setUserDetailsService(userService);
//		dao.setPasswordEncoder(passwordEncoder());
//		return dao;
//	}
//	
//	public void configure(AuthenticationManagerBuilder auth) {
//		auth.authenticationProvider(authenticationProvider());
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
