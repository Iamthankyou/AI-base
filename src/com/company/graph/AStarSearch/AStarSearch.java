package com.company.graph.AStarSearch;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Set;

public class AStarSearch {

	private Node source;
	private Node destination;
	private Set<Node> explored;
	private PriorityQueue<Node> queue;
	
	public AStarSearch(Node source, Node destination) {
		this.source = source;
		this.destination = destination;
		this.explored = new HashSet<>();
		this.queue = new PriorityQueue<>(new NodeComparator());
	}
	
	public void run() {
		
		queue.add(source);
		
		while(!queue.isEmpty()) {
			// lowest f(x) value
			Node current = queue.poll();
			explored.add(current);
			
			// found
			if(current == destination)
				break;
			
			for(Edge edge : current.getAdjacencyList()) {
				Node child = edge.getTarget();
				double cost = edge.getWeight();
				double tempG = current.getG() + cost;
				double tempF = tempG + heuristic(child, destination);
				
				// child is visit, try update if f(x) isn't higher
				if(explored.contains(child) && tempF >= child.getF()) {
					continue;
				}
				// not visited OR the f(x) score is lower
				else if(!queue.contains(child) || tempF < child.getF()) {
					// update it first explored
					child.setParent(current);
					child.setG(tempG);
                    child.setF(tempF);
                    
                    // queue exist child > child -> replace
                    if(queue.contains(child))
                    	queue.remove(child);
                    
                    queue.add(child);
				}
			}			
		}	
	}

	private double heuristic(Node node1, Node node2) {
//		return Math.sqrt(((node1.getX()-node2.getX())*(node1.getX()-node2.getX()))+
//				((node1.getY()-node2.getY())*(node1.getY()-node2.getY())));
		return node1.getH();
	}
	
	public void printSolutionPath() {
		
		List<Node> path = new ArrayList<>();
		
		for(Node node=destination;node!=null;node=node.getParent()) {
			path.add(node);
		}
		
		Collections.reverse(path);
		
		System.out.println(path);
	}
}










