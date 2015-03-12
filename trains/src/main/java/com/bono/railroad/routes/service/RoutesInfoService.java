package com.bono.railroad.routes.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

import com.bono.graphs.Graph;
import com.bono.graphs.Vertex;
import com.bono.railroad.route.Route;
import com.bono.railroad.routes.exception.NoSuchRouteException;
import com.bono.railroad.routes.exception.RoutesException;
import com.bono.railroad.station.Station;

public class RoutesInfoService implements IRoutesInfoService {

	private Map<String, Station> stationsMap = null;
	private List<Station> stationsList = null;
	
	private Graph<Station> graph = null;

	public RoutesInfoService() {
		init();
	}

	private void init() {
		
		stationsMap = new HashMap<String,Station>();
		stationsList = new ArrayList<Station>();

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

		stationsMap.put("A", A);
		stationsMap.put("B", B);
		stationsMap.put("C", C);
		stationsMap.put("D", D);
		stationsMap.put("E", E);
		
		stationsList.add(A);
		stationsList.add(B);
		stationsList.add(C);
		stationsList.add(D);
		stationsList.add(E);
		
		graph = new Graph<Station>();
		
		Vertex<Station> vA = new Vertex<Station>("A", A);
		Vertex<Station> vB = new Vertex<Station>("B", B);
		Vertex<Station> vC = new Vertex<Station>("C", C);
		Vertex<Station> vD = new Vertex<Station>("D", D);
		Vertex<Station> vE = new Vertex<Station>("E", E);
		
		vA.addOutgoingEdge(vB, 5);
		vA.addOutgoingEdge(vD, 5);
		vA.addOutgoingEdge(vE, 7);
		
		vB.addOutgoingEdge(vC, 4);
		
		vC.addOutgoingEdge(vD, 8);
		vC.addOutgoingEdge(vE, 2);
		
		vD.addOutgoingEdge(vC, 8);
		vD.addOutgoingEdge(vE, 6);
		
		vE.addOutgoingEdge(vB, 3);
		vE.addOutgoingEdge(vA, 7);
		
		graph.addVertex(vA);
		graph.addVertex(vB);
		graph.addVertex(vC);
		graph.addVertex(vD);
		graph.addVertex(vE);
		
	}

	private Station setupRoutes(Station source) {
		
		Station result = null;

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
		return result;
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
			
			if(!stationsMap.containsKey(route[i]) || !stationsMap.containsKey(route[i+1]))
				throw new NoSuchRouteException();
			
			Station origin = stationsMap.get(route[i]);
			Station destination = stationsMap.get(route[i+1]);
			
			int routeDistance = 0;
			for(Route currentRoute : origin.getRoutes()) {
				if(currentRoute.getDestination().getName().equalsIgnoreCase(destination.getName())) {
					routeDistance += currentRoute.getDistance();
				}
			}
			
			if(routeDistance == 0) {
				throw new NoSuchRouteException();
			}
			
			distance += routeDistance;
			
		}
		
		return distance;
	}

	public int getNumberOfTripsWithMaxStops(String origin, String destination,
			int maxStops) throws RoutesException {
		
		if(!stationsMap.containsKey(origin) || !stationsMap.containsKey(destination))
			throw new NoSuchRouteException();
		
		int result = 0;
		
		Station originStation = stationsMap.get(origin);
		
		int stops = 0;
		
		while(stops <= maxStops) {
			//TODO
			stops++;
		}
		
		return result;
	}

	public int getNumberOfTripsWithStops(String origin, String destination,
			int stops) throws RoutesException {
		
		// TODO Auto-generated method stub
		return 0;
	}
	
	

}
