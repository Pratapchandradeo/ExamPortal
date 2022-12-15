package com.exam.Exception;

public class UserNotFoundException extends Exception {

	public UserNotFoundException() {
		super("User with this User name is Not found !!!");
	}
	
	
	public UserNotFoundException(String message) {
		super(message);
	}
	
}
