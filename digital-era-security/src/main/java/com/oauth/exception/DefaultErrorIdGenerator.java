package com.oauth.exception;

// TODO: Auto-generated Javadoc
/**
 * The Class DefaultErrorIdGenerator.
 */
public class DefaultErrorIdGenerator implements ErrorIdGenerator {
	
	/** The id. */
	private static int id = 1;

	/* (non-Javadoc)
	 * @see com.raastech.mobile.rest.exceptions.ErrorIdGenerator#generateId()
	 */
	public String generateId() {
		return "serverErrorId:" + id++;
	}
}
