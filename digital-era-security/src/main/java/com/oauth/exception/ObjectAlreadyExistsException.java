package com.oauth.exception;

// TODO: Auto-generated Javadoc
/**
 * The Class ObjectAlreadyExistsException.
 */
public class ObjectAlreadyExistsException extends RuntimeException {

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
	 * Instantiates a new object already exists exception.
	 */
	public ObjectAlreadyExistsException() {
	}

	/**
	 * Instantiates a new object already exists exception.
	 *
	 * @param msg the msg
	 */
	public ObjectAlreadyExistsException(String msg) {
		super(msg);
	}

	/**
	 * Instantiates a new object already exists exception.
	 *
	 * @param msg the msg
	 * @param errorCode the error code
	 */
	public ObjectAlreadyExistsException(String msg, int errorCode) {
		super(msg);
		this.errorCode = errorCode;
	}

}
