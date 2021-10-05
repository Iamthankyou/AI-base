package com.company.Minimax.Practice.Dodgem;

import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

public class Dodgem {
    // 1-white, -1-black, 0-none
    private Stack<Move> moves;
    private int isSide;

    public Dodgem() {
        moves = new Stack<>();

        int[][] init = new int[3][3];
        init[0][0] = -1; init[0][1] = 0; init[0][2] = 0;
        init[1][0] = -1; init[1][1] = 0; init[1][2] = 0;
        init[2][0] =  0; init[2][1] = 1; init[2][2] = 1;

        moves.push(new Move(init));
        isSide = 1;
    }

    public void doMove(Move move){
        isSide*=-1;
        moves.push(move);
    }

    public void undoMove(){
        isSide*=-1;
        moves.pop();
    }

//    public boolean isLegalMove(){
//
//    }

    public int isEndGame(){
        int cWhite=0,cBlack=0;
        for (int i=0; i<3; i++){
            for (int j=0; j<3; j++){
                if (moves.peek().getMove()[i][j]==-1){
                    cBlack++;
                }
                if (moves.peek().getMove()[i][j]==1){
                    cWhite++;
                }
            }
        }

        if (cWhite==0){
            return 1;
        }
        if (cBlack==0){
            return -1;
        }
        return 0;
    }

    public Move getBoard(){
        return moves.peek();
    }

    public List<Move> legalMove(){
        List<Move> legalMoves = new LinkedList<>();

        if (isEndGame()!=0){
            return null;
        }

        if (isSide == 1){
            for (int i=0; i<3; i++){
                for (int j=0; j<3; j++){
                    int[][] curr = moves.peek().getMove();
                    if (curr[i][j] == 1){
                        if (i==0){
                            int[][] newMove = curr.clone();
                            for (int x=0; x<newMove.length; x++){
                                newMove[x] = newMove[x].clone();
                            }
                            newMove[i][j] = 0;
                            legalMoves.add(new Move(newMove));
                        }
                        if (i-1>=0 && curr[i-1][j]==0){
                            int[][] newMove = curr.clone();
                            for (int x=0; x<newMove.length; x++){
                                newMove[x] = newMove[x].clone();
                            }
                            newMove[i][j] = 0;
                            newMove[i-1][j] = 1;
                            legalMoves.add(new Move(newMove));
                        }

                        if (j-1>=0 && curr[i][j-1]==0){
                            int[][] newMove = curr.clone();
                            for (int x=0; x<newMove.length; x++){
                                newMove[x] = newMove[x].clone();
                            }
                            newMove[i][j] = 0;
                            newMove[i][j-1] = 1;
                            legalMoves.add(new Move(newMove));
                        }

                        if (j+1<3 && curr[i][j+1]==0){
                            int[][] newMove = curr.clone();
                            for (int x=0; x<newMove.length; x++){
                                newMove[x] = newMove[x].clone();
                            }
                            newMove[i][j] = 0;
                            newMove[i][j+1] = 1;
                            legalMoves.add(new Move(newMove));
                        }
                    }
                }
            }
        }
        else{
            for (int i=0; i<3; i++){
                for (int j=0; j<3; j++){
                    int[][] curr = moves.peek().getMove();
                    if (curr[i][j] == -1){
                        if (j==2){
                            int[][] newMove = curr.clone();
                            for (int x=0; x<newMove.length; x++){
                                newMove[x] = newMove[x].clone();
                            }
                            newMove[i][j] = 0;
                            legalMoves.add(new Move(newMove));
                        }
                        if (i-1>=0 && curr[i-1][j]==0){
                            int[][] newMove = curr.clone();
                            for (int x=0; x<newMove.length; x++){
                                newMove[x] = newMove[x].clone();
                            }
                            newMove[i][j] = 0;
                            newMove[i-1][j] = -1;
                            legalMoves.add(new Move(newMove));
                        }

                        if (i+1<3 && curr[i+1][j]==0){
                            int[][] newMove = curr.clone();
                            for (int x=0; x<newMove.length; x++){
                                newMove[x] = newMove[x].clone();
                            }
                            newMove[i][j] = 0;
                            newMove[i+1][j] = -1;
                            legalMoves.add(new Move(newMove));
                        }

                        if (j+1<3 && curr[i][j+1]==0){
                            int[][] newMove = curr.clone();
                            for (int x=0; x<newMove.length; x++){
                                newMove[x] = newMove[x].clone();
                            }
                            newMove[i][j] = 0;
                            newMove[i][j+1] = -1;
                            legalMoves.add(new Move(newMove));
                        }
                    }
                }
            }
        }

        return legalMoves;
    }
}
