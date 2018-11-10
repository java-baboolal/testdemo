package com.oauth.exception;

// TODO: Auto-generated Javadoc
/**
 * The Class InvalidArgumentsException.
 */
public class InvalidArgumentsException extends RuntimeException {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;
	
	/** The error code. */
	private int errorCode;

	/**
	 * Gets the error code.
	 *
	 * @return the error code
	 */
	public int getErrorCode() {
		return this.errorCode;
	}

	/**
	 * Instantiates a new invalid arguments exception.
	 */
	public InvalidArgumentsException() {
	}

	/**
	 * Instantiates a new invalid arguments exception.
	 *
	 * @param msg the msg
	 */
	public InvalidArgumentsException(String msg) {
		super(msg);
	}

	/**
	 * Instantiates a new invalid arguments exception.
	 *
	 * @param msg the msg
	 * @param errorCode the error code
	 */
	public InvalidArgumentsException(String msg, int errorCode) {
		super(msg);
		this.errorCode = errorCode;
	}

}