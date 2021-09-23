package com.company.graph.problem;

import java.util.*;

public class TrieuPhu_KeCuop_Practice_Solution_AStar {
    public class Node{
        private int TrieuPhu;
        private int KeCuop;
        private int Bo;
        private int level = 0;

        private double g = 0;
        private double h;
        private double f;

        public double getG() {
            return g;
        }

        public void setG(double g) {
            this.g = g;
        }

        public void setH(double h) {
            this.h = h;
        }

        public double getF() {
            return f;
        }

        public void setF(double f) {
            this.f = f;
        }

        public int getLevel() {
            return level;
        }

        public void setLevel(int level) {
            this.level = level;
        }

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
            return "(" +
                    TrieuPhu + " "+  KeCuop +" "+ Bo +
                    ')';
        }
    }

    public class heuristic implements Comparator<Node> {
        @Override
        public int compare(Node a, Node b) {
            return Double.compare(a.getF(), b.getF());
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
        visited[3][3][1] = 1;
        queue.add(root);

        Map<Node, Node> pre = new HashMap<>();
        pre.put(root,root);

        int countLoop=0;
        while( !queue.isEmpty() ){
            countLoop++;
            Node actualVertex = queue.remove();
//            System.out.println("Node u: (" + actualVertex.getTrieuPhu()+ " " + actualVertex.getKeCuop() + " " + actualVertex.getBo()+")"+"{"+actualVertex.getH()+"}");
//            System.out.println("Trang thai ke v: ");

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
            visited[x.getTrieuPhu()][x.getKeCuop()][x.getBo()] = 1;

//            if (visited[x.getTrieuPhu()][x.getKeCuop()][x.Bo] == 1){
//                continue;
//            }
//            else{
//                visited[x.getTrieuPhu()][x.getKeCuop()][x.Bo] = 1;
//            }



            if (x.getBo() == 1){
                Node print = new Node(x.getTrieuPhu()-1, x.getKeCuop(),1-x.getBo());
//                System.out.print(print+"{"+print.getH()+"} - ");
                if (isFeasible(new Node(x.getTrieuPhu()-1, x.getKeCuop(),1-x.getBo()),x) && visited[x.getTrieuPhu()-1][x.getKeCuop()][1-x.Bo] == 0){
                    Node a = new Node(x.getTrieuPhu()-1, x.getKeCuop(),1-x.getBo());

                    double cost = 1;
                    double tempG = actualVertex.getG() + cost;
                    double tempF = tempG + a.getH();

                    if (visited[x.getTrieuPhu()-1][x.getKeCuop()][1-x.Bo]==1 && tempF >= a.getF()){

                    }else
                    if(!queue.contains(a) || tempF < a.getF()) {
                        // update it first explored
                        a.setG(tempG);
                        a.setF(tempF);

                        // queue exist child > child -> replace
                        if(queue.contains(a))
                            queue.remove(a);

                        queue.add(a);
                        pre.put(a,x);
                        System.out.println("Root:"+actualVertex + " current:" +a +" k(u,v)=" + cost + " h(v)=" + a.getH()+ " g(v)=" + tempG + "f(v)=" + tempF );
                        visited[x.getTrieuPhu()-1][x.getKeCuop()][1-x.Bo] = 1;

                    }

                }
                print = new Node(x.getTrieuPhu()-2, x.getKeCuop(),1-x.getBo());
//                System.out.print(print+"{"+print.getH()+"} - ");
                if (isFeasible(new Node(x.getTrieuPhu()-2, x.getKeCuop(),1-x.getBo()),x) && visited[x.getTrieuPhu()-2][x.getKeCuop()][1-x.Bo]==0){
                    Node a = new Node(x.getTrieuPhu()-2, x.getKeCuop(),1-x.getBo());
                    double cost = 1;
                    double tempG = actualVertex.getG() + cost;
                    double tempF = tempG + a.getH();

                    if (visited[x.getTrieuPhu()-2][x.getKeCuop()][1-x.Bo]==1 && tempF >= a.getF()){

                    }else
                    if(!queue.contains(a) || tempF < a.getF()) {
                        // update it first explored
                        a.setG(tempG);
                        a.setF(tempF);

                        // queue exist child > child -> replace
                        if(queue.contains(a))
                            queue.remove(a);

                        queue.add(a);
                        pre.put(a,x);
                        System.out.println("Root:"+actualVertex + " current:" +a +" k(u,v)=" + cost + " h(v)=" + a.getH()+ " g(v)=" + tempG + "f(v)=" + tempF );
                        visited[x.getTrieuPhu()-2][x.getKeCuop()][1-x.Bo] = 1;
                    }
                }
                print = new Node(x.getTrieuPhu(), x.getKeCuop()-1,1-x.getBo());
//                System.out.print(print+"{"+print.getH()+"} - ");
                if (isFeasible(new Node(x.getTrieuPhu(), x.getKeCuop()-1,1-x.getBo()),x) && visited[x.getTrieuPhu()][x.getKeCuop()-1][1-x.Bo] == 0){
//                    visited[x.getTrieuPhu()][x.getKeCuop()-1][1-x.Bo]=1;
                    Node a = new Node(x.getTrieuPhu(), x.getKeCuop()-1,1-x.getBo());
                    double cost = 1;
                    double tempG = actualVertex.getG() + cost;
                    double tempF = tempG + a.getH();

                    if (visited[x.getTrieuPhu()][x.getKeCuop()-1][1-x.Bo]==1 && tempF >= a.getF()){
                    }else
                    if(!queue.contains(a) || tempF < a.getF()) {
                        // update it first explored
                        a.setG(tempG);
                        a.setF(tempF);

                        // queue exist child > child -> replace
                        if(queue.contains(a))
                            queue.remove(a);

                        queue.add(a);
                        pre.put(a,x);
                        System.out.println("Root:"+actualVertex + " current:" +a +" k(u,v)=" + cost + " h(v)=" + a.getH()+ " g(v)=" + tempG + "f(v)=" + tempF );
                        visited[x.getTrieuPhu()][x.getKeCuop()-1][1-x.Bo] =1;
                    }
                }
                print = new Node(x.getTrieuPhu(), x.getKeCuop()-2,1-x.getBo());
//                System.out.print(print+"{"+print.getH()+"} - ");
                if (isFeasible(new Node(x.getTrieuPhu(), x.getKeCuop()-2,1-x.getBo()),x) && visited[x.getTrieuPhu()][x.getKeCuop()-2][1-x.Bo]==0){
//                    visited[x.getTrieuPhu()][x.getKeCuop()-2][1-x.Bo] = 1;
                    Node a = new Node(x.getTrieuPhu(), x.getKeCuop()-2,1-x.getBo());
                    double cost = 1;
                    double tempG = actualVertex.getG() + cost;
                    double tempF = tempG + a.getH();

                    if (visited[x.getTrieuPhu()][x.getKeCuop()-2][1-x.Bo]==1 && tempF >= a.getF()){
                    }else
                    if(!queue.contains(a) || tempF < a.getF()) {
                        // update it first explored
                        a.setG(tempG);
                        a.setF(tempF);

                        // queue exist child > child -> replace
                        if(queue.contains(a))
                            queue.remove(a);

                        queue.add(a);
                        pre.put(a,x);
                        System.out.println("Root:"+actualVertex + " current:" +a +" k(u,v)=" + cost + " h(v)=" + a.getH()+ " g(v)=" + tempG + "f(v)=" + tempF );
                        visited[x.getTrieuPhu()][x.getKeCuop()-2][1-x.Bo] = 1;
                    }
                }
                print = new Node(x.getTrieuPhu()-1, x.getKeCuop()-1,1-x.getBo());
//                System.out.print(print+"{"+print.getH()+"} - ");
                if (isFeasible(new Node(x.getTrieuPhu()-1, x.getKeCuop()-1,1-x.getBo()),x) && visited[x.getTrieuPhu()-1][x.getKeCuop()-1][1-x.Bo] ==0){
//                    visited[x.getTrieuPhu()][x.getKeCuop()-1][1-x.Bo] = 1;
                    Node a = new Node(x.getTrieuPhu()-1, x.getKeCuop()-1,1-x.getBo());
                    double cost = 1;
                    double tempG = actualVertex.getG() + cost;
                    double tempF = tempG + a.getH();

                    if (visited[x.getTrieuPhu()-1][x.getKeCuop()-1][1-x.Bo]==1 && tempF >= a.getF()){
                    }else
                    if(!queue.contains(a) || tempF < a.getF()) {
                        // update it first explored
                        a.setG(tempG);
                        a.setF(tempF);

                        // queue exist child > child -> replace
                        if(queue.contains(a))
                            queue.remove(a);

                        queue.add(a);
                        pre.put(a,x);
                        System.out.println("Root:"+actualVertex + " current:" +a +" k(u,v)=" + cost + " h(v)=" + a.getH()+ " g(v)=" + tempG + "f(v)=" + tempF );
                        visited[x.getTrieuPhu()-1][x.getKeCuop()-1][1-x.Bo] = 1;
                    }
                }
            }
            else{
                Node print = new Node(x.getTrieuPhu()+1, x.getKeCuop(),1-x.getBo());
//                System.out.print(print+"{"+print.getH()+"} - ");
                if (isFeasible(new Node(x.getTrieuPhu()+1, x.getKeCuop(),1-x.getBo()),x) && visited[x.getTrieuPhu()+1][x.getKeCuop()][1-x.Bo] == 0){
//                    visited[x.getTrieuPhu()+1][x.getKeCuop()][1-x.Bo] = 1;
                    Node a = new Node(x.getTrieuPhu()+1, x.getKeCuop(),1-x.getBo());
                    double cost = 1;
                    double tempG = actualVertex.getG() + cost;
                    double tempF = tempG + a.getH();

                    if (visited[x.getTrieuPhu()+1][x.getKeCuop()][1-x.Bo]==1 && tempF >= a.getF()){
                    }else
                    if(!queue.contains(a) || tempF < a.getF()) {
                        // update it first explored
                        a.setG(tempG);
                        a.setF(tempF);

                        // queue exist child > child -> replace
                        if(queue.contains(a))
                            queue.remove(a);

                        queue.add(a);
                        pre.put(a,x);
                        System.out.println("Root:"+actualVertex + " current:" +a +" k(u,v)=" + cost + " h(v)=" + a.getH()+ " g(v)=" + tempG + "f(v)=" + tempF );
                        visited[x.getTrieuPhu()+1][x.getKeCuop()][1-x.Bo] = 1;
                    }
                }
                print = new Node(x.getTrieuPhu()+2, x.getKeCuop(),1-x.getBo());
//                System.out.print(print+"{"+print.getH()+"} - ");
                if (isFeasible(new Node(x.getTrieuPhu()+2, x.getKeCuop(),1-x.getBo()),x) && visited[x.getTrieuPhu()+2][x.getKeCuop()][1-x.Bo] == 0){
//                    visited[x.getTrieuPhu()+2][x.getKeCuop()][1-x.Bo] = 1;
                    Node a = new Node(x.getTrieuPhu()+2, x.getKeCuop(),1-x.getBo());
                    double cost = 1;
                    double tempG = actualVertex.getG() + cost;
                    double tempF = tempG + a.getH();

                    if (visited[x.getTrieuPhu()+2][x.getKeCuop()][1-x.Bo]==1 && tempF >= a.getF()){
                    }else
                    if(!queue.contains(a) || tempF < a.getF()) {
                        // update it first explored
                        a.setG(tempG);
                        a.setF(tempF);

                        // queue exist child > child -> replace
                        if(queue.contains(a))
                            queue.remove(a);

                        queue.add(a);
                        pre.put(a,x);
                        System.out.println("Root:"+actualVertex + " current:" +a +" k(u,v)=" + cost + " h(v)=" + a.getH()+ " g(v)=" + tempG + "f(v)=" + tempF );
                        visited[x.getTrieuPhu()+2][x.getKeCuop()][1-x.Bo] =1;
                    }
                }
                print = new Node(x.getTrieuPhu(), x.getKeCuop()+1,1-x.getBo());
//                System.out.print(print+"{"+print.getH()+"} = ");
                if (isFeasible(new Node(x.getTrieuPhu(), x.getKeCuop()+1,1-x.getBo()),x) && visited[x.getTrieuPhu()][x.getKeCuop()+1][1-x.Bo] == 0){
//                    visited[x.getTrieuPhu()][x.getKeCuop()+1][1-x.Bo] = 1;
                    Node a = new Node(x.getTrieuPhu(), x.getKeCuop()+1,1-x.getBo());
                    double cost = 1;
                    double tempG = actualVertex.getG() + cost;
                    double tempF = tempG + a.getH();

                    if (visited[x.getTrieuPhu()][x.getKeCuop()+1][1-x.Bo]==1 && tempF >= a.getF()){
                    }else
                    if(!queue.contains(a) || tempF < a.getF()) {
                        // update it first explored
                        a.setG(tempG);
                        a.setF(tempF);

                        // queue exist child > child -> replace
                        if(queue.contains(a))
                            queue.remove(a);

                        queue.add(a);
                        pre.put(a,x);
                        System.out.println("Root:"+actualVertex + " current:" +a +" k(u,v)=" + cost + " h(v)=" + a.getH()+ " g(v)=" + tempG + "f(v)=" + tempF );
                        visited[x.getTrieuPhu()][x.getKeCuop()+1][1-x.Bo] = 1;
                    }
                }
                print = new Node(x.getTrieuPhu(), x.getKeCuop()+2,1-x.getBo());
//                System.out.print(print+"{"+print.getH()+"} = ");
                if (isFeasible(new Node(x.getTrieuPhu(), x.getKeCuop()+2,1-x.getBo()),x) && visited[x.getTrieuPhu()][x.getKeCuop()+2][1-x.Bo] == 0){
//                    visited[x.getTrieuPhu()][x.getKeCuop()+2][1-x.Bo] = 1;
                    Node a = new Node(x.getTrieuPhu(), x.getKeCuop()+2,1-x.getBo());
                    double cost = 1;
                    double tempG = actualVertex.getG() + cost;
                    double tempF = tempG + a.getH();

                    if (visited[x.getTrieuPhu()][x.getKeCuop()+2][1-x.Bo]==1 && tempF >= a.getF()){
                    }else
                    if(!queue.contains(a) || tempF < a.getF()) {
                        // update it first explored
                        a.setG(tempG);
                        a.setF(tempF);

                        // queue exist child > child -> replace
                        if(queue.contains(a))
                            queue.remove(a);

                        queue.add(a);
                        pre.put(a,x);
                        System.out.println("Root:"+actualVertex + " current:" +a +" k(u,v)=" + cost + " h(v)=" + a.getH()+ " g(v)=" + tempG + "f(v)=" + tempF );
                        visited[x.getTrieuPhu()][x.getKeCuop()+2][1-x.Bo] = 1;
                    }
                }
                print = new Node(x.getTrieuPhu()+1, x.getKeCuop()+1,1-x.getBo());
//                System.out.print(print+"{"+print.getH()+"} = ");
                if (isFeasible(new Node(x.getTrieuPhu()+1, x.getKeCuop()+1,1-x.getBo()),x) && visited[x.getTrieuPhu()+1][x.getKeCuop()+1][1-x.Bo] == 0){
//                    visited[x.getTrieuPhu()+1][x.getKeCuop()+1][1-x.Bo] = 1;
                    Node a = new Node(x.getTrieuPhu()+1, x.getKeCuop()+1,1-x.getBo());
                    double cost = 1;
                    double tempG = actualVertex.getG() + cost;
                    double tempF = tempG + a.getH();


                        if (visited[x.getTrieuPhu()+1][x.getKeCuop()+1][1-x.Bo]==1 && tempF >= a.getF()){
                        }else
                        if(!queue.contains(a) || tempF < a.getF()) {
                        // update it first explored
                        a.setG(tempG);
                        a.setF(tempF);

                        // queue exist child > child -> replace
                        if(queue.contains(a))
                            queue.remove(a);

                        queue.add(a);
                        pre.put(a,x);
                        System.out.println("Root:"+actualVertex + " current:" +a +" k(u,v)=" + cost + " h(v)=" + a.getH()+ " g(v)=" + tempG + "f(v)=" + tempF );
                            visited[x.getTrieuPhu()+1][x.getKeCuop()+1][1-x.Bo]=1;
                        }
                }
            }

            System.out.print("Danh sach L: ");
            PriorityQueue<Node> tmp = new PriorityQueue(new heuristic());
            for (Node i:queue){
                tmp.add(i);
            }
            while (!tmp.isEmpty()){
                Node i = tmp.remove();
                System.out.print(i+"{"+i.getF()+"}"+" ");
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
        TrieuPhu_KeCuop_Practice_Solution_AStar solve = new TrieuPhu_KeCuop_Practice_Solution_AStar();
        solve.bfs();
    }

}
