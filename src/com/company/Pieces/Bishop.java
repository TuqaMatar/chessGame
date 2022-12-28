package com.company.Pieces;

import com.company.PieceColor;
import com.company.Position;

public class Bishop extends Piece{
    public Bishop(PieceColor pieceColor) {
        super(pieceColor);
    }

    @Override
    public boolean isMoveLegal(Position currentPosition , Position nextPosition) {
       int distanceBetweenFiles = Math.abs(nextPosition.getFile() - currentPosition.getFile());
       int distanceBetweenRanks = Math.abs(nextPosition.getRank() -currentPosition.getRank());
       if(distanceBetweenFiles == distanceBetweenRanks && distanceBetweenFiles>0)
           return true;
       return false;

    }

    @Override
    public boolean isBlocked(Position currentPosition, Position nextPosition) {
        int pathLength = Math.abs(currentPosition.getFile() - nextPosition.getFile())
                
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
