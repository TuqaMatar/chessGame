package com.company.Pieces;

import com.company.ChessBoard;
import com.company.PieceColor;
import com.company.Position;

public class Queen extends Piece {
    public Queen(PieceColor pieceColor) {
        super(pieceColor);
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
    public void updateAttackedPieces(Position currentPosition, ChessBoard chessBoard) {
        attackedPieces.clear();
        updateDiagonalLegalPositions( currentPosition,  chessBoard);
        updateCastleLikeLegalPositions( currentPosition,  chessBoard);
    }

    public void updateDiagonalLegalPositions(Position currentPosition, ChessBoard chessBoard){
        legalMoves.clear();
        //check all right diagonal forward
        Position testPosition = chessBoard.getPositionAt((char)(currentPosition.getFile()+1) ,currentPosition.getRank()+1);
        if(testPosition!=null){

            while(testPosition!=null)
            {

                Position positionToCheck = chessBoard.getPositionAt((chessBoard.getPositionAt(testPosition.getFile() ,testPosition.getRank())));
                if(positionToCheck !=null)
                {
                    if (positionToCheck.isEmpty())
                        legalMoves.add(positionToCheck);
                    else {
                        if (positionToCheck.getPiece().pieceColor != pieceColor) {
                            legalMoves.add(positionToCheck);
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
                        legalMoves.add(positionToCheck);
                    else {
                        if (positionToCheck.getPiece().pieceColor != pieceColor) {
                            legalMoves.add(positionToCheck);
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
                        legalMoves.add(positionToCheck);
                    else {
                        if (positionToCheck.getPiece().pieceColor != pieceColor) {
                            legalMoves.add(positionToCheck);
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
                        legalMoves.add(positionToCheck);
                    else {
                        if (positionToCheck.getPiece().pieceColor != pieceColor) {
                            legalMoves.add(positionToCheck);
                        }
                        break;

                    }

                }
                testPosition = chessBoard.getPositionAt((char)(testPosition.getFile()-1) ,testPosition.getRank()-1);
            }
        }
    }

    public void updateCastleLikeLegalPositions(Position currentPosition, ChessBoard chessBoard){
        legalMoves.clear();
        //add pieces that are attacked vertically
        //TODO change 8 to chess board dimension because that could change in the future if chess was expanded to another game ?
        char file = currentPosition.getFile();
        char rank = currentPosition.getRank();

        for (int i = Character.getNumericValue(currentPosition.getRank()-1); i >= 1; i--) {
            if (chessBoard.getPositionAt(file, (char)(i+'0')) != null) {

                if (chessBoard.getPositionAt(file, (char)(i+'0')).isEmpty())
                    legalMoves.add(chessBoard.getPositionAt(file, (char)(i+'0')));
                else {
                    if (chessBoard.getPositionAt(file, (char)(i+'0')).getPiece().pieceColor != pieceColor) {
                        legalMoves.add(chessBoard.getPositionAt(file, (char) (i)));
                    }
                    break;
                }
            }

        }

        for ( int i = Character.getNumericValue(currentPosition.getRank()+1); i <= 8; i++) {
            if (chessBoard.getPositionAt(file, (char)(i+'0')) != null) {
                if (chessBoard.getPositionAt(file, (char)(i+'0')).isEmpty())
                    legalMoves.add(chessBoard.getPositionAt(file, (char)(i+'0')));
                else {
                    if (chessBoard.getPositionAt(file, (char)(i+'0')).getPiece().pieceColor != pieceColor) {
                        legalMoves.add(chessBoard.getPositionAt(file, (char)(i+'0')));
                    }
                    break;
                }
            }

        }


        //add pieces that are attacked horizontally
        for (char i = (char) (currentPosition.getFile() -1); i >= 'a'; i--) {
            if (chessBoard.getPositionAt(i, rank) != null) {

                if (chessBoard.getPositionAt(i, rank).isEmpty())
                    legalMoves.add(chessBoard.getPositionAt(i, rank));
                else {
                    if (chessBoard.getPositionAt(i, rank).getPiece().pieceColor != pieceColor) {
                        legalMoves.add(chessBoard.getPositionAt(i, rank));
                    }
                    break;
                }
            }


        }

        for (char i = (char) (currentPosition.getFile() + 1); i <= 'h'; i++) {
            if (chessBoard.getPositionAt(i, rank) != null) {

                if (chessBoard.getPositionAt(i, rank).isEmpty())
                    legalMoves.add(chessBoard.getPositionAt(i, rank));
                else {
                    if (chessBoard.getPositionAt(i, rank).getPiece().pieceColor != pieceColor) {
                        legalMoves.add(chessBoard.getPositionAt(i, rank));
                    }
                    break;
                }


            }

        }
    }

    @Override
    public void updateLegalMoves(Position currentPosition, ChessBoard chessBoard) {
        legalMoves= attackedPieces;
    }

    @Override
    public String toString() {
        return "Queen";
    }
}
