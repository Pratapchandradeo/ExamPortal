package com.exam.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.DefaultSecurityFilterChain;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.exam.ServisesImp.UserDetailsServiceImpl;

@EnableWebSecurity
@Configuration
@EnableMethodSecurity
public class MySecurityConfig {

	@Autowired
	private UserDetailsServiceImpl userDe;

	@Autowired
	private JwtAuthenticationEntryPoint unauthorizedHandler;

	@Autowired
	private JwtAuthenticationFilter jwtAuthenticationFilter;

//	@Override
//	@Bean
////	public AuthenticationManager authenticationManagerBean() throws Exception {
//
//		return super.authenticationManagerBean();
//	}

	@Bean
	public AuthenticationManager authenticationManagerBean(AuthenticationConfiguration configuration ) throws Exception {

		return configuration.getAuthenticationManager();
	}

	@Bean
	public BCryptPasswordEncoder passwordEncoder() {

		return new BCryptPasswordEncoder();
	}

//	@Override
//	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//
//		auth.userDetailsService(userDe).passwordEncoder(passwordEncoder());
//	}
	
	@Bean
	public DaoAuthenticationProvider daoAuthenticationProvider() {
		
		DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
		provider.setUserDetailsService(userDe);
		provider.setPasswordEncoder(passwordEncoder());
		
		return provider;
	}
	
	

	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		http.csrf()
			.disable()
			.cors()
			.disable()
			.authorizeHttpRequests()
			.requestMatchers("/authentication", "/user/").permitAll()
			.requestMatchers(HttpMethod.OPTIONS).permitAll()
			.anyRequest()
			.authenticated()
			.and()
			.exceptionHandling()
			.authenticationEntryPoint(unauthorizedHandler)
			.and()
			.sessionManagement()
			.sessionCreationPolicy(SessionCreationPolicy.STATELESS);

		http.addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);

		http.authenticationProvider(daoAuthenticationProvider());
		DefaultSecurityFilterChain defaultSecurityFilterChain = http.build();

		return defaultSecurityFilterChain;
	}
	/*
	 * @Override protected void configure(HttpSecurity http) throws Exception {
	 * 
	 * http .csrf() .disable() .cors() .disable() .authorizeRequests()
	 * .antMatchers("/generate-token", "/user/").permitAll()
	 * .antMatchers(HttpMethod.OPTIONS).permitAll() .anyRequest() .authenticated()
	 * .and() .exceptionHandling() .authenticationEntryPoint(unauthorizedHandler)
	 * .and() .sessionManagement()
	 * .sessionCreationPolicy(SessionCreationPolicy.STATELESS);
	 * 
	 * 
	 * http.addFilterBefore(jwtAuthenticationFilter,
	 * UsernamePasswordAuthenticationFilter.class);
	 * 
	 * }
	 */

}
