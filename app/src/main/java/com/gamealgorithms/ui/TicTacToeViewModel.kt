package com.gamealgorithms.ui

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gamealgorithms.engines.TicTacToeEngine
import com.gamealgorithms.games.TicTacToeBoard
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class TicTacToeViewModel: ViewModel() {
    var board by mutableStateOf(TicTacToeBoard())
        private set

    var isGameOver by mutableStateOf(false)
        private set

    var isThinking by mutableStateOf(false)
        private set

    var winner by mutableStateOf<String?>(null)
        private set

    fun placeX(row: Int, col: Int) {
        board.placeX(row, col)
        board = board.copy()
        viewModelScope.launch {
            isThinking = true
            val engine = TicTacToeEngine(board)
            val bestMove = withContext(Dispatchers.Default)
                {engine.findBestMove()}
            if (!isGameOver && !board.isFull()) {
                board.placeO(bestMove[0], bestMove[1])
                board = board.copy()
            }
            if (board.result() != null) {
                isGameOver = true
                Log.i("MYTAG", "${board.result()}")
                winner = when(board.result()) {
                    1 -> "X"
                    -1 -> "O"
                    else -> null
                }
                board = TicTacToeBoard()
            }
            isThinking = false
        }
    }

    fun resetGame() {
        isGameOver = false
        winner = null

    }

}