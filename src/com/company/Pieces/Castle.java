package com.company.Pieces;

import com.company.ChessBoard;
import com.company.PieceColor;
import com.company.Position;

public class Castle extends Piece{
    public Castle(PieceColor pieceColor) {
        super(pieceColor);
    }

    @Override
   public boolean isMoveLegal(Position currentPosition , Position nextPosition) {
        if((currentPosition.getFile() == nextPosition.getFile()
                || currentPosition.getRank()==nextPosition.getRank())
        && !currentPosition.equals(nextPosition)
        )
            return true;
        return false;
    }

    @Override
    public boolean isBlocked(Position currentPosition, Position nextPosition , ChessBoard board) {
        boolean isMovingRanks = currentPosition.getRank()> nextPosition.getRank() || currentPosition.getRank()<nextPosition.getRank();
        int pathLength = isMovingRanks? Math.abs(currentPosition.getRank() - nextPosition.getRank()) : Math.abs(currentPosition.getFile()-nextPosition.getFile());
        boolean isMovingForward = currentPosition.getRank() < nextPosition.getRank();
        for(int i=0 ; i<pathLength ; i++)
        {
            char file = isMovingForward?(char) (currentPosition.getFile()+i):(char) (currentPosition.getFile()-i);
            char rank = isMovingForward?(char) (currentPosition.getRank()+i):(char) (currentPosition.getRank()-i);

            if(!board.getPositionAt(file,rank).isEmpty())
                if(board.getPositionAt(file,rank).getPiece().getPieceColor()== currentPosition.getPiece().pieceColor)
                    return true;

        }
        return false;
    }

    @Override
    public void move(Position currentPosition, Position nextPosition) {
        // TODO document why this method is empty
    }


    @Override
    public String toString() {
        return "Ca";
    }


}
