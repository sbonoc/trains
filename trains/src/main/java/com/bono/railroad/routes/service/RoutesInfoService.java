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

			distance += railNetwork.getRoute(origin.getId(),
					destination.getId()).getDistance();

		}

		return distance;
	}

	public int getNumberOfTripsWithMaxStops(String origin, String destination,
			int maxStops) throws RailroadRuntimeException {

		Station originStation = railNetwork.getStation(origin);
		Station destinationStation = railNetwork.getStation(destination);

		return railNetwork.getNumberOfTripsWithMaxStops(originStation.getId(),
				destinationStation.getId(), maxStops);

	}

	public int getNumberOfTripsWithNumStops(String origin, String destination,
			int numStops) throws RailroadRuntimeException {

		Station originStation = railNetwork.getStation(origin);
		Station destinationStation = railNetwork.getStation(destination);

		return railNetwork.getNumberOfTripsWithNumStops(originStation.getId(),
				destinationStation.getId(), numStops);

	}

	public int getDistanceOfShortestPathBetween(String origin,
			String destination) {

		Station originStation = railNetwork.getStation(origin);
		Station destinationStation = railNetwork.getStation(destination);

		return railNetwork.getDistanceOfShortestPathBetween(
				originStation.getId(), destinationStation.getId());

	}

}
