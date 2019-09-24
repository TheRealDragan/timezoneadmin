package com.petar.timezoneadmin.controller;


import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.petar.timezoneadmin.exception.UsernameTakenException;
import com.petar.timezoneadmin.model.User;
import com.petar.timezoneadmin.service.UserAdministrationService;

@RestController
@PreAuthorize("hasAuthority('admin_users')")
@RequestMapping("/admin/users")
public class UserAdministrationController {
	
	@Autowired
	private UserAdministrationService userAdministrationService;

	@GetMapping
	public List<User> getAllUsers() {
		return userAdministrationService.getAllUsers();
	}
	
	@GetMapping(value="/{userId}", produces = APPLICATION_JSON_VALUE)
	public User getUser(@PathVariable("userId") Long userId) {
		return userAdministrationService.getUser(userId);
	}
	
	@PostMapping(consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
	public User createUser(@Valid @RequestBody User user) throws UsernameTakenException {
		return userAdministrationService.createUser(user);
	}
	
	@PutMapping(value="/{userId}", produces = APPLICATION_JSON_VALUE)
	public User updateUser(@PathVariable("userId") Long userId, @Valid @RequestBody User jwtUser) throws UsernameNotFoundException, UsernameTakenException{
		return userAdministrationService.updateUser(jwtUser);
	}
	
	@DeleteMapping(value="/{userId}", produces = APPLICATION_JSON_VALUE)
	public void deleteUser(@PathVariable("userId") Long userId) {
		userAdministrationService.deleteUser(userId);
	}
}
