package com.petar.timezoneadmin.service;

import com.petar.timezoneadmin.exception.UsernameTakenException;
import com.petar.timezoneadmin.model.User;

public interface RegistrationService {
	public User registerUser(User user) throws UsernameTakenException;

}
