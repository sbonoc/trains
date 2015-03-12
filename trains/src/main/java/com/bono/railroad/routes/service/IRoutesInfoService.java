package com.bono.railroad.routes.service;

import com.bono.railroad.routes.exception.RoutesException;

public interface IRoutesInfoService {
	
	public int getRouteDistance(String[] route) throws RoutesException;
	public int getNumberOfTripsWithMaxStops(String origin, String destination, int maxStops) throws RoutesException;
	public int getNumberOfTripsWithStops(String origin, String destination, int stops) throws RoutesException;
	
}
