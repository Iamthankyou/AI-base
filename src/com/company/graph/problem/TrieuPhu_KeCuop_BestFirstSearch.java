package com.company.graph.problem;

import java.util.*;

public class TrieuPhu_KeCuop_BestFirstSearch {
    public class Node{
        private int TrieuPhu;
        private int KeCuop;
        private int Bo;

        public Node(int trieuPhu, int keCuop, int bo) {
            TrieuPhu = trieuPhu;
            KeCuop = keCuop;
            Bo = bo;
        }

        public int getH(){
            return TrieuPhu + KeCuop;
        }

        public int getTrieuPhu() {
            return TrieuPhu;
        }

        public void setTrieuPhu(int trieuPhu) {
            TrieuPhu = trieuPhu;
        }

        public int getKeCuop() {
            return KeCuop;
        }

        public void setKeCuop(int keCuop) {
            KeCuop = keCuop;
        }

        public int getBo() {
            return Bo;
        }

        public void setBo(int bo) {
            Bo = bo;
        }

        @Override
        public String toString() {
            return "Node{" +
                    "TrieuPhu=" + TrieuPhu +
                    ", KeCuop=" + KeCuop +
                    ", Bo=" + Bo +
                    '}';
        }
    }

    public class heuristic implements Comparator<Node> {
        @Override
        public int compare(Node a, Node b) {
            return Integer.compare(a.getH(), b.getH());
        }
    }


