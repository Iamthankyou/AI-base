package com.company.Minimax.Practice;

import com.github.bhlangonijr.chesslib.Board;
import com.github.bhlangonijr.chesslib.Piece;
import com.github.bhlangonijr.chesslib.Square;
import com.github.bhlangonijr.chesslib.move.Move;

import java.util.ArrayList;
import java.util.List;

public class Chess_Minimax_Alpha_Beta {
    private final int DEPTH = 3;
    private final int MAX_PLAYER = 1;
    private final int MIN_PLAYER = 0;

    private Board board;
    private Move bestMove;

    public Chess_Minimax_Alpha_Beta(Board board) {
        this.board = board;
    }

    private int evalution(String map) {
        int res = 0; // Default
        for (char i : map.toCharArray()) {
            if (i == 'B') {
                res += 30;
            }
            if (i == 'K') {
                res += 900;
            }
            if (i == 'N') {
                res += 35;
            }
            if (i == 'P') {
                res += 10;
            }
            if (i == 'R') {
                res += 50;
            }
            if (i == 'Q') {
                res += 90;
            }

            if (i == 'b') {
                res -= 30;
            }
            if (i == 'k') {
                res -= 900;
            }
            if (i == 'n') {
                res -= 30;
            }
            if (i == 'p') {
                res -= 10;
            }
            if (i == 'r') {
                res -= 50;
            }
            if (i == 'q') {
                res -= 90;
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
            return evalution(board.toString().substring(0, board.toString().length() - 11));
        }

        System.out.println(evalution(board.toString().substring(0, board.toString().length() - 11)));
        if (player == MAX_PLAYER) {
//            System.out.println("DEPTH:" + (depth));
            int best = Integer.MIN_VALUE;
            for (Move move : board.legalMoves()) {
                board.doMove(move);
//                System.out.println(board.toString());
                int val = minimax(depth + 1, MIN_PLAYER, alpha, beta);
                board.undoMove();
                best = Math.max(best, val);
                bestMove = move;
                alpha = Math.max(alpha, best);

                if (alpha > beta)
                    break;
            }
            return best;
        } else {
            int best = Integer.MAX_VALUE;
//            System.out.println("DEPTH:" + (depth));
            for (Move move : board.legalMoves()) {
                board.doMove(move);
//                System.out.println(board.toString());
                int val = minimax(depth + 1, MAX_PLAYER, alpha, beta);
                board.undoMove();
                best = Math.min(best, val);
                beta = Math.min(beta, best);

                if (alpha > beta)
                    break;
            }
            return best;
        }
    }

    public static void main(String args[]) {
        Board board = new Board();
        board.loadFromFen("rnbqkbnr/pp2pppp/3p4/2p5/3PP3/5N2/PPP2PPP/RNBQKB1R b KQkq d3 0 3")   ;
        Chess_Minimax_Alpha_Beta minimax = new Chess_Minimax_Alpha_Beta(board);
        Move move = minimax.getBestMove(1);
        System.out.println(move);
    }
}

