package com.gamealgorithms.games

class AaduPuliAatamBoard {
    var board = Array(23) { index ->
        if (index in listOf(0, 3, 4)) {
            BoardPiece.TIGER // Top 3 positions tigers
        } else {
            BoardPiece.EMPTY
        }
    }

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
    private var goatsCaptured = 0

    /**
     * @return true if valid placement else false
     */
    fun placeGoat(position: Int): Boolean {
        if (goatsLeftToPlace > 0 && board[position] == BoardPiece.EMPTY) {
            board[position] = BoardPiece.GOAT
            goatsLeftToPlace--
            return true
        }
        if (goatsLeftToPlace == 0) {
            boardState = BoardState.MOVEMENT
        }
        return false
    }

    /**
     * @return true if valid position else false
     */
    fun moveGoat(currPosition: Int, finalPosition: Int): Boolean {
        if (board[currPosition] == BoardPiece.GOAT &&
            board[finalPosition] == BoardPiece.EMPTY &&
            MOVES[currPosition]?.contains(finalPosition) == true
        ) {
            board[currPosition] = BoardPiece.EMPTY
            board[finalPosition] = BoardPiece.GOAT
            return true
        }
        return false
    }

    /**
     * @return true if valid position else false
     */
    fun moveTiger(currPosition: Int, finalPosition: Int): Boolean {
        if (board[currPosition] == BoardPiece.TIGER && board[finalPosition] == BoardPiece.EMPTY) {
            if (MOVES[currPosition]?.contains(finalPosition) == true) {
                board[currPosition] = BoardPiece.EMPTY
                board[finalPosition] = BoardPiece.TIGER
            }  else if (board[jumpOverPosition(currPosition, finalPosition)] == BoardPiece.GOAT) {
                board[currPosition] = BoardPiece.EMPTY
                board[jumpOverPosition(currPosition, finalPosition)] = BoardPiece.EMPTY
                board[finalPosition] = BoardPiece.TIGER
                goatsCaptured++
            }
            return true
        }
        return false
    }

    /**
     * @return The index that the tiger jumps over, given the initial and final position of tiger
     */
    fun jumpOverPosition(tigerInitialPos: Int, tigerFinalPos: Int): Int {
        return CAPTURES[tigerInitialPos]!!.filter{it.second == tigerFinalPos}[0].first
    }

    /**
     * @return 1 if Tiger wins, -1 if Goat wins, null if game in progress
     */
    fun result(): Int? {
        if (goatsCaptured == 5) {
            return 1
        }
        for ((ind, piece) in board.withIndex()) {
            if (piece == BoardPiece.TIGER) {
                // Neighbouring nodes blocked and tigers cannot capture the goat
                var blocked = true
                for (pos in MOVES[ind]!!) {
                    if (board[pos] != BoardPiece.GOAT && board[CAPTURES[ind]!!.filter{it.first == pos}[0].second] != BoardPiece.EMPTY) {
                        blocked = false
                    } // Possible errors another tiger can capture the goat, one tiger blocking another tiger
                }
                if (!blocked) {
                    return -1
                }
            }
        }
        return null
    }

    override fun toString(): String {
        fun pieceAtPos(pos: Int): String {
            return pieceToChar(board[pos])
        }
        val str = "       ${pieceAtPos(0)}\n" +
                "     / ▲ \\ \n"+
                "  ${pieceAtPos(1)} ${pieceAtPos(2)} ${pieceAtPos(3)} ${pieceAtPos(4)} ${pieceAtPos(5)} ${pieceAtPos(6)}\n" +
                " ${pieceAtPos(7)} ${pieceAtPos(8)} ${pieceAtPos(9)}   ${pieceAtPos(10)} ${pieceAtPos(11)} ${pieceAtPos(12)}   \n" +
                "${pieceAtPos(13)} ${pieceAtPos(14)} ${pieceAtPos(15)}     ${pieceAtPos(16)} ${pieceAtPos(17)} ${pieceAtPos(18)} \n" +
                " ${pieceAtPos(19)} ${pieceAtPos(20)}       ${pieceAtPos(21)} ${pieceAtPos(22)}\n"
        return str
    }
}

enum class BoardState {
    POSITIONING,
    MOVEMENT
}

enum class BoardPiece {
    EMPTY,
    TIGER,
    GOAT
}

fun pieceToChar(piece: BoardPiece): String {
    return when (piece) {
        BoardPiece.GOAT -> "G"
        BoardPiece.TIGER -> "T"
        BoardPiece.EMPTY -> "-"
    }
}