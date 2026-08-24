package com.example.gamealgorithms.game

import com.gamealgorithms.game.TicTacToeBoard
import junit.framework.TestCase.assertFalse
import org.junit.Test
import kotlin.test.DefaultAsserter.assertEquals

class TicTacToeBoardTest {
    @Test
    fun newBoardIsCreatedAndIsNotFull() {
        val ticTacToeBoard = TicTacToeBoard()
        assertFalse(ticTacToeBoard.isFull())
    }

    @Test
    fun xWinsBackDiagonal() {
        // "\"
        val ticTacToeBoard = TicTacToeBoard()
        ticTacToeBoard.placeX(0, 0)
        ticTacToeBoard.placeX(1, 1)
        ticTacToeBoard.placeX(2, 2)

        assertEquals("X Won backslash", 1, ticTacToeBoard.result())
    }

    @Test
    fun yWinsBackDiagonal() {
        val ticTacToeBoard = TicTacToeBoard()
        ticTacToeBoard.placeO(0, 0)
        ticTacToeBoard.placeO(1, 1)
        ticTacToeBoard.placeO(2, 2)

        assertEquals("Y Won backslash", -1, ticTacToeBoard.result())
    }

    @Test
    fun yWinsFrontDiagonal() {
        // "/"
        val ticTacToeBoard = TicTacToeBoard()
        ticTacToeBoard.placeO(0, 2)
        ticTacToeBoard.placeO(1, 1)
        ticTacToeBoard.placeO(2, 0)

        assertEquals("Y Won frontslash", -1, ticTacToeBoard.result())
    }

    @Test
    fun yWinFullBoard() {
        val ticTacToeBoard = TicTacToeBoard()
        ticTacToeBoard.placeO(0, 0)
        ticTacToeBoard.placeX(0, 1)
        ticTacToeBoard.placeO(0, 2)
        ticTacToeBoard.placeX(1, 0)
        ticTacToeBoard.placeO(1, 1)
        ticTacToeBoard.placeO(1, 2)
        ticTacToeBoard.placeX(2, 0)
        ticTacToeBoard.placeX(2, 1)
        ticTacToeBoard.placeO(2, 2)

        assertEquals("X and Y Draw with full board", -1, ticTacToeBoard.result())
    }

    @Test
    fun drawFullBoard() {
        val ticTacToeBoard = TicTacToeBoard()
        ticTacToeBoard.placeO(0, 0)
        ticTacToeBoard.placeX(0, 1)
        ticTacToeBoard.placeO(0, 2)
        ticTacToeBoard.placeX(1, 0)
        ticTacToeBoard.placeX(1, 1)
        ticTacToeBoard.placeO(1, 2)
        ticTacToeBoard.placeX(2, 0)
        ticTacToeBoard.placeO(2, 1)
        ticTacToeBoard.placeX(2, 2)

        assertEquals("X and Y Draw with full board", 0, ticTacToeBoard.result())
    }
}
