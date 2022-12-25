package com.company;

public enum PieceColor {
    WHITE ,
    BlACK;

    @Override
    public String toString() {
        switch (this){
            case WHITE:
                return "white player";
            case BlACK:
                return "black player";
        }
        return "white player";
    }

}
