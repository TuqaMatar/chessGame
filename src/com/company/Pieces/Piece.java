package com.company.Pieces;

import com.company.PieceColor;
import com.company.Position;

public abstract class Piece {
    PieceColor pieceColor;

    public Piece(PieceColor pieceColor){
        this.pieceColor = pieceColor;
    }
    public abstract boolean isMoveLegal(Position currentPosition , Position nextPosition);
    public abstract void move(Position currentPosition , Position nextPosition);

    public PieceColor getPieceColor() {
        return pieceColor;
    }
}
