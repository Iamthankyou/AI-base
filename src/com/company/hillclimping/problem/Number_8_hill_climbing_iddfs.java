package com.company.hillclimping.problem;

import com.company.graph.problem.Number_8_BestFirstSearch;

import java.util.*;

public class Number_8_hill_climbing_iddfs {

	public class Node{
		private int mat[][];
		// cordinate of null element
		private int x;
		private int y;
		private int depthLevel = 0;

		public int getDepthLevel() {
			return depthLevel;
		}

		public void setDepthLevel(int depthLevel) {
			this.depthLevel = depthLevel;
		}

		public Node(int[][] mat, int x, int y) {
			this.mat = mat;
			this.x = x;
			this.y = y;
		}


		private int getH1(){
			int value = 0;
			for (int i=0; i<mat[0].length; i++){
				for (int j=0; j<mat.length; j++){
					if (mat[i][j]!=end[i][j]){
						value++;
					}
				}
			}

			return value;
		}

		private int getH2(){
			// Mathatan distance
			Map<Integer, pair> dictCurr = new HashMap<>();
			Map<Integer, pair> dictEnd = new HashMap<>();

			for (int i=0; i<mat[0].length; i++){
				for (int j=0; j<mat.length; j++){
					dictCurr.put(mat[i][j],new pair(i,j));
					dictEnd.put(end[i][j],new pair(i,j));
				}
			}

			int value = 0;

			// Next 0
			for (int i=1; i<=8; i++){
				value += Math.abs(dictCurr.get(i).getX()-dictEnd.get(i).getX()) + Math.abs(dictCurr.get(i).getY()-dictEnd.get(i).getY());
			}

			return value;
		}

		public void set(int x, int y, int val){
			this.mat[x][y] = val;
		}

		public void swap(int xo, int yo, int xi, int yi){
			int tmp = this.mat[xo][yo];
			this.mat[xo][yo] = this.mat[xi][yi];
			this.mat[xi][yi] = tmp;

			this.x = xi;
			this.y = yi;
		}

		public boolean compare(int[][] x){
			for (int i=0; i<3; i++){
				for (int j=0; j<3; j++){
					if (x[i][j]!=this.mat[i][j]){
						return false;
					}
				}
			}
			return true;
		}

		public int[][] getMat() {
			return mat;
		}

		public void setMat(int[][] mat) {
			this.mat = mat;
		}

		public int getX() {
			return x;
		}

		public void setX(int x) {
			this.x = x;
		}

		public int getY() {
			return y;
		}

		public void setY(int y) {
			this.y = y;
		}

		@Override
		public String toString() {
			return "Node{" +
					"mat=" + Arrays.toString(mat) +
					'}';
		}

		public void print(){
			for (int i=0; i<3; i++){
				for (int j=0; j<3; j++){
					System.out.print(this.mat[i][j] + " ");
				}
				System.out.println();
			}

			System.out.println();
		}
	}

	public class pair{
		int x;
		int y;

		public pair(int x, int y) {
			this.x = x;
			this.y = y;
		}

		public int getX() {
			return x;
		}

		public void setX(int x) {
			this.x = x;
		}

		public int getY() {
			return y;
		}

		public void setY(int y) {
			this.y = y;
		}
	}

	public class h1 implements Comparator<Node> {
		@Override
		public int compare(Node a, Node b) {
			if (a.getH1()<b.getH1())
				return 1;
			else if (a.getH1()>b.getH1())
				return -1;
			return 0;
		}
	}

	public class h2 implements Comparator<Node> {
		@Override
		public int compare(Node a, Node b) {
			if (a.getH2()<b.getH2())
				return 1;
			else if (a.getH2()>b.getH2())
				return -1;
			return 0;
		}
	}

	private Node targetVertex;
	private volatile boolean isTargetFound;
	private int end[][];

	public Number_8_hill_climbing_iddfs(){
	}
	
	public void runDeepeningSearch() {

		int start[][];
		start = new int[3][3];
		start[0][0] = 2;
		start[0][1] = 8;
		start[0][2] = 3;
		start[1][0] = 1;
		start[1][1] = 6;
		start[1][2] = 4;
		start[2][0] = 7;
		start[2][1] = 0;
		start[2][2] = 5;

		end = new int[3][3];
		end[0][0] = 1;
		end[0][1] = 2;
		end[0][2] = 3;
		end[1][0] = 8;
		end[1][1] = 0;
		end[1][2] = 4;
		end[2][0] = 7;
		end[2][1] = 6;
		end[2][2] = 5;

		Node root = new Node(start, 2,1);
		Node ending = new Node(end,1,0);

		Map<Node, Node> pre = new HashMap<>();
		pre.put(root,root);

		Set<Node> set = new HashSet<>();
		set.add(root);

		int depth = 0;

		while(!isTargetFound){
			System.out.println("DS with depth = " + depth) ;
			dfs(root,ending,depth,pre,set, start);
			depth++;
		}	
	}
	
