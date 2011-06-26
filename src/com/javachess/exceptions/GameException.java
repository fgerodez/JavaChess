package com.javachess.exceptions;

public class GameException extends Exception {

	private static final long serialVersionUID = 1L;

	private String message;
	
	public GameException(String string) {
		this.message = string;
	}

	@Override
	public String getMessage() {
		return this.message;
	}	
}
