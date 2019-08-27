package com.bridgelabz.utility;

import java.io.UnsupportedEncodingException;
import java.util.Date;

import org.springframework.stereotype.Component;

import io.jsonwebtoken.Jws;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

     @Component
   public class JWTTockenGenerator implements ITockenGenerator {

	String secret_Key = "yesicandoit";
	
	
	
	@Override
	public String generateTocken(String id) throws IllegalArgumentException, UnsupportedEncodingException {
	
		      String jwtTocken =Jwts.builder().setId(id).setSubject("fundooNotes")
				                .setExpiration(new Date(System.currentTimeMillis() + 1000000000))
				                .signWith(SignatureAlgorithm.HS256, secret_Key)
				                .compact();
		
		              return jwtTocken;
	}
	
	
	public String verifyTocken(String jwtTocken)
	  {
		Jws<io.jsonwebtoken.Claims> claims = Jwts.parser()
				                             .setSigningKey(secret_Key)
				                             .parseClaimsJws(jwtTocken);
		                    String userId  =  claims.getBody().getId();
				
		return userId;
	}


}

	