	public void dfs(Node sourceVertex, Node end, int depthLevel, Map<Node,Node> pre, Set<Node> set, int [][] start){
		
		Stack<Node> stack = new Stack<>();
		sourceVertex.setDepthLevel(0);
		stack.push(sourceVertex);
		int countLoop = 0;

		while( !stack.isEmpty() ){
			countLoop++;
			Node actualNode = stack.pop();
			actualNode.print();

			if(actualNode.compare(end.getMat())){
				System.out.println("\nNode found... with depth = " + depthLevel + " countLoop= " + countLoop);
				this.isTargetFound = true;

				// Track trace
				Stack<Node> solution  = new Stack<>();
				solution.push(actualNode);

				while (!actualNode.compare(start)){
					actualNode = pre.get(actualNode);
					solution.push(actualNode);
				}

				while (!solution.isEmpty()){
					solution.pop().print();
				}

				return;
			}

			// FOR ITER DFS
			if( actualNode.getDepthLevel() >= depthLevel ){
				continue;
			}

			Node x = actualNode;

			// PRIORITY QUEUE FOR HILLCLIMBING
//			PriorityQueue<Node> priorityNearActual = new PriorityQueue<>(new h1());
			PriorityQueue<Node> priorityNearActual = new PriorityQueue<>(new h2());

			int xo = x.getX();
			int yo = x.getY();

			// UP TO DOWN
			if (xo-1>=0){
				int a[][] ;
				a = new int[3][3];
				for (int i=0; i<3; i++){
					for (int j=0; j<3; j++){
						a[i][j] = x.getMat()[i][j];
					}
				}

				Node xx = new Node(a,x.getX(),x.getY());
				xx.swap(xo,yo,xo-1,yo);
				if (!set.contains(xx)){
					pre.put(xx,x);
					xx.setDepthLevel(actualNode.getDepthLevel()+1);
//					stack.push(xx);
					priorityNearActual.add(xx);
				}
			}

			//DOWN TO UP
			if (xo+1<=2){
				int a[][] ;
				a = new int[3][3];
				for (int i=0; i<3; i++){
					for (int j=0; j<3; j++){
						a[i][j] = x.getMat()[i][j];
					}
				}

				Node xx = new Node(a,x.getX(),x.getY());
				xx.swap(xo,yo,xo+1,yo);
				if (!set.contains(xx)){
					xx.setDepthLevel(actualNode.getDepthLevel()+1);
//					stack.push(xx);
					pre.put(xx,x);
					priorityNearActual.add(xx);

				}
			}

			//LEFT TO RIGHT
			if (yo-1>=0){
				int a[][] ;
				a = new int[3][3];
				for (int i=0; i<3; i++){
					for (int j=0; j<3; j++){
						a[i][j] = x.getMat()[i][j];
					}
				}

				Node xx = new Node(a,x.getX(),x.getY());
				xx.swap(xo,yo,xo,yo-1);
				if (!set.contains(xx)){
					xx.setDepthLevel(actualNode.getDepthLevel()+1);
//					stack.push(xx);
					pre.put(xx,x);
					priorityNearActual.add(xx);
				}
			}

			//RIGHT TO LEFT
			if (yo+1<=2){
				int a[][] ;
				a = new int[3][3];
				for (int i=0; i<3; i++){
					for (int j=0; j<3; j++){
						a[i][j] = x.getMat()[i][j];
					}
				}

				Node xx = new Node(a,x.getX(),x.getY());
				xx.swap(xo,yo,xo,yo+1);
				if (!set.contains(xx)){
					xx.setDepthLevel(actualNode.getDepthLevel()+1);
//					stack.push(xx);
					pre.put(xx,x);
					priorityNearActual.add(xx);
				}
			}

			System.out.print("Heuristic value: ");
			while (!priorityNearActual.isEmpty()){
				System.out.print(priorityNearActual.peek().getH1()+" ");
				stack.push(priorityNearActual.poll());
			}
			System.out.println();
		}
	}

	public static void main(String[] args) {

		Number_8_hill_climbing_iddfs algorithm = new Number_8_hill_climbing_iddfs();
		algorithm.runDeepeningSearch();

	}
}
