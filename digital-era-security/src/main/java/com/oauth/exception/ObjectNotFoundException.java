package com.oauth.exception;

// TODO: Auto-generated Javadoc
/**
 * The Class ObjectNotFoundException.
 */
public class ObjectNotFoundException extends RuntimeException {

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
	 * Instantiates a new object not found exception.
	 */
	public ObjectNotFoundException() {
	}

	/**
	 * Instantiates a new object not found exception.
	 *
	 * @param msg the msg
	 */
	public ObjectNotFoundException(String msg) {
		super(msg);
	}

	/**
	 * Instantiates a new object not found exception.
	 *
	 * @param msg the msg
	 * @param errorCode the error code
	 */
	public ObjectNotFoundException(String msg, int errorCode) {
		super(msg);
		this.errorCode = errorCode;
	}

}
