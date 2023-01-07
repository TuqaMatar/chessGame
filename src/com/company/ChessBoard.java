package com.company;

import com.company.Pieces.*;

import java.util.ArrayList;

public  class ChessBoard   {
    final int CHESS_BOARD_SIZE = 64;
    ArrayList<Position> positions;

    private static  ChessBoard chessBoard = null ;
    private ChessBoard(){}

    public static ChessBoard getInstance(){
        if(chessBoard == null)
            chessBoard = new ChessBoard();
        return chessBoard;
    }
    public Position getPositionAt(char file, int rank) {
        //binary search to lower complexity
        Position searchedPosition = new Position(file, (char) rank);
        int start = 0;
        int end = positions.size() - 1;

        while (start <= end) {
            int mid = start + (end - start) / 2;
            if (searchedPosition.equals(positions.get(mid))) {
                // Element is found
                return positions.get(mid);
            } else if (searchedPosition.getRank() < positions.get(mid).getRank() || (searchedPosition.getFile() != positions.get(mid).getFile() &&
                    searchedPosition.getFile() > positions.get(mid).getFile())) {
                start = mid + 1;
            } else {
                end = mid - 1;
            }
        }
        return null;
    }
    public Position getPositionAt(Position position) {
        for (Position value : positions) {
            if (value.file == position.getFile() && value.rank == position.getRank()) {
                return value;
            }
        }
        return null;
    }
    ArrayList<Position> initializeBoard() {
        positions = new ArrayList<>();
        //initializing black pieces on board
        positions.add(new Position("a8", new Castle(PieceColor.BlACK)));
        positions.add(new Position("b8", new Knight(PieceColor.BlACK)));
        positions.add(new Position("c8", new Bishop(PieceColor.BlACK)));
        positions.add(new Position("d8", new Queen(PieceColor.BlACK)));
        positions.add(new Position("e8", new King(PieceColor.BlACK)));
        positions.add(new Position("f8", new Bishop(PieceColor.BlACK)));
        positions.add(new Position("g8", new Knight(PieceColor.BlACK)));
        positions.add(new Position("h8", new Castle(PieceColor.BlACK)));

        //initializing black pawns
        for (char c = 'a'; c <= 'h'; c++)
            positions.add(new Position(c + "7", new Pawn(PieceColor.BlACK)));

        //initializing empty spaces
        for (int i = 6; i >= 3; i--) {
            for (char c = 'a'; c <= 'h'; c++)
                positions.add(new Position(c + String.valueOf(i), null));
        }

        //initializing white pawns
        for (char c = 'a'; c <= 'h'; c++)
            positions.add(new Position(c + "2", new Pawn(PieceColor.WHITE)));

        //initializing white pieces on board
        positions.add(new Position("a1", new Castle(PieceColor.WHITE)));
        positions.add(new Position("b1", new Knight(PieceColor.WHITE)));
        positions.add(new Position("c1", new Bishop(PieceColor.WHITE)));
        positions.add(new Position("d1", new Queen(PieceColor.WHITE)));
        positions.add(new Position("e1", new King(PieceColor.WHITE)));
        positions.add(new Position("f1", new Bishop(PieceColor.WHITE)));
        positions.add(new Position("g1", new Knight(PieceColor.WHITE)));
        positions.add(new Position("h1", new Castle(PieceColor.WHITE)));

        return positions;
    }
    public ArrayList<Position> getPositions() {
        return positions;
    }
    public int size() {
        return CHESS_BOARD_SIZE;
    }
    public void updateAttackedPieces() {
        for (Position position : positions) {
            if (!position.isEmpty()) {
                position.getPiece().updateAttackedPieces(position, this);

            }
        }
    }
    public void updatePiecesLegalMoves() {
        for (Position position : positions) {
            if (!position.isEmpty()) {
                position.getPiece().updateLegalMoves(position, this);
            }
        }
    }

}
