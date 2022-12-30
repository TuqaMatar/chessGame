package com.company.Pieces;

import com.company.ChessBoard;
import com.company.PieceColor;
import com.company.Position;

public class Pawn extends Piece {
    public Pawn( PieceColor pieceColor) {
        super(pieceColor);
    }

    @Override
   public boolean isMoveLegal(Position currentPosition , Position nextPosition) {
        switch(currentPosition.getPiece().getPieceColor()){
            case WHITE:
                if(currentPosition.getFile() == nextPosition.getFile() && nextPosition.getRank()==currentPosition.getRank()+1)
                    return true;
                break;
            case BlACK:
                if(currentPosition.getFile() == nextPosition.getFile() && nextPosition.getRank()==currentPosition.getRank()-1)
                    return true;
                break;
        }
        return false;
    }

    @Override
    public boolean isBlocked(Position currentPosition, Position nextPosition, ChessBoard chessBoard) {
        return !nextPosition.isEmpty() && nextPosition.getPiece().getPieceColor()==currentPosition.getPiece().pieceColor;

    }

    @Override
   public void move(Position currentPosition, Position nextPosition) {

    }

    @Override
    public String toString() {
        return "Pa";
    }

}
