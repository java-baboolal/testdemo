package com.oauth.data;

import java.io.Serializable;

public class UserRole implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer roleId;
    private String role;

    public UserRole() {
    }

    public UserRole(Integer roleId, String role) {
        this.roleId = roleId;
        this.role = role;
    }

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return "UserRole{" +
                "role='" + role + '\'' +
                '}';
    }
}
