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
//        boolean isMovingVertically = currentPosition.getRank()> nextPosition.getRank() || currentPosition.getRank()<nextPosition.getRank();
//        int pathLength = isMovingVertically? Math.abs(currentPosition.getRank() - nextPosition.getRank()) : Math.abs(currentPosition.getFile()-nextPosition.getFile());
//        boolean isMovingLikeCastle =currentPosition.getRank() == nextPosition.getRank() || currentPosition.getFile()==nextPosition.getFile();
//        boolean isMovingForward = currentPosition.getRank() < nextPosition.getRank();
//        boolean isMovingRight = currentPosition.getFile()< nextPosition.getFile();
//
//        if(isMovingLikeCastle){
//            for(int i=1 ; i<=pathLength ; i++)
//            {
//
//                char file = 0 , rank =0 ;
//                if(isMovingVertically)
//                {
//                    if(isMovingForward)
//                    {
//                        file = (char)currentPosition.getFile();
//                        rank = (char)(currentPosition.getRank()+i);
//                    }
//                    else {
//                        file = (char)currentPosition.getFile();
//                        rank = (char)(currentPosition.getRank()-i);
//                    }
//                }
//                else
//                {
//                    if(isMovingRight)
//                    {
//                        file = (char)(currentPosition.getFile()+i);
//                        rank = (char)currentPosition.getRank();
//                    }
//                    else {
//                        file = (char)(currentPosition.getFile()-i);
//                        rank = (char)(currentPosition.getRank());
//                    }
//                }
//
//                if(!board.getPositionAt(file,rank).isEmpty())
//                    if(board.getPositionAt(file,rank).getPiece().getPieceColor()== currentPosition.getPiece().pieceColor)
//                        return true;
//            }
//        }
//
//        else {
//            pathLength = Math.abs(currentPosition.getFile() - nextPosition.getFile());
//            char file = 0, rank = 0;
//
//            for(int i =1 ; i<= pathLength ; i++)
//            {
//
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
//                    {
//                        rank = (char) (currentPosition.getRank()-1);
//                    }
//                    else
//                    {
//                        rank= (char)  (currentPosition.getRank()+i);
//                    }
//                }
//
//
//            }
//      if(!board.getPositionAt(file,rank).isEmpty())
//                if(board.getPositionAt(file,rank).getPiece().getPieceColor()== currentPosition.getPiece().pieceColor)
//                    return true;
//        }
        for(Position position :attackedPieces )
        {
            if(position.equals(nextPosition))
                return false;

        }


        return true;
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
    public void updateAttackedPieces(Position currentPosition, ChessBoard chessBoard) {
        attackedPieces.clear();

        updateDiagonalAttackedPieces( currentPosition,  chessBoard);
        updateCastleLikeAttackedPieces( currentPosition,  chessBoard);
    }
    @Override
    public void updateAttackedPieces(Position currentPosition) {

    }

    public void updateDiagonalAttackedPieces(Position currentPosition, ChessBoard chessBoard){
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

    public void updateCastleLikeAttackedPieces(Position currentPosition, ChessBoard chessBoard){
        char file = currentPosition.getFile();
        char rank = currentPosition.getRank();

        for (int i = Character.getNumericValue(currentPosition.getRank()-1); i >= 1; i--) {
            if (chessBoard.getPositionAt(file, (char)(i+'0')) != null) {

                if (chessBoard.getPositionAt(file, (char)(i+'0')).isEmpty())
                    attackedPieces.add(chessBoard.getPositionAt(file, (char)(i+'0')));
                else {
                    if (chessBoard.getPositionAt(file, (char)(i+'0')).getPiece().pieceColor != pieceColor) {
                        attackedPieces.add(chessBoard.getPositionAt(file, (char) (i)));
                    }
                    break;
                }
            }

        }

        for ( int i = Character.getNumericValue(currentPosition.getRank()+1); i <= 8; i++) {
            if (chessBoard.getPositionAt(file, (char)(i+'0')) != null) {
                if (chessBoard.getPositionAt(file, (char)(i+'0')).isEmpty())
                    attackedPieces.add(chessBoard.getPositionAt(file, (char)(i+'0')));
                else {
                    if (chessBoard.getPositionAt(file, (char)(i+'0')).getPiece().pieceColor != pieceColor) {
                        attackedPieces.add(chessBoard.getPositionAt(file, (char)(i+'0')));
                    }
                    break;
                }
            }

        }


        //add pieces that are attacked horizontally
        for (char i = (char) (currentPosition.getFile() -1); i >= 'a'; i--) {
            if (chessBoard.getPositionAt(i, rank) != null) {

                if (chessBoard.getPositionAt(i, rank).isEmpty())
                    attackedPieces.add(chessBoard.getPositionAt(i, rank));
                else {
                    if (chessBoard.getPositionAt(i, rank).getPiece().pieceColor != pieceColor) {
                        attackedPieces.add(chessBoard.getPositionAt(i, rank));
                    }
                    break;
                }
            }


        }

        for (char i = (char) (currentPosition.getFile() + 1); i <= 'h'; i++) {
            if (chessBoard.getPositionAt(i, rank) != null) {

                if (chessBoard.getPositionAt(i, rank).isEmpty())
                    attackedPieces.add(chessBoard.getPositionAt(i, rank));
                else {
                    if (chessBoard.getPositionAt(i, rank).getPiece().pieceColor != pieceColor) {
                        attackedPieces.add(chessBoard.getPositionAt(i, rank));
                    }
                    break;
                }


            }

        }
    }
    @Override
    public String toString() {
        return "Qu";
    }
    @Override
    public void updateLegalMoves(Position currentPosition, ChessBoard chessBoard) {
        legalMoves= attackedPieces;
    }
}