    public void bfs(){
        int[][][] visited= new int[4][4][4];

//        Queue<Node> queue = new LinkedList<>();
        PriorityQueue<Node> queue = new PriorityQueue<>(new heuristic());

        for (int i=0; i<4; i++){
            for (int j=0; j<4;j++){
                for (int k=0; k<4; k++){
                    visited[i][j][k]=0;
                }
            }
        }

        Node root = new Node(3,3,1);
//        visited[3][3][1] = 1;
        queue.add(root);

        Map<Node, Node> pre = new HashMap<>();
        pre.put(root,root);

        int countLoop=0;
        while( !queue.isEmpty() ){
            countLoop++;
            Node actualVertex = queue.remove();
            System.out.println(actualVertex.getTrieuPhu()+ " " + actualVertex.getKeCuop() + " " + actualVertex.getBo());

            if (actualVertex.getTrieuPhu() == 0 && actualVertex.getKeCuop() == 0 && actualVertex.Bo == 0){
                System.out.println("Solve");
                System.out.println("Countloop=" + countLoop);

                // Track trace

                Stack<Node> solution = new Stack<>();
                solution.push(actualVertex);

                while (!(actualVertex.getTrieuPhu()==3 && actualVertex.getKeCuop() ==3 && actualVertex.getBo()==1)){
                    actualVertex=pre.get(actualVertex);
                    solution.push(actualVertex);
                }

                System.out.println();

                Node xx = solution.pop();

                while(!solution.isEmpty()){
                    Node xx2 = solution.pop();

                    if (xx.getBo() == 1) {
                        System.out.println("Chuyển " + (xx.getTrieuPhu() - xx2.getTrieuPhu()) + " triệu phú, " + (xx.getKeCuop() - xx2.getKeCuop()) + " kẻ cướp từ bờ đi sang bờ đích : " + "(" +xx2.getTrieuPhu() +", "+ xx2.getKeCuop()+", " + xx2.getBo()+ ")");
                    }
                    else{
                        System.out.println("Chuyển " + (xx2.getTrieuPhu() - xx.getTrieuPhu()) + " triệu phú, " + (xx2.getKeCuop() - xx.getKeCuop()) + " kẻ cướp từ bờ đích  sang bờ đi: "+ "(" +xx2.getTrieuPhu() +", "+ xx2.getKeCuop()+", " + xx2.getBo()+ ")");
                    }

                    xx = xx2;
                }

                break;
            }

            Node x = actualVertex;

            if (visited[x.getTrieuPhu()][x.getKeCuop()][x.Bo] == 1){
                continue;
            }
            else{
                visited[x.getTrieuPhu()][x.getKeCuop()][x.Bo] = 1;
            }

            if (x.getBo() == 1){
                if (isFeasible(new Node(x.getTrieuPhu()-1, x.getKeCuop(),1-x.getBo()),x) && visited[x.getTrieuPhu()-1][x.getKeCuop()][1-x.Bo] == 0){
//                    visited[x.getTrieuPhu()-1][x.getKeCuop()][1-x.Bo] = 1;
                    Node a = new Node(x.getTrieuPhu()-1, x.getKeCuop(),1-x.getBo());
                    queue.add(a);
                    pre.put(a,x);
                }
                if (isFeasible(new Node(x.getTrieuPhu()-2, x.getKeCuop(),1-x.getBo()),x) && visited[x.getTrieuPhu()-2][x.getKeCuop()][1-x.Bo]==0){
//                    visited[x.getTrieuPhu()-1][x.getKeCuop()][1-x.Bo] = 1;
                    Node a = new Node(x.getTrieuPhu()-2, x.getKeCuop(),1-x.getBo());
                    queue.add(a);
                    pre.put(a,x);
                }
                if (isFeasible(new Node(x.getTrieuPhu(), x.getKeCuop()-1,1-x.getBo()),x) && visited[x.getTrieuPhu()][x.getKeCuop()-1][1-x.Bo] == 0){
//                    visited[x.getTrieuPhu()][x.getKeCuop()-1][1-x.Bo]=1;
                    Node a = new Node(x.getTrieuPhu(), x.getKeCuop()-1,1-x.getBo());
                    queue.add(a);
                    pre.put(a,x);
                }
                if (isFeasible(new Node(x.getTrieuPhu(), x.getKeCuop()-2,1-x.getBo()),x) && visited[x.getTrieuPhu()][x.getKeCuop()-2][1-x.Bo]==0){
//                    visited[x.getTrieuPhu()][x.getKeCuop()-2][1-x.Bo] = 1;
                    Node a = new Node(x.getTrieuPhu(), x.getKeCuop()-2,1-x.getBo());
                    queue.add(a);
                    pre.put(a,x);
                }
                if (isFeasible(new Node(x.getTrieuPhu()-1, x.getKeCuop()-1,1-x.getBo()),x) && visited[x.getTrieuPhu()-1][x.getKeCuop()-1][1-x.Bo] ==0){
//                    visited[x.getTrieuPhu()][x.getKeCuop()-1][1-x.Bo] = 1;
                    Node a = new Node(x.getTrieuPhu()-1, x.getKeCuop()-1,1-x.getBo());
                    queue.add(a);
                    pre.put(a,x);
                }
            }
            else{
                if (isFeasible(new Node(x.getTrieuPhu()+1, x.getKeCuop(),1-x.getBo()),x) && visited[x.getTrieuPhu()+1][x.getKeCuop()][1-x.Bo] == 0){
//                    visited[x.getTrieuPhu()+1][x.getKeCuop()][1-x.Bo] = 1;
                    Node a = new Node(x.getTrieuPhu()+1, x.getKeCuop(),1-x.getBo());
                    queue.add(a);
                    pre.put(a,x);
                }
                if (isFeasible(new Node(x.getTrieuPhu()+2, x.getKeCuop(),1-x.getBo()),x) && visited[x.getTrieuPhu()+2][x.getKeCuop()][1-x.Bo] == 0){
//                    visited[x.getTrieuPhu()+2][x.getKeCuop()][1-x.Bo] = 1;
                    Node a = new Node(x.getTrieuPhu()+2, x.getKeCuop(),1-x.getBo());
                    queue.add(a);
                    pre.put(a,x);
                }
                if (isFeasible(new Node(x.getTrieuPhu(), x.getKeCuop()+1,1-x.getBo()),x) && visited[x.getTrieuPhu()][x.getKeCuop()+1][1-x.Bo] == 0){
//                    visited[x.getTrieuPhu()][x.getKeCuop()+1][1-x.Bo] = 1;
                    Node a = new Node(x.getTrieuPhu(), x.getKeCuop()+1,1-x.getBo());
                    queue.add(a);
                    pre.put(a,x);
                }
                if (isFeasible(new Node(x.getTrieuPhu(), x.getKeCuop()+2,1-x.getBo()),x) && visited[x.getTrieuPhu()][x.getKeCuop()+2][1-x.Bo] == 0){
//                    visited[x.getTrieuPhu()][x.getKeCuop()+2][1-x.Bo] = 1;
                    Node a = new Node(x.getTrieuPhu(), x.getKeCuop()+2,1-x.getBo());
                    queue.add(a);
                    pre.put(a,x);
                }
                if (isFeasible(new Node(x.getTrieuPhu()+1, x.getKeCuop()+1,1-x.getBo()),x) && visited[x.getTrieuPhu()+1][x.getKeCuop()+1][1-x.Bo] == 0){
//                    visited[x.getTrieuPhu()+1][x.getKeCuop()+1][1-x.Bo] = 1;
                    Node a = new Node(x.getTrieuPhu()+1, x.getKeCuop()+1,1-x.getBo());
                    queue.add(a);
                    pre.put(a,x);
                }
            }

        }
    }

    private boolean isFeasible(Node x, Node preX) {

        if (x.getTrieuPhu() == 0 && x.getKeCuop() == 0 && x.getBo() == 0){
            return true;
        }

        if (x.getTrieuPhu()<0 || x.getKeCuop()<0 || x.getTrieuPhu()>3 || x.getKeCuop()>3){
            return false;
        }

        if (x.getKeCuop()>x.getTrieuPhu() && x.getTrieuPhu() !=0 || ((3-x.getKeCuop() > (3-x.getTrieuPhu())) && (3-x.getTrieuPhu() !=0 )) ){
            return false;
        }

        return true;

    }

    public static void main(String args[]){
        TrieuPhu_KeCuop_BestFirstSearch solve = new TrieuPhu_KeCuop_BestFirstSearch();
        solve.bfs();
    }

}
