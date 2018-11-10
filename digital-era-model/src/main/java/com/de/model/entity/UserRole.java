package com.de.model.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "user_role")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class UserRole {

	@Id
	@GeneratedValue
	@Column(name = "role_id")
	private Integer roleId;
	
	
	@Column(name = "role_user")
	private String userRole;
	
	@Column(name = "role_description")
	private String roleDescriprion;


	public Integer getRoleId() {
		return roleId;
	}

	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}


	public String getUserRole() {
		return userRole;
	}


	public void setUserRole(String userRole) {
		this.userRole = userRole;
	}


	public String getRoleDescriprion() {
		return roleDescriprion;
	}


	public void setRoleDescriprion(String roleDescriprion) {
		this.roleDescriprion = roleDescriprion;
	}
	
}
