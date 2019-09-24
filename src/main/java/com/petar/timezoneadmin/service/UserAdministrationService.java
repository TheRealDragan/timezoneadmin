package com.petar.timezoneadmin.service;

import java.util.List;

import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.petar.timezoneadmin.exception.UsernameTakenException;
import com.petar.timezoneadmin.model.User;

public interface UserAdministrationService {
	
	public List<User> getAllUsers();
	
	public User getUser(Long userId);
	
	public User createUser(User user) throws UsernameTakenException;
	
	public User updateUser(User user) throws UsernameNotFoundException, UsernameTakenException;

	public void deleteUser(Long userId);
}
