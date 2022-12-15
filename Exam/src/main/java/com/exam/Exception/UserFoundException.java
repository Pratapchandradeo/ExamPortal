package com.exam.Exception;

public class UserFoundException extends Exception {

	public UserFoundException() {
		super("User with this user name is alerdy there !! try with anther one !");
	}
	public UserFoundException(String message) {
		super(message);
	}
	
}
