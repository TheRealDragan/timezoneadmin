package com.petar.timezoneadmin.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.petar.timezoneadmin.model.User;
@Component
public class JwtUserValidator implements Validator{

	@Override
	public boolean supports(Class<?> aClass) {
		return User.class.equals(aClass);
	}

	@Override
	public void validate(Object input, Errors errors) {
		User jwtUser = (User) input;
		if (jwtUser.getPassword()==null || jwtUser.getPassword().length()<5) {
			errors.rejectValue("password", "invalidPassword", new Object[]{"'password'"}, "The required password length is between 5 and 15 characters");
		}
		
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "username", "username.required");
			}

}
