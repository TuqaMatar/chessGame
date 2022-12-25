package com.company;

import com.company.Pieces.Piece;

import java.util.List;
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

    public boolean isNotationCorrect(String currentPosition , String nextPosition){
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
        ChessBoard chessBoard = new ChessBoard();
        chessBoard.printBoard();

        int moves =0;

        Scanner scanner = new Scanner(System.in);
        currentPlayer = Player.WHITE;
        String move;
        Position currentPosition , nextPosition;

        while (moves<50) {
            System.out.print("Enter next move (" + currentPlayer + ") :");
            move = scanner.next();
            String scanCurrentPosition = scanner.next();
            String scanNextPosition = scanner.next();

            if(!isNotationCorrect(scanCurrentPosition,scanNextPosition))
                continue;
            else
            {
                 currentPosition = new Position(scanCurrentPosition);
                 nextPosition = new Position(scanNextPosition);
            }
            for(int i = 0 ; i<chessBoard.size() ; i++)
            {
                Piece chosenPiece ;;
                if(chessBoard.positions.get(i).equals(currentPosition))
                {
                    chosenPiece= chessBoard.positions.get(i).getPiece();
                    if(!chosenPiece.isMoveLegal(currentPosition,nextPosition)){}
                        System.out.println("Move is not legal for : " + chosenPiece);
                    if(chosenPiece.getPieceColor() == PieceColor.WHITE && currentPlayer==Player.BlACK )
                        System.out.println("Black Player cannot move white pieces");
                    else if(chosenPiece.getPieceColor() == PieceColor.BlACK && currentPlayer==Player.WHITE )
                        System.out.println("White Player cannot move black pieces");


                }

            }

            switchPlayer(currentPlayer);
            moves++;
        }
    }
}
