package com.cg.oam.exception;

public class UserNotFoundException extends RuntimeException {
	
	private static final long serialVersionUID = 1L;
	String message;
	public UserNotFoundException(String message) {
		super();
		this.message = message;
	}


	@Override
	public String getMessage() {
		return message;

	}

}
