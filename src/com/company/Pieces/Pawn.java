package com.company.Pieces;

import com.company.ChessBoard;
import com.company.PieceColor;
import com.company.Position;
import javafx.geometry.Pos;

import java.util.ArrayList;

public class Pawn extends Piece {
    public void setFirstTimeMoving(boolean firstTimeMoving) {
        isFirstTimeMoving = firstTimeMoving;
    }

    boolean isFirstTimeMoving;

    public boolean isFirstTimeMoving() {
        return isFirstTimeMoving;
    }

    public Pawn(PieceColor pieceColor) {
        super(pieceColor);
        isFirstTimeMoving = true;
    }

    @Override
    public boolean isBlocked(Position currentPosition, Position nextPosition, ChessBoard chessBoard) {
        return !nextPosition.isEmpty() && nextPosition.getPiece().getPieceColor() == currentPosition.getPiece().pieceColor;
    }

    @Override
    public void updateAttackedPieces(Position currentPosition, ChessBoard chessBoard) {
        char rank;
        char file;
        attackedPieces.clear();

        switch (currentPosition.getPiece().getPieceColor()) {
            case WHITE: {
                Position position = chessBoard.getPositionAt((char) (currentPosition.getFile() + 1), (char) (currentPosition.getRank() + 1));
                if (position != null) {
                    if (position.isEmpty()) {
                        attackedPieces.add(position);
                    } else if (position.getPiece().getPieceColor() != pieceColor)
                        attackedPieces.add(position);

                }

                position = chessBoard.getPositionAt((char) (currentPosition.getFile() + 1), (char) (currentPosition.getRank() + 1));
                if (position != null) {
                    if (position.isEmpty()) {
                        attackedPieces.add(position);
                    } else if (position.getPiece().getPieceColor() != pieceColor)
                        attackedPieces.add(position);

                }
            }
            break;
            case BlACK: {
                Position position = chessBoard.getPositionAt((char) (currentPosition.getFile() + 1), (char) (currentPosition.getRank() - 1));
                if (position != null) {
                    if (position.isEmpty()) {
                        attackedPieces.add(position);
                    } else if (position.getPiece().getPieceColor() != pieceColor)
                        attackedPieces.add(position);

                }
                position = chessBoard.getPositionAt((char) (currentPosition.getFile() + -1), (char) (currentPosition.getRank() - 1));
                if (position != null) {
                    if (position.isEmpty()) {
                        attackedPieces.add(position);
                    } else if (position.getPiece().getPieceColor() != pieceColor)
                        attackedPieces.add(position);

                }

            }
            break;
        }
    }

    @Override
    public void updateLegalMoves(Position currentPosition, ChessBoard chessBoard) {
        ArrayList<Position> possiblePositions = new ArrayList<>();
        legalMoves.clear();

        if (isFirstTimeMoving) {
            if (currentPosition.getPiece().getPieceColor() == PieceColor.WHITE)
                possiblePositions.add(new Position((char) (currentPosition.getFile()), (char) (currentPosition.getRank() + 2)));
            else
                possiblePositions.add(new Position((char) (currentPosition.getFile()), (char) (currentPosition.getRank() - 2)));
        }

        if (currentPosition.getPiece().getPieceColor() == PieceColor.WHITE)
            possiblePositions.add(new Position((char) (currentPosition.getFile()), (char) (currentPosition.getRank() + 1)));
        else
            possiblePositions.add(new Position((char) (currentPosition.getFile()), (char) (currentPosition.getRank() - 1)));


        for (Position position : possiblePositions) {
            if (chessBoard.getPositionAt(position) != null) {
                if (position.isEmpty()) {
                    legalMoves.add(position);
                }
            }

        }
    }

    public String toString() {
        return "Pawn";
    }

}
