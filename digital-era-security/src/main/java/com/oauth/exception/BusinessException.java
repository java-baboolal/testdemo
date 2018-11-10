package com.oauth.exception;

// TODO: Auto-generated Javadoc
/**
 * The Class BusinessException.
 */
public class BusinessException extends RuntimeException {

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
	 * Instantiates a new business exception.
	 */
	public BusinessException() {
	}

	/**
	 * Instantiates a new business exception.
	 *
	 * @param msg the msg
	 */
	public BusinessException(String msg) {
		super(msg);
	}

	/**
	 * Instantiates a new business exception.
	 *
	 * @param msg the msg
	 * @param errorCode the error code
	 */
	public BusinessException(String msg, int errorCode) {
		super(msg);
		this.errorCode = errorCode;
	}

}
