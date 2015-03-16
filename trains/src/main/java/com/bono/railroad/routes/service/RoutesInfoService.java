package com.bono.railroad.routes.service;

import com.bono.railroad.exception.RailroadRuntimeException;
import com.bono.railroad.network.RailNetwork;
import com.bono.railroad.network.station.Station;
import com.bono.railroad.routes.service.exception.NoSuchRouteException;

public class RoutesInfoService implements IRoutesInfoService {

	private RailNetwork railNetwork;

	public RoutesInfoService() {
		init();
	}

	private void init() {
		railNetwork = RailNetwork.getInstance();
	}

	public int getRouteDistance(String[] route) throws RailroadRuntimeException {

		if (route == null || route.length == 0)
			throw new NoSuchRouteException();

		int distance = 0;

		Station origin, destination;

		for (int i = 0; i < (route.length - 1); i++) {

			origin = railNetwork.getStation(route[i]);
			destination = railNetwork.getStation(route[i + 1]);

			distance += railNetwork.getRouteDistance(origin.getId(),
					destination.getId());

		}

		return distance;
	}

	public int getNumberOfTripsWithMaxStops(String origin, String destination,
			int maxStops) throws RailroadRuntimeException {

		return railNetwork.getNumberOfTripsWithMaxStops(origin,
				destination, maxStops);

	}

	public int getNumberOfTripsWithNumStops(String origin, String destination,
			int numStops) throws RailroadRuntimeException {

		return railNetwork.getNumberOfTripsWithNumStops(origin,
				destination, numStops);

	}

	public int getDistanceOfShortestPathBetween(String origin,
			String destination) {

		Station originStation = railNetwork.getStation(origin);
		Station destinationStation = railNetwork.getStation(destination);

		return railNetwork.getDistanceOfShortestPathBetween(
				originStation.getId(), destinationStation.getId());

	}

}
