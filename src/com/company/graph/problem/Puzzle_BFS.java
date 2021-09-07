package com.company.graph.problem;

import java.util.*;

public class Puzzle_BFS {
    public class Node{
        int v,x,d;
        int bo;
        int v5,x3,d8;

        public Node(int v, int x, int d, int bo, int v5, int x3, int d8) {
            this.v = v;
            this.x = x;
            this.d = d;
            this.bo = bo;
            this.v5 = v5;
            this.x3 = x3;
            this.d8 = d8;
        }

        public int getV() {
            return v;
        }

        public void setV(int v) {
            this.v = v;
        }

        public int getX() {
            return x;
        }

        public void setX(int x) {
            this.x = x;
        }

        public int getD() {
            return d;
        }

        public void setD(int d) {
            this.d = d;
        }

        public int getBo() {
            return bo;
        }

        public void setBo(int bo) {
            this.bo = bo;
        }

        public int getV5() {
            return v5;
        }

        public void setV5(int v5) {
            this.v5 = v5;
        }

        public int getX3() {
            return x3;
        }

        public void setX3(int x3) {
            this.x3 = x3;
        }

        public int getD8() {
            return d8;
        }

        public void setD8(int d8) {
            this.d8 = d8;
        }

        @Override
        public String toString() {
            return "Node{" +
                    "v=" + v +
                    ", x=" + x +
                    ", d=" + d +
                    ", bo=" + bo +
                    ", v5=" + v5 +
                    ", x3=" + x3 +
                    ", d8=" + d8 +
                    '}';
        }
    }

