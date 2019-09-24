package com.petar.timezoneadmin.model;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class User {
	private Long id;
	@NotNull
	@NotEmpty
	private String username;
	@Size(min=5, max=30)
	private String password;
	private UserRole role;	
	
	public String getUserName() {
		return username;
	}

	public Long getId() {
		return id;
	}

	public UserRole getRole() {
		return role;
	}

	public void setUsername(String username) {
		this.username = username;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public void setRole(UserRole role) {
		this.role = role;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getUsername() {
		return username;
	}	
	
	@Override
	public String toString() {
		return "JwtUser: " + "username = "+ getUsername() +",password = "+getPassword()+",role = "+getRole();
	}

}
