package com.gamealgorithms.ui

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.gamealgorithms.engines.TicTacToeEngine
import com.gamealgorithms.games.TicTacToeBoard

@Composable
fun TicTacToeBoardUI(board: TicTacToeBoard, modifier: Modifier = Modifier) {
    Column(
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center
    ) {
        LazyVerticalGrid(
            columns = GridCells.Fixed(3)
        ) {
            items(9) { index ->
                Box(
                    modifier = Modifier
                        .fillMaxWidth(1f)
                        .fillMaxHeight(1f)
                        .background(Color.DarkGray)
                        .border(2.dp, Color.White)
                        .clickable {
                            board.placeX(index/3, index%3)
                            val engine = TicTacToeEngine(board)
                            Log.i("MYTAG", "TicTacToeBoardUI: $index, $board, ${engine.findBestMove()}")
                        },
                    contentAlignment = Alignment.Center,
                ) {
                    Text(
                        text = "$index",
                        color = Color.White
                    )
                }
            }
        }
    }
}

@Preview
@Composable
fun TicTacToeBoardUIPreview() {
    val ticTacToeBoard = TicTacToeBoard()
    TicTacToeBoardUI(ticTacToeBoard)
}
