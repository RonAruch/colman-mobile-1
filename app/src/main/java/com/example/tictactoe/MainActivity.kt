package com.example.tictactoe

import android.graphics.Color
import android.os.Bundle
import android.widget.Button
import android.widget.GridLayout
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {

    private lateinit var game: Game
    private lateinit var buttons: Array<Array<Button>>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        game = Game()
        buttons = Array(3) { row ->
            Array(3) { column ->
                Button(this).apply {
                    textSize = 50f
                    setBackgroundResource(R.drawable.tic_tac_toe_button)
                    setTextColor(Color.WHITE)
                    setOnClickListener {
                        // todo: do player move
                    }
                }
            }
        }

        val gridLayout = findViewById<GridLayout>(R.id.activity_main_grid_layout)
        gridLayout.rowCount = 3
        gridLayout.columnCount = 3

        for (row in 0..2) {
            for (column in 0..2) {
                val button = buttons[row][column]

                val params = GridLayout.LayoutParams().apply {
                    width = 0
                    height = 0
                    rowSpec = GridLayout.spec(row, 1f)
                    columnSpec = GridLayout.spec(column, 1f)
                    setMargins(16,16,16,16)
                }

                button.layoutParams = params
                gridLayout.addView(button)
            }
        }

        findViewById<Button>(R.id.activity_main_reset_button).setOnClickListener {
            game.resetBoard()
            drawBoard()
        }

        drawBoard()

    }

    private fun drawBoard() {
        val board = game.getBoard()
        for (row in board.indices) {
            for (column in board[row].indices) {
                buttons[row][column].text = board[row][column].toString()
                buttons[row][column].isEnabled = board[row][column] == EMPTY
            }
        }
    }
}