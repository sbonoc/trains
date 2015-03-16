package com.bono.graphs;

public class Edge {

	private final String destination;
	private final int distance;

	public Edge(String destination, int distance) {
		this.destination = destination;
		this.distance = distance;
	}

	public String getDestination() {
		return destination;
	}

	public int getDistance() {
		return distance;
	}

}