package com.company;

import com.company.Pieces.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ChessBoard {
    final int CHESS_BOARD_SIZE = 64;
    ArrayList<Position> positions;

    public ChessBoard() {
        positions = initializeBoard();
    }

    public Position getPositionAt(char file, int rank) {

        for (int i = 0; i < positions.size(); i++) {
            if (positions.get(i).file == file && positions.get(i).rank == rank) {
                return positions.get(i);
            }
        }
        return null;
    }

    ArrayList<Position> initializeBoard() {
        positions = new ArrayList<>();

        //initializing black pieces on board
        positions.add(new Position("a8", new Castle(PieceColor.BlACK), true));
        positions.add(new Position("b8", new Knight(PieceColor.BlACK), true));
        positions.add(new Position("c8", new Bishop(PieceColor.BlACK), true));
        positions.add(new Position("d8", new Queen(PieceColor.BlACK), true));
        positions.add(new Position("e8", new King(PieceColor.BlACK), true));
        positions.add(new Position("f8", new Bishop(PieceColor.BlACK), true));
        positions.add(new Position("g8", new Knight(PieceColor.BlACK), true));
        positions.add(new Position("h8", new Castle(PieceColor.BlACK), true));

        //initializing black pawns
        for (char c = 'a'; c <= 'h'; c++)
            positions.add(new Position(c + "7", new Pawn(PieceColor.BlACK), true));

        //initializing empty spaces
        for (int i = 6; i >= 3; i--) {
            for (char c = 'a'; c <= 'h'; c++)
                positions.add(new Position(c + String.valueOf(i), null, false));
        }

        //initializing white pawns
        for (char c = 'a'; c <= 'h'; c++)
            positions.add(new Position(c + "2", new Pawn(PieceColor.WHITE), true));

        //initializing white pieces on board
        positions.add(new Position("a1", new Castle(PieceColor.WHITE), true));
        positions.add(new Position("b1", new Knight(PieceColor.WHITE), true));
        positions.add(new Position("c1", new Bishop(PieceColor.WHITE), true));
        positions.add(new Position("d1", new Queen(PieceColor.WHITE), true));
        positions.add(new Position("e1", new King(PieceColor.WHITE), true));
        positions.add(new Position("f1", new Bishop(PieceColor.WHITE), true));
        positions.add(new Position("g1", new Knight(PieceColor.WHITE), true));
        positions.add(new Position("h1", new Castle(PieceColor.WHITE), true));

        return positions;
    }

    public void printBoard() {

        for (int i = 0; i < positions.size(); i++) {
            if (i % 8 == 0) {
                System.out.println();
            }
            System.out.print(positions.get(i).piece + " ");
        }
        System.out.println();

    }


    public ArrayList<Position> getPositions() {
        return positions;
    }

    public int size() {
        return CHESS_BOARD_SIZE;
    }
}
