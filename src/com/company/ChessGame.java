package com.company;
import com.company.Pieces.Pawn;
import com.company.Pieces.Piece;
import java.util.Scanner;

public class ChessGame {
    final int MAX_MOVES = 50;
    Player currentPlayer;
    ChessBoard chessBoard;
    ChessManager chessManager;

    public ChessGame() {
        chessBoard = ChessBoard.getInstance();
        chessBoard.initializeBoard();
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
        String move;
        Position currentPosition;
        Position nextPosition;
        currentPlayer = Player.WHITE;// in chess the first player is always the white player

        chessManager.updatePiecesMoves();
        while (moves < MAX_MOVES) {

            System.out.print("Enter next move (" + currentPlayer + ") :");
            move = scanner.next();

            String scanCurrentPosition = scanner.next();
            String scanNextPosition = scanner.next();

            if (!chessManager.isNotationCorrect(scanCurrentPosition, scanNextPosition))
                continue;

            nextPosition = new Position(scanNextPosition);
            currentPosition = new Position(scanCurrentPosition);

            for (int i = 0; i < chessBoard.size(); i++) {
                Position boardPiece = chessBoard.positions.get(i);
                if (boardPiece.equals(currentPosition)) {
                    currentPosition = chessBoard.positions.get(i); //get current position

                    for (int j = 0; j < chessBoard.size(); j++) {
                        if (chessBoard.positions.get(j).equals(nextPosition)) {
                            nextPosition = chessBoard.positions.get(j); //get next position

                            if (!chessManager.isLegalMove(currentPlayer, currentPosition, nextPosition)) {
                                wrongMove = true;
                                break;
                            } else wrongMove = false;

                            if (currentPosition.getPiece() instanceof Pawn) {
                                if (((Pawn) currentPosition.getPiece()).isFirstTimeMoving())
                                    ((Pawn) currentPosition.getPiece()).setFirstTimeMoving(false);
                            }

                            Piece movedPiece = currentPosition.getPiece();

                            nextPosition.setPiece(movedPiece);
                            currentPosition.setPiece(null);

                            chessManager.updatePiecesMoves();
                            break;
                        }
                    }
                }
            }

            if (wrongMove)
                continue;

            Player otherPlayer = currentPlayer == Player.WHITE ? Player.BlACK : Player.WHITE;
            if (chessManager.isKingChecked(otherPlayer)) {
                System.out.println("KING IS IN CHECK");

                chessManager.setCheckingForCheckmate(true);
                if (chessManager.isCheckMate(otherPlayer)) {
                    System.out.println("CHECKMATE for : " + otherPlayer);
                    switchPlayer(otherPlayer);
                    System.out.println(currentPlayer + " WINS");
                    break;
                }
            }

            chessManager.setCheckingForCheckmate(false);
            switchPlayer(currentPlayer);
            moves++;
        }


    }
}
