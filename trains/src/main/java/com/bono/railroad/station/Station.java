package com.bono.railroad.station;

import com.bono.railroad.route.Route;

public class Station implements Cloneable, Comparable<Station> {

	private final String name;

	private Route[] routes;
	private Station previous;
	private double minDistance;

	public Station(String name) {
		this.name = name;
		this.minDistance = Double.POSITIVE_INFINITY;
	}

	public int compareTo(Station other) {
		return Double.compare(this.minDistance, other.getMinDistance());
	}

	public String toString() {

		String result = "station: { name: " + this.name + ", routes: [";

		for (Route route : routes) {
			result += route.toString();
		}

		result += "] }";

		return result;
	}

	public Route[] getRoutes() {
		return routes;
	}

	public void setRoutes(Route[] routes) {
		this.routes = routes;
	}

	public Station getPrevious() {
		return previous;
	}

	public void setPrevious(Station previous) {
		this.previous = previous;
	}

	public double getMinDistance() {
		return minDistance;
	}

	public void setMinDistance(double minDistance) {
		this.minDistance = minDistance;
	}

	public String getName() {
		return name;
	}

	public Station clone() {
		Station cloned = new Station(this.name);
		cloned.setMinDistance(this.minDistance);
		cloned.setPrevious(this.previous.clone());
		cloned.setRoutes(this.routes.clone());
		return cloned;
	}

}
