package com.gamealgorithms.engines

import com.gamealgorithms.games.TicTacToeBoard
import com.gamealgorithms.games.TicTacToePlayer

class TicTacToeEngine() {
    fun getAvailableMoves(board: TicTacToeBoard): MutableList<List<Int>> {
        val available: MutableList<List<Int>> = mutableListOf()
        for (i in 0 until 3) {
            for (j in 0 until 3) {
                if (board.getIndex(i, j) == 0) {
                    available.add(listOf(i, j))
                }
            }
        }
        return available
    }
    /**
     * @return [x, y, bestScore]
     */
    fun findBestMove(board: TicTacToeBoard, depth: Int = 0): MutableList<Int> {
        val availableMoves = getAvailableMoves(board)

        val result = board.result()
        if (result != null) {
            val score = when {
                result > 0 -> result*10 - depth
                result < 0 -> result*10 + depth
                else -> 0
            }
            return mutableListOf(-1, -1, score)
        }
        val isX = board.nextPlayer() == TicTacToePlayer.X
        var bestScore = if (isX) Int.MIN_VALUE else Int.MAX_VALUE
        val bestMove = mutableListOf(-1, -1)

        for (move in availableMoves) {
            val tempBoard = board.copy()
            if (isX) tempBoard.placeX(move[0], move[1]) else tempBoard.placeO(move[0], move[1])

            val score = findBestMove(tempBoard, depth + 1)[2]
            if (isX) {
                if (score > bestScore) {
                    bestScore = score
                    bestMove[0] = move[0]
                    bestMove[1] = move[1]
                }
            } else {
                if (score < bestScore) {
                    bestScore = score
                    bestMove[0] = move[0]
                    bestMove[1] = move[1]
                }
            }
        }
        bestMove.add(bestScore)
        return bestMove
    }
}
