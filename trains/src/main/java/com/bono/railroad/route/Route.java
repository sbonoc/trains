package com.bono.railroad.route;

import com.bono.railroad.station.Station;

public class Route implements Cloneable {

	private final Station destination;
	private final double distance;

	public Route(Station destination, double distance) {
		this.destination = destination;
		this.distance = distance;
	}

	public String toString() {
		return "station: { destination: \"" + this.destination.getName()
				+ "\", distance: " + this.distance + " }";
	}

	public Station getDestination() {
		return destination;
	}

	public double getDistance() {
		return distance;
	}

	public Route clone() {
		return new Route(this.destination, this.distance);
	}

}
