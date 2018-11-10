package com.oauth.exception;

import com.fasterxml.jackson.databind.JsonNode;

import java.util.ArrayList;
import java.util.List;

// TODO: Auto-generated Javadoc

/**
 * The Class JWTAudienceException.
 */
public class JWTAudienceException extends JWTVerifyException {
  
  /** The Constant serialVersionUID. */
  private static final long serialVersionUID = 1L;
  
  /** The audience node. */
  private JsonNode audienceNode;

  /**
   * Instantiates a new JWT audience exception.
   *
   * @param audienceNode the audience node
   */
  public JWTAudienceException(JsonNode audienceNode) {
      this.audienceNode = audienceNode;
  }

  /**
   * Instantiates a new JWT audience exception.
   *
   * @param message the message
   * @param audienceNode the audience node
   */
  public JWTAudienceException(String message, JsonNode audienceNode) {
      super(message);
      this.audienceNode = audienceNode;
  }

  /**
   * Gets the audience.
   *
   * @return the audience
   */
  public List<String> getAudience() {
      ArrayList<String> audience = new ArrayList<String>();
      if (audienceNode.isArray()) {
          for (JsonNode jsonNode : audienceNode) {
              audience.add(jsonNode.textValue());
          }
      } else if (audienceNode.isTextual()) {
          audience.add(audienceNode.textValue());
      }
      return audience;
  }
}
