package com.gamealgorithms.ui

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

    fun placeX(row: Int, col: Int) {
        board.placeX(row, col)
        viewModelScope.launch {
            val engine = TicTacToeEngine(board)
            val bestMove = withContext(Dispatchers.Default)
                {engine.findBestMove()}
            board.placeO(bestMove[0], bestMove[1])
            print(board)
            board = board.copy()
        }

    }

}