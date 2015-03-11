package com.bono.railroad.routes.service;

import com.bono.railroad.routes.exception.RoutesException;

public interface IRoutesInfoService {
	
	public int getRouteDistance(String[] route) throws RoutesException;
	public int getNumberOfTripsWithMaxStops(int maxStops) throws RoutesException;
	public int getNumberOfTripsWithStops(int stops) throws RoutesException;
	
}
