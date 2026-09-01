package com.gamealgorithms.games

class AaduPuliAatamBoard {
    private val board = Array(23) { 0 }

    private val MOVES = mapOf(
        0 to listOf(2, 3, 4, 5),
        1 to listOf(2, 7),
        2 to listOf(1, 0, 2, 8),
        3 to listOf(2, 0, 4, 9),
        4 to listOf(3, 0, 5, 10),
        5 to listOf(4, 0, 6, 11),
        6 to listOf(5, 12),
        7 to listOf(1, 8, 13),
        8 to listOf(7, 2, 9, 14),
        9 to listOf(3, 10, 15, 8),
        10 to listOf(9, 4, 11, 16),
        11 to listOf(5, 12, 17, 10),
        12 to listOf(11, 6, 18),
        13 to listOf(7, 14),
        14 to listOf(13, 8, 15, 19),
        15 to listOf(14, 9, 16, 20),
        16 to listOf(15, 10, 17, 21),
        17 to listOf(16, 11, 18, 22),
        18 to listOf(17, 12),
        19 to listOf(14, 20),
        20 to listOf(19, 15, 21),
        21 to listOf(20, 16, 22),
        22 to listOf(21, 17)
    )

    private val CAPTURES = mapOf(
        0 to listOf(Pair(2, 8), Pair(3, 9), Pair(4, 10), Pair(5, 11)),
        1 to listOf(Pair(2, 3), Pair(7, 13)),
        2 to listOf(Pair(3, 4), Pair(8, 14)),
        3 to listOf(Pair(2, 1), Pair(4, 5), Pair(9, 15)),
        4 to listOf(Pair(3, 2), Pair(5, 6), Pair(10, 16)),
        5 to listOf(Pair(4, 3), Pair(11, 17)),
        6 to listOf(Pair(5, 4), Pair(12, 18)),
        7 to listOf(Pair(8, 9)),
        8 to listOf(Pair(2, 0), Pair(9, 10), Pair(14, 19)),
        9 to listOf(Pair(8, 7), Pair(3, 0), Pair(10, 11), Pair(15, 20)),
        10 to listOf(Pair(9, 8), Pair(4, 0), Pair(11, 12), Pair(16, 21)),
        11 to listOf(Pair(10, 9), Pair(5, 0), Pair(17, 22)),
        12 to listOf(Pair(11, 10)),
        13 to listOf(Pair(7, 1), Pair(14, 15)),
        14 to listOf(Pair(8, 2), Pair(15, 16)),
        15 to listOf(Pair(14, 13), Pair(9, 3), Pair(16, 17)),
        16 to listOf(Pair(15, 14), Pair(10, 4), Pair(17, 18)),
        17 to listOf(Pair(16, 15), Pair(11, 5)),
        18 to listOf(Pair(17, 16), Pair(12, 6)),
        19 to listOf(Pair(14, 8), Pair(20, 21)),
        20 to listOf(Pair(15, 9), Pair(21, 22)),
        21 to listOf(Pair(20, 19), Pair(16, 10)),
        22 to listOf(Pair(17, 11))
    )
    private var boardState = BoardState.POSITIONING
    private var goatsLeftToPlace = 15

    /**
     * @return true if valid placement else false
     */
    fun placeGoat(position: Int): Boolean {
        if (goatsLeftToPlace > 0 && board[position] == 0) {
            board[position] = 1
            goatsLeftToPlace--
            return true
        }
        if (goatsLeftToPlace == 0) {
            boardState = BoardState.MOVEMENT
        }
        return false
    }
}

enum class BoardState {
    POSITIONING,
    MOVEMENT
}