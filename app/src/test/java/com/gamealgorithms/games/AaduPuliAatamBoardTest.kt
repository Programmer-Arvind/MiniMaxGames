package com.gamealgorithms.games

import org.junit.Test
import kotlin.test.assertEquals

class AaduPuliAatamBoardTest {
    @Test
    fun initialBoardStateCheck() {
        val board = AaduPuliAatamBoard()
        print(board)
    }

    @Test
    fun fiveGoatCapturedInPositioningPhase() {
        val board = AaduPuliAatamBoard()
        board.placeGoat(9)
        board.moveTiger(3, 15)
        board.placeGoat(10)
        board.moveTiger(4, 16)
        board.placeGoat(14)
        board.moveTiger(15, 13)
        board.placeGoat(17)
        board.moveTiger(16, 18)
        board.placeGoat(2)
        board.moveTiger(0, 8)
        assertEquals(1, board.result())
    }
}