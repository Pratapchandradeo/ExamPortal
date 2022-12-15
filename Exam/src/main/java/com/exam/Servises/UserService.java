package com.exam.Servises;

import java.util.Set;
import com.exam.Exception.UserNotFoundException;
import com.exam.Exception.UserFoundException;
import com.exam.Models.User;
import com.exam.Models.UserRole;




public interface UserService {
	
	//	Create User
	public User createUser(User user, Set<UserRole> userRole)throws UserFoundException ;
	
	//getByUserName
	
	public User GetUserByUserName(String userName)throws UserNotFoundException ;
	
	//delete user
	public void deleteUser(Long UserId);
	
	//update user
	public User UpdateUser(User user)throws UserNotFoundException ;
	
}
