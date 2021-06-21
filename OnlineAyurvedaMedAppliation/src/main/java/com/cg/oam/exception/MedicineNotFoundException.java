package com.cg.oam.exception;

public class MedicineNotFoundException extends RuntimeException {
	private static final long serialVersionUID = 1L;
	String message;

	public MedicineNotFoundException(String message) {
		super();
		this.message = message;
	}

	@Override
	public String getMessage() {
		return message;

}
}
