package com.bono.railroad.network.exception;

import com.bono.railroad.exception.RailroadRuntimeException;

public class RailNetworkInitalizationException extends RailroadRuntimeException {

	private static final long serialVersionUID = 4247096932583891875L;

	private static final String ERROR_CODE = "INIT_ERROR";
	private static final String ERROR_MESSAGE = "There was a problem initializing the rail network.";

	public RailNetworkInitalizationException() {
		super(ERROR_CODE, ERROR_MESSAGE);
	}

	public RailNetworkInitalizationException(Throwable cause) {
		super(ERROR_CODE, ERROR_MESSAGE, cause);
	}

	public RailNetworkInitalizationException(Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(ERROR_CODE, ERROR_MESSAGE, cause, enableSuppression,
				writableStackTrace);
	}

}
