package org.alphind.alphamcs.exception;

/** Copyright (C) 2023  Alphind Solution Software Pvt. Ltd. - All Rights Reserved.
 * 
 *  created by  Nandhalala, on date 22-SEPT-2023.
 *  
 *  You may use, distribute and modify this code for internal purpose,  however, distribution outside the organization     
 *  is prohibited without prior and proper license agreement
 *  
 */

public class CannotUpdateClaimException extends RuntimeException{


	static final long serialVersionUID = 1L;

	public CannotUpdateClaimException() {
		super();
	}

	public CannotUpdateClaimException(String message, Throwable cause) {
		super(message, cause);
	}

	public CannotUpdateClaimException(String message) {
		super(message);
	}
}
