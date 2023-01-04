package com.company;
import com.company.Pieces.King;
import com.company.Pieces.Pawn;
import com.company.Pieces.Piece;
import java.util.ArrayList;
import java.util.Scanner;

public class ChessGame {
    Player currentPlayer;
    ChessBoard chessBoard;
    ChessManager chessManager;

    public ChessGame() {
        chessBoard = new ChessBoard();
        chessManager = new ChessManager(chessBoard);
    }

    public void switchPlayer(Player currentPlayer) {
        if (currentPlayer == Player.WHITE)
            this.currentPlayer = Player.BlACK;
        else
            this.currentPlayer = Player.WHITE;
    }

    public void start() {
        int moves = 0;
        boolean wrongMove = false;
        Scanner scanner = new Scanner(System.in);
        currentPlayer = Player.WHITE;
        String move;
        Position currentPosition;
        Position nextPosition;
        chessBoard.updatePiecesLegalMoves();
        chessBoard.updateAttackedPieces();

        while (moves < 50) {
            System.out.print("Enter next move (" + currentPlayer + ") :");
            move = scanner.next();
            String scanCurrentPosition = scanner.next();
            String scanNextPosition = scanner.next();

            if (!chessManager.isNotationCorrect(scanCurrentPosition,scanNextPosition))
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

                            if (!chessManager.isLegalMove(currentPlayer,currentPosition, nextPosition)) {
                                wrongMove = true;
                                break;
                            } else wrongMove = false;

                            if(currentPosition.getPiece() instanceof Pawn )
                            {
                                if(((Pawn)currentPosition.getPiece()).isFirstTimeMoving())
                                    ((Pawn)currentPosition.getPiece()).setFirstTimeMoving(false);
                            }
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


            if (chessManager.isKingChecked()) {
                System.out.println("KING IS IN CHECK");

            chessManager.setCheckingForCheckmate(true);
            if (chessManager.isCheckMate(currentPlayer)) {

                System.out.println("CHECKMATE for : " + chessManager.getCheckedPosition().getPiece().getPieceColor());

                if(chessManager.getCheckedPosition().getPiece().getPieceColor()==PieceColor.WHITE)
                    System.out.println(Player.BlACK +"WINS !" );
                else
                    System.out.println(Player.WHITE +"WINS !" );

                break;
            }
            }
            chessManager.setCheckingForCheckmate(false);
            switchPlayer(currentPlayer);
            moves++;
        }


    }
}
