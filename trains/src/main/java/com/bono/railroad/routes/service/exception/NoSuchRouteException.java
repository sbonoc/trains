package com.bono.railroad.routes.service.exception;

import com.bono.railroad.exception.RailroadRuntimeException;

public class NoSuchRouteException extends RailroadRuntimeException {

	private static final long serialVersionUID = -9155685702963272079L;
	
	private static final String ERROR_CODE = "NO_SUCH_ROUTE";
	private static final String ERROR_MESSAGE = "NO SUCH ROUTE";
	
	public NoSuchRouteException() {
		super(ERROR_CODE, ERROR_MESSAGE);
	}

}
