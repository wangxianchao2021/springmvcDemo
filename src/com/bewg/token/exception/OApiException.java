package com.bewg.token.exception;

public class OApiException extends Exception {

	private static final long serialVersionUID = 7938128848275708081L;

	public OApiException(int errCode, String errMsg) {
		super("error code: " + errCode + ", error message: " + errMsg);
	}
}
