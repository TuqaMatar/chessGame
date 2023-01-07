package com.company;

import com.company.Pieces.King;
import com.company.Pieces.Piece;
import com.company.Pieces.PieceColor;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.company.Player.BlACK;
import static com.company.Player.WHITE;

public class ChessManager {
    ChessBoard chessBoard;
    CheckMateChecker checkMateChecker;
    boolean isCheckingForCheckmate;


    public ChessManager(ChessBoard chessBoard) {
        this.chessBoard = chessBoard;
        checkMateChecker = new CheckMateChecker(chessBoard);
    }

    public void setCheckingForCheckmate(boolean checkingForCheckmate) {
        isCheckingForCheckmate = checkingForCheckmate;
    }

    public void updatePiecesMoves() {
        chessBoard.updatePiecesLegalMoves();
        chessBoard.updateAttackedPieces();
    }

    public boolean isNotationCorrect(String currentPosition, String nextPosition) {
        Pattern movePattern = Pattern.compile("[a-h][1-8]");
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
            if (!isCheckingForCheckmate)
                System.out.println("no piece exists in " + currentPosition.getFile() + currentPosition.getRank() + ", try again");
            return false;
        }
        if (!chosenPiece.isMoveLegal(currentPosition, nextPosition)) {
            if (!isCheckingForCheckmate)
                System.out.println("Move is not legal for : " + chosenPiece);
            return false;
        }
        if (chosenPiece.isBlocked(currentPosition, nextPosition, chessBoard)) {
            if (!isCheckingForCheckmate) {
                System.out.println("cannot move  : " + chosenPiece + " because its BLOCKED");
            }
            return false;
        }
        if (chosenPiece.getPieceColor() == PieceColor.WHITE && currentPlayer == BlACK && !isCheckingForCheckmate) {
            System.out.println("Black Player cannot move white pieces , try again");
            return false;
        } else if (chosenPiece.getPieceColor() == PieceColor.BlACK && currentPlayer == WHITE && !isCheckingForCheckmate) {
            System.out.println("White Player cannot move black pieces, try again");
            return false;
        }
        return true;
    }

    public boolean isKingChecked(Player otherPlayer) {
        return checkMateChecker.isKingChecked(otherPlayer);
    }

    public boolean isCheckMate(Player otherPlayer) {
      return  checkMateChecker.isCheckMate(otherPlayer);
    }
}
