package com.sonhoai.sonho.tiktak;

import android.util.Log;

/**
 * Created by sonho on 3/21/2018.
 */

public class Minimax {

    public Record minimaxRecode(ChessBoard chessBoard,int currentDept, int maxDept, int alpha, int beta) {
        Move bestMove=null;//
        int bestScore;

        if(chessBoard.isGameOver() || currentDept == maxDept) {
            Log.d("====== evalue ", chessBoard.evaluate() + "");
            return new Record(null, chessBoard.evaluate());
        }

        bestScore = Integer.MIN_VALUE;

        for(Move move:chessBoard.getMove()){
            ChessBoard newChess = new ChessBoard(chessBoard.getContext(),chessBoard.getBitmapWidth(), chessBoard.getBitmapHeight(),chessBoard.getColQty(),chessBoard.getRowQty());
            newChess.initBoard2();
            newChess.setBoard(chessBoard.getNewBoard());
            newChess.setPlayer(chessBoard.getPlayer());

            newChess.makeMove(move);
            Record record = minimaxRecode(
                    newChess,
                    currentDept++,
                    maxDept,
                    -beta,
                    -Math.max(alpha, bestScore)
            );

            int currentScore = -record.getScore();

            if(currentScore > bestScore) {
                bestScore = currentScore;
                bestMove = move;
            }

            if (currentScore >= beta || currentScore <= alpha) {
                return new Record(bestMove,bestScore);
            }
        }

        return new Record(bestMove,bestScore);
    }

}
