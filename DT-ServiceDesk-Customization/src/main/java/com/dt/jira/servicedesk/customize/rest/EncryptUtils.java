package com.dt.jira.servicedesk.customize.rest;

import  org.apache.commons.codec.binary.Base64;
/**
 * Class for encrypt and decrypt of password to store.
 * only used for configuration screen.
 * @author srinadh.garlapati
 *
 */
public class EncryptUtils {

	
		public static String encodeLDAPDetails(String encode){
	    	 byte[] encoded = Base64.encodeBase64(encode.getBytes()); 
	    	 //System.out.println("encoded:   "+new String(encoded));
	    	 return  new String(encoded);
	    }
	    
	    public static String decodeLDAPDetails(String decode){
	    	 byte[] decoded = Base64.decodeBase64(decode);
	    	 //System.out.println("decoded:   "+new String(decoded));
	    	 return  new String(decoded);
	    }
	    
	}


	


