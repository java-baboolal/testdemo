package com.oauth.exception;

// TODO: Auto-generated Javadoc
/**
 * The Class RequiredFieldMissingException.
 */
public class RequiredFieldMissingException extends RuntimeException {

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
	 * Instantiates a new required field missing exception.
	 */
	public RequiredFieldMissingException() {
	}

	/**
	 * Instantiates a new required field missing exception.
	 *
	 * @param msg the msg
	 */
	public RequiredFieldMissingException(String msg) {
		super(msg);
	}

	/**
	 * Instantiates a new required field missing exception.
	 *
	 * @param msg the msg
	 * @param errorCode the error code
	 */
	public RequiredFieldMissingException(String msg, int errorCode) {
		super(msg);
		this.errorCode = errorCode;
	}

}