    public void bfs(){
        int[][][][][][][] visited= new int[2][2][2][2][2][2][2];

        Queue<Node> queue = new LinkedList<>();

        for (int i=0; i<2; i++){
            for (int j=0; j<2; j++){
                for (int k=0; j<2; j++){
                    for (int l=0; l<2; l++){
                        for (int m=0; m<2; m++){
                            for (int n=0; n<2; n++){
                                for (int o=0; o<2; o++){
                                    visited[i][j][k][l][m][n][o] = 0;
                                }
                            }
                        }
                    }
                }
            }
        }

        Node root = new Node(1,1,1,1,1,1,1);
        queue.add(root);
        Map<Node,Node> pre = new HashMap<>();
        pre.put(root,root);
        visited[1][1][1][1][1][1][1] = 1;

        while( !queue.isEmpty() ){
            Node actualVertex = queue.remove();
            System.out.println(actualVertex);

            if (actualVertex.getBo() == 0 && actualVertex.getV()==0 && actualVertex.getD()==0&&actualVertex.getX()==0&&actualVertex.getD8()==0&&actualVertex.getV5()==0 && actualVertex.getX3()==0){
                System.out.println("Solve");

                Stack<Node> solution = new Stack<>();
                solution.push(actualVertex);

                while (!(actualVertex.getBo() == 1 && actualVertex.getV()==1 && actualVertex.getD()==1&&actualVertex.getX()==1&&actualVertex.getD8()==1&&actualVertex.getV5()==1 && actualVertex.getX3()==1)){
                    actualVertex=pre.get(actualVertex);
                    solution.push(actualVertex);
                }

                System.out.println();

                Node xx = solution.pop();
                System.out.println("1 nguoi xanh, 1 nguoi do, 1 nguoi vang, 1 mon 3, 1 mon 5, 1 mon 8 o bo 1 ");

                while(!solution.isEmpty()){
                    Node xx2 = solution.pop();
                    if (xx2.getBo() == 0) {
                       System.out.print(xx);
                       System.out.println("Chuyen" + (xx2.getV()!=xx.getV()&&xx2.getV()==0?" nguoi vang ":"") + (xx2.getD()!=xx.getD()&&xx2.getD()==0?" nguoi do ":"") + (xx2.getX()!=xx.getX()&&xx2.getX()==0?" nguoi xanh ":"") + (xx2.getV5()!=xx.getV5()&&xx2.getV5()==0?" tui 5 ":"")+ (xx2.getX3()!=xx.getX3()&&xx2.getX3()==0?" tui 3 ":"")+ (xx2.getD8()!=xx.getD8()&&xx2.getD8()==0?" tui 8 ":"") +" sang bo dich");
                    }
                    else{
                        System.out.print(xx);
                        System.out.println("Chuyen" + (xx2.getV()!=xx.getV()&&xx2.getV()==1?" nguoi vang ":"") + (xx2.getD()!=xx.getD()&&xx2.getD()==1?" nguoi do ":"") + (xx2.getX()!=xx.getX()&&xx2.getX()==1?" nguoi xanh ":"") + (xx2.getV5()!=xx.getV5()&&xx2.getV5()==1?" tui 5 ":"")+ (xx2.getX3()!=xx.getX3()&&xx2.getX3()==1?" tui 3 ":"")+ (xx2.getD8()!=xx.getD8()&&xx2.getD8()==1?" tui 8 ":"") +" sang bo den");
                    }

                    xx =xx2;
                }

                break;
            }

            Node x = actualVertex;

            // Move 1 people and 1 puzzle
            if (x.getBo()==1){

                Node a = new Node(1-x.getV(),x.getX(), x.getD(),1-x.getBo(),1-x.getV5(),x.getX3(),x.getD8());
                if (visited[a.getV()][a.getX()][a.getD()][a.getBo()][a.getV5()][a.getX3()][a.getD8()] == 0 && x.getV()==1 && x.getV5()==1 && isFeasible(new Node(1-x.getV(),x.getX(), x.getD(),1-x.getBo(),1-x.getV5(),x.getX3(),x.getD8()))){
                    queue.add(a);
                    pre.put(a,x);
                    visited[a.getV()][a.getX()][a.getD()][a.getBo()][a.getV5()][a.getX3()][a.getD8()] = 1;
                }
                a = new Node(1-x.getV(),x.getX(), x.getD(),1-x.getBo(),x.getV5(),1-x.getX3(),x.getD8());
                if (visited[a.getV()][a.getX()][a.getD()][a.getBo()][a.getV5()][a.getX3()][a.getD8()] == 0 && x.getV()==1 && x.getX3()==1 && isFeasible(new Node(1-x.getV(),x.getX(), x.getD(),1-x.getBo(),x.getV5(),1-x.getX3(),x.getD8()))){
                    queue.add(a);
                    pre.put(a,x);
                    visited[a.getV()][a.getX()][a.getD()][a.getBo()][a.getV5()][a.getX3()][a.getD8()] = 1;
                }
                a = new Node(1-x.getV(),x.getX(), x.getD(),1-x.getBo(),x.getV5(),x.getX3(),1-x.getD8());
                if (visited[a.getV()][a.getX()][a.getD()][a.getBo()][a.getV5()][a.getX3()][a.getD8()] == 0 && x.getV()==1 && x.getD8()==1 && isFeasible(new Node(1-x.getV(),x.getX(), x.getD(),1-x.getBo(),x.getV5(),x.getX3(),1-x.getD8()))){
                    queue.add(a);
                    pre.put(a,x);
                    visited[a.getV()][a.getX()][a.getD()][a.getBo()][a.getV5()][a.getX3()][a.getD8()] = 1;
                }

                a = new Node(x.getV(),1-x.getX(), x.getD(),1-x.getBo(),1-x.getV5(),x.getX3(),x.getD8());
                if (visited[a.getV()][a.getX()][a.getD()][a.getBo()][a.getV5()][a.getX3()][a.getD8()] == 0 && x.getX()==1 && x.getV5()==1 && isFeasible(new Node(x.getV(),1-x.getX(), x.getD(),1-x.getBo(),1-x.getV5(),x.getX3(),x.getD8()))){
                    queue.add(a);
                    pre.put(a,x);
                    visited[a.getV()][a.getX()][a.getD()][a.getBo()][a.getV5()][a.getX3()][a.getD8()] = 1;
                }
                a = new Node(x.getV(),1-x.getX(), x.getD(),1-x.getBo(),x.getV5(),1-x.getX3(),x.getD8());
                if (visited[a.getV()][a.getX()][a.getD()][a.getBo()][a.getV5()][a.getX3()][a.getD8()] == 0 && x.getX()==1 && x.getX3()==1 && isFeasible(new Node(x.getV(),1-x.getX(), x.getD(),1-x.getBo(),x.getV5(),1-x.getX3(),x.getD8()))){
                    queue.add(a);
                    pre.put(a,x);
                    visited[a.getV()][a.getX()][a.getD()][a.getBo()][a.getV5()][a.getX3()][a.getD8()] = 1;
                }
                a = new Node(x.getV(),1-x.getX(), x.getD(),1-x.getBo(),x.getV5(),x.getX3(),1-x.getD8());
                if (visited[a.getV()][a.getX()][a.getD()][a.getBo()][a.getV5()][a.getX3()][a.getD8()] == 0 && x.getX()==1 && x.getD8()==1 && isFeasible(new Node(x.getV(),1-x.getX(), x.getD(),1-x.getBo(),x.getV5(),x.getX3(),1-x.getD8()))){
                    queue.add(a);
                    pre.put(a,x);
                    visited[a.getV()][a.getX()][a.getD()][a.getBo()][a.getV5()][a.getX3()][a.getD8()] = 1;
                }

                a = new Node(x.getV(),x.getX(),1- x.getD(),1-x.getBo(),1-x.getV5(),x.getX3(),x.getD8());
                if (visited[a.getV()][a.getX()][a.getD()][a.getBo()][a.getV5()][a.getX3()][a.getD8()] == 0 && x.getD()==1 && x.getV5()==1 && isFeasible(new Node(x.getV(),x.getX(),1- x.getD(),1-x.getBo(),1-x.getV5(),x.getX3(),x.getD8()))){
                    queue.add(a);
                    pre.put(a,x);
                    visited[a.getV()][a.getX()][a.getD()][a.getBo()][a.getV5()][a.getX3()][a.getD8()] = 1;
                }
                a = new Node(x.getV(),x.getX(),1- x.getD(),1-x.getBo(),x.getV5(),1-x.getX3(),x.getD8());
                if (visited[a.getV()][a.getX()][a.getD()][a.getBo()][a.getV5()][a.getX3()][a.getD8()] == 0 && x.getD()==1 && x.getX3()==1 && isFeasible(new Node(x.getV(),x.getX(),1- x.getD(),1-x.getBo(),x.getV5(),1-x.getX3(),x.getD8()))){
                    queue.add(a);
                    pre.put(a,x);
                    visited[a.getV()][a.getX()][a.getD()][a.getBo()][a.getV5()][a.getX3()][a.getD8()] = 1;
                }
                a = new Node(x.getV(),x.getX(),1- x.getD(),1-x.getBo(),x.getV5(),x.getX3(),1-x.getD8());
                if (visited[a.getV()][a.getX()][a.getD()][a.getBo()][a.getV5()][a.getX3()][a.getD8()] == 0 && x.getD()==1 && x.getD8()==1 && isFeasible(new Node(x.getV(),x.getX(),1- x.getD(),1-x.getBo(),x.getV5(),x.getX3(),1-x.getD8()))){
                    queue.add(a);
                    pre.put(a,x);
                    visited[a.getV()][a.getX()][a.getD()][a.getBo()][a.getV5()][a.getX3()][a.getD8()] = 1;
                }

                // Move 2 people
                a = new Node(x.getV(),1-x.getX(),1- x.getD(),1-x.getBo(),x.getV5(),x.getX3(),x.getD8());
                if (visited[a.getV()][a.getX()][a.getD()][a.getBo()][a.getV5()][a.getX3()][a.getD8()] == 0 && x.getD()==1 && x.getX()==1 && isFeasible(new Node(x.getV(),1-x.getX(),1- x.getD(),1-x.getBo(),x.getV5(),x.getX3(),x.getD8()))){
                    queue.add(a);
                    pre.put(a,x);
                    visited[a.getV()][a.getX()][a.getD()][a.getBo()][a.getV5()][a.getX3()][a.getD8()] = 1;
                }
                a = new Node(1-x.getV(),x.getX(),1- x.getD(),1-x.getBo(),x.getV5(),x.getX3(),x.getD8());
                if (visited[a.getV()][a.getX()][a.getD()][a.getBo()][a.getV5()][a.getX3()][a.getD8()] == 0 && x.getD()==1 && x.getV()==1 && isFeasible(new Node(1-x.getV(),x.getX(),1- x.getD(),1-x.getBo(),x.getV5(),x.getX3(),x.getD8()))){
                    queue.add(a);
                    pre.put(a,x);
                    visited[a.getV()][a.getX()][a.getD()][a.getBo()][a.getV5()][a.getX3()][a.getD8()] = 1;
                }
                a = new Node(1-x.getV(),1-x.getX(),x.getD(),1-x.getBo(),x.getV5(),x.getX3(),x.getD8());
                if (visited[a.getV()][a.getX()][a.getD()][a.getBo()][a.getV5()][a.getX3()][a.getD8()] == 0 && x.getV()==1 && x.getX()==1 && isFeasible(new Node(1-x.getV(),1-x.getX(),x.getD(),1-x.getBo(),x.getV5(),x.getX3(),x.getD8()))){
                    queue.add(a);
                    pre.put(a,x);
                    visited[a.getV()][a.getX()][a.getD()][a.getBo()][a.getV5()][a.getX3()][a.getD8()] = 1;
                }

                // Move 1 people
                a = new Node(x.getV(),x.getX(),1-x.getD(),1-x.getBo(),x.getV5(),x.getX3(),x.getD8());
                if (visited[a.getV()][a.getX()][a.getD()][a.getBo()][a.getV5()][a.getX3()][a.getD8()] == 0 && x.getD()==1 && isFeasible(new Node(x.getV(),x.getX(),1-x.getD(),1-x.getBo(),x.getV5(),x.getX3(),x.getD8()))){
                    queue.add(a);
                    pre.put(a,x);
                    visited[a.getV()][a.getX()][a.getD()][a.getBo()][a.getV5()][a.getX3()][a.getD8()] = 1;
                }
                a = new Node(x.getV(),1-x.getX(),x.getD(),1-x.getBo(),x.getV5(),x.getX3(),x.getD8());
                if (visited[a.getV()][a.getX()][a.getD()][a.getBo()][a.getV5()][a.getX3()][a.getD8()] == 0 && x.getX()==1 && isFeasible(new Node(x.getV(),1-x.getX(),x.getD(),1-x.getBo(),x.getV5(),x.getX3(),x.getD8()))){
                    queue.add(a);
                    pre.put(a,x);
                    visited[a.getV()][a.getX()][a.getD()][a.getBo()][a.getV5()][a.getX3()][a.getD8()] = 1;
                }
                a = new Node(1-x.getV(),x.getX(),x.getD(),1-x.getBo(),x.getV5(),x.getX3(),x.getD8());
                if (visited[a.getV()][a.getX()][a.getD()][a.getBo()][a.getV5()][a.getX3()][a.getD8()] == 0 && x.getV()==1 && isFeasible(new Node(1-x.getV(),x.getX(),x.getD(),1-x.getBo(),x.getV5(),x.getX3(),x.getD8()))){
                    queue.add(a);
                    pre.put(a,x);
                    visited[a.getV()][a.getX()][a.getD()][a.getBo()][a.getV5()][a.getX3()][a.getD8()] = 1;
                }
//
//            // Move 1 puzzle
//
//            if (isFeasible(new Node(x.getV(),x.getX(),x.getD(),1-x.getBo(),1-x.getV5(),x.getX3(),x.getD8()))){
//                Node a = new Node(x.getV(),x.getX(),x.getD(),1-x.getBo(),1-x.getV5(),x.getX3(),x.getD8());
//                queue.add(a);
//                pre.put(a,x);
//            }
//            if (isFeasible(new Node(x.getV(),x.getX(),x.getD(),1-x.getBo(),x.getV5(),1-x.getX3(),x.getD8()))){
//                Node a = new Node(x.getV(),x.getX(),x.getD(),1-x.getBo(),x.getV5(),1-x.getX3(),x.getD8());
//                queue.add(a);
//                pre.put(a,x);
//            }
//            if (isFeasible(new Node(x.getV(),x.getX(),x.getD(),1-x.getBo(),x.getV5(),x.getX3(),1-x.getD8()))){
//                Node a = new Node(x.getV(),x.getX(),x.getD(),1-x.getBo(),x.getV5(),x.getX3(),1-x.getD8());
//                queue.add(a);
//                pre.put(a,x);
//            }
            }
            else{

                Node a = new Node(1-x.getV(),x.getX(), x.getD(),1-x.getBo(),1-x.getV5(),x.getX3(),x.getD8());
                if (visited[a.getV()][a.getX()][a.getD()][a.getBo()][a.getV5()][a.getX3()][a.getD8()] == 0 && x.getV()==0 && x.getV5()==0 && isFeasible(new Node(1-x.getV(),x.getX(), x.getD(),1-x.getBo(),1-x.getV5(),x.getX3(),x.getD8()))){
                    queue.add(a);
                    pre.put(a,x);
                    visited[a.getV()][a.getX()][a.getD()][a.getBo()][a.getV5()][a.getX3()][a.getD8()] = 1;
                }

                a = new Node(1-x.getV(),x.getX(), x.getD(),1-x.getBo(),x.getV5(),1-x.getX3(),x.getD8());
                if (visited[a.getV()][a.getX()][a.getD()][a.getBo()][a.getV5()][a.getX3()][a.getD8()] == 0 && x.getV()==0 && x.getX3()==0 && isFeasible(new Node(1-x.getV(),x.getX(), x.getD(),1-x.getBo(),x.getV5(),1-x.getX3(),x.getD8()))){
                    queue.add(a);
                    pre.put(a,x);
                    visited[a.getV()][a.getX()][a.getD()][a.getBo()][a.getV5()][a.getX3()][a.getD8()] = 1;
                }
                a = new Node(1-x.getV(),x.getX(), x.getD(),1-x.getBo(),x.getV5(),x.getX3(),1-x.getD8());
                if (visited[a.getV()][a.getX()][a.getD()][a.getBo()][a.getV5()][a.getX3()][a.getD8()] == 0 && x.getV()==0 && x.getD8()==0 && isFeasible(new Node(1-x.getV(),x.getX(), x.getD(),1-x.getBo(),x.getV5(),x.getX3(),1-x.getD8()))){
                    queue.add(a);
                    pre.put(a,x);
                    visited[a.getV()][a.getX()][a.getD()][a.getBo()][a.getV5()][a.getX3()][a.getD8()] = 1;
                }

                a = new Node(x.getV(),1-x.getX(), x.getD(),1-x.getBo(),1-x.getV5(),x.getX3(),x.getD8());
                if (visited[a.getV()][a.getX()][a.getD()][a.getBo()][a.getV5()][a.getX3()][a.getD8()] == 0 && x.getX()==0 && x.getV5()==0 && isFeasible(new Node(x.getV(),1-x.getX(), x.getD(),1-x.getBo(),1-x.getV5(),x.getX3(),x.getD8()))){
                    queue.add(a);
                    pre.put(a,x);
                    visited[a.getV()][a.getX()][a.getD()][a.getBo()][a.getV5()][a.getX3()][a.getD8()] = 1;
                }
                a = new Node(x.getV(),1-x.getX(), x.getD(),1-x.getBo(),x.getV5(),1-x.getX3(),x.getD8());
                if (visited[a.getV()][a.getX()][a.getD()][a.getBo()][a.getV5()][a.getX3()][a.getD8()] == 0 && x.getX()==0 && x.getX3()==0 && isFeasible(new Node(x.getV(),1-x.getX(), x.getD(),1-x.getBo(),x.getV5(),1-x.getX3(),x.getD8()))){
                    queue.add(a);
                    pre.put(a,x);
                    visited[a.getV()][a.getX()][a.getD()][a.getBo()][a.getV5()][a.getX3()][a.getD8()] = 1;
                }
                a = new Node(x.getV(),1-x.getX(), x.getD(),1-x.getBo(),x.getV5(),x.getX3(),1-x.getD8());
                if (visited[a.getV()][a.getX()][a.getD()][a.getBo()][a.getV5()][a.getX3()][a.getD8()] == 0 && x.getX()==0 && x.getD8()==0 && isFeasible(new Node(x.getV(),1-x.getX(), x.getD(),1-x.getBo(),x.getV5(),x.getX3(),1-x.getD8()))){
                    queue.add(a);
                    pre.put(a,x);
                    visited[a.getV()][a.getX()][a.getD()][a.getBo()][a.getV5()][a.getX3()][a.getD8()] = 1;
                }

                a = new Node(x.getV(),x.getX(),1- x.getD(),1-x.getBo(),1-x.getV5(),x.getX3(),x.getD8());
                if (visited[a.getV()][a.getX()][a.getD()][a.getBo()][a.getV5()][a.getX3()][a.getD8()] == 0 && x.getD()==0 && x.getV5()==0 && isFeasible(new Node(x.getV(),x.getX(),1- x.getD(),1-x.getBo(),1-x.getV5(),x.getX3(),x.getD8()))){
                    queue.add(a);
                    pre.put(a,x);
                    visited[a.getV()][a.getX()][a.getD()][a.getBo()][a.getV5()][a.getX3()][a.getD8()] = 1;
                }
                a = new Node(x.getV(),x.getX(),1- x.getD(),1-x.getBo(),x.getV5(),1-x.getX3(),x.getD8());
                if (visited[a.getV()][a.getX()][a.getD()][a.getBo()][a.getV5()][a.getX3()][a.getD8()] == 0 && x.getD()==0 && x.getX3()==0 && isFeasible(new Node(x.getV(),x.getX(),1- x.getD(),1-x.getBo(),x.getV5(),1-x.getX3(),x.getD8()))){
                    queue.add(a);
                    pre.put(a,x);
                    visited[a.getV()][a.getX()][a.getD()][a.getBo()][a.getV5()][a.getX3()][a.getD8()] = 1;
                }
                a = new Node(x.getV(),x.getX(),1- x.getD(),1-x.getBo(),x.getV5(),x.getX3(),1-x.getD8());
                if (visited[a.getV()][a.getX()][a.getD()][a.getBo()][a.getV5()][a.getX3()][a.getD8()] == 0 && x.getD()==0 && x.getD8()==0 && isFeasible(new Node(x.getV(),x.getX(),1- x.getD(),1-x.getBo(),x.getV5(),x.getX3(),1-x.getD8()))){
                    queue.add(a);
                    pre.put(a,x);
                    visited[a.getV()][a.getX()][a.getD()][a.getBo()][a.getV5()][a.getX3()][a.getD8()] = 1;
                }

                // Move 2 people
                a = new Node(x.getV(),1-x.getX(),1- x.getD(),1-x.getBo(),x.getV5(),x.getX3(),x.getD8());
                if (visited[a.getV()][a.getX()][a.getD()][a.getBo()][a.getV5()][a.getX3()][a.getD8()] == 0 && x.getD()==0 && x.getX()==0 && isFeasible(new Node(x.getV(),1-x.getX(),1- x.getD(),1-x.getBo(),x.getV5(),x.getX3(),x.getD8()))){
                    queue.add(a);
                    pre.put(a,x);
                    visited[a.getV()][a.getX()][a.getD()][a.getBo()][a.getV5()][a.getX3()][a.getD8()] = 1;
                }
                a = new Node(1-x.getV(),x.getX(),1- x.getD(),1-x.getBo(),x.getV5(),x.getX3(),x.getD8());
                if (visited[a.getV()][a.getX()][a.getD()][a.getBo()][a.getV5()][a.getX3()][a.getD8()] == 0 && x.getD()==0 && x.getV()==0 && isFeasible(new Node(1-x.getV(),x.getX(),1- x.getD(),1-x.getBo(),x.getV5(),x.getX3(),x.getD8()))){
                    queue.add(a);
                    pre.put(a,x);
                    visited[a.getV()][a.getX()][a.getD()][a.getBo()][a.getV5()][a.getX3()][a.getD8()] = 1;
                }
                a = new Node(1-x.getV(),1-x.getX(),x.getD(),1-x.getBo(),x.getV5(),x.getX3(),x.getD8());
                if (visited[a.getV()][a.getX()][a.getD()][a.getBo()][a.getV5()][a.getX3()][a.getD8()] == 0 && x.getV()==0 && x.getX()==0 && isFeasible(new Node(1-x.getV(),1-x.getX(),x.getD(),1-x.getBo(),x.getV5(),x.getX3(),x.getD8()))){
                    queue.add(a);
                    pre.put(a,x);
                    visited[a.getV()][a.getX()][a.getD()][a.getBo()][a.getV5()][a.getX3()][a.getD8()] = 1;
                }

                // Move 1 people
                a = new Node(x.getV(),x.getX(),1-x.getD(),1-x.getBo(),x.getV5(),x.getX3(),x.getD8());
                if (visited[a.getV()][a.getX()][a.getD()][a.getBo()][a.getV5()][a.getX3()][a.getD8()] == 0 && x.getD()==0 && isFeasible(new Node(x.getV(),x.getX(),1-x.getD(),1-x.getBo(),x.getV5(),x.getX3(),x.getD8()))){
                    queue.add(a);
                    pre.put(a,x);
                    visited[a.getV()][a.getX()][a.getD()][a.getBo()][a.getV5()][a.getX3()][a.getD8()] = 1;
                }
                a = new Node(x.getV(),1-x.getX(),x.getD(),1-x.getBo(),x.getV5(),x.getX3(),x.getD8());
                if (visited[a.getV()][a.getX()][a.getD()][a.getBo()][a.getV5()][a.getX3()][a.getD8()] == 0 && x.getX()==0 && isFeasible(new Node(x.getV(),1-x.getX(),x.getD(),1-x.getBo(),x.getV5(),x.getX3(),x.getD8()))){
                    queue.add(a);
                    pre.put(a,x);
                    visited[a.getV()][a.getX()][a.getD()][a.getBo()][a.getV5()][a.getX3()][a.getD8()] = 1;
                }
                a = new Node(1-x.getV(),x.getX(),x.getD(),1-x.getBo(),x.getV5(),x.getX3(),x.getD8());
                if (visited[a.getV()][a.getX()][a.getD()][a.getBo()][a.getV5()][a.getX3()][a.getD8()] == 0 && x.getV()==0 && isFeasible(new Node(1-x.getV(),x.getX(),x.getD(),1-x.getBo(),x.getV5(),x.getX3(),x.getD8()))){
                    queue.add(a);
                    pre.put(a,x);
                    visited[a.getV()][a.getX()][a.getD()][a.getBo()][a.getV5()][a.getX3()][a.getD8()] = 1;
                }
            }


        }
    }

    private boolean isFeasible(Node x) {
        int numBo1 =0, numBo2=0;
        numBo1 = x.getD8()*8+x.getV5()*5+x.getX3()*3;
        numBo2 = 16-numBo1;

        int numPeo1 = (x.getD()==1?8:0) + (x.getX()==1?3:0) + (x.getV()==1?5:0);
        int numPeo2 = 16-numPeo1;

        if (numBo1 > numPeo1 && numPeo1!=0|| numBo2>numPeo2&&numPeo2!=0) {
            return false;
        }

        return true;

    }

    public static void main(String args[]){
        Puzzle_BFS solve = new Puzzle_BFS();
        solve.bfs();
    }

}
