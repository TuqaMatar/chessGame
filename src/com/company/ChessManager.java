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
    Position checkedPosition;
    boolean isCheckingForCheckmate;

    public void updatePiecesMoves(){
        chessBoard.updatePiecesLegalMoves();
        chessBoard.updateAttackedPieces();
    }

    public ChessManager(ChessBoard chessBoard) {
        this.chessBoard = chessBoard;
    }

    public void setCheckingForCheckmate(boolean checkingForCheckmate) {
        isCheckingForCheckmate = checkingForCheckmate;
    }

    private Position getKingPosition(ChessBoard chessBoard, Player currentPlayer) {
        ArrayList<Position> positions = chessBoard.getPositions();
        for (int i = 0; i < positions.size(); i++) {
            Piece checkPiece = positions.get(i).getPiece();

            if (checkPiece != null) {
                if (checkPiece instanceof King)
                    if (currentPlayer == WHITE && checkPiece.getPieceColor() == PieceColor.WHITE
                            || currentPlayer == BlACK && checkPiece.getPieceColor() == PieceColor.BlACK) {
                        return positions.get(i);
                    }
            }

        }
        return null;
    }

    public boolean isKingChecked(Player otherPlayer) {
        Position kingPosition = getKingPosition(chessBoard, otherPlayer);

        //test if the kings position is in any of his opponents attacked pieces List
        Player opponent = otherPlayer == WHITE ? BlACK : WHITE;

        ArrayList<Position> positions = chessBoard.getPositions();
        for (Position position : positions) {
            if (!position.isEmpty()) {
                Piece testPiece = position.getPiece();
                if ((opponent == BlACK && testPiece.getPieceColor() == PieceColor.BlACK) || (
                        opponent == WHITE && testPiece.getPieceColor() == PieceColor.WHITE
                )) {
                    // get opponents attacked piece lists
                    ArrayList<Position> attackedPositions = testPiece.getAttackedPieces();
                    if (attackedPositions.contains(kingPosition))
                        return true;

                }
            }
        }
        return false;
    }

    public boolean isCheckMate(Player otherPlayer) {
        if (kingHasNoLegalMoves(otherPlayer, checkedPosition)) {
            System.out.println("King has no legal moves");

            if (kingCannotBeBlocked(otherPlayer)) {
                return true;
            }
        }
        return false;
    }

    private boolean kingCannotBeBlocked(Player currentPlayer) {
        ArrayList<Position> allPositions = chessBoard.getPositions();
        for (Position position : allPositions) {
            if (!position.isEmpty()) {
                Piece checkPiece = position.getPiece();
                if ((currentPlayer == WHITE && checkPiece.getPieceColor() == PieceColor.WHITE
                        || currentPlayer == BlACK && checkPiece.getPieceColor() == PieceColor.BlACK)) {

                    ArrayList<Position> legalPositions = checkPiece.getLegalMoves();
                    // simulate a legal move
                    for (int i = 0 ; i<legalPositions.size() ;i++) {
                        if (isLegalMove(currentPlayer, position, legalPositions.get(i))) {
                            Position initialPosition = chessBoard.getPositionAt(position);
                            Position nextLegalPosition = legalPositions.get(i);

                            chessBoard.getPositionAt(nextLegalPosition).setPiece(checkPiece);
                            chessBoard.getPositionAt(initialPosition).setPiece(null);
                            updatePiecesMoves();

                          //if it is not in check then return false;
                            if (!isKingChecked(currentPlayer)) {

                                chessBoard.getPositionAt(initialPosition).setPiece(checkPiece);
                                chessBoard.getPositionAt(nextLegalPosition).setPiece(null);

                                return false;
                            } else {
                                // reverse move
                                chessBoard.getPositionAt(initialPosition).setPiece(checkPiece);
                                chessBoard.getPositionAt(nextLegalPosition).setPiece(null);
                            }
                            updatePiecesMoves();
                        }
                    }
                }

            }
        }
        return true;
    }

    private ArrayList<Position> getAllOpponentsAttackedPositions(Player currentPlayer) {
        Player opponent = currentPlayer == WHITE ? BlACK : WHITE;
        ArrayList<Position> positions = chessBoard.getPositions();
        ArrayList<Position> allAttackedPositions = new ArrayList<>();
        for (Position position : positions) {
            if (!position.isEmpty()) {
                Piece piece = position.getPiece();
                if ((opponent == WHITE && piece.getPieceColor() == PieceColor.WHITE)
                        || ((opponent == BlACK && piece.getPieceColor() == PieceColor.BlACK))) {
                    allAttackedPositions.addAll(piece.getAttackedPieces());
                }
            }
        }
        return allAttackedPositions;
    }

    private boolean kingHasNoLegalMoves(Player otherPlayer, Position currentPosition) {
        Position kingPosition = getKingPosition(chessBoard, otherPlayer);
        Piece kingPiece = kingPosition.getPiece();
        ArrayList<Position> kingLegalMoves = kingPiece.getLegalMoves();
        ArrayList<Position> boardAttackedPositions = getAllOpponentsAttackedPositions(otherPlayer);

        for (Position position : kingLegalMoves) {
            if (!boardAttackedPositions.contains(position))
                return false;
        }
        return true;
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

}
