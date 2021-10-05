package com.company.Minimax.Practice.Dodgem;

import com.company.Minimax.Cell;

import java.util.*;

public class Dodgem_Minimax {
    private final int DEPTH = 5;
    private int MAX_PLAYER = 1;
    private int MIN_PLAYER = -1;

    public int getMAX_PLAYER() {
        return MAX_PLAYER;
    }

    public void setMAX_PLAYER(int MAX_PLAYER) {
        this.MAX_PLAYER = MAX_PLAYER;
    }

    public int getMIN_PLAYER() {
        return MIN_PLAYER;
    }

    public void setMIN_PLAYER(int MIN_PLAYER) {
        this.MIN_PLAYER = MIN_PLAYER;
    }

    private Dodgem board;
    private Move bestMove;
    private Map<Integer, List<Move>> map = new HashMap<>();
    private List<Move>[] list = new List[DEPTH];
    private List<Move> rootValues;

    public Dodgem_Minimax(Dodgem board) {
        this.board = board;
        this.map = map = new HashMap<>();
        for (int i=0; i<DEPTH; i++){
            map.put(i, new LinkedList<>());
            list[i] = new LinkedList<>();
        }
    }

    public Dodgem_Minimax() {
    }

    private int evalution(int[][] map){
        int res = 0;
        if (MAX_PLAYER==1){
            int[][] pointWhite = new int[][]{
                    {30,35,40},
                    {15,20,25},
                    {0,5,10}
            };

            int[][] pointBlack= new int[][]{
                    {-10,-25,-40},
                    {-5,-20,-35},
                    {0,-15,-30}
            };

            int cBlack = 0, cWhite=0;
            for (int i=0; i<3; i++){
                for (int j=0; j<3; j++){
                    if (map[i][j]==1){
                        res+=pointWhite[i][j];
                        cWhite++;

                        if (j-1>=0 && map[i][j-1]==-1){
                            res+=40;
                        }
                        if (j-2>=0 && map[i][j-2]==-1){
                            res+=30;
                        }
                    }
                    else if (map[i][j]==-1){
                        res+=pointBlack[i][j];
                        cBlack++;

                        if (i+1<3 && map[i+1][j]==1){
                            res-=40;
                        }
                        if (i+2<3 && map[i+2][j]==1){
                            res-=30;
                        }
                    }
                }
            }

            if (cBlack==1){
                res-=500;
            }
            if (cWhite==1){
                res+=500;
            }
            if (cBlack==0){
                res-=10000;
            }
            if (cWhite==0){
                res+=10000;
            }
        }
        else{
            int[][] pointWhite = new int[][]{
                    {-30,-35,-40},
                    {-15,-20,-25},
                    {0,-5,-10}
            };

            int[][] pointBlack= new int[][]{
                    {10,25,40},
                    {5,20,35},
                    {0,15,30}
            };

            int cBlack = 0, cWhite=0;
            for (int i=0; i<3; i++){
                for (int j=0; j<3; j++){
                    if (map[i][j]==1){
                        res+=pointWhite[i][j];
                        cWhite++;

                        if (j-1>=0 && map[i][j-1]==-1){
                            res-=40;
                        }
                        if (j-2>=0 && map[i][j-2]==-1){
                            res-=30;
                        }
                    }
                    else if (map[i][j]==-1){
                        res+=pointBlack[i][j];
                        cBlack++;

                        if (i+1<3 && map[i+1][j]==1){
                            res+=40;
                        }
                        if (i+2<3 && map[i+2][j]==1){
                            res+=30;
                        }
                    }
                }
            }

            if (cBlack==1){
                res+=500;
            }
            if (cWhite==1){
                res-=500;
            }
            if (cBlack==0){
                res+=10000;
            }
            if (cWhite==0){
                res-=10000;
            }
        }

        return res;
    }

    public Move getBestMove(int player){
        rootValues = new LinkedList<>();
        bestMove = null;
        int val = minimax2(0,player,Integer.MIN_VALUE,Integer.MAX_VALUE);
        System.out.println("MINIMAX VAL "+val);
        System.out.println("<=====================>");
        for (Move move:rootValues){
            System.out.print(move.getMinimaxValue()+" ");
            if (move.getMinimaxValue()==val){
                bestMove = move;
//                break;
            }
        }
        System.out.println();
        System.out.println(bestMove);
        if (bestMove==null){
            System.out.println(rootValues.size());
        }
        return bestMove;
    }

