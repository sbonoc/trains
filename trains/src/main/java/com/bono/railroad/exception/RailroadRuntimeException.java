package com.bono.railroad.exception;

import java.util.UUID;

public class RailroadRuntimeException extends RuntimeException {

	private static final long serialVersionUID = -3564503347035135838L;

	private final UUID uuid = UUID.randomUUID();
	
	private final String errorCode;
	private final String errorMessage;

	public RailroadRuntimeException(String errorCode, String errorMessage) {
		super(errorMessage);
		this.errorCode = errorCode;
		this.errorMessage = errorMessage;
	}

	public RailroadRuntimeException(String errorCode, String errorMessage, Throwable cause) {
		super(errorMessage, cause);
		this.errorCode = errorCode;
		this.errorMessage = errorMessage;
	}

	public RailroadRuntimeException(String errorCode, String errorMessage, Throwable cause,
			boolean enableSuppression, boolean writableStackTrace) {
		super(errorMessage, cause, enableSuppression, writableStackTrace);
		this.errorCode = errorCode;
		this.errorMessage = errorMessage;
	}

	public UUID getUuid() {
		return uuid;
	}

	public String getErrorCode() {
		return errorCode;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	

}
