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
    public void updateAttackedPieces(Position currentPosition, ChessBoard chessBoard) {
        attackedPieces.clear();
        //add pieces that are attacked vertically
        //TODO change 8 to chess board dimension because that could change in the future if chess was expanded to another game ?
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
        return "Ca";
    }

    @Override
    public void updateLegalMoves(Position currentPosition,ChessBoard chessBoard) {
        legalMoves= attackedPieces;
    }

}
