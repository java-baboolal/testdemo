package com.oauth.interfaces;

import com.oauth.data.RoleUrlMapping;

import java.util.List;

/**
 * This interface is used for role mapping with the url.
 */
public interface RESTRoleUrlMapping {

    /**
     * Implement this method to map all the roles with the corresponding urls for the security.
     *
     * @return List<RoleUrlMapping> contains role with the authenticated urls.
     */
    List<RoleUrlMapping> fetchRoleUrlMapping();
}
