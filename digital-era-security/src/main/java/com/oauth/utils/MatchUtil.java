package com.oauth.utils;

import com.oauth.constants.SecurityConstants;

public final class MatchUtil {

    public static boolean checkContentType(String header) {
        return SecurityConstants.APPLICATION_JSON.equals(header);
    }
}
