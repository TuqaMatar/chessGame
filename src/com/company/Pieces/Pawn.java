package com.company.Pieces;

import com.company.PieceColor;
import com.company.Position;

public class Pawn extends Piece {
    public Pawn( PieceColor pieceColor) {
        super(pieceColor);
    }

    @Override
   public boolean isMoveLegal(Position currentPosition , Position nextPosition) {
        if(isBlocked(currentPosition,nextPosition))
        {
            System.out.println("illegal move , piece is blocked by :" + nextPosition.getPiece());
            return false;
        }
        switch(currentPosition.getPiece().getPieceColor()){
            case WHITE:
                if(currentPosition.getFile() == nextPosition.getFile() && nextPosition.getRank()==currentPosition.getRank()+1)
                    return true;
            case BlACK:
                if(currentPosition.getFile() == nextPosition.getFile() && nextPosition.getRank()==currentPosition.getRank()-1)
                    return true;
        }
        return false;
    }

    @Override
    public boolean isBlocked(Position currentPosition, Position nextPosition) {
        return !nextPosition.isEmpty();
    }

    @Override
   public void move(Position currentPosition, Position nextPosition) {

    }

    @Override
    public String toString() {
        return "Pa";
    }

}
