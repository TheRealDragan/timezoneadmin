package com.petar.timezoneadmin.model;

public enum UserRole {
    REGULAR("admin_owned_timezones"),
    ADMIN("admin_users"), 
    SUPER_ADMIN("admin_owned_timezones, admin_all_timezones,admin_users");
	
	private String granthedAuthorities;
	
	private UserRole(String grantedAuthorities) {
		this.granthedAuthorities = grantedAuthorities;
	}
	
	public String getGrantedAuthorities() {
		return granthedAuthorities;
	}
}
