package com.oauth.exception;

// TODO: Auto-generated Javadoc
/**
 * The Class JWTIssuerException.
 */
public class JWTIssuerException extends JWTVerifyException {
  
  /** The Constant serialVersionUID. */
  private static final long serialVersionUID = 1L;
  
  /** The issuer. */
  private final String issuer;

  /**
   * Instantiates a new JWT issuer exception.
   *
   * @param issuer the issuer
   */
  public JWTIssuerException(String issuer) {
      this.issuer = issuer;
  }

  /**
   * Instantiates a new JWT issuer exception.
   *
   * @param message the message
   * @param issuer the issuer
   */
  public JWTIssuerException(String message, String issuer) {
      super(message);
      this.issuer = issuer;
  }

  /**
   * Gets the issuer.
   *
   * @return the issuer
   */
  public String getIssuer() {
      return issuer;
  }
}
