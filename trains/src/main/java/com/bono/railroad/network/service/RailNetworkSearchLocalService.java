package com.bono.railroad.network.service;

import com.bono.graphs.Graph;
import com.bono.railroad.network.RailNetwork;
import com.bono.railroad.network.exception.NoSuchStationException;
import com.bono.railroad.network.search.RouteSearch;
import com.bono.railroad.network.station.Station;
import com.bono.railroad.routes.service.exception.NoSuchRouteException;

public class RailNetworkSearchLocalService implements
		IRailNetworkSearchLocalService {

	private final Graph railNetworkGraph;
	private final int[][] railNetworkMatrix;

	public RailNetworkSearchLocalService() {
		this.railNetworkGraph = RailNetwork.getInstance().getRailNetworkGraph();
		this.railNetworkMatrix = RailNetwork.getInstance()
				.getRailNetworkMatrix();
	}

	public Station getStation(String name) throws NoSuchStationException {

		return RailNetwork.getInstance().getStation(name);

	}

	public int getRouteDistance(int idStationOrigin, int idStationDestination)
			throws NoSuchRouteException {

		int result = -1;

		try {

			result = railNetworkMatrix[idStationOrigin - 1][idStationDestination - 1];

		} catch (Exception e) {
			throw new NoSuchRouteException();
		}

		if (result == -1) {
			throw new NoSuchRouteException();
		}

		return result;

	}

	public int getNumberOfTripsWithMaxStops(String origin, String destination,
			int maxStops) {

		RouteSearch search = new RouteSearch(railNetworkGraph,
				railNetworkMatrix);
		return search.getNumberOfTripsWithMaxStops(origin, destination,
				maxStops);

	}

	public int getNumberOfTripsWithNumStops(String origin, String destination,
			int numStops) {

		RouteSearch search = new RouteSearch(railNetworkGraph,
				railNetworkMatrix);
		return search.getNumberOfTripsWithNumStops(origin, destination,
				numStops);

	}

	public int getDistanceOfShortestPathBetween(int idStationOrigin,
			int idStationDestionation) {

		RouteSearch search = new RouteSearch(railNetworkGraph,
				railNetworkMatrix);
		return search.getDistanceOfShortestRouteBetween(idStationOrigin,
				idStationDestionation);

	}

	public int getNumberOfTripsWithMaxDistance(String origin,
			String destination, int distance) {

		RouteSearch search = new RouteSearch(railNetworkGraph,
				railNetworkMatrix);
		return search.getNumberOfTripsWithMaxDistance(origin, destination,
				distance);

	}

}
