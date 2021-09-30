package com.company.Minimax.Practice.Dodgem;

public class Piece {
    private int x,y;
    private int name;

    public Piece(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public Piece(int x, int y, int name) {
        this.x = x;
        this.y = y;
        this.name = name;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getName() {
        return name;
    }

    public void setName(int name) {
        this.name = name;
    }
}
