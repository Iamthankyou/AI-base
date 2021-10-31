package com.company.Minimax.Practice.Chess;

import com.github.bhlangonijr.chesslib.Piece;
import com.github.bhlangonijr.chesslib.Side;
import com.github.bhlangonijr.chesslib.Square;

public class Move extends com.github.bhlangonijr.chesslib.move.Move {
    public Move(Square from, Square to) {
        super(from, to);
    }

    public Move(Square from, Square to, Piece promotion) {
        super(from, to, promotion);
    }

    public Move(String move, Side side) {
        super(move, side);
    }

    private int minimaxValue;

    public int getMinimaxValue() {
        return minimaxValue;
    }

    public void setMinimaxValue(int minimaxValue) {
        this.minimaxValue = minimaxValue;
    }
}
