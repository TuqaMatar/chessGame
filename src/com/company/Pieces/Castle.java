package com.company.Pieces;

import com.company.ChessBoard;
import com.company.PieceColor;
import com.company.Position;

import java.util.ArrayList;

public class Castle extends Piece{
    public Castle(PieceColor pieceColor) {
        super(pieceColor);
    }


    @Override
    public void updateLegalMoves(Position currentPosition,ChessBoard chessBoard) {
        legalMoves.clear();

        ArrayList<Position> startingPositions = new ArrayList<>();

        int[][] offsets = {{0, +1}, {0, -1}, {+1, 0}, {-1, 0}};
        startingPositions.add(chessBoard.getPositionAt((char) (currentPosition.getFile() + offsets[0][0]), currentPosition.getRank() + offsets[0][1])); // right diagonal forward
        startingPositions.add(chessBoard.getPositionAt((char) (currentPosition.getFile() + offsets[1][0]), currentPosition.getRank() +offsets[1][1])); //right diagonal backward
        startingPositions.add(chessBoard.getPositionAt((char) (currentPosition.getFile() + offsets[2][0]), currentPosition.getRank() + offsets[2][1])); //left diagonal forward
        startingPositions.add(chessBoard.getPositionAt((char) (currentPosition.getFile() + offsets[3][0]), currentPosition.getRank() +offsets[3][1]));//left diagonal backward


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
        return "Castle";
    }

}
