package com.bono.graphs.algorithms;

import java.util.PriorityQueue;

public class Dijkstra {

	private static class Node implements Comparable<Node> {

		public final int index;
		public final int priority;

		public Node(int index, int priority) {
			this.index = index;
			this.priority = priority;
		}

		public int compareTo(Node other) {
			return Integer.valueOf(priority).compareTo(other.priority);
		}

	}

	public static int dijkstra(final int[][] graph, int from, int to) {

		int[] distance = new int[graph.length];
		PriorityQueue<Node> priorityQueue = new PriorityQueue<Node>();

		for (int i = 0; i < graph.length; i++) {
			distance[i] = Integer.MAX_VALUE;
		}

		for (int i = 0; i < graph.length / 2; i++) {
			for (int j = 0; j < graph.length; j++) {
				if (graph[i][j] > 0) {
					priorityQueue.add(new Node(i, graph[i][j]));
				}
			}
		}

		priorityQueue.add(new Node(from, 0));
		distance[from] = 0;

		while (!priorityQueue.isEmpty()) {
			int current = priorityQueue.remove().index;
			for (int i = 0; i < graph.length; i++) {
				if (graph[current][i] > 1) {
					int alt = distance[current] + graph[current][i];
					if (alt < distance[i]) {
						distance[i] = alt;
						priorityQueue.add(new Node(current, distance[i]));
					}
				}
			}
		}

		System.out.println("Distances from " + from);
		for (int i = 0; i < graph.length; i++) {
			System.out.print(from + " =(" + distance[i] + ")=> " + i + "\n");
		}

		return distance[to];

	}
}
