package com.exam.ServisesImp;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.exam.Models.User;
import com.exam.Repo.UserRepositry;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	private UserRepositry userR;
	
	
	
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		System.out.println(username+"==============================================================================================");
		User user =	userR.findByUserName(username);
		System.out.println(user);
		System.out.println("***********************************************************************************************************");
		if(user==null)
		{
			System.out.println("User not found in UserDeImp");
			throw new UsernameNotFoundException("No user found !!");
		}
		System.out.println(user.getUserName());
		return user;
	}

}
