package com.company.Pieces;

import com.company.ChessBoard;
import com.company.PieceColor;
import com.company.Position;

public class Knight extends Piece{
    public Knight(PieceColor pieceColor) {
        super(pieceColor);
    }

    @Override
    public boolean isMoveLegal(Position currentPosition , Position nextPosition) {
        //knight has 8 possible movements i will check all of them
        if(nextPosition.getRank()== currentPosition.getRank()+2 && nextPosition.getFile() == currentPosition.getFile()+1
         ||nextPosition.getRank()== currentPosition.getRank()+2 && nextPosition.getFile() == currentPosition.getFile()-1
         ||nextPosition.getRank()== currentPosition.getRank()-2 && nextPosition.getFile() == currentPosition.getFile()+1
         ||nextPosition.getRank()== currentPosition.getRank()-2 && nextPosition.getFile() == currentPosition.getFile()-1

         ||nextPosition.getRank()== currentPosition.getRank()+1 && nextPosition.getFile() == currentPosition.getFile()+2
         ||nextPosition.getRank()== currentPosition.getRank()-1 && nextPosition.getFile() == currentPosition.getFile()+2
         ||nextPosition.getRank()== currentPosition.getRank()+1 && nextPosition.getFile() == currentPosition.getFile()-2
         ||nextPosition.getRank()== currentPosition.getRank()-1 && nextPosition.getFile() == currentPosition.getFile()-2)
            return true;
        return false;
    }

    @Override
    public boolean isBlocked(Position currentPosition, Position nextPosition , ChessBoard chessBoard) {
        return false;
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
