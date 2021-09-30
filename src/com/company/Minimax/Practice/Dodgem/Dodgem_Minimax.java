package com.company.Minimax.Practice.Dodgem;

import java.util.*;

public class Dodgem_Minimax {
    private final int DEPTH = 3;
    private final int MAX_PLAYER = 1;
    private final int MIN_PLAYER = -1;

    private Dodgem board;
    private Move bestMove;
    private Map<Integer, List<Move>> map = new HashMap<>();
    private List<Move>[] list = new List[DEPTH];

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

        for (int i=0; i<3; i++){
            for (int j=0; j<3; j++){
                if (map[i][j]==1){
                    res+=pointWhite[i][j];
                    if (j-1>=0 && map[i][j-1]==-1){
                        res+=40;
                    }
                    if (j-2>=0 && map[i][j-2]==-1){
                        res+=30;
                    }
                }
                else if (map[i][j]==-1){
                    res+=pointBlack[i][j];

                    if (i+1<3 && map[i+1][j]==1){
                        res-=40;
                    }
                    if (i+2<3 && map[i+2][j]==1){
                        res-=30;
                    }
                }
            }
        }

        return res;
    }

        public Move getBestMove(int player){
        bestMove = null;
        int val = minimax(0,player,Integer.MIN_VALUE,Integer.MAX_VALUE);
        System.out.println(val);
        return bestMove;
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
                bestMove = move;
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





