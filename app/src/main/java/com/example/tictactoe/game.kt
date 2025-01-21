package com.example.tictactoe

const val PLAYER_X = 'X'
const val PLAYER_0 = '0'
const val EMPTY = ' '

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

}