package com.bono.railroad.routes.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

import com.bono.railroad.route.Route;
import com.bono.railroad.routes.exception.NoSuchRouteException;
import com.bono.railroad.routes.exception.RoutesException;
import com.bono.railroad.station.Station;

public class RoutesInfoService implements IRoutesInfoService {

	private Map<String, Station> stations = null;

	public RoutesInfoService() {
		init();
	}

	private void init() {

		stations = setupStations();
	}

	private Map<String,Station> setupStations() {

		Map<String,Station> result = new HashMap<String,Station>();

		Station A = new Station("A");
		Station B = new Station("B");
		Station C = new Station("C");
		Station D = new Station("D");
		Station E = new Station("E");

		A.setRoutes(new Route[] { new Route(B, 5), new Route(D, 5),
				new Route(E, 7) });

		B.setRoutes(new Route[] { new Route(C, 4) });

		C.setRoutes(new Route[] { new Route(D, 8), new Route(E, 2) });

		D.setRoutes(new Route[] { new Route(C, 8), new Route(E, 6) });

		E.setRoutes(new Route[] { new Route(B, 3) });

		result.put("A", A);
		result.put("B", B);
		result.put("C", C);
		result.put("D", D);
		result.put("E", E);

		return result;

	}

	private void setupRoutes(Station source) {

		if (source != null) {

			source.setMinDistance(0);
			PriorityQueue<Station> stationQueue = new PriorityQueue<Station>();

			stationQueue.add(source);

			while (!stationQueue.isEmpty()) {

				Station current = stationQueue.poll();

				for (Route route : current.getRoutes()) {

					Station destination = route.getDestination();
					double distance = route.getDistance();

					double distanceThroughCurrent = distance
							+ current.getMinDistance();

					if (distanceThroughCurrent < destination.getMinDistance()) {

						stationQueue.remove(destination);
						destination.setMinDistance(distanceThroughCurrent);
						destination.setPrevious(current);
						stationQueue.add(destination);

					}

				}

			}

		}
	}
	
	private void resetRoutes(Station source) {
		
		for(Station station = source ; station != null ; station = station.getPrevious()) {
			station.setMinDistance(Double.POSITIVE_INFINITY);
		}
		
	}
	
	private List<Station> getShortestRoute(Station destination) {
		
		List<Station> route = new ArrayList<Station>();
		for(Station station = destination ; station != null ; station = station.getPrevious()) {
			route.add(station);
		}
		Collections.reverse(route);
		return route;
		
	}

	public int getRouteDistance(String[] route) throws RoutesException {
		
		if(route == null || route.length == 0)
			throw new NoSuchRouteException();
		
		int distance = 0;
		
		for(int i=0 ; i<(route.length -1) ; i++) {
			
			if(!stations.containsKey(route[i]) || !stations.containsKey(route[i+1]))
				throw new NoSuchRouteException();
			
			Station origin = stations.get(route[i]);
			Station destination = stations.get(route[i+1]);
			
			setupRoutes(origin);
			
			for(Station station : getShortestRoute(destination)) {
				distance += station.getMinDistance();
			}
			
			resetRoutes(origin);
			
		}
		
		return distance;
	}

	public int getNumberOfTripsWithMaxStops(int maxStops)
			throws RoutesException {
		// TODO Auto-generated method stub
		return 0;
	}

	public int getNumberOfTripsWithStops(int stops) throws RoutesException {
		// TODO Auto-generated method stub
		return 0;
	}

}
