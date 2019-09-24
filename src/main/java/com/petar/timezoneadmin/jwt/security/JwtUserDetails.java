package com.petar.timezoneadmin.jwt.security;

import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;


@SuppressWarnings("serial")
public class JwtUserDetails implements UserDetails {
	
	private String userName;
	private String token;
	private Long id;
	@SuppressWarnings("unused")
	private Collection<? extends GrantedAuthority> authorities;
	
	public JwtUserDetails(String userName, long id, String token, List<GrantedAuthority> grantedAuthorities) {
		this.userName = userName;
		this.id = id;
		this.token = token;
		this.authorities = grantedAuthorities;
	}

	@Override
	public String getUsername() {
		return userName;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

	public String getUserName() {
		return userName;
	}

	public String getToken() {
		return token;
	}

	public Long getId() {
		return id;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return authorities;
	}

	@Override
	public String getPassword() {
		return null;
	}	
	
	public boolean isAbleToAdminOnlyOwnedTimezones() {
		return authorities.stream()
						  .filter(a->a.toString().equals("admin_owned_timezones"))
						  .findFirst()
						  .isPresent() && !isAbleToAdminAllTimezones();
	}
	
	public boolean isAbleToAdminAllTimezones() {
		return authorities.stream()
			  .filter(a->a.toString().equals("admin_all_timezones"))
			  .findFirst()
			  .isPresent();
	}
}
