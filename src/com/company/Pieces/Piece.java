package com.company.Pieces;

import com.company.ChessBoard;
import com.company.PieceColor;
import com.company.Position;

import java.util.ArrayList;

public abstract class Piece {
    PieceColor pieceColor;

    public ArrayList<Position> getAttackedPieces() {
        return attackedPieces;
    }

    ArrayList<Position> attackedPieces;

    public ArrayList<Position> getLegalMoves() {
        return legalMoves;
    }

    ArrayList<Position> legalMoves;

    public Piece(PieceColor pieceColor){
        this.pieceColor = pieceColor;
        attackedPieces = new ArrayList<>();
        legalMoves = new ArrayList<>();
    }
    public abstract boolean isBlocked(Position currentPosition , Position nextPosition , ChessBoard board);

    public boolean isMoveLegal(Position currentPosition , Position nextPosition) {
        return false;
    }

    public PieceColor getPieceColor() {
        return pieceColor;
    }

    public abstract void updateAttackedPieces (Position currentPosition , ChessBoard chessBoard);
    public abstract void updateLegalMoves(Position currentPosition,ChessBoard chessBoard);

}
