package com.bono.railroad.routes.exception;

public class RoutesException extends Exception implements IRoutesException{

	private static final long serialVersionUID = -7010295335558944662L;

	public RoutesException(String message) {
		super(message);
	}
}
