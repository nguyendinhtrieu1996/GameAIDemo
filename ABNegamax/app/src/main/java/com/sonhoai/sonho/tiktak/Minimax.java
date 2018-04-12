package com.sonhoai.sonho.tiktak;

import android.util.Log;

/**
 * Created by sonho on 3/21/2018.
 */

public class Minimax {

    public static int index = 0;
    private ChessBoard chessBoard;

    public Minimax(ChessBoard chessBoard) {
        this.chessBoard = chessBoard;
    }

    public Record minimaxRecode(int currentDept, int maxDept, int alpha, int beta) {
        Move bestMove=null;//
        int bestScore;

        if(chessBoard.isGameOver() || currentDept == maxDept) {

            Log.d(index++ + "====== evalue ", chessBoard.evaluate() + "");
            chessBoard.over = true;
            return new Record(null, chessBoard.evaluate());
        }

        bestScore = Integer.MIN_VALUE;

        for(Move move:chessBoard.getMove()){
            chessBoard.makeMove(move);
            Record record = minimaxRecode(
                        currentDept++,
                        maxDept,
                        -beta,
                        -alpha
                );


            int currentScore = -record.getScore();
            chessBoard.removeMove(move);
            chessBoard.resetWinner();

            alpha = Math.max(alpha, currentScore);

            if(currentScore > bestScore) {
                bestScore = currentScore;
                bestMove = move;
            }

            if (currentScore >= beta || alpha >= beta) {
                return new Record(bestMove, bestScore);
            }

        }

        return new Record(bestMove, bestScore);
    }

}






