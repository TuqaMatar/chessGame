package com.company.Pieces;

import com.company.PieceColor;
import com.company.Position;

public class King extends Piece{

    public King(PieceColor pieceColor) {
        super( pieceColor);
    }

    public void isInCheck(){
    }

    @Override
   public boolean isMoveLegal(Position currentPosition , Position nextPosition) {
        return false;
    }

    @Override
    public void move(Position currentPosition, Position nextPosition) {

    }


    @Override
    public String toString() {
        return "Ki";
    }
}
