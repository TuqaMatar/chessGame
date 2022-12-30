package com.company.Pieces;

import com.company.ChessBoard;
import com.company.PieceColor;
import com.company.Position;

public class Queen extends Piece {
    public Queen(PieceColor pieceColor) {
        super(pieceColor);
    }

    @Override
    public boolean isBlocked(Position currentPosition, Position nextPosition, ChessBoard board) {
        boolean isMovingVertically = currentPosition.getRank()> nextPosition.getRank() || currentPosition.getRank()<nextPosition.getRank();
        int pathLength = isMovingVertically? Math.abs(currentPosition.getRank() - nextPosition.getRank()) : Math.abs(currentPosition.getFile()-nextPosition.getFile());
        boolean isMovingLikeCastle =currentPosition.getRank() == nextPosition.getRank() || currentPosition.getFile()==nextPosition.getFile();
        boolean isMovingForward = currentPosition.getRank() < nextPosition.getRank();
        boolean isMovingRight = currentPosition.getFile()< nextPosition.getFile();



        if(isMovingLikeCastle){
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
        }

        else {
            pathLength = Math.abs(currentPosition.getFile() - nextPosition.getFile());
            char file = 0, rank = 0;

            for(int i =1 ; i<= pathLength ; i++)
            {

                if(isMovingForward)
                {
                    rank= (char)  (currentPosition.getRank()+i);

                    if(isMovingRight)
                    {
                        file= (char) (currentPosition.getFile()+i) ;
                    }
                    else {
                        file = (char) (currentPosition.getFile()-i);
                    }

                }
                else {
                    file = (char) (currentPosition.getFile()+i);
                    if(isMovingRight)
                    {
                        rank = (char) (currentPosition.getRank()-1);
                    }
                    else
                    {
                        rank= (char)  (currentPosition.getRank()+i);
                    }
                }


            }
      if(!board.getPositionAt(file,rank).isEmpty())
                if(board.getPositionAt(file,rank).getPiece().getPieceColor()== currentPosition.getPiece().pieceColor)
                    return true;
        }


        return false;
    }

    @Override
    public boolean isMoveLegal(Position currentPosition , Position nextPosition) {
        int distanceBetweenFiles = Math.abs(nextPosition.getFile() - currentPosition.getFile());
        int distanceBetweenRanks = Math.abs(nextPosition.getRank() -currentPosition.getRank());
        if(distanceBetweenFiles == distanceBetweenRanks && distanceBetweenFiles>0
        || (currentPosition.getFile() == nextPosition.getFile()
                || currentPosition.getRank()==nextPosition.getRank())
                && !currentPosition.equals(nextPosition))
            return true;
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
        return "Qu";
    }
}
