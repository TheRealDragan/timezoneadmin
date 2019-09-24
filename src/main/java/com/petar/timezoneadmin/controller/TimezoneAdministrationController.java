package com.petar.timezoneadmin.controller;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.petar.timezoneadmin.exception.DuplicateTimezoneException;
import com.petar.timezoneadmin.exception.TimezoneNotFoundException;
import com.petar.timezoneadmin.exception.TimezoneOwnershipChangeNotAllowedException;
import com.petar.timezoneadmin.jwt.security.JwtUserDetails;
import com.petar.timezoneadmin.model.Timezone;
import com.petar.timezoneadmin.model.User;
import com.petar.timezoneadmin.service.TimeZoneAdministrationService;

@RestController
@PreAuthorize("hasAuthority('admin_owned_timezones')")
@RequestMapping("/admin/timezones")

public class TimezoneAdministrationController {
  
	@Autowired
	private TimeZoneAdministrationService timezoneAdministrationService;
	
	@GetMapping
	public List<User> getAllTimezones(@AuthenticationPrincipal JwtUserDetails details) {
		return timezoneAdministrationService.getAllTimezones(details);
	}
	@GetMapping(value="/{timezoneId}", produces = APPLICATION_JSON_VALUE)
	public Timezone getTimezone(@PathVariable("timezoneId") Long timezoneId, @AuthenticationPrincipal JwtUserDetails details) throws TimezoneNotFoundException{
		return timezoneAdministrationService.getTimezone(timezoneId, details);
	}
	
	@PostMapping(consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
	public Timezone createTimezone(@RequestBody Timezone timezone, @AuthenticationPrincipal JwtUserDetails details) throws DuplicateTimezoneException {
		return timezoneAdministrationService.createTimezone(timezone, details);
	}
	
	@PutMapping(value="/{timezoneId}", produces = APPLICATION_JSON_VALUE)
	public Timezone updateTimezone(@PathVariable("timezoneId") Long timezoneId, @RequestBody Timezone timezone, @AuthenticationPrincipal JwtUserDetails details) throws DuplicateTimezoneException, TimezoneNotFoundException, TimezoneOwnershipChangeNotAllowedException  {
		return timezoneAdministrationService.updateTimezone(timezone, details);
	}
	
	@DeleteMapping("/{timezoneId}")
	public void deleteTimezone(@PathVariable("timezoneId") Long timezoneId, @AuthenticationPrincipal JwtUserDetails details) throws TimezoneNotFoundException {
		  timezoneAdministrationService.deleteTimezone(timezoneId, details);
	}
	
}
