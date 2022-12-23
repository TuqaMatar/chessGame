package com.company;

import java.util.Scanner;
import java.util.regex.*;

public class ChessGame {
    Player currentPlayer;

    public  void switchPlayer(Player currentPlayer) {
        if (currentPlayer == Player.WHITE)
            this.currentPlayer = Player.BlACK;
        else
            this.currentPlayer = Player.WHITE;
    }

    public boolean moveIsValid(String currentPosition , String nextPosition){
        Pattern movePattern = Pattern.compile("[a-h]+[1-8]+");

        Matcher currentPositionMatcher = movePattern.matcher(currentPosition);

        Matcher nextPositionMatcher = movePattern.matcher(nextPosition);

        if(!currentPositionMatcher.matches())
        {
            System.out.println(currentPosition +" is not a piece on the chess board , try again");
            return false;
        }
        if(!nextPositionMatcher.matches())
        {
            System.out.println(nextPosition +" is not a piece on the chess board , try again");
            return false;
        }
        return true;
    }
    public void start() {
        Scanner scanner = new Scanner(System.in);
        currentPlayer = Player.WHITE;
        String move;

        while (true) {
            System.out.print("Enter next move (" + currentPlayer + ") :");
            move = scanner.next();
            String currentPosition = scanner.next();
            String nextPosition = scanner.next();

            if(!moveIsValid(currentPosition,nextPosition))
                continue;

            switchPlayer(currentPlayer);
        }
    }
}
