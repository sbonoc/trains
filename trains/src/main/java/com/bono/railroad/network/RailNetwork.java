package com.bono.railroad.network;

import java.util.Map;

import com.bono.graphs.Graph;
import com.bono.railroad.network.exception.NoSuchStationException;
import com.bono.railroad.network.station.Station;

public class RailNetwork {

	private static RailNetwork _instance = null;

	private Graph railNetworkGraph = null;
	private Map<String, Station> railNetwork = null;
	private int[][] railNetworkMatrix = null;

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
		railNetworkGraph = loader.getRailNetworkGraph();
		railNetwork = loader.getRailNetwork();
		railNetworkMatrix = loader.getRailNetworkMatrix();

	}

	private boolean existsStation(String name) {
		return this.railNetwork.containsKey(name);
	}

	public Station getStation(String name) throws NoSuchStationException {

		if (!existsStation(name) || this.railNetwork.get(name) == null) {
			throw new NoSuchStationException();
		}

		return this.railNetwork.get(name);

	}

	public Graph getRailNetworkGraph() {
		return railNetworkGraph;
	}

	public int[][] getRailNetworkMatrix() {
		return railNetworkMatrix;
	}

}
