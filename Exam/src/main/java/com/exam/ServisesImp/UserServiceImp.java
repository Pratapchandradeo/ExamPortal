package com.exam.ServisesImp;

import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.exam.Exception.UserNotFoundException;
import com.exam.Exception.UserFoundException;
import com.exam.Models.User;
import com.exam.Models.UserRole;
import com.exam.Repo.RoleRepositry;
import com.exam.Repo.UserRepositry;
import com.exam.Servises.UserService;



@Service
public class UserServiceImp implements UserService {

	@Autowired
	private UserRepositry userR;
	@Autowired
	private RoleRepositry roleR;
	
	
//	Create User 
	@Override
	public User createUser(User user, Set<UserRole> userRole) throws UserFoundException {
		User local = this.userR.findByUserName(user.getUserName());
		if(local !=null) 
		{
			System.out.println("User is Alerdy there !!");
			throw new UserFoundException();
		}
		else
		{
//	create user
			for(UserRole u : userRole)
			{
				roleR.save(u.getRole());
			}
			
			user.getUserRoles().addAll(userRole);
			local =this.userR.save(user);
			
		}
		return local;
	}

//getUser by UserName
	@Override
	public User GetUserByUserName(String userName) throws UserNotFoundException {
		User data = this.userR.findByUserName(userName);
		if(data==null)
		{
			throw new UserNotFoundException("There is no user on this UserName !!!!!");
		}
		return data;
	}

	
	//delete user by id
	@Override
	public void deleteUser(Long UserId){

		this.userR.deleteById(UserId);
	}

//	Update User
	@Override
	public User UpdateUser(User user) throws UserNotFoundException {
		Optional<User> data = this.userR.findById(user.getId());
		
		if(data.isPresent())
		{
			return userR.save(user);
		}
		else
		{
			throw new UserNotFoundException("No user found to update");
		}
	}

}
