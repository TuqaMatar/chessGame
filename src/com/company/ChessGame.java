package com.company;

import com.company.Pieces.King;
import com.company.Pieces.Piece;
import javafx.geometry.Pos;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.*;

public class ChessGame {
    Player currentPlayer;
    ChessBoard chessBoard;
    Position checkedPosition;
    boolean isCheckingForCheckmate;

    public void switchPlayer(Player currentPlayer) {
        if (currentPlayer == Player.WHITE)
            this.currentPlayer = Player.BlACK;
        else
            this.currentPlayer = Player.WHITE;
    }

    public boolean isNotationCorrect(String currentPosition, String nextPosition) {
        Pattern movePattern = Pattern.compile("[a-h]+[1-8]+");

        Matcher currentPositionMatcher = movePattern.matcher(currentPosition);
        Matcher nextPositionMatcher = movePattern.matcher(nextPosition);

        if (!currentPositionMatcher.matches()) {
            System.out.println(currentPosition + " is not a piece on the chess board , try again");
            return false;
        }
        if (!nextPositionMatcher.matches()) {
            System.out.println(nextPosition + " is not a piece on the chess board , try again");
            return false;
        }
        return true;
    }

    public boolean isLegalMove(Position currentPosition, Position nextPosition) {
        Piece chosenPiece = currentPosition.getPiece();
        if (chosenPiece == null) {
            if(!isCheckingForCheckmate)
            System.out.println("no piece exists in " + currentPosition.getFile() + currentPosition.getRank() + ", try again");
            return false;
        }
        if (!chosenPiece.isMoveLegal(currentPosition, nextPosition)) {
            if(!isCheckingForCheckmate)
                System.out.println("Move is not legal for : " + chosenPiece);
            return false;
        }
        if (chosenPiece.isBlocked(currentPosition, nextPosition, chessBoard)) {
            if(!isCheckingForCheckmate){
                System.out.println("Move is not legal for : " + chosenPiece);
                System.out.println("BLOCKED");
            }
            return false;
        }
        if (chosenPiece.getPieceColor() == PieceColor.WHITE && currentPlayer == Player.BlACK && !isCheckingForCheckmate) {
            System.out.println("Black Player cannot move white pieces , try again");
            return false;
        } else if (chosenPiece.getPieceColor() == PieceColor.BlACK && currentPlayer == Player.WHITE && !isCheckingForCheckmate) {
            System.out.println("White Player cannot move black pieces, try again");
            return false;
        }
        return true;
    }


    public boolean isKingChecked() {
        Position kingPosition;
        for (int i = 0; i < chessBoard.size(); i++) {
            if (chessBoard.getPositions().get(i).getPiece() instanceof King) {
                kingPosition = chessBoard.getPositions().get(i);
                for (int j = 0; j < chessBoard.size(); j++) {
                    if (chessBoard.getPositions().get(j).getPiece() != null) {
                        for (int k = 0; k < chessBoard.getPositions().get(j).getPiece().getAttackedPieces().size(); k++) {
                            if (kingPosition == chessBoard.getPositions().get(j).getPiece().getAttackedPieces().get(k)) {
                                checkedPosition= kingPosition;
                                return true;
                            }
                        }
                    }

                }
            }
        }
        return false;
    }

    public boolean isCheckMate(Position currentKingPosition) {
        if (isKingChecked())
            if(kingHasNoLegalMoves(currentKingPosition))
            {
                System.out.println("King has no legal moves");
                if(kingCannotBeBlocked(currentKingPosition))
                {
                    return true;

                }

            }

        return false;
    }

