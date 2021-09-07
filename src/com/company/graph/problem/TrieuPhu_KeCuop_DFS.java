package com.company.graph.problem;

import java.util.Stack;

public class TrieuPhu_KeCuop_DFS {
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

    private int[][][] visited= new int[4][4][4];
    private Node[][][] pre = new Node[4][4][4];

    private void solve(){
        Node graph = new Node(3,3,1); // solve to (0,0,0)
        for (int i=0; i<4; i++){
            for (int j=0; j<4;j++){
                for (int k=0; k<4; k++){
                    visited[i][j][k]=0;
                }
            }
        }

        for (int i=0; i<4; i++){
            for (int j=0; j<4;j++){
                for (int k=0; k<4; k++){
                    pre[i][j][k]=new Node(i,j,k);
                }
            }
        }


        visited[3][3][1] = 1;
        dfs(graph,graph);

        Node x = new Node(0,0,0);
        System.out.print(x.getTrieuPhu() + " " + x.getKeCuop() + " " + x.getBo() + "->");

        Stack<Node> solution = new Stack<>();
        solution.push(x);

        while (!(x.getTrieuPhu()==3 && x.getKeCuop() ==3 && x.Bo==1)){
            x=pre[x.getTrieuPhu()][x.getKeCuop()][x.getBo()];
            System.out.print(x.getTrieuPhu() + " " + x.getKeCuop() + " " + x.getBo() + "->");
            solution.push(x);
        }

        System.out.println();

        Node xx = solution.pop();

        while(!solution.isEmpty()){
            Node xx2 = solution.pop();

//            System.out.println(xx + " " + xx2);

            if (xx.getBo() == 1) {
                System.out.println("Chuyển " + (xx.getTrieuPhu() - xx2.getTrieuPhu()) + " triệu phú, " + (xx.getKeCuop() - xx2.getKeCuop()) + " kẻ cướp từ bờ đi sang bờ đích : " + "(" +xx2.getTrieuPhu() +", "+ xx2.getKeCuop()+", " + xx2.getBo()+ ")");
            }
            else{
                System.out.println("Chuyển " + (xx2.getTrieuPhu() - xx.getTrieuPhu()) + " triệu phú, " + (xx2.getKeCuop() - xx.getKeCuop()) + " kẻ cướp từ bờ đích  sang bờ đi: "+ "(" +xx2.getTrieuPhu() +", "+ xx2.getKeCuop()+", " + xx2.getBo()+ ")");
            }

            xx = xx2;
        }
    }


    public void findWay() {
//        if (dfs(new Node(3,3,1), new Node(3,3,1))){
//            System.out.println("Solved");
//        }
//        else{
//            System.out.println("Not solve");
//        }
    }

