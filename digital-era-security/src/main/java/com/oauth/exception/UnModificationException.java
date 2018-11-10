package com.oauth.exception;

// TODO: Auto-generated Javadoc
/**
 * The Class UnModificationException.
 */
public class UnModificationException extends RuntimeException {

	
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
	 * Instantiates a new un modification exception.
	 */
	public UnModificationException() {
	}

	/**
	 * Instantiates a new un modification exception.
	 *
	 * @param msg the msg
	 */
	public UnModificationException(String msg) {
		super(msg);
	}

	/**
	 * Instantiates a new un modification exception.
	 *
	 * @param msg the msg
	 * @param errorCode the error code
	 */
	public UnModificationException(String msg, int errorCode) {
		super(msg);
		this.errorCode = errorCode;
	}
}
