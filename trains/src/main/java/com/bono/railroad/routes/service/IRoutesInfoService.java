package com.bono.railroad.routes.service;

import com.bono.railroad.exception.RailroadRuntimeException;

public interface IRoutesInfoService {
	
	public int getRouteDistance(String[] route) throws RailroadRuntimeException;
	public int getNumberOfTripsWithMaxStops(String origin, String destination, int maxStops) throws RailroadRuntimeException;
	public int getNumberOfTripsWithNumStops(String origin, String destination, int stops) throws RailroadRuntimeException;
	public int getDistanceOfShortestPathBetween(String origin, String destination) throws RailroadRuntimeException;
	
}
