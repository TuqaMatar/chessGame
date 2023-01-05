package com.company;

import com.company.Pieces.King;
import com.company.Pieces.Piece;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ChessManager {
    ChessBoard chessBoard;

    boolean isCheckingForCheckmate;

    public ChessBoard getChessBoard() {
        return chessBoard;
    }

    public void setChessBoard(ChessBoard chessBoard) {
        this.chessBoard = chessBoard;
    }

    public Position getCheckedPosition() {
        return checkedPosition;
    }

    public void setCheckedPosition(Position checkedPosition) {
        this.checkedPosition = checkedPosition;
    }

    Position checkedPosition;

    public ChessManager(ChessBoard chessBoard){
        this.chessBoard = chessBoard;
    }
    public boolean isCheckingForCheckmate() {
        return isCheckingForCheckmate;
    }

    public void setCheckingForCheckmate(boolean checkingForCheckmate) {
        isCheckingForCheckmate = checkingForCheckmate;
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
    public boolean isCheckMate(Player currentPlayer) {
        if (isKingChecked())
            if(kingHasNoLegalMoves(currentPlayer,checkedPosition))
            {
                System.out.println("King has no legal moves");
                if(kingCannotBeBlocked(currentPlayer,checkedPosition))
                {
                    return true;

                }

            }

        return false;
    }

    private boolean kingCannotBeBlocked(Player currentPlayer,Position currentPosition) {
        ArrayList<Position> allPositions = chessBoard.getPositions();
        for(Position position :allPositions)
        {
            if(!position.isEmpty())
            {
                if(position.getPiece().getPieceColor() == currentPosition.getPiece().getPieceColor())
                {
                    Piece piece = position.getPiece();
                    ArrayList<Position> legalPositions = piece.getLegalMoves();

                    // simulate a legal move
                    for(Position legalPosition: legalPositions)
                    {
                        if(isLegalMove(currentPlayer,position,legalPosition))
                        {
//                            Position LegalPosition = chessBoard.getPositionAt(legalPosition);
                            Position initialPosition = chessBoard.getPositionAt(position);
                            //simulate that move
                            legalPosition.setPiece(piece);
                            initialPosition.setPiece(null);

                            //if it is not in check then return false;
                            if(!isKingChecked())
                            {
                                return false;
                            }
                            else
                            {
                                // reverse move
                                initialPosition.setPiece(piece);
                                legalPosition.setPiece(null);
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

    private boolean kingHasNoLegalMoves(Player currentPlayer,Position currentPosition) {
        King king = (King) currentPosition.getPiece();
        ArrayList<Position> positions = king.getKingPossibleMoves(currentPosition);
        for (Position position : positions) {
            if (chessBoard.getPositionAt(position) != null) {
                if (isLegalMove(currentPlayer,currentPosition, position) && !isUnderAttack(position)) {
                    return false;
                }
            }
        }

        return true;
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
    public boolean isLegalMove(Player currentPlayer, Position currentPosition, Position nextPosition) {
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
                System.out.println("cannot move  : " + chosenPiece + " because its BLOCKED");
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


}
