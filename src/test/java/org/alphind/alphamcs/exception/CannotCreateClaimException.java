package org.alphind.alphamcs.exception;

/** Copyright (C) 2023  Alphind Solution Software Pvt. Ltd. - All Rights Reserved.
 * 
 *  created by  Nandhalala, on date 15-SEPT-2023.
 *  
 *  You may use, distribute and modify this code for internal purpose,  however, distribution outside the organization     
 *  is prohibited without prior and proper license agreement
 *  
 */

public class CannotCreateClaimException extends RuntimeException{


	static final long serialVersionUID = 1L;

	public CannotCreateClaimException() {
		super();
	}

	public CannotCreateClaimException(String message, Throwable cause) {
		super(message, cause);
	}

	public CannotCreateClaimException(String message) {
		super(message);
	}

	
	
}
