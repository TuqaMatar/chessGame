package com.company;

enum Player {
    WHITE ,
    BlACK;

    @Override
    public String toString() {
        switch (this){
            case WHITE:
                return "WHITE PLAYER";
            case BlACK:
                return "BLACK PLAYER ";
        }
        return "WHITE ";
    }
}