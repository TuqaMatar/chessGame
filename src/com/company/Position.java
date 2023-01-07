package com.company;

import com.company.Pieces.Piece;

public class Position {
    /**
     * in chess ,a file represents a column and a rank represents a row
     * so for example e5 the file is e and rank is 5 .
     */

    char file;
    char rank;
    Piece piece;

    public Position(char file, char rank) {
        this.file = file;
        this.rank = rank;
    }

    public Position(String position) {
        this.file = position.charAt(0);
        this.rank = position.charAt(1);
    }

    public Position(String position ,Piece piece) {
        this.file = position.charAt(0);
        this.rank = position.charAt(1);
        this.piece = piece;

    }

    public boolean isEmpty(){
        return piece == null;
    }

    public char getFile() {
        return file;
    }

    public char getRank() {
        return rank;
    }

    public Piece getPiece() {
        return piece;
    }

    public void setPiece(Piece piece) {
        this.piece = piece;
    }

    @Override
    public boolean equals(Object obj) {
        if(this ==obj)
            return true;
        if(obj == null || !(obj instanceof Position))
            return false;
        Position other = (Position) obj;
        if(this.file == other.file && this.rank ==other.rank)
            return true;
        return false;
    }

}
