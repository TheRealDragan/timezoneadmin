package com.petar.timezoneadmin.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException;
import com.petar.timezoneadmin.exception.DuplicateTimezoneException;
import com.petar.timezoneadmin.exception.TimezoneNotFoundException;
import com.petar.timezoneadmin.exception.TimezoneOwnershipChangeNotAllowedException;
import com.petar.timezoneadmin.jwt.security.JwtUserDetails;
import com.petar.timezoneadmin.mappers.TimeZoneMapper;
import com.petar.timezoneadmin.model.Timezone;
import com.petar.timezoneadmin.model.User;

@Service
public class TimeZoneAdministrationServiceImpl implements TimeZoneAdministrationService{
	
	@Autowired 
	private TimeZoneMapper timezoneMapper;

	@Override
	public List<User> getAllTimezones(JwtUserDetails details) {
		if (details.isAbleToAdminOnlyOwnedTimezones()) {
			return timezoneMapper.findTimezonesByUserId(details.getId());
		}
		if (details.isAbleToAdminAllTimezones()) {
			return timezoneMapper.findAllTimezones();
		}
		return new ArrayList<User>();
	}

	@Override
	public Timezone getTimezone(Long timezoneId, JwtUserDetails details) throws TimezoneNotFoundException {
		Timezone foundTimezone = timezoneMapper.findById(timezoneId);
		if (foundTimezone == null || !doesUserHaveAccessToThisRecord(details, foundTimezone)) {
			throw new TimezoneNotFoundException();
		}
		return  foundTimezone;
	}
	@Override
	@Transactional
	public Timezone createTimezone(Timezone timezone, JwtUserDetails details) throws DuplicateTimezoneException {
		if (timezoneMapper.findbyNameAndUserId(timezone.getName(), details.getId())!=null) {
			throw new DuplicateTimezoneException();
		}
		timezone.setUserId(details.getId());
		timezoneMapper.createTimezone(timezone);
		return timezoneMapper.findbyNameAndUserId(timezone.getName(), timezone.getUserId());
	}

	@Override
	@Transactional
	public Timezone updateTimezone(Timezone timezone, JwtUserDetails details) throws DuplicateTimezoneException, TimezoneNotFoundException, TimezoneOwnershipChangeNotAllowedException {
		Timezone foundTimezone = timezoneMapper.findById(timezone.getId());
		if (foundTimezone == null || !doesUserHaveAccessToThisRecord(details, foundTimezone)) {
			throw new TimezoneNotFoundException();
		}
		if (details.isAbleToAdminOnlyOwnedTimezones() && !timezone.getUserId().equals(details.getId())) {
			throw new TimezoneOwnershipChangeNotAllowedException();
		}
		try {
		timezoneMapper.updateTimezone(timezone);
		} catch (Exception e) {
			throw new DuplicateTimezoneException();
		}
		return timezoneMapper.findById(timezone.getId());
	}

	@Override
	public void deleteTimezone(Long timezoneId, JwtUserDetails details) throws TimezoneNotFoundException {
		Timezone foundTimezone = timezoneMapper.findById(timezoneId);
		if (foundTimezone == null || !doesUserHaveAccessToThisRecord(details, foundTimezone)) {
			throw new TimezoneNotFoundException();
		}
		timezoneMapper.deleteTimezone(foundTimezone);
	}	

	private boolean doesUserHaveAccessToThisRecord(JwtUserDetails details, Timezone foundTimezone) {
		return details.isAbleToAdminAllTimezones() || (details.isAbleToAdminOnlyOwnedTimezones() && details.getId().equals(foundTimezone.getUserId()));
	}	

}
