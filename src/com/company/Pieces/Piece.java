package com.company.Pieces;

import com.company.ChessBoard;
import com.company.PieceColor;
import com.company.Position;

import java.util.ArrayList;

public abstract class Piece {
    PieceColor pieceColor;
    ArrayList<Position> attackedPieces;

    public Piece(PieceColor pieceColor){
        this.pieceColor = pieceColor;
        attackedPieces = new ArrayList<>();
        legalMoves = new ArrayList<>();
    }

    public ArrayList<Position> getLegalMoves() {
        return legalMoves;
    }
    public ArrayList<Position> getAttackedPieces() {
        return attackedPieces;
    }

    ArrayList<Position> legalMoves;

    public  boolean isBlocked(Position currentPosition , Position nextPosition , ChessBoard board)
    {
        for(Position position :attackedPieces )
        {
            if(position.equals(nextPosition))
                return false;
        }
        return true;
    }
    public boolean isMoveLegal(Position currentPosition , Position nextPosition) {
        return legalMoves.contains(nextPosition); //should go to manager
    }
    public PieceColor getPieceColor() {
        return pieceColor;
    }
    public void updateAttackedPieces(Position currentPosition, ChessBoard chessBoard) {
        attackedPieces = legalMoves;
    }
    public abstract void updateLegalMoves(Position currentPosition,ChessBoard chessBoard);

}
