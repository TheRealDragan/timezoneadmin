package com.petar.timezoneadmin.controller;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

import org.springframework.beans.factory.annotation.Autowired;import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.petar.timezoneadmin.model.User;
import com.petar.timezoneadmin.service.CredentialsCheckingService;
import com.petar.timezoneadmin.dto.TokenWithRoleDTO;
import com.petar.timezoneadmin.jwt.security.JwtGenerator;

@RestController
@RequestMapping("/login")
public class TokenController {
	
	private JwtGenerator jwtGenerator;
	
	@Autowired
	private CredentialsCheckingService credentialsCheckingService;
	
	public TokenController(JwtGenerator jwtGenerator) {
		this.jwtGenerator = jwtGenerator;
	}
	
	@PostMapping(value="/token", consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
	public TokenWithRoleDTO generate(@RequestBody User jwtUser) {
        User foundUser= credentialsCheckingService.checkUserCredentials(jwtUser);
		return new TokenWithRoleDTO(jwtGenerator.generate(foundUser), foundUser.getRole());
	}

}
