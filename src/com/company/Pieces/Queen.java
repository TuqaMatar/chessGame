package com.company.Pieces;

import com.company.ChessBoard;
import com.company.PieceColor;
import com.company.Position;

public class Queen extends Piece {
    public Queen(PieceColor pieceColor) {
        super(pieceColor);
    }

    @Override
    public boolean isMoveLegal(Position currentPosition, Position nextPosition) {
        return false;
    }

    @Override
    public boolean isBlocked(Position currentPosition, Position nextPosition, ChessBoard chessBoard) {
        return false;
    }


    @Override
    public void move(Position currentPosition, Position nextPosition) {
        // TODO document why this method is empty
    }

    @Override
    public String toString() {
        return "Qu";
    }
}
