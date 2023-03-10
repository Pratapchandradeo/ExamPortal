package com.exam.Controllers;

import java.util.HashSet;
import java.util.Set;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.exam.Exception.UserNotFoundException;
import com.exam.Exception.UserFoundException;
import com.exam.Models.Role;
import com.exam.Models.User;
import com.exam.Models.UserRole;
import com.exam.Servises.UserService;


@RestController
@RequestMapping("/user")
@CrossOrigin("*")
public class UserController {
	
	@Autowired
	private UserService userS;
	
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	
	
//	creating user
	@PostMapping("/")
	public User createUser(@RequestBody User user) throws UserFoundException{
		
		user.setProfile("default.png");
		
//		encoding password with BcryptPasswordEncoder
		user.setPassword(this.bCryptPasswordEncoder.encode(user.getPassword()));
		
		
		
		Set<UserRole> roles = new HashSet<>();
		Role role = new Role();
		role.setRoleId(45L);
		role.setRoleName("Normal");
		UserRole Ur = new UserRole();
		Ur.setUser(user);
		Ur.setRole(role);
		
		roles.add(Ur);
		
		
		return this.userS.createUser(user, roles);
		
	}
	
//	get User By UserName
	
	@GetMapping("/{userName}")
	public User getUser(@PathVariable("userName") String UserName) throws UserNotFoundException {
		
		return this.userS.GetUserByUserName(UserName);
	}
	
//	delete user by id
	@DeleteMapping("/{userId}")
	public void deleteUserById(@PathVariable("userId") Long userID)
	{
		this.userS.deleteUser(userID);
	}
	
//	update user 
	@PutMapping("/edit")
	public User updateUser(@RequestBody User user) throws UserNotFoundException
	{
		return userS.UpdateUser(user);
	}
	
	@ExceptionHandler(UserFoundException.class)
	public ResponseEntity<?> exceptionHandler(UserFoundException ex){
		return ResponseEntity.ok(ex.getMessage());
	}

}
