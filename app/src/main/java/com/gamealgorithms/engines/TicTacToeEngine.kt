package com.gamealgorithms.engines

import com.gamealgorithms.games.TicTacToeBoard
import com.gamealgorithms.games.TicTacToePlayer

class TicTacToeEngine(private val board: TicTacToeBoard) {
    fun getAvailableMoves(): MutableList<List<Int>> {
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
    fun findBestMove(): MutableList<Int> {
        val availableMoves = getAvailableMoves()

        val result = board.result()
        if (result != null) {
            return mutableListOf(-1, -1, result)
        }

        if (board.nextPlayer() == TicTacToePlayer.X) {
            var maxScore = Int.MIN_VALUE
            val maxMove: MutableList<Int> = mutableListOf(-1, -1)
            for (move in availableMoves) {
                val tempBoard = board.copy()
                tempBoard.placeX(move[0], move[1])
                // if board returns result, continue computing else return result
                val score = tempBoard.result() ?: TicTacToeEngine(tempBoard).findBestMove()[2]
                if (score > maxScore) {
                    maxScore = score
                    maxMove[0] = move[0]
                    maxMove[1] = move[1]
                }
            }
            maxMove.add(maxScore)
            return maxMove
        }
        else {
            var minScore = Int.MAX_VALUE
            val minMove: MutableList<Int> = mutableListOf(-1, -1)
            for (move in availableMoves) {
                val tempBoard = board.copy()
                tempBoard.placeO(move[0], move[1])
                val score = tempBoard.result() ?: TicTacToeEngine(tempBoard).findBestMove()[2]
                if (score < minScore) {
                    minScore = score
                    minMove[0] = move[0]
                    minMove[1] = move[1]
                }
            }
            minMove.add(minScore)
            return minMove
        }
    }
}
