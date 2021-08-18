package com.company.graph.AStarSearch;

public class Edge {

	private double weight;
	private Node target;
	
	public Edge(Node target, double weight) {
		this.target = target;
		this.weight = weight;
	}

	public double getWeight() {
		return weight;
	}

	public Node getTarget() {
		return target;
	}
}
