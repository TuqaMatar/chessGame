package com.company.Pieces;

import com.company.ChessBoard;
import com.company.PieceColor;
import com.company.Position;

import javax.swing.*;

public class Bishop extends Piece {
    public Bishop(PieceColor pieceColor) {
        super(pieceColor);
    }

    @Override
    public void updateLegalMoves(Position currentPosition, ChessBoard chessBoard) {
        legalMoves.clear();
        //check all right diagonal forward
        Position testPosition = chessBoard.getPositionAt((char) (currentPosition.getFile() + 1), currentPosition.getRank() + 1);
        if (testPosition != null) {

            while (testPosition != null) {

                Position positionToCheck = chessBoard.getPositionAt((chessBoard.getPositionAt(testPosition.getFile(), testPosition.getRank())));
                if (positionToCheck != null) {
                    if (positionToCheck.isEmpty())
                        legalMoves.add(positionToCheck);
                    else {
                        if (positionToCheck.getPiece().pieceColor != pieceColor) {
                            legalMoves.add(positionToCheck);
                        }
                        break;
                    }

                }
                testPosition = chessBoard.getPositionAt((char) (testPosition.getFile() + 1), testPosition.getRank() + 1);

            }
        }

        //check all right diagonal backward
        testPosition = chessBoard.getPositionAt((char) (currentPosition.getFile() + 1), currentPosition.getRank() - 1);
        if (testPosition != null) {

            while (testPosition != null) {

                Position positionToCheck = chessBoard.getPositionAt((chessBoard.getPositionAt(testPosition.getFile(), testPosition.getRank())));
                if (positionToCheck != null) {
                    if (positionToCheck.isEmpty())
                        legalMoves.add(positionToCheck);
                    else {
                        if (positionToCheck.getPiece().pieceColor != pieceColor) {
                            legalMoves.add(positionToCheck);
                        }
                        break;

                    }

                }
                testPosition = chessBoard.getPositionAt((char) (testPosition.getFile() + 1), testPosition.getRank() - 1);
            }
        }
        //check all left diagonal forward
        testPosition = chessBoard.getPositionAt((char) (currentPosition.getFile() - 1), currentPosition.getRank() + 1);
        if (testPosition != null) {

            while (testPosition != null) {

                Position positionToCheck = chessBoard.getPositionAt((chessBoard.getPositionAt(testPosition.getFile(), testPosition.getRank())));
                if (positionToCheck != null) {
                    if (positionToCheck.isEmpty())
                        legalMoves.add(positionToCheck);
                    else {
                        if (positionToCheck.getPiece().pieceColor != pieceColor) {
                            legalMoves.add(positionToCheck);
                        }
                        break;

                    }

                }
                testPosition = chessBoard.getPositionAt((char) (testPosition.getFile() - 1), testPosition.getRank() + 1);
            }
        }

        //check all left diagonal backward
        testPosition = chessBoard.getPositionAt((char) (currentPosition.getFile() - 1), currentPosition.getRank() - 1);
        if (testPosition != null) {

            while (testPosition != null) {

                Position positionToCheck = chessBoard.getPositionAt((chessBoard.getPositionAt(testPosition.getFile(), testPosition.getRank())));
                if (positionToCheck != null) {
                    if (positionToCheck.isEmpty())
                        legalMoves.add(positionToCheck);
                    else {
                        if (positionToCheck.getPiece().pieceColor != pieceColor) {
                            legalMoves.add(positionToCheck);
                        }
                        break;

                    }

                }
                testPosition = chessBoard.getPositionAt((char) (testPosition.getFile() - 1), testPosition.getRank() - 1);
            }
        }
    }

    @Override
    public String toString() {
        return "Bishop";
    }
}
