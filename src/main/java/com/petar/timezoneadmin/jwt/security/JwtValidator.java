package com.petar.timezoneadmin.jwt.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.petar.timezoneadmin.model.User;
import com.petar.timezoneadmin.model.UserRole;
import com.petar.timezoneadmin.utils.StatusChangeTimeByUser;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;

@Component
public class JwtValidator {
	
	@Value("${jwt.secretKey}")
	private String secretKey;	
	
	@Autowired
	private StatusChangeTimeByUser statusChangeTimeByUser;

	public User validate(String token) {
		 User jwtUser = null;
		 try {
			Claims body = Jwts.parser()
					            .setSigningKey(secretKey)
					            .parseClaimsJws(token)
					            .getBody();
		  jwtUser = new User();		  
		  jwtUser.setUsername(body.getSubject());
		  jwtUser.setId(Long.parseLong((String)body.get("userId")));
		  jwtUser.setRole(UserRole.valueOf((String)body.get("role")));
		  Long createdAt = Long.parseLong((String)body.get("createdAt"));
		  Long userModifiedAt = statusChangeTimeByUser.fetch(jwtUser.getId());
		  if (userModifiedAt > createdAt) {
			  jwtUser = null;
		  }
		 } catch (Exception e) {
		 }
		  
		  return jwtUser;
	}
}
