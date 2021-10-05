package com.company.Minimax.Practice.Dodgem;

import java.util.Arrays;

public class Move {
    private int[][] move;

    public Move(int[][] move) {
        this.move = move;
    }

    public int[][] getMove() {
        return move;
    }

    public void setMove(int[][] move) {
        this.move = move;
    }

    private int minimaxValue;

    public int getMinimaxValue() {
        return minimaxValue;
    }

    public void setMinimaxValue(int minimaxValue) {
        this.minimaxValue = minimaxValue;
    }

    @Override
    public String toString() {
        StringBuilder s = new StringBuilder();
        for (int i=0; i<3; i++){
            for (int j=0; j<3; j++){
                s.append(move[i][j]+" ");
            }
            s.append("\n");
        }
        return s.toString() ;
    }
}
