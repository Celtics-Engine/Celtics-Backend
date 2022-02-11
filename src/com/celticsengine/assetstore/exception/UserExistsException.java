package com.celticsengine.assetstore.exception;

public class UserExistsException extends RuntimeException{
	public UserExistsException() {
		super("The user exists in the database.");
	}
}
