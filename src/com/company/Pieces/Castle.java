package com.company.Pieces;

import com.company.ChessBoard;
import com.company.PieceColor;
import com.company.Position;

public class Castle extends Piece{
    public Castle(PieceColor pieceColor) {
        super(pieceColor);
    }


    @Override
    public void updateLegalMoves(Position currentPosition,ChessBoard chessBoard) {
        legalMoves.clear();
        //add pieces that are attacked vertically
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

        }    }

    @Override
    public String toString() {
        return "Castle";
    }

}
