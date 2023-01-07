package com.company.Pieces;

import com.company.ChessBoard;
import com.company.Position;

import java.util.ArrayList;

public class Bishop extends Piece {
    public Bishop(PieceColor pieceColor) {
        super(pieceColor);
    }

    @Override
    public void updateLegalMoves(Position currentPosition, ChessBoard chessBoard) {
        legalMoves.clear();
        ArrayList<Position> startingPositions = new ArrayList<>();
        int[][] offsets = {{+1, +1}, {+1, -1}, {-1, +1}, {-1, -1}};

        startingPositions.add(chessBoard.getPositionAt((char) (currentPosition.getFile() + 1), currentPosition.getRank() + 1)); // right diagonal forward
        startingPositions.add(chessBoard.getPositionAt((char) (currentPosition.getFile() + 1), currentPosition.getRank() - 1)); //right diagonal backward
        startingPositions.add(chessBoard.getPositionAt((char) (currentPosition.getFile() - 1), currentPosition.getRank() + 1)); //left diagonal forward
        startingPositions.add(chessBoard.getPositionAt((char) (currentPosition.getFile() - 1), currentPosition.getRank() - 1));//left diagonal backward

        for (int i = 0; i < startingPositions.size(); i++) {
            Position testPosition = startingPositions.get(i);
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

                    testPosition = chessBoard.getPositionAt((char) (testPosition.getFile() + offsets[i][0]), testPosition.getRank() + offsets[i][1]);

                }
            }
        }
    }

    @Override
    public String toString() {
        return "Bishop";
    }

}
