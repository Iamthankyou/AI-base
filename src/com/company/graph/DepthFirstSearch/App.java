package com.company.graph.DepthFirstSearch;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class App {

	public static void main(String[] args) {

//		Vertex vertex1 = new Vertex("1");
//		Vertex vertex2 = new Vertex("2");
//		Vertex vertex3 = new Vertex("3");
//		Vertex vertex4 = new Vertex("4");
//		Vertex vertex5 = new Vertex("5");
//
//		vertex1.addNeighbour(vertex2);
//		vertex1.addNeighbour(vertex4);
//		vertex4.addNeighbour(vertex5);
//		vertex2.addNeighbour(vertex3);

		ArrayList<Vertex> graph = new ArrayList<>();

		try {
			File file = new File("/home/beter/AI-base/src/com/company/graph/data/graph.txt");
			Scanner scanner = new Scanner(file);

			int num = scanner.nextInt();

			for (int i=0; i<=num; i++){
				graph.add(new Vertex(i));
			}

			for (int i=0; i<num; i++){
				int s = scanner.nextInt();
				int v = scanner.nextInt();
				int w = scanner.nextInt();

				graph.get(s).addNeighbour(graph.get(v));
				System.out.println("Vertex " + (s) + " add neighbour " + " vertex " + (v));
			}

			scanner.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		DepthFirstSearch depthFirstSearch = new DepthFirstSearch();
		depthFirstSearch.dfsNormal(graph.get(1), graph.get(7));

	}
}
