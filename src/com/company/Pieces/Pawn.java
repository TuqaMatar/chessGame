package com.company.Pieces;

import com.company.ChessBoard;
import com.company.PieceColor;
import com.company.Position;

public class Pawn extends Piece {
    public Pawn( PieceColor pieceColor) {
        super(pieceColor);
    }

    @Override
   public boolean isMoveLegal(Position currentPosition , Position nextPosition) {
        switch(currentPosition.getPiece().getPieceColor()){
            case WHITE:
                if(currentPosition.getFile() == nextPosition.getFile() && nextPosition.getRank()==currentPosition.getRank()+1)
                    return true;
                break;
            case BlACK:
                if(currentPosition.getFile() == nextPosition.getFile() && nextPosition.getRank()==currentPosition.getRank()-1)
                    return true;
                break;
        }
        return false;
    }

    @Override
    public boolean isBlocked(Position currentPosition, Position nextPosition, ChessBoard chessBoard) {
        return !nextPosition.isEmpty() && nextPosition.getPiece().getPieceColor()==currentPosition.getPiece().pieceColor;
    }

    @Override
   public void move(Position currentPosition, Position nextPosition) {}

    @Override
    public void updateAttackedPieces(Position currentPosition) {
        switch(currentPosition.getPiece().getPieceColor()){
            case WHITE: {
                attackedPieces.add(new Position((char)(currentPosition.getFile()+1) , (char)(currentPosition.getRank()+1)));
                attackedPieces.add(new Position((char)(currentPosition.getFile()+-1) , (char)(currentPosition.getRank()+1)));
            }
                break;
            case BlACK:
            {
                attackedPieces.add(new Position((char)(currentPosition.getFile()+1) , (char)(currentPosition.getRank()-1)));
                attackedPieces.add(new Position((char)(currentPosition.getFile()+-1) , (char)(currentPosition.getRank()-1)));
            }
                break;
        }
    }

    @Override
    public void updateAttackedPieces(Position currentPosition, ChessBoard chessBoard) {
        char rank ;
        char file ;
        attackedPieces.clear();

        switch(currentPosition.getPiece().getPieceColor()){
            case WHITE: {
                Position position = chessBoard.getPositionAt((char)(currentPosition.getFile()+1),(char)(currentPosition.getRank()+1));
                if(position!=null)
                {
                    if(position.isEmpty())
                    {attackedPieces.add(position);}
                    else if(position.getPiece().getPieceColor() !=pieceColor)
                        attackedPieces.add(position);

                }

                position=chessBoard.getPositionAt((char)(currentPosition.getFile()+1),(char)(currentPosition.getRank()+1));
                if(position!=null)
                {
                    if(position.isEmpty())
                    {attackedPieces.add(position);}
                    else if(position.getPiece().getPieceColor() !=pieceColor)
                        attackedPieces.add(position);

                }
            }
            break;
            case BlACK:
            {
                Position position = chessBoard.getPositionAt((char)(currentPosition.getFile()+1),(char)(currentPosition.getRank()-1));
                if(position!=null)
                {
                    if(position.isEmpty())
                    {attackedPieces.add(position);}
                    else if(position.getPiece().getPieceColor() !=pieceColor)
                        attackedPieces.add(position);

                }
                position=chessBoard.getPositionAt((char)(currentPosition.getFile()+-1),(char)(currentPosition.getRank()-1));
                if(position!=null)
                {
                    if(position.isEmpty())
                    {attackedPieces.add(position);}
                    else if(position.getPiece().getPieceColor() !=pieceColor)
                        attackedPieces.add(position);

                }

            }
            break;
        }
    }

    @Override
    public void updateLegalMoves(Position currentPosition, ChessBoard chessBoard) {
        legalMoves.clear();
        switch(currentPosition.getPiece().getPieceColor()){
            case WHITE: {
                legalMoves.add(new Position((char)(currentPosition.getFile()) , (char)(currentPosition.getRank()+1)));
            }
            break;
            case BlACK:
            {
                legalMoves.add(new Position((char)(currentPosition.getFile()) , (char)(currentPosition.getRank()-1)));
            }
            break;
        }
    }

    public String toString() {
        return "Pa";
    }

}
