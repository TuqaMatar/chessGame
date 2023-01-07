package com.company.Pieces;

public enum PieceColor {
    WHITE ,
    BlACK;

    @Override
    public String toString() {
        switch (this){
            case WHITE:
                return "WHITE";
            case BlACK:
                return "BLACK";
        }
        return "WHITE";
    }

}
