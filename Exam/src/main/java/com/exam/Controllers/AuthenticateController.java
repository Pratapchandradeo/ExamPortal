package com.exam.Controllers;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.exam.Models.JwtRequest;
import com.exam.Models.JwtResponse;
import com.exam.Models.User;
import com.exam.ServisesImp.UserDetailsServiceImpl;
import com.exam.config.JwtUtils;

@RestController
@CrossOrigin("*")
public class AuthenticateController {
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private UserDetailsServiceImpl userDetailsServiceImpl;
	
	@Autowired
	private JwtUtils jwtUtils;
	
	
	
//	generate token
	@PostMapping("/authentication")
	public ResponseEntity<?> generateToken(@RequestBody JwtRequest jwtRequest) throws Exception{
		
		try {
			
			authenticate(jwtRequest.getUsername(),jwtRequest.getPassword());
			
			
		} catch (UsernameNotFoundException e) {
			e.printStackTrace();
			throw new Exception("User Not found !!!!");
		}
		
//		valide user
		
		UserDetails usrDetails =  this.userDetailsServiceImpl.loadUserByUsername(jwtRequest.getUsername());
		String token = this.jwtUtils.generateToken(usrDetails);
		System.out.println(token);
		return ResponseEntity.ok(new JwtResponse(token));
		
	}

	private void authenticate(String username, String password) throws Exception {
		
		try {
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
			
		} catch (DisabledException e) {
			throw new Exception("USER DESABLED"+e.getMessage());
		}
		catch (BadCredentialsException e) {
			throw new Exception("Invalide Credentials"+e.getMessage());
		}
		
	}
	
	
//	get current user details
	
	@GetMapping("/current-user")
	public User getCurrentUser(Principal principal) {
		
		return ((User) this.userDetailsServiceImpl.loadUserByUsername(principal.getName()));
	}

}
