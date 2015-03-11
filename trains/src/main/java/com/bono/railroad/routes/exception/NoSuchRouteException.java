package com.bono.railroad.routes.exception;

public class NoSuchRouteException extends RoutesException {

	private static final long serialVersionUID = -9155685702963272079L;
	
	private static final String EXCEPTION_MESSAGE = "NO SUCH ROUTE";
	
	public NoSuchRouteException() {
		super(EXCEPTION_MESSAGE);
	}

}
