package com.oauth.exception;

// TODO: Auto-generated Javadoc
/**
 * The Class JWTExpiredException.
 */
public class JWTExpiredException extends JWTVerifyException {
  
  /** The Constant serialVersionUID. */
  private static final long serialVersionUID = 1L;
  
  /** The expiration. */
  private long expiration;

  /**
   * Instantiates a new JWT expired exception.
   *
   * @param expiration the expiration
   */
  public JWTExpiredException(long expiration) {
      this.expiration = expiration;
  }

  /**
   * Instantiates a new JWT expired exception.
   *
   * @param message the message
   * @param expiration the expiration
   */
  public JWTExpiredException(String message, long expiration) {
      super(message);
      this.expiration = expiration;
  }

  /**
   * Gets the expiration.
   *
   * @return the expiration
   */
  public long getExpiration() {
      return expiration;
  };
}
