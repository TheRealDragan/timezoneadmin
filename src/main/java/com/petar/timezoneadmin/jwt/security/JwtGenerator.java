package com.petar.timezoneadmin.jwt.security;

import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.petar.timezoneadmin.model.User;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
@Component
public class JwtGenerator {
	
	@Value("${jwt.secretKey}")
	private String secretKey;
	
	@Value("${jwt.duration}")
	private Long jwtDuration;


	public String generate(User jwtUser) {
		Claims claims = Jwts.claims()
							.setSubject(jwtUser.getUserName());
		claims.put("userId", String.valueOf(jwtUser.getId()));
		claims.put("role", jwtUser.getRole());
		claims.put("createdAt", Long.toString(new Date().getTime()));
		
		return Jwts.builder()
				   .setClaims(claims)
 				   .setExpiration(new Date(new Date().getTime()+jwtDuration))
			       .signWith(SignatureAlgorithm.HS512, secretKey)
			       .compact();							
	}
	
	public void setJwtDuration(Long duration) {
		this.jwtDuration = duration;
	}
}
