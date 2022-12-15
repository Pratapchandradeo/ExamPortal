package com.exam.Exception;

public class CategoryIdNotFoundException extends Exception {

	public CategoryIdNotFoundException() {
		super("Category not found on this ID ");
	}
	
	public CategoryIdNotFoundException(String message) {
		super(message);
	}
	
	
}
