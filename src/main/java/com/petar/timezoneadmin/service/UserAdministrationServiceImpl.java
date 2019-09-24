package com.petar.timezoneadmin.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.petar.timezoneadmin.exception.UsernameTakenException;
import com.petar.timezoneadmin.mappers.UserMapper;
import com.petar.timezoneadmin.utils.StatusChangeTimeByUser;
import com.petar.timezoneadmin.model.User;

@Service
public class UserAdministrationServiceImpl implements UserAdministrationService {
	
	@Autowired
	private UserMapper userMapper;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private StatusChangeTimeByUser statusChangeTimeByUser;

	@Override
	public List<User> getAllUsers() {
		return userMapper.findAllUsers();
	}

	@Override
	public User getUser(Long userId) {
		User foundUser = userMapper.findById(userId);
		if (userMapper.findById(userId)==null) {
			throw new UsernameNotFoundException("An user with the provided username does not exist");
		}
		foundUser.setPassword(null);
		return foundUser;
	}

	@Override
	@Transactional
	public User createUser(User user) throws UsernameTakenException {
		if (userMapper.findByUsername(user.getUserName())!=null) {
			throw new UsernameTakenException();
		}
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		userMapper.createUser(user);
		return userMapper.findByUsername(user.getUserName());
	}

	@Override
	@Transactional
	public User updateUser(User user) throws UsernameNotFoundException, UsernameTakenException {
		if (userMapper.findById(user.getId())==null) {
			throw new UsernameNotFoundException("An user with the provided username does not exist");
		}
		User foundUser = userMapper.findByUsername(user.getUserName()); 
		
		if (foundUser!=null && !foundUser.getId().equals(user.getId())) {
			throw new UsernameTakenException();
		}
		if (user.getPassword()!=null) {
			user.setPassword(passwordEncoder.encode(user.getPassword()));
		}
		userMapper.updateUser(user);
		foundUser = userMapper.findById(user.getId());
		foundUser.setPassword(null);
		if (user.getRole()!=null) {
			statusChangeTimeByUser.addToMap(user.getId(), System.currentTimeMillis());
		}
		return foundUser;
	}

	@Override
	public void deleteUser(Long userId) {
		if (userMapper.findById(userId)==null) {
			throw new UsernameNotFoundException("An user with the provided username does not exist");
		}
		userMapper.deleteUser(userId);	
		statusChangeTimeByUser.addToMap(userId, System.currentTimeMillis());

	}
}
