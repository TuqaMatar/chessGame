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
    public String toString() {
        return "Kn";
    }

    @Override
    public void updateLegalMoves(Position currentPosition,ChessBoard chessBoard) {
        legalMoves.clear();
        ArrayList<Position> positionsKnightCanAttack = new ArrayList<Position>();
        positionsKnightCanAttack.add(new Position((char) (currentPosition.getFile() + 1), (char) (currentPosition.getRank() + 2)));
        positionsKnightCanAttack.add(new Position((char) (currentPosition.getFile() + 1), (char) (currentPosition.getRank() - 2)));
        positionsKnightCanAttack.add(new Position((char) (currentPosition.getFile() - 1), (char) (currentPosition.getRank() - 2)));
        positionsKnightCanAttack.add(new Position((char) (currentPosition.getFile() - 1), (char) (currentPosition.getRank() + 2)));

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
                        legalMoves.add(position);
                }
                else {
                    legalMoves.add(position);
                }

            }

        }
    }
}
