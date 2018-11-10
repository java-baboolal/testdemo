package com.oauth.constants;

// TODO: Auto-generated Javadoc

/**
 * The Enum Algorithm.
 */
public enum Algorithm {

    /**
     * The H s256.
     */
    HS256("HmacSHA256"),
    /**
     * The H s384.
     */
    HS384("HmacSHA384"),
    /**
     * The H s512.
     */
    HS512("HmacSHA512"),
    /**
     * The R s256.
     */
    RS256("RS256"),
    /**
     * The R s384.
     */
    RS384("RS384"),
    /**
     * The R s512.
     */
    RS512("RS512");

    /**
     * Instantiates a new algorithm.
     *
     * @param value the value
     */
    private Algorithm(String value) {
        this.value = value;
    }

    /**
     * The value.
     */
    private String value;

    /**
     * Gets the value.
     *
     * @return the value
     */
    public String getValue() {
        return value;
    }
}
