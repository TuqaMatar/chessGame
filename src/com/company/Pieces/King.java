package com.company.Pieces;

import com.company.ChessBoard;
import com.company.PieceColor;
import com.company.Position;

public class King extends Piece{

    public King(PieceColor pieceColor) {
        super( pieceColor);
    }

    public void isInCheck(){
    }

    @Override
   public boolean isMoveLegal(Position currentPosition , Position nextPosition ) {
        if(nextPosition.getRank() == currentPosition.getRank()+1
                || nextPosition.getRank() == currentPosition.getRank()-1
                || nextPosition.getFile() ==currentPosition.getFile()+1
                || nextPosition.getFile() == currentPosition.getFile()-1)
            return true;
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
    public void updateAttackedPieces(Position currentPosition) {

    }


    @Override
    public String toString() {
        return "Ki";
    }
}
