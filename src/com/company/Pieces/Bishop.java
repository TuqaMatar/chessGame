package com.company.Pieces;

import com.company.PieceColor;
import com.company.Position;

public class Bishop extends Piece{
    public Bishop(PieceColor pieceColor) {
        super(pieceColor);
    }

    @Override
    public boolean isMoveLegal(Position currentPosition , Position nextPosition) {
      return false;
    }

    @Override
    public void move(Position currentPosition, Position nextPosition) {
        // TODO document why this method is empty
    }

    @Override
    public String toString() {
        return "Bi";
    }
}
