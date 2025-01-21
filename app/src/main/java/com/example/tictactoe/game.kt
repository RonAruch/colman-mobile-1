package com.example.tictactoe

const val PLAYER_X = 'X'
const val PLAYER_0 = '0'
const val EMPTY = ' '

enum class EndGameOption {
    PLAYER_X, PLAYER_0, DRAW_INDICATOR
}

class Game {
    private val board =  Array(3) { CharArray(3) { EMPTY } }
    private var player : Char = PLAYER_X

    fun getBoard(): Array<CharArray> {
        return board
    }

    fun resetBoard() {
        for (row in board.indices) {
            for (column in board[row].indices) {
                board[row][column] = EMPTY
            }
        }

        player = PLAYER_X
    }

    fun takeTurn(row: Int, column: Int) {
        board[row][column] = player
        player = if (player == PLAYER_X) PLAYER_0 else PLAYER_X
    }

    fun validate(row: Int, column: Int) : Boolean {
        return !(row !in board.indices || column !in board.indices|| board[row][column] != EMPTY)
    }

    fun getWinner(): EndGameOption? {
        for (i in board.indices) {
            if (board[i][0] != EMPTY && board[i][0] == board[i][1] && board[i][0] == board[i][2]) {
                return winnerMessage(board[i][0])
            }
            if (board[0][i] != EMPTY && board[0][i] == board[1][i] && board[0][i] == board[2][i]) {
                return winnerMessage(board[0][i])
            }
        }

        if (board[0][0] != EMPTY && board[0][0] == board[1][1] && board[0][0] == board[2][2]) {
            return winnerMessage(board[0][0])
        }
        if (board[0][2] != EMPTY && board[0][2] == board[1][1] && board[0][2] == board[2][0]) {
            return winnerMessage(board[0][2])
        }

        if (board.all { row -> row.all { cell -> cell != EMPTY } }) {
           return EndGameOption.DRAW_INDICATOR
        }

        return null
    }

    private fun winnerMessage(winner: Char) : EndGameOption {
        return if(winner == PLAYER_X) {
            EndGameOption.PLAYER_X
        } else {
            EndGameOption.PLAYER_0
        }
    }

}