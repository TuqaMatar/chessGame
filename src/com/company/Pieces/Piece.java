package com.company.Pieces;

public abstract class Piece {
    String position;

    void isMoveLegal() {
    }

    abstract void move(String currentPosition , String nextPosition);
}