    private boolean kingCannotBeBlocked(Position currentPosition) {
        ArrayList<Position> allPositions = chessBoard.getPositions();
        for(Position position :allPositions)
        {
            if(!position.isEmpty())
            {
                if(position.getPiece().getPieceColor() == currentPosition.getPiece().getPieceColor())
                {
                    Piece piece = position.getPiece();
                    ArrayList<Position> legalMoves = piece.getLegalMoves();

                    // simulate a legal move
                    for(Position legalMove : legalMoves)
                    {
                        if(isLegalMove(position,legalMove))
                        {
                            //simulate that move
                            chessBoard.getPositionAt(legalMove).setPiece(piece);
                            chessBoard.getPositionAt(position).setPiece(null);

                            //if it is not in check then return false;
                            if(!isKingChecked())
                            {
                                return false;
                            }
                            else
                            {
                                // reverse move
                                chessBoard.getPositionAt(position).setPiece(piece);
                                chessBoard.getPositionAt(legalMove).setPiece(null);
                            }


                        }
                    }
                }

            }
        }
        return true;
    }

    private boolean isUnderAttack(Position position) {
        ArrayList<Position> boardPositions = chessBoard.getPositions();
        for (Position boardPosition : boardPositions) {
            if(boardPosition.getPiece()!=null){
                ArrayList<Position> attackedPositions = boardPosition.getPiece().getAttackedPieces();
                for (Position attackedPosition : attackedPositions) {
                    if (position.equals(attackedPosition))
                        return true;
                }
            }
        }
        return false;
    }

    private boolean kingHasNoLegalMoves(Position currentPosition) {
        King king = (King) currentPosition.getPiece();
        ArrayList<Position> positions = king.getKingPossibleMoves(currentPosition);
        for (Position position : positions) {
            if (chessBoard.getPositionAt(position) != null) {
                if (isLegalMove(currentPosition, position) && !isUnderAttack(position)) {
                    return false;
                }
            }
        }

        return true;
    }

    public void start() {
        chessBoard = new ChessBoard();
        int moves = 0;
        boolean wrongMove = false;
        Scanner scanner = new Scanner(System.in);
        currentPlayer = Player.WHITE;
        String move;
        Position currentPosition;
        Position nextPosition;
        chessBoard.updateAttackedPieces();

        while (moves < 50) {

            System.out.print("Enter next move (" + currentPlayer + ") :");
            move = scanner.next();
            String scanCurrentPosition = scanner.next();
            String scanNextPosition = scanner.next();

            if (!isNotationCorrect(scanCurrentPosition, scanNextPosition))
                continue;

            currentPosition = new Position(scanCurrentPosition);
            nextPosition = new Position(scanNextPosition);

            for (int i = 0; i < chessBoard.size(); i++) {
                Piece chosenPiece;
                Position boardPiece = chessBoard.positions.get(i);

                if (boardPiece.equals(currentPosition)) {
                    currentPosition = chessBoard.positions.get(i);

                    for (int j = 0; j < chessBoard.size(); j++) {
                        if (chessBoard.positions.get(j).equals(nextPosition)) {
                            nextPosition = chessBoard.positions.get(j);

                            if (!isLegalMove(currentPosition, nextPosition)) {
                                wrongMove = true;
                                break;
                            } else wrongMove = false;

                            Piece movedPiece = currentPosition.getPiece();
                            nextPosition.setPiece(movedPiece);
                            //movedPiece.updateAttackedPieces(nextPosition);
                            currentPosition.setPiece(null);
                            chessBoard.updatePiecesLegalMoves();
                            chessBoard.updateAttackedPieces();
                            break;
                        }
                    }
                }
            }

            if (wrongMove)
                continue;


            if (isKingChecked()) {
                System.out.println("KING IS IN CHECK");

            isCheckingForCheckmate = true;
            if (isCheckMate(checkedPosition)) {

                System.out.println("CHECKMATE for : " + checkedPosition.getPiece().getPieceColor());

                if(checkedPosition.getPiece().getPieceColor()==PieceColor.WHITE)
                    System.out.println(Player.BlACK +"WINS !" );
                else
                    System.out.println(Player.WHITE +"WINS !" );

                break;
            }
            }
            isCheckingForCheckmate = false;
            switchPlayer(currentPlayer);
            moves++;
        }


    }
}
