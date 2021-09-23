package com.company.graph.problem;

import java.util.LinkedList;
import java.util.Queue;

public class TrieuPhu_KeCuop_Practice_Solution_BFS {

    // Class trang thai, vi du: (3,3,1)
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
            return "(" +
                      TrieuPhu + " "+  KeCuop +" "+ Bo +
                    ')';
        }
    }


    public void bfs(){
        // Trang thai da tham (1), chua tham(0)
        int[][][] visited= new int[4][4][4];
        for (int i=0; i<4; i++){
            for (int j=0; j<4;j++){
                for (int k=0; k<4; k++){
                    visited[i][j][k]=0;
                }
            }
        }

        Queue<Node> queue = new LinkedList<>();


        Node root = new Node(3,3,1);
        visited[3][3][1] = 1;
        queue.add(root);

        while( !queue.isEmpty() ){

            Node actualVertex = queue.remove();
            System.out.println("Node u: (" + actualVertex.getTrieuPhu()+ " " + actualVertex.getKeCuop() + " " + actualVertex.getBo()+")");
            System.out.println("Trang thai ke v: ");

            if (actualVertex.getTrieuPhu() == 0 && actualVertex.getKeCuop() == 0 && actualVertex.Bo == 0){
                System.out.println("Solve");
                break;
            }

            Node x = actualVertex;
            visited[x.getTrieuPhu()][x.getKeCuop()][x.Bo] = 1;

            if (x.getBo() == 1){
                Node print = new Node(x.getTrieuPhu()-1, x.getKeCuop(),1-x.getBo());
                System.out.print(print +"<->");
                if (isFeasible(new Node(x.getTrieuPhu()-1, x.getKeCuop(),1-x.getBo()),x) && visited[x.getTrieuPhu()-1][x.getKeCuop()][1-x.Bo] == 0){
                    visited[x.getTrieuPhu()-1][x.getKeCuop()][1-x.Bo] = 1;
                    queue.add(new Node(x.getTrieuPhu()-1, x.getKeCuop(),1-x.getBo()));
                }

                print = new Node(x.getTrieuPhu()-2, x.getKeCuop(),1-x.getBo());
                System.out.print(print +"<->");
                if (isFeasible(new Node(x.getTrieuPhu()-2, x.getKeCuop(),1-x.getBo()),x) && visited[x.getTrieuPhu()-2][x.getKeCuop()][1-x.Bo]==0){
                    visited[x.getTrieuPhu()-1][x.getKeCuop()][1-x.Bo] = 1;
                    queue.add(new Node(x.getTrieuPhu()-2, x.getKeCuop(),1-x.getBo()));
                }
                print = new Node(x.getTrieuPhu(), x.getKeCuop()-1,1-x.getBo());
                System.out.print(print +"<->");
                if (isFeasible(new Node(x.getTrieuPhu(), x.getKeCuop()-1,1-x.getBo()),x) && visited[x.getTrieuPhu()][x.getKeCuop()-1][1-x.Bo] == 0){
                    visited[x.getTrieuPhu()][x.getKeCuop()-1][1-x.Bo]=1;
                    queue.add(new Node(x.getTrieuPhu(), x.getKeCuop()-1,1-x.getBo()));
                }
                print = new Node(x.getTrieuPhu(), x.getKeCuop()-2,1-x.getBo());
                System.out.print(print +"<->");
                if (isFeasible(new Node(x.getTrieuPhu(), x.getKeCuop()-2,1-x.getBo()),x) && visited[x.getTrieuPhu()][x.getKeCuop()-2][1-x.Bo]==0){
                    visited[x.getTrieuPhu()][x.getKeCuop()-2][1-x.Bo] = 1;
                    queue.add(new Node(x.getTrieuPhu(), x.getKeCuop()-2,1-x.getBo()));
                }
                print = new Node(x.getTrieuPhu()-1, x.getKeCuop()-1,1-x.getBo());
                System.out.print(print +"<->");
                if (isFeasible(new Node(x.getTrieuPhu()-1, x.getKeCuop()-1,1-x.getBo()),x) && visited[x.getTrieuPhu()-1][x.getKeCuop()-1][1-x.Bo] ==0){
                    visited[x.getTrieuPhu()][x.getKeCuop()-1][1-x.Bo] = 1;
                    queue.add(new Node(x.getTrieuPhu()-1, x.getKeCuop()-1,1-x.getBo()));
                }
            }
            else{
                Node print = new Node(x.getTrieuPhu()+1, x.getKeCuop(),1-x.getBo());
                System.out.print(print +"<->");
                if (isFeasible(new Node(x.getTrieuPhu()+1, x.getKeCuop(),1-x.getBo()),x) && visited[x.getTrieuPhu()+1][x.getKeCuop()][1-x.Bo] == 0){
                    visited[x.getTrieuPhu()+1][x.getKeCuop()][1-x.Bo] = 1;
                    queue.add(new Node(x.getTrieuPhu()+1, x.getKeCuop(),1-x.getBo()));
                }
                print = new Node(x.getTrieuPhu()+2, x.getKeCuop(),1-x.getBo());
                System.out.print(print +"<->");
                if (isFeasible(new Node(x.getTrieuPhu()+2, x.getKeCuop(),1-x.getBo()),x) && visited[x.getTrieuPhu()+2][x.getKeCuop()][1-x.Bo] == 0){
                    visited[x.getTrieuPhu()+2][x.getKeCuop()][1-x.Bo] = 1;
                    queue.add(new Node(x.getTrieuPhu()+2, x.getKeCuop(),1-x.getBo()));
                }
                print = new Node(x.getTrieuPhu(), x.getKeCuop()+1,1-x.getBo());
                System.out.print(print +"<->");
                if (isFeasible(new Node(x.getTrieuPhu(), x.getKeCuop()+1,1-x.getBo()),x) && visited[x.getTrieuPhu()][x.getKeCuop()+1][1-x.Bo] == 0){
                    visited[x.getTrieuPhu()][x.getKeCuop()+1][1-x.Bo] = 1;
                    queue.add(new Node(x.getTrieuPhu(), x.getKeCuop()+1,1-x.getBo()));
                }
                print = new Node(x.getTrieuPhu(), x.getKeCuop()+2,1-x.getBo());
                System.out.print(print +"<->");
                if (isFeasible(new Node(x.getTrieuPhu(), x.getKeCuop()+2,1-x.getBo()),x) && visited[x.getTrieuPhu()][x.getKeCuop()+2][1-x.Bo] == 0){
                    visited[x.getTrieuPhu()][x.getKeCuop()+2][1-x.Bo] = 1;
                    queue.add(new Node(x.getTrieuPhu(), x.getKeCuop()+2,1-x.getBo()));
                }
                print = new Node(x.getTrieuPhu()+1, x.getKeCuop()+1,1-x.getBo());
                System.out.print(print +"<->");
                if (isFeasible(new Node(x.getTrieuPhu()+1, x.getKeCuop()+1,1-x.getBo()),x) && visited[x.getTrieuPhu()+1][x.getKeCuop()+1][1-x.Bo] == 0){
                    visited[x.getTrieuPhu()+1][x.getKeCuop()+1][1-x.Bo] = 1;
                    queue.add(new Node(x.getTrieuPhu()+1, x.getKeCuop()+1,1-x.getBo()));
                }
            }
            System.out.println();
            System.out.println("Danh sach L: ");
            for (Node i:queue){
                System.out.print(i+" ");
            }
            System.out.println();
            System.out.println();
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
        TrieuPhu_KeCuop_Practice_Solution_BFS solve = new TrieuPhu_KeCuop_Practice_Solution_BFS();
        solve.bfs();
    }

}
