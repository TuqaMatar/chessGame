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
        return (currentPosition.getFile() == nextPosition.getFile()
                || currentPosition.getRank() == nextPosition.getRank())
                && !currentPosition.equals(nextPosition);
    }

    @Override
    public boolean isBlocked(Position currentPosition, Position nextPosition , ChessBoard board) {
        boolean isMovingRanks = currentPosition.getRank()> nextPosition.getRank() || currentPosition.getRank()<nextPosition.getRank();
        boolean isMovingForward = currentPosition.getRank() < nextPosition.getRank();
        int pathLength = isMovingRanks? Math.abs(currentPosition.getRank() - nextPosition.getRank()) : Math.abs(currentPosition.getFile()-nextPosition.getFile());

        boolean isMovingRight = currentPosition.getFile()< nextPosition.getFile();
        boolean isMovingVertically = currentPosition.getRank()> nextPosition.getRank() || currentPosition.getRank()<nextPosition.getRank();

         for(int i=1 ; i<=pathLength ; i++)
            {

                char file = 0 , rank =0 ;
                if(isMovingVertically)
                {
                    if(isMovingForward)
                    {
                        file = (char)currentPosition.getFile();
                        rank = (char)(currentPosition.getRank()+i);
                    }
                    else {
                        file = (char)currentPosition.getFile();
                        rank = (char)(currentPosition.getRank()-i);
                    }
                }
                else
                {
                    if(isMovingRight)
                    {
                        file = (char)(currentPosition.getFile()+i);
                        rank = (char)currentPosition.getRank();
                    }
                    else {
                        file = (char)(currentPosition.getFile()-i);
                        rank = (char)(currentPosition.getRank());
                    }
                }

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
    public void updateAttackedPieces(Position currentPosition) {

    }


    @Override
    public String toString() {
        return "Ca";
    }


}
