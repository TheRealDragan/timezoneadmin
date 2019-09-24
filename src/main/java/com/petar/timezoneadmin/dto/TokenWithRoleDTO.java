package com.petar.timezoneadmin.dto;

import com.petar.timezoneadmin.model.UserRole;

public class TokenWithRoleDTO {
	private String token;
	private UserRole role;
	
	public TokenWithRoleDTO() {
		
	}
	
	public TokenWithRoleDTO(String token, UserRole role) {
		this.token = token;
		this.role = role;
	}
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	public UserRole getRole() {
		return role;
	}
	public void setRole(UserRole role) {
		this.role = role;
	}
}
