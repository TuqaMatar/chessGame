package com.company.Pieces;

import com.company.PieceColor;
import com.company.Position;

public class Pawn extends Piece {
    public Pawn( PieceColor pieceColor) {
        super(pieceColor);
    }

    @Override
   public boolean isMoveLegal(Position currentPosition , Position nextPosition) {
        if(currentPosition.getFile() == nextPosition.getFile() && nextPosition.getRank()==currentPosition.getRank()+1)
            return true;
        return false;
    }

    @Override
   public void move(Position currentPosition, Position nextPosition) {

    }

    @Override
    public String toString() {
        return "Pa";
    }

}
