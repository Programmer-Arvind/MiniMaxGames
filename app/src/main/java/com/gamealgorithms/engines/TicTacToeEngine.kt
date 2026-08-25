package com.gamealgorithms.engines

import com.gamealgorithms.games.TicTacToeBoard
import com.gamealgorithms.games.TicTacToePlayer

class TicTacToeEngine(private val board: TicTacToeBoard, botPlayer: TicTacToePlayer) {
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
    fun findBestMove() {

    }
}
