package com.petar.timezoneadmin.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.petar.timezoneadmin.exception.UsernameTakenException;
import com.petar.timezoneadmin.model.User;
import com.petar.timezoneadmin.model.UserRole;


@Service
public class RegistrationServiceImpl implements RegistrationService{
	
	@Autowired
	private UserAdministrationService userAdministrationService;

	@Override
	@Transactional
	public User registerUser(User user) throws UsernameTakenException {	
		user.setRole(UserRole.REGULAR);
		return userAdministrationService.createUser(user);		
	}
}
