package com.company.Pieces;

import com.company.ChessBoard;
import com.company.PieceColor;
import com.company.Position;

import java.util.ArrayList;

public class Knight extends Piece {
    public Knight(PieceColor pieceColor) {
        super(pieceColor);
    }

    @Override
    public boolean isMoveLegal(Position currentPosition, Position nextPosition) {
        //knight has 8 possible movements i will check all of them
        if (nextPosition.getRank() == currentPosition.getRank() + 2 && nextPosition.getFile() == currentPosition.getFile() + 1
                || nextPosition.getRank() == currentPosition.getRank() + 2 && nextPosition.getFile() == currentPosition.getFile() - 1
                || nextPosition.getRank() == currentPosition.getRank() - 2 && nextPosition.getFile() == currentPosition.getFile() + 1
                || nextPosition.getRank() == currentPosition.getRank() - 2 && nextPosition.getFile() == currentPosition.getFile() - 1

                || nextPosition.getRank() == currentPosition.getRank() + 1 && nextPosition.getFile() == currentPosition.getFile() + 2
                || nextPosition.getRank() == currentPosition.getRank() - 1 && nextPosition.getFile() == currentPosition.getFile() + 2
                || nextPosition.getRank() == currentPosition.getRank() + 1 && nextPosition.getFile() == currentPosition.getFile() - 2
                || nextPosition.getRank() == currentPosition.getRank() - 1 && nextPosition.getFile() == currentPosition.getFile() - 2)
            return true;
        return false;
    }

    @Override
    public boolean isBlocked(Position currentPosition, Position nextPosition, ChessBoard chessBoard) {
        return !nextPosition.isEmpty() && nextPosition.getPiece().getPieceColor() == currentPosition.getPiece().pieceColor;
    }




    @Override
    public void updateAttackedPieces(Position currentPosition, ChessBoard chessBoard) {
        attackedPieces.clear();
        ArrayList<Position> positionsKnightCanAttack = new ArrayList<Position>();
        positionsKnightCanAttack.add(new Position((char) (currentPosition.getFile() + 1), (char) (currentPosition.getRank() + 2)));
        positionsKnightCanAttack.add(new Position((char) (currentPosition.getFile() + 1), (char) (currentPosition.getRank() - 2)));
        positionsKnightCanAttack.add(new Position((char) (currentPosition.getFile() - 1), (char) (currentPosition.getRank() - 2)));

        positionsKnightCanAttack.add(new Position((char) (currentPosition.getFile() + 2), (char) (currentPosition.getRank() + 1)));
        positionsKnightCanAttack.add(new Position((char) (currentPosition.getFile() + 2), (char) (currentPosition.getRank() - 1)));
        positionsKnightCanAttack.add(new Position((char) (currentPosition.getFile() - 2), (char) (currentPosition.getRank() + 1)));
        positionsKnightCanAttack.add(new Position((char) (currentPosition.getFile() - 2), (char) (currentPosition.getRank() - 1)));


        for(int i = 0 ; i<positionsKnightCanAttack.size() ;i++){
            Position position = chessBoard.getPositionAt(positionsKnightCanAttack.get(i));
            if(position!= null)
            {
                if(!position.isEmpty()) {
                    if(position.getPiece().getPieceColor()!=pieceColor)
                        attackedPieces.add(position);
                }
                else {
                    attackedPieces.add(position);
                }

            }

        }
    }


    @Override
    public String toString() {
        return "Kn";
    }
    @Override
    public void updateLegalMoves(Position currentPosition,ChessBoard chessBoard) {
        legalMoves= attackedPieces;
    }
}