    public int minimax2(int depth, int player, int alpha, int beta) {
        if (depth == DEPTH) {
            return evalution(board.getBoard().getMove());
        }

        if (board.isEndGame()==MAX_PLAYER){
            return 10000;
        }

        if (board.isEndGame()==MIN_PLAYER){
            return -10000;
        }

        if (player == MAX_PLAYER) {
            int best = Integer.MIN_VALUE;
            if (board.legalMove().size() == 0) {
                return -10000;
            }

            if (board.legalMove()==null){
                return -10000;
            }

            for (Move move : board.legalMove()) {
                System.out.println("DEPTH:" + (depth));
                System.out.println("Current:");
                System.out.println(board.getBoard());
                board.doMove(move);
                System.out.println(board.getBoard());
                System.out.println("VAl=" + evalution(move.getMove()));
                map.get(depth).add(move);
                list[depth].add(board.getBoard());

                if (board.isEndGame()==1){
                    board.undoMove();
                    if (depth==0){
                        move.setMinimaxValue(10000);
                        rootValues.add(move);
                    }
                    return 10000;
                }

                if (board.isEndGame()==-1){
                    board.undoMove();
                    if (depth==0){
                        move.setMinimaxValue(-10000);
                        rootValues.add(move);
                    }
                    return -10000;
                }

                int val = minimax2(depth + 1, MIN_PLAYER, alpha, beta);

                if (depth==0){
                    move.setMinimaxValue(val);
                    rootValues.add(move);
                }
                board.undoMove();

                best = Math.max(best, val);
                alpha = Math.max(alpha, best);

                if (alpha > beta)
                    break;
            }
            return best;
        } else {
            int best = Integer.MAX_VALUE;
            if (board.legalMove().size() == 0) {
                return 10000;
            }
            if (board.legalMove()==null){
                return 10000;
            }

            for (Move move : board.legalMove()) {
                System.out.println("DEPTH:" + (depth));
                System.out.println("Current:");
                System.out.println(board.getBoard());
                board.doMove(move);
                System.out.println(board.getBoard());
                System.out.println("VAL=" + evalution(move.getMove()));
                map.get(depth).add(move);
                list[depth].add(move);
                if (board.isEndGame()==1){
                    board.undoMove();
                    return 10000;
                }

                if (board.isEndGame()==-1){
                    board.undoMove();
                    return -10000;
                }

                int val = minimax2(depth + 1, MAX_PLAYER, alpha, beta);
                board.undoMove();
                best = Math.min(best, val);
                beta = Math.min(beta, best);

                if (alpha > beta)
                    break;
            }
            return best;
        }
    }
        public int minimax(int depth, int player, int alpha, int beta) {
        if (depth == DEPTH) {
            return evalution(board.getBoard().getMove());
        }

        if (player == MAX_PLAYER) {
            int best = Integer.MIN_VALUE;
            if (board.legalMove().size()==0){
                return Integer.MIN_VALUE;
            }

            for (Move move : board.legalMove()) {
                System.out.println("DEPTH:" + (depth));
                System.out.println("Current:");
                System.out.println(board.getBoard());
                board.doMove(move);
                System.out.println(board.getBoard());
                System.out.println("=>"+evalution(move.getMove()));
                map.get(depth).add(move);
                list[depth].add(board.getBoard());
                int val = minimax(depth + 1, MIN_PLAYER, alpha, beta);
                board.undoMove();

                best = Math.max(best, val);
                alpha = Math.max(alpha, best);

//                if (alpha > beta)
//                    break;
            }
            return best;
        } else {
            int best = Integer.MAX_VALUE;
            if (board.legalMove().size()==0){
                return Integer.MAX_VALUE;
            }

            for (Move move : board.legalMove()) {
                System.out.println("DEPTH:" + (depth));
                System.out.println("Current:");
                System.out.println(board.getBoard());
                board.doMove(move);
                System.out.println(board.getBoard());
                System.out.println("=>"+evalution(move.getMove()));
                map.get(depth).add(move);
                list[depth].add(move);
                int val = minimax(depth + 1, MAX_PLAYER, alpha, beta);
                board.undoMove();
                best = Math.min(best, val);
                beta = Math.min(beta, best);

//                if (alpha > beta)
//                    break;
            }
            return best;
        }
    }

    public void getTree(){
        for (int i=0; i<DEPTH; i++){
            System.out.println("DEPTH == " + i);
            for (Move move:list[i]){
                System.out.println(move);
                System.out.println("Cost="+evalution(move.getMove()));
            }
        }
    }

    public static void main(String args[]){
        Dodgem_Minimax game = new Dodgem_Minimax(new Dodgem());
        Move best = game.getBestMove(1);
        System.out.print(best);
//        List<Move> moves = game.board.legalMove();
//        for (Move move:moves){
//            System.out.println(move);
//        }
        System.out.println("THIS IS TREE:");
        game.getTree();
    }
}





