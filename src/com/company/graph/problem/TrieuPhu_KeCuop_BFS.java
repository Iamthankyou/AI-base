package com.company.graph.problem;

import com.company.graph.BreadthFirstSearch.Vertex;

import java.util.LinkedList;
import java.util.Queue;

public class TrieuPhu_KeCuop_BFS {
    public class Node{
        private int TrieuPhu;
        private int KeCuop;
        private int Bo;

        public Node(int trieuPhu, int keCuop, int bo) {
            TrieuPhu = trieuPhu;
            KeCuop = keCuop;
            Bo = bo;
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


    public void bfs(){
        int[][][] visited= new int[4][4][4];

        Queue<Node> queue = new LinkedList<>();
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

        while( !queue.isEmpty() ){

            Node actualVertex = queue.remove();
            System.out.println(actualVertex.getTrieuPhu()+ " " + actualVertex.getKeCuop() + " " + actualVertex.getBo());

            if (actualVertex.getTrieuPhu() == 0 && actualVertex.getKeCuop() == 0 && actualVertex.Bo == 0){
                System.out.println("Solve");
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
                    queue.add(new Node(x.getTrieuPhu()-1, x.getKeCuop(),1-x.getBo()));
                }
                if (isFeasible(new Node(x.getTrieuPhu()-2, x.getKeCuop(),1-x.getBo()),x) && visited[x.getTrieuPhu()-2][x.getKeCuop()][1-x.Bo]==0){
//                    visited[x.getTrieuPhu()-1][x.getKeCuop()][1-x.Bo] = 1;
                    queue.add(new Node(x.getTrieuPhu()-2, x.getKeCuop(),1-x.getBo()));
                }
                if (isFeasible(new Node(x.getTrieuPhu(), x.getKeCuop()-1,1-x.getBo()),x) && visited[x.getTrieuPhu()][x.getKeCuop()-1][1-x.Bo] == 0){
//                    visited[x.getTrieuPhu()][x.getKeCuop()-1][1-x.Bo]=1;
                    queue.add(new Node(x.getTrieuPhu(), x.getKeCuop()-1,1-x.getBo()));
                }
                if (isFeasible(new Node(x.getTrieuPhu(), x.getKeCuop()-2,1-x.getBo()),x) && visited[x.getTrieuPhu()][x.getKeCuop()-2][1-x.Bo]==0){
//                    visited[x.getTrieuPhu()][x.getKeCuop()-2][1-x.Bo] = 1;
                    queue.add(new Node(x.getTrieuPhu(), x.getKeCuop()-2,1-x.getBo()));
                }
                if (isFeasible(new Node(x.getTrieuPhu()-1, x.getKeCuop()-1,1-x.getBo()),x) && visited[x.getTrieuPhu()-1][x.getKeCuop()-1][1-x.Bo] ==0){
//                    visited[x.getTrieuPhu()][x.getKeCuop()-1][1-x.Bo] = 1;
                    queue.add(new Node(x.getTrieuPhu()-1, x.getKeCuop()-1,1-x.getBo()));
                }
            }
            else{
                if (isFeasible(new Node(x.getTrieuPhu()+1, x.getKeCuop(),1-x.getBo()),x) && visited[x.getTrieuPhu()+1][x.getKeCuop()][1-x.Bo] == 0){
//                    visited[x.getTrieuPhu()+1][x.getKeCuop()][1-x.Bo] = 1;
                    queue.add(new Node(x.getTrieuPhu()+1, x.getKeCuop(),1-x.getBo()));
                }
                if (isFeasible(new Node(x.getTrieuPhu()+2, x.getKeCuop(),1-x.getBo()),x) && visited[x.getTrieuPhu()+2][x.getKeCuop()][1-x.Bo] == 0){
//                    visited[x.getTrieuPhu()+2][x.getKeCuop()][1-x.Bo] = 1;
                    queue.add(new Node(x.getTrieuPhu()+2, x.getKeCuop(),1-x.getBo()));
                }
                if (isFeasible(new Node(x.getTrieuPhu(), x.getKeCuop()+1,1-x.getBo()),x) && visited[x.getTrieuPhu()][x.getKeCuop()+1][1-x.Bo] == 0){
//                    visited[x.getTrieuPhu()][x.getKeCuop()+1][1-x.Bo] = 1;
                    queue.add(new Node(x.getTrieuPhu(), x.getKeCuop()+1,1-x.getBo()));
                }
                if (isFeasible(new Node(x.getTrieuPhu(), x.getKeCuop()+2,1-x.getBo()),x) && visited[x.getTrieuPhu()][x.getKeCuop()+2][1-x.Bo] == 0){
//                    visited[x.getTrieuPhu()][x.getKeCuop()+2][1-x.Bo] = 1;
                    queue.add(new Node(x.getTrieuPhu(), x.getKeCuop()+2,1-x.getBo()));
                }
                if (isFeasible(new Node(x.getTrieuPhu()+1, x.getKeCuop()+1,1-x.getBo()),x) && visited[x.getTrieuPhu()+1][x.getKeCuop()+1][1-x.Bo] == 0){
//                    visited[x.getTrieuPhu()+1][x.getKeCuop()+1][1-x.Bo] = 1;
                    queue.add(new Node(x.getTrieuPhu()+1, x.getKeCuop()+1,1-x.getBo()));
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
        TrieuPhu_KeCuop_BFS solve = new TrieuPhu_KeCuop_BFS();
        solve.bfs();
    }

}
