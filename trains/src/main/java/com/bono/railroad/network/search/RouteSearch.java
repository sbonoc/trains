package com.bono.railroad.network.search;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import com.bono.graphs.Edge;
import com.bono.graphs.Graph;
import com.bono.graphs.algorithms.BFS;
import com.bono.graphs.algorithms.Dijkstra;

public class RouteSearch {

	private final Graph railNetworkGraph;
	private final int[][] railNetworkMatrix;

	public RouteSearch(Graph railNetworkGraph, int[][] railNetworkMatrix) {

		this.railNetworkGraph = railNetworkGraph.clone();
		this.railNetworkMatrix = Arrays.copyOf(railNetworkMatrix,
				railNetworkMatrix.length);

	}

	public int getNumberOfTripsWithNumStops(String origin, String destination,
			int stops) {

		int result = 0;

		List<LinkedList<Edge>> trips = this.getAllPossibleRoutes(origin,
				destination, stops);

		for (LinkedList<Edge> edge : trips) {
			if (edge.size() == (stops + 1)) {
				result++;
			}
		}

		return result;

	}

	public int getNumberOfTripsWithMaxStops(String origin, String destination,
			int maxStops) {

		return this.getAllPossibleRoutes(origin, destination, maxStops).size();
	}
	
	public int getNumberOfTripsWithMaxDistance(String origin, String destination,
			int distance) {
		
		List<LinkedList<Edge>> result = new ArrayList<LinkedList<Edge>>();
		LinkedList<Edge> visited = new LinkedList<Edge>();
		visited.add(new Edge(origin, 0));
		
		BFS.limitedDistanceBFS(railNetworkGraph, visited, destination, distance, 0, result);

		System.out.println("RESULT ==> ");

		for (LinkedList<Edge> route : result) {
			BFS.printPath(route, distance, -1);
		}

		return result.size();
		
	}

	public int getDistanceOfShortestPathBetween(int originStationId,
			int destinationStationId) {

		return Dijkstra.dijkstra(railNetworkMatrix, originStationId - 1,
				destinationStationId - 1);

	}

	private List<LinkedList<Edge>> getAllPossibleRoutes(String origin,
			String destination, int stops) {

		List<LinkedList<Edge>> result = new ArrayList<LinkedList<Edge>>();
		LinkedList<Edge> visited = new LinkedList<Edge>();
		visited.add(new Edge(origin, 0));

		BFS.limitedDepthBFS(railNetworkGraph, visited, destination, stops, 0, result);

		System.out.println("RESULT ==> ");

		for (LinkedList<Edge> route : result) {
			BFS.printPath(route, stops, -1);
		}

		return result;

	}

}
