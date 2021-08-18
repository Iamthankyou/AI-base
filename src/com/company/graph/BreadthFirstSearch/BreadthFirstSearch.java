package com.company.graph.BreadthFirstSearch;

import java.util.LinkedList;
import java.util.Queue;

public class BreadthFirstSearch {

	public void bfs(Vertex root, Vertex end){
		
		Queue<Vertex> queue = new LinkedList<>();
		
		root.setVisited(true);
		queue.add(root);

		if (root.getData() == end.getData()){
			System.out.print("-");
		}

		while( !queue.isEmpty() ){
			
			Vertex actualVertex = queue.remove();
			System.out.print(actualVertex+" ");
			if (end.getData() == actualVertex.getData()){
				break;
			}

			for( Vertex v : actualVertex.getNeighbourList() ){
				if( !v.isVisited() ){
					v.setVisited(true);
					queue.add(v);
				}
			}			
		}
	}
}
