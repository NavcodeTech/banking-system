package com.example.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.servlet.configuration.EnableWebMvcSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.servlet.util.matcher.MvcRequestMatcher;
import org.springframework.web.servlet.handler.HandlerMappingIntrospector;

import com.example.filter.JwtAuthFilter;

@EnableWebSecurity
@Configuration
@EnableMethodSecurity
public class MyConfig {
	
	@Autowired JwtAuthFilter jwtAuthFilter;
	 @Autowired private UserDetailsServiceImpl userDetailsServiceImpl;
	
	
	@Bean
	public UserDetailsService getUserDetailService()
	{
		return new UserDetailsServiceImpl();
	}
	
	@Bean
	public BCryptPasswordEncoder passwordEncoder()
	{
		return new BCryptPasswordEncoder();
	}
	
	@Bean
	public DaoAuthenticationProvider authenticationProvider()
	{
		DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
		
		daoAuthenticationProvider.setUserDetailsService(this.getUserDetailService());
		daoAuthenticationProvider.setPasswordEncoder(passwordEncoder());
		
		return daoAuthenticationProvider;
	}
	
	 @Bean
	 public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
	        return config.getAuthenticationManager();
	 }
	 
	 @Bean
	 MvcRequestMatcher.Builder mvc(HandlerMappingIntrospector introspector) {
	     return new MvcRequestMatcher.Builder(introspector);
	 }
	 protected void configure(AuthenticationManagerBuilder auth) throws Exception
	    {
	        auth.userDetailsService(this.userDetailsServiceImpl).passwordEncoder(passwordEncoder());
	    }
	 
	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http,MvcRequestMatcher.Builder mvc) throws Exception
	{
		 
		 return http.cors().and().csrf().disable().authorizeHttpRequests().
					requestMatchers(mvc.pattern("/api/users/register")).permitAll()
			.requestMatchers(mvc.pattern("/api/users/login")).permitAll()
			.requestMatchers(mvc.pattern("/api/users/change_password")).permitAll()
			.requestMatchers(mvc.pattern("/api/users/verify_otp")).permitAll()
			.requestMatchers(mvc.pattern("/api/users/send_otp")).permitAll()
			.requestMatchers(mvc.pattern("/api/users/**")).authenticated()
			.anyRequest().authenticated()
			.and().sessionManagement()
            .sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
            .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class).build();
			
	}
}


