package com.bono.graphs;

import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.Set;

public class Graph implements Cloneable {

	private Map<String, LinkedHashSet<Edge>> map = new HashMap<String, LinkedHashSet<Edge>>();

	public void addEdge(String origin, String destination, int distance) {
		LinkedHashSet<Edge> adjacent = map.get(origin);
		if (adjacent == null) {
			adjacent = new LinkedHashSet<Edge>();
			map.put(origin, adjacent);
		}
		adjacent.add(new Edge(destination, distance));
	}

	public void addTwoWayVertex(String origin, String destination, int distance) {
		addEdge(origin, destination, distance);
		addEdge(destination, origin, distance);
	}

	public boolean isConnected(String origin, String destination) {
		Set<Edge> adjacent = map.get(origin);
		if (adjacent == null) {
			return false;
		}
		return adjacent.contains(destination);
	}

	public LinkedList<Edge> adjacentNodes(String last) {
		LinkedHashSet<Edge> adjacent = map.get(last);
		if (adjacent == null) {
			return new LinkedList<Edge>();
		}
		return new LinkedList<Edge>(adjacent);
	}

	public Graph clone() {

		Graph clone = new Graph();
		for (String key : this.map.keySet()) {
			LinkedHashSet<Edge> edges = this.map.get(key);
			for (Edge edge : edges) {
				clone.addEdge(key, edge.getDestination(), edge.getDistance());
			}
		}

		return clone;
	}

}