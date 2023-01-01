package com.company;

import com.company.Pieces.Piece;
import sun.rmi.runtime.Log;

import java.util.List;
import java.util.Scanner;
import java.util.regex.*;

public class ChessGame {
    Player currentPlayer;
    ChessBoard chessBoard ;

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

    public boolean isLegalMove( Position currentPosition , Position nextPosition )
    {
        Piece chosenPiece = currentPosition.getPiece();
        if(chosenPiece == null)
        {
            System.out.println("no piece exists in "+ currentPosition.getFile()+currentPosition.getRank() + ", try again");
            return false;
        }
        if(!chosenPiece.isMoveLegal(currentPosition,nextPosition)){
            System.out.println("Move is not legal for : " + chosenPiece);
            return false;
        }
        if(chosenPiece.isBlocked(currentPosition,nextPosition,chessBoard))
        {
            System.out.println("Move is not legal for : " + chosenPiece);
            System.out.println("BLOCKED");

            return false;
        }
        if(chosenPiece.getPieceColor() == PieceColor.WHITE && currentPlayer==Player.BlACK ){
            System.out.println("Black Player cannot move white pieces , try again");
            return false;
        }
        else if(chosenPiece.getPieceColor() == PieceColor.BlACK && currentPlayer==Player.WHITE ) {
            System.out.println("White Player cannot move black pieces, try again");
            return false;
        }
        return true;
    }

    public boolean isKingChecked(){

        return false ;
    }

    public void start() {
         chessBoard = new ChessBoard();
        //chessBoard.printBoard();
        int moves =0;
        boolean wrongMove = false;
        Scanner scanner = new Scanner(System.in);
        currentPlayer = Player.WHITE;
        String move;
        Position currentPosition , nextPosition;

        chessBoard.updateAttackedPieces();
        while (moves<50) {
            System.out.print("Enter next move (" + currentPlayer + ") :");
            move = scanner.next();
            String scanCurrentPosition = scanner.next();
            String scanNextPosition = scanner.next();

            if(!isNotationCorrect(scanCurrentPosition,scanNextPosition))
                continue;

            currentPosition = new Position(scanCurrentPosition);
            nextPosition = new Position(scanNextPosition);

            for(int i = 0 ; i<chessBoard.size() ; i++)
            {
                    Piece chosenPiece ;
                    Position boardPiece = chessBoard.positions.get(i);

                    if(boardPiece.equals(currentPosition))
                    {
                        currentPosition= chessBoard.positions.get(i);

                    for(int j = 0 ; j<chessBoard.size() ; j++){
                        if(chessBoard.positions.get(j).equals(nextPosition)){
                            nextPosition= chessBoard.positions.get(j);

                            if(!isLegalMove(currentPosition,nextPosition)){
                                wrongMove = true;
                                break;
                            }
                            else wrongMove = false;

                            Piece movedPiece = currentPosition.getPiece();
                            nextPosition.setPiece(movedPiece);
                            //movedPiece.updateAttackedPieces(nextPosition);
                            currentPosition.setPiece(null);
                            chessBoard.updateAttackedPieces();

                            System.out.println("here");
                            break;
                        }
                    }
                }
            }

            if(wrongMove)
                continue;



            switchPlayer(currentPlayer);
            moves++;
        }
    }
}
