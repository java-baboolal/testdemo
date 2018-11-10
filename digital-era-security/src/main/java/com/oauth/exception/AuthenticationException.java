package com.oauth.exception;

// TODO: Auto-generated Javadoc
/**
 * The Class AuthenticationException.
 */
public class AuthenticationException extends RuntimeException {

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
	 * Instantiates a new authentication exception.
	 */
	public AuthenticationException() {
	}

	/**
	 * Instantiates a new authentication exception.
	 *
	 * @param msg the msg
	 */
	public AuthenticationException(String msg) {
		super(msg);
	}

	/**
	 * Instantiates a new authentication exception.
	 *
	 * @param msg the msg
	 * @param errorCode the error code
	 */
	public AuthenticationException(String msg, int errorCode) {
		super(msg);
		this.errorCode = errorCode;
	}

}