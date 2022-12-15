package com.exam.Models;

import java.util.HashSet;
import java.util.Set;


import jakarta.persistence.OneToMany;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;

@Entity
public class Role {

	@Id
	private Long roleId;
	private String roleName;
	
	@OneToMany(cascade = CascadeType.ALL,fetch = FetchType.LAZY,mappedBy ="role" )
	private Set<UserRole> userRoles = new HashSet<>();
	
	public Role() {
		// TODO Auto-generated constructor stub
	}

	public Role(Long roleId, String roleName, Set<UserRole> userRoles) {
		super();
		this.roleId = roleId;
		this.roleName = roleName;
	}

	public Long getRoleId() {
		return roleId;
	}

	public void setRoleId(Long roleId) {
		this.roleId = roleId;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public Set<UserRole> getUserRoles() {
		return userRoles;
	}

	public void setUserRoles(Set<UserRole> userRoles) {
		this.userRoles = userRoles;
	}
	
	
	
	
}