    // (3,3,1)
    // (2,2,0)
    //
    private boolean isFeasible(Node x, Node preX) {

        if (x.getTrieuPhu() == 0 && x.getKeCuop() == 0 && x.Bo == 0){
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

    private int countLoop = 0;
    private boolean dfs(Node x, Node preX) {
        countLoop++;
        if (x.getKeCuop() == 0 && x.getTrieuPhu() == 0 && x.getBo() == 0){
            System.out.println(x.getTrieuPhu() + " " + x.getKeCuop() + " " + x.getBo());

//            Node xx = pre[0][0][0];
//            for (int i=0; i<5; i++){
//                System.out.print(xx + " ");
//                xx = pre[xx.getTrieuPhu()][xx.getKeCuop()][xx.Bo];
//            }

            System.out.println("Countloop = " + countLoop);
            return true;
        }

        System.out.println(x.getTrieuPhu() + " " + x.getKeCuop() + " " + x.getBo());

//        if(isFeasible(x, preX)) {
            if (x.getBo() == 1){
                if (isFeasible(new Node(x.getTrieuPhu()-1, x.getKeCuop(),1-x.getBo()),x) && visited[x.getTrieuPhu()-1][x.getKeCuop()][1-x.Bo] == 0){
                    visited[x.getTrieuPhu()-1][x.getKeCuop()][1-x.Bo] = 1;
                    if (dfs(new Node(x.getTrieuPhu()-1, x.getKeCuop(),1-x.getBo()),x))
                        pre[x.getTrieuPhu()-1][x.getKeCuop()][1-x.Bo] = x;
                    else
                        pre[x.getTrieuPhu()-1][x.getKeCuop()][1-x.Bo] = x;

                }
                if (isFeasible(new Node(x.getTrieuPhu()-2, x.getKeCuop(),1-x.getBo()),x) && visited[x.getTrieuPhu()-2][x.getKeCuop()][1-x.Bo]==0){
                    visited[x.getTrieuPhu()-2][x.getKeCuop()][1-x.Bo] = 1;
                    if (dfs(new Node(x.getTrieuPhu()-2, x.getKeCuop(),1-x.getBo()),x))
                        pre[x.getTrieuPhu()-2][x.getKeCuop()][1-x.Bo] = x;
                    else
                        pre[x.getTrieuPhu()-2][x.getKeCuop()][1-x.Bo] = x;

                }
                if (isFeasible(new Node(x.getTrieuPhu(), x.getKeCuop()-1,1-x.getBo()),x) && visited[x.getTrieuPhu()][x.getKeCuop()-1][1-x.Bo] == 0){
                    visited[x.getTrieuPhu()][x.getKeCuop()-1][1-x.Bo]=1;
                    if (dfs(new Node(x.getTrieuPhu(), x.getKeCuop()-1,1-x.getBo()),x))
                        pre[x.getTrieuPhu()][x.getKeCuop()-1][1-x.Bo]=x;
                    else
                        pre[x.getTrieuPhu()][x.getKeCuop()-1][1-x.Bo]=x;

                }
                if (isFeasible(new Node(x.getTrieuPhu(), x.getKeCuop()-2,1-x.getBo()),x) && visited[x.getTrieuPhu()][x.getKeCuop()-2][1-x.Bo]==0){
                    visited[x.getTrieuPhu()][x.getKeCuop()-2][1-x.Bo] = 1;
                    if (dfs(new Node(x.getTrieuPhu(), x.getKeCuop()-2,1-x.getBo()),x))
                        pre[x.getTrieuPhu()][x.getKeCuop()-2][1-x.Bo]=x;
                    else
                        pre[x.getTrieuPhu()][x.getKeCuop()-2][1-x.Bo]=x;

                }
                if (isFeasible(new Node(x.getTrieuPhu()-1, x.getKeCuop()-1,1-x.getBo()),x) && visited[x.getTrieuPhu()-1][x.getKeCuop()-1][1-x.Bo] ==0){
                    visited[x.getTrieuPhu()][x.getKeCuop()-1][1-x.Bo] = 1;
                    if (dfs(new Node(x.getTrieuPhu()-1, x.getKeCuop()-1,1-x.getBo()),x))
                        pre[x.getTrieuPhu()-1][x.getKeCuop()-1][1-x.Bo]=x;
                    else
                        pre[x.getTrieuPhu()-1][x.getKeCuop()-1][1-x.Bo]=x;
                }
            }
            else{
                if (isFeasible(new Node(x.getTrieuPhu()+1, x.getKeCuop(),1-x.getBo()),x) && visited[x.getTrieuPhu()+1][x.getKeCuop()][1-x.Bo] == 0){
                    visited[x.getTrieuPhu()+1][x.getKeCuop()][1-x.Bo] = 1;
                    if (dfs(new Node(x.getTrieuPhu()+1, x.getKeCuop(),1-x.getBo()),x))
                        pre[x.getTrieuPhu()+1][x.getKeCuop()][1-x.Bo] = x;
                    else
                        pre[x.getTrieuPhu()+1][x.getKeCuop()][1-x.Bo] = x;
                }
                if (isFeasible(new Node(x.getTrieuPhu()+2, x.getKeCuop(),1-x.getBo()),x) && visited[x.getTrieuPhu()+2][x.getKeCuop()][1-x.Bo] == 0){
                    visited[x.getTrieuPhu()+2][x.getKeCuop()][1-x.Bo] = 1;
                    if (dfs(new Node(x.getTrieuPhu()+2, x.getKeCuop(),1-x.getBo()),x))
                        pre[x.getTrieuPhu()+2][x.getKeCuop()][1-x.Bo] = x;
                    else
                        pre[x.getTrieuPhu()+2][x.getKeCuop()][1-x.Bo] = x;
                }
                if (isFeasible(new Node(x.getTrieuPhu(), x.getKeCuop()+1,1-x.getBo()),x) && visited[x.getTrieuPhu()][x.getKeCuop()+1][1-x.Bo] == 0){
                    visited[x.getTrieuPhu()][x.getKeCuop()+1][1-x.Bo] = 1;
                    if (dfs(new Node(x.getTrieuPhu(), x.getKeCuop()+1,1-x.getBo()),x))
                        pre[x.getTrieuPhu()][x.getKeCuop()+1][1-x.Bo] = x;
                    else
                        pre[x.getTrieuPhu()][x.getKeCuop()+1][1-x.Bo] = x;
                }
                if (isFeasible(new Node(x.getTrieuPhu(), x.getKeCuop()+2,1-x.getBo()),x) && visited[x.getTrieuPhu()][x.getKeCuop()+2][1-x.Bo] == 0){
                    visited[x.getTrieuPhu()][x.getKeCuop()+2][1-x.Bo] = 1;
                    if (dfs(new Node(x.getTrieuPhu(), x.getKeCuop()+2,1-x.getBo()),x))
                        pre[x.getTrieuPhu()][x.getKeCuop()+2][1-x.Bo] = x;
                    else
                        pre[x.getTrieuPhu()][x.getKeCuop()+2][1-x.Bo] = x;
                }
                if (isFeasible(new Node(x.getTrieuPhu()+1, x.getKeCuop()+1,1-x.getBo()),x) && visited[x.getTrieuPhu()+1][x.getKeCuop()+1][1-x.Bo] == 0){
                    visited[x.getTrieuPhu()+1][x.getKeCuop()+1][1-x.Bo] = 1;
                    if (dfs(new Node(x.getTrieuPhu()+1, x.getKeCuop()+1,1-x.getBo()),x))
                        pre[x.getTrieuPhu()+1][x.getKeCuop()+1][1-x.Bo] = x;
                    else
                        pre[x.getTrieuPhu()+1][x.getKeCuop()+1][1-x.Bo] = x;
                }
            }
//        }
        return false;
    }

    public static void main(String args[]){
        TrieuPhu_KeCuop_DFS solve = new TrieuPhu_KeCuop_DFS();
        solve.solve();
    }
}
