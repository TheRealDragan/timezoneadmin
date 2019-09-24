package com.petar.timezoneadmin.controller;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.petar.timezoneadmin.exception.UsernameTakenException;
import com.petar.timezoneadmin.model.User;
import com.petar.timezoneadmin.service.RegistrationService;
import com.petar.timezoneadmin.validator.JwtUserValidator;


@RestController
@RequestMapping("/registration")
public class RegistrationController {
	
	@Autowired
	private RegistrationService registrationService;
	
	@Autowired
	private JwtUserValidator userValidator;
	
	
	 @InitBinder
	    protected void initBinder(final WebDataBinder binder)
	    {
	        binder.setValidator(userValidator);
	    }
	
	@PostMapping(value = "/register-user", consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.CREATED)
	public User registerUser(@RequestBody @Valid User user) throws UsernameTakenException {
		return registrationService.registerUser(user);
	}
}
