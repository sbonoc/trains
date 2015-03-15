package com.bono.railroad.routes.service;

import java.util.Arrays;
import java.util.Stack;

import com.bono.railroad.network.route.Route;

public class RouteSearch {

	private static final int MAX_LOOPS = 1000;

	private final Route[][] railNetworkMatrix;
	private final boolean[][] stationRoutesVisited;
	private final Stack<Integer> stack;

	public RouteSearch(Route[][] railNetworkMatrix) {
		this.railNetworkMatrix = Arrays.copyOf(railNetworkMatrix,
				railNetworkMatrix.length);
		this.stationRoutesVisited = new boolean[railNetworkMatrix.length][railNetworkMatrix.length];
		this.stack = new Stack<Integer>();
	}

	public int getNumberOfTripsWithNumStops(int originStationId,
			int destinationStationId, int stops) {

		return this.getNumberOfTripsWithStops(originStationId,
				destinationStationId, stops, false);
	}

	public int getNumberOfTripsWithMaxStops(int originStationId,
			int destinationStationId, int maxStops) {

		return this.getNumberOfTripsWithStops(originStationId,
				destinationStationId, maxStops, true);
	}

	private int getNumberOfTripsWithStops(int originStationId,
			int destinationStationId, int allowedStops, boolean lessStopsAllowed) {

		System.out.println();

		int result = 0;
		int tripStops = -1;

		stack.push(originStationId - 1);

		int currentStationId;

		while (!stack.isEmpty()) {

			if (tripStops > MAX_LOOPS)
				break;

			System.out.println("Stack => " + stack);

			currentStationId = stack.pop();
			tripStops++;

			System.out.println("Stops => " + tripStops);
			System.out.println("Current Station => " + currentStationId);

			for (int nextStationId = 0; nextStationId < railNetworkMatrix.length; nextStationId++) {

				if (railNetworkMatrix[currentStationId][nextStationId] != null
						&& !stationRoutesVisited[currentStationId][nextStationId]) {

					System.out.println("From " + currentStationId + " to "
							+ nextStationId + " visited.");

					if (nextStationId == (destinationStationId - 1)) {

						System.out.println("Next station is destination = "
								+ nextStationId);
						System.out.println("Stops => " + tripStops);

						if ((lessStopsAllowed && tripStops <= allowedStops)
								|| (!lessStopsAllowed && tripStops == (allowedStops - 1))) {

							tripStops = 0;
							result++;

							stack.clear();
							stack.push(originStationId - 1);

							resetAllVisitedRoutesExceptOriginStation(originStationId);

							System.out.println("Routes found => " + result);

							System.out.println("RETURN TO ORIGIN Stack => "
									+ stack);

							break;

						}
					}

					stack.push(nextStationId);
					stationRoutesVisited[currentStationId][nextStationId] = true;
					currentStationId = nextStationId;

					System.out.println("Stack after visit: " + stack);

					break;

				}

			}

		}

		System.out.println();

		return result;

	}

	private void resetAllVisitedRoutesExceptOriginStation(int originStationId) {

		for (int i = 0; i < stationRoutesVisited.length; i++) {

			if (i != (originStationId - 1)) {
				for (int j = 0; j < stationRoutesVisited[0].length; j++) {
					stationRoutesVisited[i][j] = false;
				}
			}

		}

	}

	public int getDistanceOfShortestPathBetween(int originStationId,
			int destinationStationId) {

		int[][] dijkstraMatrix = new int[railNetworkMatrix.length][railNetworkMatrix.length];

		for (int i = 0; i < railNetworkMatrix.length; i++) {
			for (int j = 0; j < railNetworkMatrix.length; j++) {
				if(i == j) {
					dijkstraMatrix[i][j] = 0;
				}
				else if (railNetworkMatrix[i][j] == null) {
					dijkstraMatrix[i][j] = Integer.MAX_VALUE;
				} else {
					dijkstraMatrix[i][j] = (int) railNetworkMatrix[i][j]
							.getDistance();
				}
			}
		}
		for (int k = 0; k < dijkstraMatrix.length; k++) {
			for (int i = 0; i < dijkstraMatrix.length; i++) {
				for (int j = 0; j < dijkstraMatrix.length; j++) {
					dijkstraMatrix[i][j] = Math.min(dijkstraMatrix[i][j],
							dijkstraMatrix[i][k] + dijkstraMatrix[k][j]);
				}
			}
		}

		return dijkstraMatrix[originStationId - 1][destinationStationId - 1];

	}

}
