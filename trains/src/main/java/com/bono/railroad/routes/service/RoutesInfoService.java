package com.bono.railroad.routes.service;

import com.bono.railroad.exception.RailroadRuntimeException;
import com.bono.railroad.network.service.IRailNetworkSearchLocalService;
import com.bono.railroad.network.service.RailNetworkSearchLocalService;
import com.bono.railroad.network.station.Station;
import com.bono.railroad.routes.service.exception.NoSuchRouteException;

public class RoutesInfoService implements IRoutesInfoService {

	private IRailNetworkSearchLocalService railNetworkSearchService;

	public RoutesInfoService() {
		railNetworkSearchService = new RailNetworkSearchLocalService();
	}

	public int getRouteDistance(String[] route) throws RailroadRuntimeException {

		if (route == null || route.length == 0)
			throw new NoSuchRouteException();

		int distance = 0;

		Station origin, destination;

		for (int i = 0; i < (route.length - 1); i++) {

			origin = railNetworkSearchService.getStation(route[i]);
			destination = railNetworkSearchService.getStation(route[i + 1]);

			distance += railNetworkSearchService.getRouteDistance(origin.getId(),
					destination.getId());

		}

		return distance;
	}

	public int getNumberOfTripsWithMaxStops(String origin, String destination,
			int maxStops) throws RailroadRuntimeException {

		return railNetworkSearchService.getNumberOfTripsWithMaxStops(origin, destination,
				maxStops);

	}

	public int getNumberOfTripsWithNumStops(String origin, String destination,
			int numStops) throws RailroadRuntimeException {

		return railNetworkSearchService.getNumberOfTripsWithNumStops(origin, destination,
				numStops);

	}

	public int getDistanceOfShortestPathBetween(String origin,
			String destination) {

		Station originStation = railNetworkSearchService.getStation(origin);
		Station destinationStation = railNetworkSearchService.getStation(destination);

		return railNetworkSearchService.getDistanceOfShortestPathBetween(
				originStation.getId(), destinationStation.getId());

	}

	public int getNumberOfTripsWithMaxDistance(String origin,
			String destination, int maxDistance)
			throws RailroadRuntimeException {

		return railNetworkSearchService.getNumberOfTripsWithMaxDistance(origin, destination,
				maxDistance);
	}

}
