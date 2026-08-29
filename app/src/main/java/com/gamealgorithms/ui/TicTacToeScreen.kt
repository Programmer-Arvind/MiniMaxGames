package com.gamealgorithms.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.gamealgorithms.games.TicTacToeBoard

@Composable
fun TicTacToeBoardUI(viewModel: TicTacToeViewModel,
                     modifier: Modifier = Modifier) {
    Column(
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center
    ) {
        val board = viewModel.board
        LazyVerticalGrid(
            columns = GridCells.Fixed(3),
        ) {
            items(9) { index ->
                Box(
                    modifier = Modifier
                        .fillMaxHeight(1f)
                        .background(Color.DarkGray)
                        .border(2.dp, Color.White)
                        .aspectRatio(1f)
                        .padding(4.dp)
                        .clickable(enabled = !viewModel.isThinking) {
                            viewModel.placeX(index/3, index%3)
                        },
                    contentAlignment = Alignment.Center,
                ) {
                    Text(
                        text = boardNumToSymbol(board.board[index/3][index%3]),
                        color = Color.White
                    )
                    if (viewModel.isThinking && index == 4) {
                        CircularProgressIndicator()
                    }
                }
            }
        }
    }

    if (viewModel.isGameOver) {
        AlertDialog(
            onDismissRequest = { },
            title = {
                Text(text = "Game Over")
            },
            text = {
                Text(text = if (viewModel.winner != null) "${viewModel.winner} Wins!" else "It's a Draw!")
            },
            confirmButton = {
                Button(
                    onClick = {
                        viewModel.resetGame()
                    }
                ) {
                    Text("Play Again")
                }
            }
        )
    }
}

fun boardNumToSymbol(num: Int): String {
    return when(num) {
        1 -> "X"
        -1 -> "O"
        else -> ""
    }
}

@Preview
@Composable
fun TicTacToeBoardUIPreview() {
    val ticTacToeBoard = TicTacToeBoard()
//    TicTacToeBoardUI(ticTacToeBoard)
}
