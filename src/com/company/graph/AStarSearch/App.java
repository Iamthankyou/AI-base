package com.company.graph.AStarSearch;

public class App {

	public static void main(String[] args) {
		
//		Node n1 = new Node("A",0,0);
//        Node n2 = new Node("B",10,20);
//        Node n3 = new Node("C",20,40);
//        Node n4 = new Node("D",30,10);
//        Node n5 = new Node("E",40,30);
//        Node n6 = new Node("F",50,10);
//        Node n7 = new Node("G",50,40);
//
//        n1.addNeighbor(new Edge(n2, 10));
//        n1.addNeighbor(new Edge(n4, 50));
//
//        n2.addNeighbor(new Edge(n3, 10));
//        n2.addNeighbor(new Edge(n4, 20));
//
//        n3.addNeighbor(new Edge(n5, 10));
//        n3.addNeighbor(new Edge(n7, 30));
//
//        n4.addNeighbor(new Edge(n6, 80));
//
//        n5.addNeighbor(new Edge(n6, 50));
//        n5.addNeighbor(new Edge(n7, 10));
//
//        n7.addNeighbor(new Edge(n6, 10));
//

        Node a = new Node("A",14);
        Node b = new Node("B",0);
        Node c = new Node("C",7);
        Node d = new Node("D",5);
        Node e = new Node("E",6);
        Node f = new Node("F",12);
        Node g = new Node("G",15);
        Node h = new Node("H",2);
        Node i = new Node("I",4);
        Node k = new Node("K",10);

        a.addNeighbor(new Edge(c,20));
        a.addNeighbor(new Edge(d,13));
        a.addNeighbor(new Edge(e,7));
        a.addNeighbor(new Edge(g,9));
        c.addNeighbor(new Edge(f,4));
        c.addNeighbor(new Edge(i,6));
        d.addNeighbor(new Edge(h,2));
        d.addNeighbor(new Edge(i,5));
        e.addNeighbor(new Edge(k,8));
        e.addNeighbor(new Edge(d,4));
        g.addNeighbor(new Edge(k,6));
        h.addNeighbor(new Edge(b,3));
        i.addNeighbor(new Edge(b,5));
        i.addNeighbor(new Edge(h,9));
        k.addNeighbor(new Edge(h,5));

        AStarSearch search = new AStarSearch(a,b);
        search.run();
        search.printSolutionPath();
	}
}
