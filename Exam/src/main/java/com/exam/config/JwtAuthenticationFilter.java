package com.exam.config;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.exam.ServisesImp.UserDetailsServiceImpl;

import io.jsonwebtoken.ExpiredJwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter  {

	@Autowired
	private UserDetailsServiceImpl userDS;
	
	@Autowired
	private JwtUtils jwtutil;
	
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		
		
		final String requestTokenHeader =	request.getHeader("Authorization");
		
		System.out.println(requestTokenHeader);
		
		String username =null;
		String jwtToken=null;
		
		if(requestTokenHeader != null && requestTokenHeader.startsWith("Bearer ")) {
			
//			Yes 
			
			jwtToken = requestTokenHeader.substring(7);
			
			
			try {
				username = jwtutil.extractUsername(jwtToken);
			} catch (ExpiredJwtException e) {
				e.printStackTrace();
				System.out.println("jwt token has expired");
			}
			catch (Exception e) {
				e.printStackTrace();
				System.out.println("Error");
			}	
		}
		else
		{
			System.out.println("Invalid token , not start with bearer ");
		}
		
//		validate 
		
		if(username != null && SecurityContextHolder.getContext().getAuthentication()==null)
		{
			final UserDetails userDe =  this.userDS.loadUserByUsername(username);
			if(this.jwtutil.validateToken(jwtToken, userDe)) {
				
				//token is valid
				UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(userDe,null,userDe.getAuthorities());
				usernamePasswordAuthenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
				SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
			}
		}
		else
		{
			System.out.println("Token is Not Valid !!!!!!!");
		}
		
		filterChain.doFilter(request,response);
	}

}
