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
    fun findBestMove(): List<Int> {
        val availableMoves = getAvailableMoves()
        print(availableMoves)
        if (board.nextPlayer() == TicTacToePlayer.X) {
            var maxScore = Int.MIN_VALUE
            var maxMove: List<Int> = availableMoves[0]
            for (move in availableMoves) {
                val tempBoard = board
                tempBoard.placeX(move[0], move[1])
                tempBoard.result()?.let {
                    if (it > maxScore) {
                        maxScore = it
                        maxMove = move
                    }
                }
            }
            return maxMove
        }
        else {
            var minScore = Int.MAX_VALUE
            var minMove: List<Int> = availableMoves[0]
            for (move in availableMoves) {
                val tempBoard = board
                tempBoard.placeO(move[0], move[1])
                tempBoard.result()?.let {
                    if (it < minScore) {
                        minScore = it
                        minMove = move
                    }
                }
            }
            return minMove
        }
    }
}
