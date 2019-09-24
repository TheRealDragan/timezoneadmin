package com.petar.timezoneadmin.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.petar.timezoneadmin.mappers.UserMapper;
import com.petar.timezoneadmin.model.User;
@Service
public class CredentialsCheckingServiceImpl implements CredentialsCheckingService{
	
	@Autowired
	private UserMapper userMapper;
	
	@Autowired
	private PasswordEncoder passwordEncoder;

	@Override
	public User checkUserCredentials(User user) throws BadCredentialsException {
		User foundUser = userMapper.findByUsername(user.getUserName());
		if (foundUser==null || !passwordEncoder.matches(user.getPassword(), foundUser.getPassword())) {
			throw new BadCredentialsException("The provided credentials are incorrect");
		}		
		return foundUser;
	}

}
