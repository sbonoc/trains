package com.bono.graphs;

import java.util.LinkedList;
import java.util.List;

public class BFS {
	
	public static void limitedWeightBFS(final Graph graph, LinkedList<Edge> visited, final String end,
			final int weight, int counter, List<LinkedList<Edge>> result) {
		
		LinkedList<Edge> nodes = graph.adjacentNodes(visited.getLast()
				.getDestination());

		for (Edge node : nodes) {
			if (visited.contains(node)) {
				continue;
			}
			if (node.getDestination().equals(end)) {
				visited.add(node);
				printPath(visited, weight, counter);
				counter += node.getDistance();
				if (weight >= counter) {
					result.add(new LinkedList<Edge>(visited));
				}
				visited.removeLast();
				break;
			}
		}
		
		for (Edge node : nodes) {
			if (visited.contains(node) || node.getDestination().equals(end)) {
				counter += node.getDistance();
				continue;
			}
			visited.addLast(node);
			limitedDepthBFS(graph, visited, end, weight, counter, result);
			visited.removeLast();
		}
		
	}

	public static void limitedDepthBFS(final Graph graph, LinkedList<Edge> visited, final String end,
			final int depth, int counter, List<LinkedList<Edge>> result) {

		LinkedList<Edge> nodes = graph.adjacentNodes(visited.getLast()
				.getDestination());

		for (Edge node : nodes) {
			if (visited.contains(node)) {
				continue;
			}
			if (node.getDestination().equals(end)) {
				visited.add(node);
				counter++;
				printPath(visited, depth, counter);
				if (depth >= counter) {
					result.add(new LinkedList<Edge>(visited));
				}
				visited.removeLast();
				counter--;
				break;
			}
		}
		
		counter++;
		
		for (Edge node : nodes) {
			if (visited.contains(node) || node.getDestination().equals(end)) {
				continue;
			}
			visited.addLast(node);
			limitedDepthBFS(graph, visited, end, depth, counter, result);
			visited.removeLast();
		}
	}

	public static void printPath(List<Edge> visited, int depth,
			int counter) {
		for (Edge node : visited) {
			System.out.print(" (" + node.getDistance() + ") "
					+ node.getDestination());
		}
		System.out
				.print(" [depth: " + depth + " | counter: " + counter + "]\n");
	}

}
