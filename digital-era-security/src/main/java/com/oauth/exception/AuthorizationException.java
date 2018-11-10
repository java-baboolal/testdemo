package com.oauth.exception;

// TODO: Auto-generated Javadoc
/**
 * The Class AuthorizationException.
 */
public class AuthorizationException extends RuntimeException {

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
	 * Instantiates a new authorization exception.
	 */
	public AuthorizationException() {
	}

	/**
	 * Instantiates a new authorization exception.
	 *
	 * @param msg the msg
	 */
	public AuthorizationException(String msg) {
		super(msg);
	}

	/**
	 * Instantiates a new authorization exception.
	 *
	 * @param msg the msg
	 * @param errorCode the error code
	 */
	public AuthorizationException(String msg, int errorCode) {
		super(msg);
		this.errorCode = errorCode;
	}

}