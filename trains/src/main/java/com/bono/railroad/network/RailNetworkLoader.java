package com.bono.railroad.network;

import java.io.IOException;
import java.text.MessageFormat;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Properties;

import com.bono.graphs.Graph;
import com.bono.railroad.network.exception.RailNetworkInitalizationException;
import com.bono.railroad.network.station.Station;

public class RailNetworkLoader {

	public static String CONFIG_PATH = "config/config.properties";

	public static String CONFIG_STATION_TOTAL_KEY = "rail.network.station.total";
	public static String CONFIG_STATION_ID_FORMAT_KEY = "rail.network.station.{0}.id";
	public static String CONFIG_STATION_NAME_FORMAT_KEY = "rail.network.station.{0}.name";

	public static String CONFIG_ROUTE_TOTAL_KEY = "rail.network.route.total";
	public static String CONFIG_ROUTE_FROM_FORMAT_KEY = "rail.network.route.{0}.from";
	public static String CONFIG_ROUTE_TO_FORMAT_KEY = "rail.network.route.{0}.to";
	public static String CONFIG_ROUTE_DISTANCE_FORMAT_KEY = "rail.network.route.{0}.distance";

	private Properties config;

	private Graph railNetworkGraph;
	private Map<String, Station> railNetwork;
	private int[][] railNetworkMatrix;

	public RailNetworkLoader() {
		init();
	}

	private void init() {
		config = new Properties();
		try {
			config.load(this.getClass().getClassLoader()
					.getResourceAsStream(CONFIG_PATH));
			loadRailNetwork();
		} catch (IOException e) {
			throw new RailNetworkInitalizationException(e);
		}
	}

	private void loadRailNetwork() {

		System.out.println("Loading RailNetwork from: "
				+ this.getClass().getClassLoader().getResource(CONFIG_PATH)
						.getPath());

		try {

			railNetworkGraph = new Graph();
			railNetwork = new HashMap<String, Station>();

			Map<Integer, Station> auxMap = new HashMap<Integer, Station>();

			int totalStations = Integer.parseInt(config
					.getProperty(CONFIG_STATION_TOTAL_KEY));

			railNetworkMatrix = new int[totalStations][totalStations];

			for (int i = 0; i < totalStations; i++) {
				for (int j = 0; j < totalStations; j++) {
					if (i == j) {
						railNetworkMatrix[i][j] = 0;
					} else {
						railNetworkMatrix[i][j] = -1;
					}
				}
			}

			int id;
			String name;

			Station auxStation;

			for (int i = 0; i < totalStations; i++) {

				id = Integer.parseInt(config.getProperty(
						MessageFormat.format(CONFIG_STATION_ID_FORMAT_KEY, i))
						.trim());
				name = config.getProperty(MessageFormat.format(
						CONFIG_STATION_NAME_FORMAT_KEY, i));

				auxStation = new Station(id, name);

				railNetwork.put(name, auxStation);
				auxMap.put(id, auxStation);

			}

			int totalRoutes = Integer.parseInt(config
					.getProperty(CONFIG_ROUTE_TOTAL_KEY));

			Station origin, destination;
			int idFrom, idTo, distance;

			for (int i = 0; i < totalRoutes; i++) {

				idFrom = Integer.parseInt(config.getProperty(
						MessageFormat.format(CONFIG_ROUTE_FROM_FORMAT_KEY, i))
						.trim());
				idTo = Integer.parseInt(config.getProperty(
						MessageFormat.format(CONFIG_ROUTE_TO_FORMAT_KEY, i))
						.trim());
				distance = Integer.parseInt(config.getProperty(
						MessageFormat.format(CONFIG_ROUTE_DISTANCE_FORMAT_KEY,
								i)).trim());

				origin = auxMap.get(idFrom);
				destination = auxMap.get(idTo);

				railNetworkGraph.addEdge(origin.getName(),
						destination.getName(), distance);

				railNetworkMatrix[idFrom - 1][idTo - 1] = distance;

			}

		} catch (Exception e) {
			e.printStackTrace();
			throw new RailNetworkInitalizationException(e);
		}

		System.out.println("RailNetwork load successfully!");

		System.out.println("\n#### STATIONS ####");
		printStations();
		System.out.println("\n#### ROUTES (Adjacency Matrix) ####");
		printMatrix();
		System.out.println();

	}

	private void printStations() {

		Iterator<String> stationNames = railNetwork.keySet().iterator();
		while (stationNames.hasNext()) {
			String stationName = stationNames.next();
			System.out.println("station:" + railNetwork.get(stationName)
					+ " mapped as " + stationName);
		}

	}

	private void printMatrix() {

		for (int i = 0; i < railNetworkMatrix.length; i++) {
			System.out.println();
			for (int j = 0; j < railNetworkMatrix[0].length; j++) {
				int distance = railNetworkMatrix[i][j];
				System.out.print("[" + i + "][" + j + "]: "
						+ (distance == -1 ? 0 : distance));
			}
			System.out.println();
		}

	}

	public Map<String, Station> getRailNetwork() {
		return railNetwork;
	}

	public int[][] getRailNetworkMatrix() {
		return railNetworkMatrix;
	}

	public Graph getRailNetworkGraph() {
		return railNetworkGraph;
	}

}
