package com.fatihaltuntas.tictactoe

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.fatihaltuntas.tictactoe.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private var player1 : Player? = null
    private var player2 : Player? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        intent = Intent(this@MainActivity, GameScreen::class.java)
    }

    fun startGame(view: View) {
        if (binding.txtPlayer1.text.isNotEmpty()) {
            player1 = Player(binding.txtPlayer1.text.toString(), Shapes.SHAPE_X)
        }
        if (binding.txtPlayer2.text.isNotEmpty()) {
            player2 = Player(binding.txtPlayer2.text.toString(), Shapes.SHAPE_O)
        }

        if(player1 != null && player2 != null) {
            intent.putExtra("player1", player1)
            intent.putExtra("player2", player2)
            intent.putExtra("winner", Player("null", Shapes.SHAPE_X))
            finish()
            startActivity(intent)
        } else {
            Toast.makeText(this@MainActivity, "Please enter player names!", Toast.LENGTH_LONG).show()
        }
    }
}