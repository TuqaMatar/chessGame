package com.company.Pieces;

import com.company.ChessBoard;
import com.company.PieceColor;
import com.company.Position;

import java.util.ArrayList;

public class King extends Piece{

    public King(PieceColor pieceColor) {
        super( pieceColor);
    }
    ArrayList<Position> kingPossibleMoves;

    @Override
   public boolean isMoveLegal(Position currentPosition , Position nextPosition ) {
        if(nextPosition.getRank() == currentPosition.getRank()+1
                || nextPosition.getRank() == currentPosition.getRank()-1
                || nextPosition.getFile() ==currentPosition.getFile()+1
                || nextPosition.getFile() == currentPosition.getFile()-1
        )
            return true;
        return false;
    }

    public ArrayList<Position> getKingPossibleMoves(Position currentPosition){
        ArrayList<Position> positionsKingCanAttack = new ArrayList<Position>();
        positionsKingCanAttack.add(new Position((char) (currentPosition.getFile()), (char) (currentPosition.getRank() + 1)));
        positionsKingCanAttack.add(new Position((char) (currentPosition.getFile() ), (char) (currentPosition.getRank() - 1)));
        positionsKingCanAttack.add(new Position((char) (currentPosition.getFile() + 1), (char) (currentPosition.getRank() )));
        positionsKingCanAttack.add(new Position((char) (currentPosition.getFile() -1), (char) (currentPosition.getRank())));

        positionsKingCanAttack.add(new Position((char) (currentPosition.getFile() + 1), (char) (currentPosition.getRank() +1)));
        positionsKingCanAttack.add(new Position((char) (currentPosition.getFile() +1), (char) (currentPosition.getRank() - 1)));
        positionsKingCanAttack.add(new Position((char) (currentPosition.getFile() -1), (char) (currentPosition.getRank() +1 )));
        positionsKingCanAttack.add(new Position((char) (currentPosition.getFile() -1), (char) (currentPosition.getRank() -1 )));

        return positionsKingCanAttack;
    }


    @Override
    public void updateAttackedPieces(Position currentPosition, ChessBoard chessBoard) {
        attackedPieces.clear();
        ArrayList<Position> positionsKingCanAttack = new ArrayList<Position>();
        positionsKingCanAttack.add(new Position((char) (currentPosition.getFile()), (char) (currentPosition.getRank() + 1)));
        positionsKingCanAttack.add(new Position((char) (currentPosition.getFile() ), (char) (currentPosition.getRank() - 1)));
        positionsKingCanAttack.add(new Position((char) (currentPosition.getFile() + 1), (char) (currentPosition.getRank() )));
        positionsKingCanAttack.add(new Position((char) (currentPosition.getFile() -1), (char) (currentPosition.getRank())));

        positionsKingCanAttack.add(new Position((char) (currentPosition.getFile() + 1), (char) (currentPosition.getRank() +1)));
        positionsKingCanAttack.add(new Position((char) (currentPosition.getFile() +1), (char) (currentPosition.getRank() - 1)));
        positionsKingCanAttack.add(new Position((char) (currentPosition.getFile() -1), (char) (currentPosition.getRank() +1 )));
        positionsKingCanAttack.add(new Position((char) (currentPosition.getFile() -1), (char) (currentPosition.getRank() -1 )));



        for(int i = 0 ; i<positionsKingCanAttack.size() ;i++){
            Position position = chessBoard.getPositionAt(positionsKingCanAttack.get(i));
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
    public void updateLegalMoves(Position currentPosition ,ChessBoard chessBoard) {
        legalMoves= attackedPieces;
    }

    @Override
    public String toString() {
        return "King";
    }
}
