package com.cropdata.exception;

public class LanguageException extends RuntimeException {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public LanguageException() {
        super("Order details cannot be empty");
    }
    
    public LanguageException(String message) {
        super(message);
    }
}
    
