package com.company;

import com.company.Pieces.King;
import com.company.Pieces.Piece;
import com.company.Pieces.PieceColor;

import java.util.ArrayList;

import static com.company.Player.BlACK;
import static com.company.Player.WHITE;

public class CheckMateChecker {
    ChessBoard chessBoard;

    public CheckMateChecker( ChessBoard chessBoard) {
        this.chessBoard = chessBoard;
    }

    public Position getKingPosition( Player currentPlayer) {
        ArrayList<Position> positions = chessBoard.getPositions();
        for (int i = 0; i < positions.size(); i++) {
            Piece checkPiece = positions.get(i).getPiece();

            if (checkPiece != null) {
                if (checkPiece instanceof King)
                    if (currentPlayer == WHITE && checkPiece.getPieceColor() == PieceColor.WHITE
                            || currentPlayer == BlACK && checkPiece.getPieceColor() == PieceColor.BlACK) {
                        return positions.get(i);
                    }
            }

        }
        return null;
    }
    public boolean isKingChecked(Player otherPlayer) {
        Position kingPosition = getKingPosition( otherPlayer);

        //test if the kings position is in any of his opponents attacked pieces List
        Player opponent = otherPlayer == WHITE ? BlACK : WHITE;

        ArrayList<Position> positions = chessBoard.getPositions();
        for (Position position : positions) {
            if (!position.isEmpty()) {
                Piece testPiece = position.getPiece();
                if ((opponent == BlACK && testPiece.getPieceColor() == PieceColor.BlACK) || (
                        opponent == WHITE && testPiece.getPieceColor() == PieceColor.WHITE
                )) {
                    // get opponents attacked piece lists
                    ArrayList<Position> attackedPositions = testPiece.getAttackedPieces();
                    if (attackedPositions.contains(kingPosition))
                        return true;

                }
            }
        }
        return false;
    }
    public boolean isCheckMate(Player otherPlayer) {
        if (kingHasNoLegalMoves(otherPlayer)) {
            System.out.println("King has no legal moves");

            if (kingCannotBeBlocked(otherPlayer)) {
                return true;
            }
        }
        return false;
    }
    public ArrayList<Position> getAllOpponentsAttackedPositions(Player currentPlayer) {
        Player opponent = currentPlayer == WHITE ? BlACK : WHITE;
        ArrayList<Position> positions = chessBoard.getPositions();
        ArrayList<Position> allAttackedPositions = new ArrayList<>();
        for (Position position : positions) {
            if (!position.isEmpty()) {
                Piece piece = position.getPiece();
                if ((opponent == WHITE && piece.getPieceColor() == PieceColor.WHITE)
                        || ((opponent == BlACK && piece.getPieceColor() == PieceColor.BlACK))) {
                    allAttackedPositions.addAll(piece.getAttackedPieces());
                }
            }
        }
        return allAttackedPositions;
    }
    public boolean kingHasNoLegalMoves(Player otherPlayer) {
        Position kingPosition = getKingPosition( otherPlayer);
        Piece kingPiece = kingPosition.getPiece();
        ArrayList<Position> kingLegalMoves = kingPiece.getLegalMoves();
        ArrayList<Position> boardAttackedPositions = getAllOpponentsAttackedPositions(otherPlayer);

        for (Position position : kingLegalMoves) {
            if (!boardAttackedPositions.contains(position))
                return false;
        }
        return true;
    }
    public boolean kingCannotBeBlocked(Player currentPlayer) {
        ChessManager manager = new ChessManager(chessBoard);
        ArrayList<Position> allPositions = chessBoard.getPositions();
        for (Position position : allPositions) {
            if (!position.isEmpty()) {
                Piece checkPiece = position.getPiece();
                if ((currentPlayer == WHITE && checkPiece.getPieceColor() == PieceColor.WHITE
                        || currentPlayer == BlACK && checkPiece.getPieceColor() == PieceColor.BlACK)) {

                    ArrayList<Position> legalPositions = checkPiece.getLegalMoves();
                    // simulate a legal move
                    for (int i = 0; i < legalPositions.size(); i++) {
                        if (manager.isLegalMove(currentPlayer, position, legalPositions.get(i))) {
                            Position initialPosition = chessBoard.getPositionAt(position);
                            Position nextLegalPosition = legalPositions.get(i);

                            chessBoard.getPositionAt(nextLegalPosition).setPiece(checkPiece);
                            chessBoard.getPositionAt(initialPosition).setPiece(null);
                            manager.updatePiecesMoves();
                            //if it is not in check then return false;
                            if (!isKingChecked(currentPlayer)) {

                                chessBoard.getPositionAt(initialPosition).setPiece(checkPiece);
                                chessBoard.getPositionAt(nextLegalPosition).setPiece(null);

                                return false;
                            } else {
                                // reverse move
                                chessBoard.getPositionAt(initialPosition).setPiece(checkPiece);
                                chessBoard.getPositionAt(nextLegalPosition).setPiece(null);
                            }
                            manager.updatePiecesMoves();
                        }
                    }
                }

            }
        }
        return true;
    }

}
