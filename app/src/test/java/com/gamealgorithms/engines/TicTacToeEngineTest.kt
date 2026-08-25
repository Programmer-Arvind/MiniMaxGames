package com.gamealgorithms.engines

import com.gamealgorithms.games.TicTacToeBoard
import com.gamealgorithms.games.TicTacToePlayer
import org.junit.Test
import kotlin.test.assertEquals

class TicTacToeEngineTest {
    @Test
    fun emptyBoardAvailableMoves() {
        val board = TicTacToeBoard()
        val ticTacToeEngine = TicTacToeEngine(board, TicTacToePlayer.X)
        val expected = mutableListOf<List<Int>>(
            listOf(0, 0), listOf(0, 1), listOf(0, 2),
            listOf(1, 0), listOf(1, 1), listOf(1, 2),
            listOf(2, 0), listOf(2, 1), listOf(2, 2)
        )
        assertEquals(expected,ticTacToeEngine.getAvailableMoves())
    }

    @Test
    fun onlyOneMoveAvailable() {
        val board = TicTacToeBoard()
        board.placeX(0, 0)
        board.placeX(0, 1)
        board.placeX(0, 2)
        board.placeX(1, 0)
        board.placeX(1, 1)
        board.placeX(1, 2)
        board.placeX(2, 1)
        board.placeX(2, 2)

        val ticTacToeEngine = TicTacToeEngine(board, TicTacToePlayer.X)
        assertEquals(mutableListOf<List<Int>>(listOf(2,0)), ticTacToeEngine.getAvailableMoves())
    }
}