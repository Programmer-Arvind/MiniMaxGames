package com.gamealgorithms.engines

import com.gamealgorithms.games.TicTacToeBoard
import org.junit.Test
import kotlin.test.assertEquals

class TicTacToeEngineTest {
    @Test
    fun emptyBoardAvailableMoves() {
        val board = TicTacToeBoard()
        val ticTacToeEngine = TicTacToeEngine(board)
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

        val ticTacToeEngine = TicTacToeEngine(board)
        assertEquals(mutableListOf<List<Int>>(listOf(2,0)), ticTacToeEngine.getAvailableMoves())
    }

    @Test
    fun playerXWinMoveOfThreeMovesAvailable() {
        val board = TicTacToeBoard()
        // X player 1,2
        board.placeX(0, 0)
        board.placeO(0, 2)
        board.placeX(1, 0)
        board.placeX(1, 1)
        board.placeO(2, 1)
        board.placeO(2, 2)
//        x _ o
//        x x _
//        _ o o

        val ticTacToeEngine = TicTacToeEngine(board)
        assertEquals(listOf(1, 2), ticTacToeEngine.findBestMove().subList(0, 2))
        // Y Player 1, 2
        /*board.placeO(0, 0)
        board.placeO(0, 1)
        board.placeX(0, 2)
        board.placeX(1, 0)
        board.placeO(1, 1)
        board.placeX(2, 2)*/
        // o o x
        // x o _
        // _ _ x

        /*board.placeX(0, 0)
        board.placeX(0, 1)
        board.placeO(0, 2)
        board.placeO(1, 0)
        board.placeX(1, 1)
        board.placeO(2, 2)*/
        // x x o
        // o x _
        // _ _ o
    }

    @Test
    fun playerXWinMoveOfThreeMovesAvailable2() {
        val board = TicTacToeBoard()
        board.placeO(0, 0)
        board.placeO(0, 1)
        board.placeX(0, 2)
        board.placeX(1, 0)
        board.placeO(1, 1)
        board.placeX(2, 2)
        // o o x
        // x o _
        // _ _ x

        val ticTacToeEngine = TicTacToeEngine(board)
        assertEquals(listOf(1, 2), ticTacToeEngine.findBestMove().subList(0, 2))
    }

    @Test
    fun playerXBestMoveOfThreeMovesAvailable2() {
        val board = TicTacToeBoard()
        board.placeX(0, 0)
        board.placeX(0, 1)
        board.placeO(0, 2)
        board.placeO(1, 0)
        board.placeX(1, 1)
        board.placeO(2, 2)
        // x x o
        // o x _
        // _ _ o

        val ticTacToeEngine = TicTacToeEngine(board)
        assertEquals(listOf(2, 1), ticTacToeEngine.findBestMove().subList(0, 2))
    }

    @Test
    fun playerXDrawOfTwoMovesAvailable() {
        val board = TicTacToeBoard()
        board.placeO(0, 0)
        board.placeX(0, 2)
        board.placeX(1, 0)
        board.placeO(1, 1)
        board.placeO(1, 2)
        board.placeX(2, 0)
        board.placeX(2, 2)
        // o _ x
        // x o o
        // x _ x

        val ticTacToeEngine = TicTacToeEngine(board)
        assertEquals(listOf(2, 1), ticTacToeEngine.findBestMove().subList(0, 2))
    }

    @Test
    fun playerOWinOfTwoMovesAvailable() {
        val board = TicTacToeBoard()
        board.placeO(0, 0)
        board.placeX(0, 1)
        board.placeX(1, 0)
        board.placeO(1, 1)
        board.placeO(2, 0)
        board.placeX(2, 1)
        board.placeX(2, 2)
        // o x _
        // x o _
        // o x x
        val ticTacToeEngine = TicTacToeEngine(board)
        assertEquals(listOf(0,2), ticTacToeEngine.findBestMove().subList(0, 2))
    }

    @Test
    fun playerOWinOfTwoMovesAvailable2() {
        val board = TicTacToeBoard()
        board.placeO(0, 0)
        board.placeX(0, 1)
        board.placeO(0, 2)
        board.placeX(1, 1)
        board.placeX(1, 2)
        board.placeO(2, 0)
        board.placeX(2, 2)
        // o x o
        // _ x x
        // o _ x
        val ticTacToeEngine = TicTacToeEngine(board)
        assertEquals(listOf(1, 0), ticTacToeEngine.findBestMove().subList(0, 2))
    }

    @Test
    fun playerOWinOfFourMovesAvailable() {
        val board = TicTacToeBoard()
        board.placeX(0, 0)
        board.placeO(0, 1)
        board.placeO(0, 2)
        board.placeX(1, 1)
        board.placeO(2, 2)
        // x x o
        // _ x _
        // _ _ o

        val ticTacToeEngine = TicTacToeEngine(board)
        assertEquals(listOf(1, 2), ticTacToeEngine.findBestMove().subList(0, 2))
    }
}