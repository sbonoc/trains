package com.bono.railroad.network;

import java.util.Map;

import com.bono.graphs.Graph;
import com.bono.railroad.network.exception.NoSuchStationException;
import com.bono.railroad.network.station.Station;
import com.bono.railroad.routes.service.RouteSearch;
import com.bono.railroad.routes.service.exception.NoSuchRouteException;

public class RailNetwork {

	private static RailNetwork _instance = null;

	private Graph railNetworkGraph = null;
	private Map<String, Station> railNetwork = null;
	private int[][] railNetworkMatrix = null;

	private RailNetwork() {
		init();
	}

	public static synchronized RailNetwork getInstance() {

		if (_instance == null) {
			_instance = new RailNetwork();
		}

		return _instance;
	}

	private void init() {
		loadRailNetworkMap();
	}

	private void loadRailNetworkMap() {

		RailNetworkLoader loader = new RailNetworkLoader();
		railNetworkGraph = loader.getRailNetworkGraph();
		railNetwork = loader.getRailNetwork();
		railNetworkMatrix = loader.getRailNetworkMatrix();

	}

	public boolean existsStation(String name) {
		return this.railNetwork.containsKey(name);
	}

	public Station getStation(String name) throws NoSuchStationException {

		if (!existsStation(name) || this.railNetwork.get(name) == null) {
			throw new NoSuchStationException();
		}

		return this.railNetwork.get(name);

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
		return search.getDistanceOfShortestPathBetween(idStationOrigin,
				idStationDestionation);

	}

}
