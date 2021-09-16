package com.company.graph.problem;

import java.util.*;

public class Number_8_BFS_Solution_Practice {
    public class Node{
        private int mat[][];
        private int matEnd[][];
        // cordinate of null element
        private int x;
        private int y;

        public Node(int[][] mat,int x,int y) {
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

        public int[][] getMatEnd() {
            return matEnd;
        }

        public void setMatEnd(int[][] matEnd) {
            this.matEnd = matEnd;
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
            return Integer.compare(a.getH1(), b.getH1());
        }
    }

    public class h2 implements Comparator<Node> {

        @Override
        public int compare(Node a, Node b) {
            return Integer.compare(a.getH2(),b.getH2());
        }
    }

    private int start[][];
    private int end[][];

    public void bfs(){

//        PriorityQueue<Node> queue = new PriorityQueue<>(new h1());
        PriorityQueue<Node> queue = new PriorityQueue<>(new h2());

        //        Queue<Node> queue = new LinkedList<>();
//        int start[][];
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

//        int end[][];
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
//        visited[3][3][1] = 1;
        queue.add(root);

        Map<Node,Node> pre = new HashMap<>();
        pre.put(root,root);

        Set<Node> set = new HashSet<>();
        set.add(root);

        int countLoop =0;

        while( !queue.isEmpty() ){
            countLoop++;
            Node actualVertex = queue.remove();
//            System.out.println(actualVertex.getTrieuPhu()+ " " + actualVertex.getKeCuop() + " " + actualVertex.getBo());
            System.out.println("Canh u: ");
            actualVertex.print();

            set.add(actualVertex);

            if (actualVertex.compare(end)){
                System.out.println("Solved until countLoop= " + countLoop);

                // Track trace
                Stack<Node> solution  = new Stack<>();
                solution.push(actualVertex);

                while (!actualVertex.compare(start)){
                    actualVertex = pre.get(actualVertex);
                    solution.push(actualVertex);
                }

                while (!solution.isEmpty()){
                    System.out.println("H2 value: " + solution.peek().getH2());
                    solution.pop().print();
                }

                break;
            }

            Node x = actualVertex;

            int xo = x.getX();
            int yo = x.getY();

            System.out.println("Danh sach ke v:");

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
                xx.print();
                if (!set.contains(xx)){
                    queue.add(xx);
                    pre.put(xx,x);
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
                xx.print();
                if (!set.contains(xx)){
                    queue.add(xx);
                    pre.put(xx,x);
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
                xx.print();
                if (!set.contains(xx)){
                    queue.add(xx);
                    pre.put(xx,x);
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
                xx.print();
                if (!set.contains(xx)){
                    queue.add(xx);
                    pre.put(xx,x);
                }
            }

            System.out.println();
            System.out.println("Danh sach L: ");
            for (Node i:queue){
                i.print();
            }
            System.out.println();
            System.out.println();
            System.out.println("Danh sach M:");
            for (Node s : set) {
                s.print();
            }

        }
    }

    private boolean isFeasible(Node x, Node preX) {
        return true;
    }

    public static void main(String args[]){
        Number_8_BFS_Solution_Practice solve = new Number_8_BFS_Solution_Practice();
        solve.bfs();
    }

}
