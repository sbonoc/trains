package com.bono.railroad.network;

import java.io.IOException;
import java.text.MessageFormat;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Properties;

import com.bono.railroad.network.exception.RailNetworkInitalizationException;
import com.bono.railroad.network.route.Route;
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

	private Map<String, Station> railNetwork;
	private Route[][] railNetworkMatrix;

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

			railNetwork = new HashMap<String, Station>();

			Map<Integer, Station> auxMap = new HashMap<Integer, Station>();

			int totalStations = Integer.parseInt(config
					.getProperty(CONFIG_STATION_TOTAL_KEY));

			railNetworkMatrix = new Route[totalStations][totalStations];

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
			Route auxRoute;
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

				auxRoute = new Route(origin, destination, distance);

				railNetworkMatrix[idFrom - 1][idTo - 1] = auxRoute;

			}

		} catch (Exception e) {
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
				Route route = railNetworkMatrix[i][j];
				System.out.print("[" + i + "][" + j + "]: "
						+ (route == null ? "0" : route.getDistance()) + " ");
			}
			System.out.println();
		}

	}

	public Map<String, Station> getRailNetwork() {
		return railNetwork;
	}

	public Route[][] getRailNetworkMatrix() {
		return railNetworkMatrix;
	}

}
