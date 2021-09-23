package com.company.graph.problem;

import java.util.*;

public class TrieuPhu_KeCuop_Practice_Solution_BFS_ef {

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

    // Class cac toan tu cua bai toan, vi du: (1,0) chuyen 1 trieu phu, 0 kecuop sang bo ben kia
    class operator{
        int TrieuPhu;
        int KeCuop;

        public operator(int trieuPhu, int keCuop) {
            TrieuPhu = trieuPhu;
            KeCuop = keCuop;
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

        // Hang doi luu cac trang thai can tham
        Queue<Node> queue = new LinkedList<>();

        // Trang thai dau tien
        Node root = new Node(3,3,1);
        visited[3][3][1] = 1;
        queue.add(root);

        // Luu cac toan tu cua bai toan, vi du : 1,1 chuyen 1 trieu phu, 1 ke cuop sang bo ben kia
        List<operator> operators = new LinkedList<>();
        operators.add(new operator(1,0));
        operators.add(new operator(2,0));
        operators.add(new operator(0,1));
        operators.add(new operator(0,2));
        operators.add(new operator(1,1));

        // Luu trang thai parent cua trang thai hien tai de truy vet
        Map<Node,Node> pre = new HashMap<>();
        pre.put(root,root);

        // Duyet BFS tat ca cac trang thai trong hang doi
        while( !queue.isEmpty() ){
            // Lay trang thai dung dau hang doi va xoa khoi hang doi
            Node actualVertex = queue.remove();
            System.out.println("Node u: (" + actualVertex.getTrieuPhu()+ " " + actualVertex.getKeCuop() + " " + actualVertex.getBo()+")");

            // Neu trang thai hien tai la trang thai cuoi cung -> Dung tim kiem, in ra duong di
            if (actualVertex.getTrieuPhu() == 0 && actualVertex.getKeCuop() == 0 && actualVertex.Bo == 0){
                System.out.println("Solved");

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
            System.out.println("Trang thai ke v: ");

            /*
            * Kiem tra cac trang thai ke voi trang thai x, neu phu hop, them vao hang doi
            * Neu thuyen dang o bo 1 cac toan tu la: chuyen -1 trieu phu, -2 trieu phu, -1 ke cuop, -2 ke cuop, -1 trieu phu -1 ke cuop
            * Neu thuyen dang o bo 1 cac toan tu la: chuyen +1 trieu phu, +2 trieu phu, 1 ke cuop, +2 ke cuop, +1 trieu phu +1 ke cuop
            * */

            if (x.getBo() == 1) {
                // Duyet cac danh sach trang thai ke
                for (operator i: operators){
                    Node adj = move(i.getTrieuPhu()*(-1),i.getKeCuop()*(-1),x);
                    System.out.print(adj + "<->");
                    if (isFeasible(adj) && visit(adj,visited)) {
                        visited[adj.getTrieuPhu()][adj.getKeCuop()][1 - x.Bo] = 1;
                        queue.add(adj);
                        // Trang thai x la parent cua trang thai adj
                        pre.put(adj,x);
                    }
                }
            }
            else{
                for (operator i: operators){
                    Node adj = move(i.getTrieuPhu()*(1),i.getKeCuop()*(1),x);
                    System.out.print(adj + "<->");
                    if (isFeasible(adj) && visit(adj,visited)) {
                        visited[adj.getTrieuPhu()][adj.getKeCuop()][1 - x.Bo] = 1;
                        queue.add(adj);
                        // Trang thai x la parent cua trang thai adj
                        pre.put(adj,x);
                    }
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

    // Neu trang thai chua duoc dua vao hang doi 0, dua vao hang doi 1
    private boolean visit(Node x, int visited[][][]){
        return visited[x.getTrieuPhu()][x.getKeCuop()][x.getBo()] == 0;
    }

    // Chuyen x trieu phu, y ke cuop sang bo z
    private Node move(int TrieuPhu, int KeCuop, Node current){
        return new Node(current.getTrieuPhu()+TrieuPhu,current.getKeCuop()+KeCuop, 1-current.getBo());
    }

    // Ham kiem tra dieu kien so trieu phu va so ke cuop o tren ca 2 bo
    private boolean isFeasible(Node x) {
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
        TrieuPhu_KeCuop_Practice_Solution_BFS_ef solve = new TrieuPhu_KeCuop_Practice_Solution_BFS_ef();
        solve.bfs();
    }
}
