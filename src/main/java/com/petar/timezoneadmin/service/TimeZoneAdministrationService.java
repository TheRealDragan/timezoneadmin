package com.petar.timezoneadmin.service;

import java.util.List;

import com.petar.timezoneadmin.exception.DuplicateTimezoneException;
import com.petar.timezoneadmin.exception.TimezoneNotFoundException;
import com.petar.timezoneadmin.exception.TimezoneOwnershipChangeNotAllowedException;
import com.petar.timezoneadmin.jwt.security.JwtUserDetails;
import com.petar.timezoneadmin.model.Timezone;
import com.petar.timezoneadmin.model.User;

public interface TimeZoneAdministrationService {

	List<User> getAllTimezones(JwtUserDetails details);

	Timezone getTimezone(Long timezoneId, JwtUserDetails details) throws TimezoneNotFoundException;

	Timezone createTimezone(Timezone timezone, JwtUserDetails details) throws DuplicateTimezoneException;

	Timezone updateTimezone(Timezone timezone, JwtUserDetails details) throws DuplicateTimezoneException, TimezoneNotFoundException, TimezoneOwnershipChangeNotAllowedException ;

	void deleteTimezone(Long timezoneId, JwtUserDetails details) throws TimezoneNotFoundException;

}
