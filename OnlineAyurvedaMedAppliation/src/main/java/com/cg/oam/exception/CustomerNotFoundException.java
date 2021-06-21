package com.cg.oam.exception;

public class CustomerNotFoundException extends RuntimeException {
	private static final long serialVersionUID = 1L;
	String message;

	public CustomerNotFoundException(String message) {
		super();
		this.message = message;
	}

	@Override
	public String getMessage() {
		return message;
	}
}
​​​​​​​