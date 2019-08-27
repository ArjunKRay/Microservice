package com.bridgelabz.utility;

import java.io.UnsupportedEncodingException;

public interface ITockenGenerator {
	
	
      String generateTocken(String id) throws IllegalArgumentException,UnsupportedEncodingException;
	
      String verifyTocken( String tocken);
	 


}
