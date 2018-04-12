package com.sonhoai.sonho.tiktak;

import android.util.Log;

/**
 * Created by sonho on 3/21/2018.
 */

public class Minimax {

    public static int index = 0;

    public Record minimaxRecode(ChessBoard chessBoard,int currentDept, int maxDept, int alpha, int beta) {
        Move bestMove=null;//
        int bestScore;

        if(chessBoard.isGameOver() || currentDept == maxDept) {

            Log.d(index++ + "====== evalue ", chessBoard.evaluate() + "");
            chessBoard.over = true;
            return new Record(null, chessBoard.evaluate());
        }

        bestScore = Integer.MIN_VALUE;

        for(Move move:chessBoard.getMove()){
            ChessBoard newChess = new ChessBoard(chessBoard.getContext(),chessBoard.getBitmapWidth(), chessBoard.getBitmapHeight(),chessBoard.getColQty(),chessBoard.getRowQty());
            newChess.initBoard2();
            newChess.setBoard(chessBoard.getNewBoard());
            newChess.setPlayer(chessBoard.getPlayer());

            newChess.makeMove(move);
            Record record;

            record = minimaxRecode(
                        newChess,
                        currentDept++,
                        maxDept,
                        -beta,
                        -Math.max(alpha, bestScore)
                );


            int currentScore = -record.getScore();

//            if (record.getScore() >= beta && !newChess.over) {
//                newChess.over = false;
//                return new Record(bestMove, currentScore);
//            }

            if(currentScore > bestScore) {
                newChess.over = false;
                bestScore = currentScore;
                bestMove = move;
            }
        }

        return new Record(bestMove, bestScore);
    }

}
