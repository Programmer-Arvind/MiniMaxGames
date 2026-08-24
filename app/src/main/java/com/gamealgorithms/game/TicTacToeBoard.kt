package com.gamealgorithms.game

class TicTacToeBoard(
    val board: Array<Array<Int>> = Array(3) { Array(3) { 0 } }
) {

    fun isFull(): Boolean {
        return !board.flatten().any { it == 0 }
    }

    fun result(): Int? {
        // Returns null in case of incomplete board and no result
        // Returns 0 if draw
        // Returns 1 if X wins
        // Returns -1 if O wins

        // Diagonal victory
        if (board[1][1] != 0 &&
            (setOf(board[0][0], board[1][1], board[2][2]).size == 1 ||
            setOf(board[0][2], board[1][1], board[2][0]).size == 1)) {
            return board[1][1]
        }
        else {
            // Rows
            for (row in board) {
                if (row.distinct().size == 1 && !row.any { it == 0 }) {
                    return row[0];
                }
            }
            // Columns
            for (i in 0 until 3) {
                val column = board.map { it[i] }.distinct()
                if (column.size == 1 && !column.any{it == 0}) return column[0]
            }
        }
        if (isFull()) return 0
        return null
    }

    fun placeX(x: Int, y: Int) {
        board[x][y] = 1
    }

    fun placeO(x: Int, y: Int) {
        board[x][y] = -1
    }

    override fun toString(): String {
        var output = ""
        for (row in board) {
            output += row.joinToString(" ") + "\n"
        }
        return output
    }
}