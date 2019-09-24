package com.petar.timezoneadmin.service;

import org.springframework.security.authentication.BadCredentialsException;

import com.petar.timezoneadmin.model.User;

public interface CredentialsCheckingService {

	public User checkUserCredentials(User user) throws BadCredentialsException;
	
}
