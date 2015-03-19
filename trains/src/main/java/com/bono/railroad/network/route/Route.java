package com.bono.railroad.network.route;

import com.bono.railroad.network.station.Station;

public class Route implements Cloneable {

	private final Station origin;
	private final Station destination;
	private final double distance;

	public Route(Station origin, Station destination, double distance) {
		this.origin = origin;
		this.destination = destination;
		this.distance = distance;
	}

	public String toString() {
		return "{ origin: \"" + this.origin.getName() + "\" destination: \""
				+ this.destination.getName() + "\", distance: " + this.distance
				+ " }";
	}

	public Route clone() {
		return new Route(this.origin, this.destination, this.distance);
	}

	public Station getOrigin() {
		return origin;
	}

	public Station getDestination() {
		return destination;
	}

	public double getDistance() {
		return distance;
	}

}
