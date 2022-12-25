package com.company.Pieces;

import com.company.PieceColor;
import com.company.Position;

public class Knight extends Piece{
    public Knight(PieceColor pieceColor) {
        super(pieceColor);
    }

    @Override
    public boolean isMoveLegal(Position currentPosition , Position nextPosition) {
        return true;
    }
    @Override
    public void move(Position currentPosition, Position nextPosition) {
        // TODO document why this method is empty
    }


    @Override
    public String toString() {
        return "Kn";
    }
}