package com.bono.railroad.network.station;

public class Station implements Cloneable {

	private final int id;
	private final String name;

	public Station(int id, String name) {
		this.id = id;
		this.name = name;
	}

	public String toString() {

		return "{ id: " + this.id + ", name: \"" + this.name + "\" }";
	}

	public Station clone() {
		return new Station(this.id, this.name);
	}

	public int getId() {
		return id;
	}

	public String getName() {
		return name;
	}

}
