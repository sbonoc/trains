package com.bono.railroad.network;

import java.util.Map;

import com.bono.railroad.network.exception.NoSuchStationException;
import com.bono.railroad.network.route.Route;
import com.bono.railroad.network.station.Station;
import com.bono.railroad.routes.service.RouteSearch;
import com.bono.railroad.routes.service.exception.NoSuchRouteException;

public class RailNetwork {

	private static RailNetwork _instance = null;

	private Map<String, Station> railNetwork = null;
	private Route[][] railNetworkMatrix = null;

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

	public Route getRoute(int idStationOrigin, int idStationDestination)
			throws NoSuchRouteException {

		Route result = null;

		try {

			result = railNetworkMatrix[idStationOrigin - 1][idStationDestination - 1];

		} catch (Exception e) {
			throw new NoSuchRouteException();
		}

		if (result == null) {
			throw new NoSuchRouteException();
		}

		return result;

	}

	public int getNumberOfTripsWithMaxStops(int idStationOrigin,
			int idStationDestination, int maxStops) {

		RouteSearch search = new RouteSearch(railNetworkMatrix);
		return search.getNumberOfTripsWithMaxStops(idStationOrigin,
				idStationDestination, maxStops);

	}

	public int getNumberOfTripsWithNumStops(int idStationOrigin,
			int idStationDestination, int numStops) {

		RouteSearch search = new RouteSearch(railNetworkMatrix);
		return search.getNumberOfTripsWithNumStops(idStationOrigin,
				idStationDestination, numStops);

	}

	public int getDistanceOfShortestPathBetween(int idStationOrigin,
			int idStationDestionation) {

		RouteSearch search = new RouteSearch(railNetworkMatrix);
		return search.getDistanceOfShortestPathBetween(idStationOrigin,
				idStationDestionation);

	}

}
