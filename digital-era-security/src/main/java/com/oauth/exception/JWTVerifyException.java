package com.oauth.exception;

// TODO: Auto-generated Javadoc
/**
 * The Class JWTVerifyException.
 */
public class JWTVerifyException extends Exception {
  
  /** The Constant serialVersionUID. */
  private static final long serialVersionUID = -4911506451239107610L;

  /**
   * Instantiates a new JWT verify exception.
   */
  public JWTVerifyException() {
  }
  
  

  /**
   * Instantiates a new JWT verify exception.
   *
   * @param message the message
   * @param cause the cause
   */
  public JWTVerifyException(String message, Throwable cause) {
      super(message, cause);
  }


  /**
   * Instantiates a new JWT verify exception.
   *
   * @param message the message
   */
  public JWTVerifyException(String message) {
      super(message);
  }
}
