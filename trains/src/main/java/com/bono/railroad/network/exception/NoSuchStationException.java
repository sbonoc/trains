package com.bono.railroad.network.exception;

import com.bono.railroad.exception.RailroadRuntimeException;

public class NoSuchStationException extends RailroadRuntimeException {
	
	private static final long serialVersionUID = 6040445702457596557L;
	
	private static final String ERROR_CODE = "NO_SUCH_EXCEPTION";
	private static final String ERROR_MESSAGE = "NO SUCH STATION";

	public NoSuchStationException() {
		super(ERROR_CODE, ERROR_MESSAGE);
	}
	
	public NoSuchStationException(Throwable cause) {
		super(ERROR_CODE, ERROR_MESSAGE, cause);
	}

	public NoSuchStationException(Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(ERROR_CODE, ERROR_MESSAGE, cause, enableSuppression,
				writableStackTrace);
	}

}
