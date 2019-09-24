package com.petar.timezoneadmin.jwt.security;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.AbstractUserDetailsAuthenticationProvider;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import com.petar.timezoneadmin.model.JwtAuthenticationToken;
import com.petar.timezoneadmin.model.User;
@Component
public class JwtAuthenticationProvider extends AbstractUserDetailsAuthenticationProvider{

    @Autowired
    private JwtValidator validator;
	
	@Override
	public boolean supports(Class<?> argumentClass) {
		return JwtAuthenticationToken.class.isAssignableFrom(argumentClass);
	}

	@Override
	protected void additionalAuthenticationChecks(UserDetails userDetails,
			UsernamePasswordAuthenticationToken authentication) throws AuthenticationException {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected UserDetails retrieveUser(String username, UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken)
			throws AuthenticationException {
		JwtAuthenticationToken jwtAuthenticationToken = (JwtAuthenticationToken) usernamePasswordAuthenticationToken;
		String token = jwtAuthenticationToken.getToken();
		User jwtUser = validator.validate(token);	
		if (jwtUser == null) {
			throw new RuntimeException("The provided JWT Token is invalid");
		}
		List<GrantedAuthority> grantedAuthorities = AuthorityUtils.commaSeparatedStringToAuthorityList(jwtUser.getRole().getGrantedAuthorities());
       return new JwtUserDetails(jwtUser.getUserName(), jwtUser.getId(), token, grantedAuthorities);
		
	}

}
