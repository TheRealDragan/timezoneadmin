package com.petar.timezoneadmin.controller;



import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.petar.timezoneadmin.exception.DuplicateTimezoneException;
import com.petar.timezoneadmin.exception.TimezoneNotFoundException;
import com.petar.timezoneadmin.exception.TimezoneOwnershipChangeNotAllowedException;
import com.petar.timezoneadmin.exception.UsernameTakenException;


@RestControllerAdvice
public class ExceptionController {
	@ExceptionHandler(UsernameTakenException.class)
	public ResponseEntity<?> errorHandler(UsernameTakenException exception) {
		return new ResponseEntity<Object>(exception.getMessage(), HttpStatus.CONFLICT);
	}
	@ExceptionHandler(BadCredentialsException.class)
	public ResponseEntity<?> errorHandler(BadCredentialsException exception) {
		return new ResponseEntity<Object>(exception.getMessage(), HttpStatus.BAD_REQUEST);
	}
	@ExceptionHandler(UsernameNotFoundException.class)
	public ResponseEntity<?> errorHandler(UsernameNotFoundException exception) {
		return new ResponseEntity<Object>(exception.getMessage(), HttpStatus.BAD_REQUEST);
	}	
	@ExceptionHandler(TimezoneNotFoundException.class)
	public ResponseEntity<?> errorHandler(TimezoneNotFoundException exception) {
		return new ResponseEntity<Object>(exception.getMessage(), HttpStatus.BAD_REQUEST);
	}	
	
	@ExceptionHandler(TimezoneOwnershipChangeNotAllowedException.class)
	public ResponseEntity<?> errorHandler(TimezoneOwnershipChangeNotAllowedException exception) {
		return new ResponseEntity<Object>(exception.getMessage(), HttpStatus.BAD_REQUEST);
	}	
	
	@ExceptionHandler(DuplicateTimezoneException.class)
	public ResponseEntity<?> errorHandler(DuplicateTimezoneException exception) {
		return new ResponseEntity<Object>(exception.getMessage(), HttpStatus.CONFLICT);
	}	
}
