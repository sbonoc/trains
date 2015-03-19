package com.bono.railroad.network.service;

import com.bono.railroad.exception.RailroadRuntimeException;
import com.bono.railroad.network.station.Station;

public interface IRailNetworkSearchLocalService {

	public Station getStation(String name) throws RailroadRuntimeException;

	public int getRouteDistance(int idStationOrigin, int idStationDestination)
			throws RailroadRuntimeException;

	public int getNumberOfTripsWithMaxStops(String origin, String destination,
			int maxStops) throws RailroadRuntimeException;

	public int getNumberOfTripsWithNumStops(String origin, String destination,
			int numStops) throws RailroadRuntimeException;

	public int getDistanceOfShortestPathBetween(int idStationOrigin,
			int idStationDestionation) throws RailroadRuntimeException;

	public int getNumberOfTripsWithMaxDistance(String origin,
			String destination, int distance) throws RailroadRuntimeException;

}
