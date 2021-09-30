package com.company.branch_and_bound;

import com.company.graph.AStarSearch.Edge;
import com.company.graph.AStarSearch.Node;

import java.util.*;

public class App {
    private double heuristic(Node node1, Node node2) {
//		return Math.sqrt(((node1.getX()-node2.getX())*(node1.getX()-node2.getX()))+
//				((node1.getY()-node2.getY())*(node1.getY()-node2.getY())));
        return node1.getH();
    }

    public void dfs(Node a, Node b){
        int[][][] visited= new int[4][4][4];

//        Queue<Node> queue = new LinkedList<>();
//        PriorityQueue<Node> queue = new PriorityQueue<>(new heuristic());
        Stack<Node> stack = new Stack<>();

        for (int i=0; i<4; i++){
            for (int j=0; j<4;j++){
                for (int k=0; k<4; k++){
                    visited[i][j][k]=0;
                }
            }
        }

        Node root = a;
//        visited[3][3][1] = 1;
        stack.add(root);

        Map<Node, Node> pre = new HashMap<>();
        pre.put(root,root);

        HashSet<Node> explored = new HashSet<>();
        explored.add(root);

        int countLoop=0;
        while( !stack.isEmpty() ){
            countLoop++;
            Node actualVertex = stack.pop();
//            System.out.println(actualVertex.getTrieuPhu()+ " " + actualVertex.getKeCuop() + " " + actualVertex.getBo());
//            System.out.println("Node u: (" + actualVertex.getTrieuPhu()+ " " + actualVertex.getKeCuop() + " " + actualVertex.getBo()+")"+"{"+actualVertex.getH()+"}");
            System.out.println("Trang thai ke v: ");

//            if(actualVertex == b)
//                break;

            Node x = actualVertex;
            PriorityQueue<Node> priorityNearActual = new PriorityQueue<Node>(new NodeComparator());

            for(Edge edge : x.getAdjacencyList()) {
                Node child = edge.getTarget();
                double cost = edge.getWeight();
                double tempG = x.getG() + cost;
                double tempF = tempG + heuristic(child, b);

                // child is visit, try update if f(x) isn't higher
                if(explored.contains(child) && tempF >= child.getF()) {
                    continue;
                }
                // not visited OR the f(x) score is lower
                else if(!stack.contains(child) || tempF < child.getF()) {
                    // update it first explored
                    child.setParent(a);
                    child.setG(tempG);
                    child.setF(tempF);


//                    queue.add(child);
                    priorityNearActual.add(child);
                    pre.put(child,a);
                    System.out.println("Root:"+a + " current:" +child+ " k(u,v)=" + cost + " h(v)=" + heuristic(child, b) + " g(v)=" + tempG + "f(v)=" + tempF );
                }
            }

            System.out.println();
            System.out.print("Heuristic value: ");
            while (!priorityNearActual.isEmpty()){
                System.out.print(priorityNearActual.peek().getH()+" ");
                // queue exist child > child -> replace
                if(stack.contains(priorityNearActual.peek()))
                    stack.remove(priorityNearActual.peek());
                stack.push(priorityNearActual.poll());
            }
            System.out.println();
            System.out.println("Danh sach L: ");
            Stack<Node> tmp = (Stack)stack.clone();
            while (!tmp.isEmpty()){
                Node i = tmp.pop();
                System.out.print(i+"{"+i.getH()+"}"+" ");
            }
            System.out.println();
            System.out.println("Hang doi Q: ");
            for (int i=0; i<=3; i++){
                for (int j=0; j<=3; j++){
                    for (int k=0; k<=3; k++){
                        if (visited[i][j][k]!=0){
                            System.out.print("("+i+","+j+","+k+")");
                        }
                    }
                }
            }
            System.out.println();
            System.out.println();
        }
    }

    public static void main(String args[]){
        App solve = new App();
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

        solve.dfs(a,b);
    }
}
