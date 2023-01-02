package com.company.Pieces;

import com.company.ChessBoard;
import com.company.PieceColor;
import com.company.Position;

import javax.swing.*;

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
    public boolean isBlocked(Position currentPosition, Position nextPosition, ChessBoard board) {
//        int pathLength = Math.abs(currentPosition.getFile() - nextPosition.getFile());
//
//        boolean isMovingForward = currentPosition.getRank() < nextPosition.getRank();
//        boolean isMovingRight = currentPosition.getFile()< nextPosition.getFile();
//        char file = 0, rank = 0;
//
//            for(int i =1 ; i<= pathLength ; i++)
//            {
//                if(isMovingForward)
//                {
//                    rank= (char)  (currentPosition.getRank()+i);
//
//                    if(isMovingRight)
//                    {
//                        file= (char) (currentPosition.getFile()+i) ;
//                    }
//                    else {
//                        file = (char) (currentPosition.getFile()-i);
//                    }
//
//                }
//                else {
//                    file = (char) (currentPosition.getFile()+i);
//                    if(isMovingRight)
//                        {
//                            rank = (char) (currentPosition.getRank()-1);
//                        }
//                        else
//                        {
//                            rank= (char)  (currentPosition.getRank()+i);
//                        }
//                }
//
//
//                if(board.getPositionAt(file,rank)!=null){
//                    if(!board.getPositionAt(file,rank).isEmpty())
//                        if(board.getPositionAt(file,rank).getPiece().getPieceColor()== currentPosition.getPiece().pieceColor)
//                            return true;
//                }
//
//            }

         for(Position position :attackedPieces )
         {
             if(position.equals(nextPosition))
                 return false;

         }

        return true;
    }

    @Override
    public void move(Position currentPosition, Position nextPosition) {
        // TODO document why this method is empty
    }

    @Override
    public void updateAttackedPieces(Position currentPosition) {

    }


    @Override
    public void updateAttackedPieces(Position currentPosition, ChessBoard chessBoard) {
        attackedPieces.clear();
        //check all right diagonal forward
      Position testPosition = chessBoard.getPositionAt((char)(currentPosition.getFile()+1) ,currentPosition.getRank()+1);
      if(testPosition!=null){

          while(testPosition!=null)
          {

              Position positionToCheck = chessBoard.getPositionAt((chessBoard.getPositionAt(testPosition.getFile() ,testPosition.getRank())));
              if(positionToCheck !=null)
              {
                  if (positionToCheck.isEmpty())
                      attackedPieces.add(positionToCheck);
                  else {
                      if (positionToCheck.getPiece().pieceColor != pieceColor) {
                          attackedPieces.add(positionToCheck);
                      }
                      break;
                  }

              }
              testPosition = chessBoard.getPositionAt((char)(testPosition.getFile()+1) ,testPosition.getRank()+1);

          }
      }

        //check all right diagonal backward
        testPosition = chessBoard.getPositionAt((char)(currentPosition.getFile()+1) ,currentPosition.getRank()-1);
        if(testPosition!=null){

            while(testPosition!=null)
            {

                Position positionToCheck = chessBoard.getPositionAt((chessBoard.getPositionAt(testPosition.getFile() ,testPosition.getRank())));
                if(positionToCheck !=null)
                {
                    if (positionToCheck.isEmpty())
                        attackedPieces.add(positionToCheck);
                    else {
                        if (positionToCheck.getPiece().pieceColor != pieceColor) {
                            attackedPieces.add(positionToCheck);
                        }
                        break;

                    }

                }
                testPosition = chessBoard.getPositionAt((char)(testPosition.getFile()+1) ,testPosition.getRank()-1);
            }
        }
        //check all left diagonal forward
         testPosition = chessBoard.getPositionAt((char)(currentPosition.getFile()-1) ,currentPosition.getRank()+1);
        if(testPosition!=null){

            while(testPosition!=null)
            {

                Position positionToCheck = chessBoard.getPositionAt((chessBoard.getPositionAt(testPosition.getFile() ,testPosition.getRank())));
                if(positionToCheck !=null)
                {
                    if (positionToCheck.isEmpty())
                        attackedPieces.add(positionToCheck);
                    else {
                        if (positionToCheck.getPiece().pieceColor != pieceColor) {
                            attackedPieces.add(positionToCheck);
                        }
                        break;

                    }

                }
                testPosition = chessBoard.getPositionAt((char)(testPosition.getFile()-1) ,testPosition.getRank()+1);
            }
        }

        //check all left diagonal backward
        testPosition = chessBoard.getPositionAt((char)(currentPosition.getFile()-1) ,currentPosition.getRank()-1);
        if(testPosition!=null){

            while(testPosition!=null)
            {

                Position positionToCheck = chessBoard.getPositionAt((chessBoard.getPositionAt(testPosition.getFile() ,testPosition.getRank())));
                if(positionToCheck !=null)
                {
                    if (positionToCheck.isEmpty())
                        attackedPieces.add(positionToCheck);
                    else {
                        if (positionToCheck.getPiece().pieceColor != pieceColor) {
                            attackedPieces.add(positionToCheck);
                        }
                        break;

                    }

                }
                testPosition = chessBoard.getPositionAt((char)(testPosition.getFile()-1) ,testPosition.getRank()-1);
            }
        }
    }

    @Override
    public void updateLegalMoves(Position currentPosition ,ChessBoard chessBoard) {
        legalMoves= attackedPieces;
    }

    @Override
    public String toString() {
        return "Bi";
    }
}
