package com.fatihaltuntas.tictactoe

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.fatihaltuntas.tictactoe.databinding.ActivityGameScreenBinding
import java.util.LinkedList
import java.util.Queue

class GameScreen : AppCompatActivity() {

    private lateinit var binding: ActivityGameScreenBinding
    private lateinit var player1: Player
    private lateinit var player2: Player
    private lateinit var winner : Player
    private lateinit var activePlayer: Player
    private lateinit var player1Moves: Queue<ImageView>
    private lateinit var player2Moves: Queue<ImageView>
    private var lastX: ImageView? = null
    private var lastO: ImageView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityGameScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)

        player1 = intent.getSerializableExtra("player1") as Player
        player2 = intent.getSerializableExtra("player2") as Player
        activePlayer = player1

        binding.txtPlayer1Name.text = player1.name
        binding.txtPlayer2Name.text = player2.name
        binding.txtActivePlayer.text = "${player1.name}: "

        player1Moves = LinkedList()
        player2Moves = LinkedList()
        winner = intent.getSerializableExtra("winner") as Player

        showWinner()

        // ImageView'leri tıklanabilir hale getiriyoruz
        binding.img00.setOnClickListener { clicked(it) }
        binding.img01.setOnClickListener { clicked(it) }
        binding.img02.setOnClickListener { clicked(it) }
        binding.img10.setOnClickListener { clicked(it) }
        binding.img11.setOnClickListener { clicked(it) }
        binding.img12.setOnClickListener { clicked(it) }
        binding.img20.setOnClickListener { clicked(it) }
        binding.img21.setOnClickListener { clicked(it) }
        binding.img22.setOnClickListener { clicked(it) }
    }

    private fun showWinner() {
        if(winner.name.equals(player1.name)) {
            binding.txtPlayer1Name.visibility = TextView.VISIBLE
            binding.imgPlayer1Crown.visibility = ImageView.VISIBLE
        } else if (winner.name.equals(player2.name)) {
            binding.txtPlayer2Name.visibility = TextView.VISIBLE
            binding.imgPlayer2Crown.visibility = ImageView.VISIBLE
        }
    }

    private fun choosePlayer() {
        if (activePlayer == player1) {
            binding.txtActivePlayer.text = "${player1.name}: "
            binding.imgActiveShape.setImageResource(R.drawable.x_solid)
        } else {
            binding.txtActivePlayer.text = "${player2.name}: "
            binding.imgActiveShape.setImageResource(R.drawable.o_solid)
        }
    }

    fun clicked(view: View) {
        if (view is ImageView) {
            when (view.id) {
                R.id.img_0_0 -> changeImage(binding.img00)
                R.id.img_0_1 -> changeImage(binding.img01)
                R.id.img_0_2 -> changeImage(binding.img02)
                R.id.img_1_0 -> changeImage(binding.img10)
                R.id.img_1_1 -> changeImage(binding.img11)
                R.id.img_1_2 -> changeImage(binding.img12)
                R.id.img_2_0 -> changeImage(binding.img20)
                R.id.img_2_1 -> changeImage(binding.img21)
                R.id.img_2_2 -> changeImage(binding.img22)
            }

            if (!checkWinner()) {  // Eğer kazanan yoksa oyuncuyu değiştir
                changePlayer()
            } else {
                showImageDialog()  // Eğer kazanan varsa, kazananı göster
            }
        }
    }

    private fun changePlayer() {
        activePlayer = if (activePlayer == player1) player2 else player1
        choosePlayer()
    }

    private fun changeImage(img: ImageView) {
        if (img.drawable != null && img.drawable.constantState != resources.getDrawable(R.drawable.empty).constantState) {
            return
        }

        if (activePlayer == player1) {
            lastX?.let { removeImage(it) }
            img.setImageResource(R.drawable.`x_solid`)
            player1Moves.add(img)

            if (player1Moves.size > 2) {
                lastX = player1Moves.poll()
                lastX?.let {
                    Handler(Looper.getMainLooper()).postDelayed({
                        it.setImageResource(R.drawable.x_solid_faint)
                    }, 1000) // 1 saniye gecikme
                }
            }
        } else {
            lastO?.let { removeImage(it) }
            img.setImageResource(R.drawable.o_solid)
            player2Moves.add(img)

            if (player2Moves.size > 2) {
                lastO = player2Moves.poll()
                lastO?.let {
                    Handler(Looper.getMainLooper()).postDelayed({
                        it.setImageResource(R.drawable.o_solid_faint)
                    }, 300)
                }
            }
        }
        //changePlayer()
    }

    private fun removeImage(img: ImageView) {
        img.setImageResource(R.drawable.empty)
    }

    private fun checkWinner(): Boolean {
        val board = arrayOf(
            arrayOf(binding.img00, binding.img01, binding.img02),
            arrayOf(binding.img10, binding.img11, binding.img12),
            arrayOf(binding.img20, binding.img21, binding.img22)
        )

        // Check the rows
        for (i in 0..2) {
            if (board[i][0].drawable != null &&
                board[i][0].drawable.constantState != resources.getDrawable(R.drawable.empty).constantState &&
                board[i][0].drawable.constantState == board[i][1].drawable.constantState &&
                board[i][0].drawable.constantState == board[i][2].drawable.constantState
            ) {
                return true
            }
        }

        // Check the columns
        for (i in 0..2) {
            if (board[0][i].drawable != null &&
                board[0][i].drawable.constantState != resources.getDrawable(R.drawable.empty).constantState &&
                board[0][i].drawable.constantState == board[1][i].drawable.constantState &&
                board[0][i].drawable.constantState == board[2][i].drawable.constantState
            ) {
                return true
            }
        }

        // Check the diagonals
        if (board[0][0].drawable != null &&
            board[0][0].drawable.constantState != resources.getDrawable(R.drawable.empty).constantState &&
            board[0][0].drawable.constantState == board[1][1].drawable.constantState &&
            board[0][0].drawable.constantState == board[2][2].drawable.constantState
        ) {
            return true
        }

        if (board[0][2].drawable != null &&
            board[0][2].drawable.constantState != resources.getDrawable(R.drawable.empty).constantState &&
            board[0][2].drawable.constantState == board[1][1].drawable.constantState &&
            board[0][2].drawable.constantState == board[2][0].drawable.constantState
        ) {
            return true
        }

        return false
    }

    private fun showImageDialog() {
        val inflater = LayoutInflater.from(this)
        val view = inflater.inflate(R.layout.dialog_image, null)

        val imageView = view.findViewById<ImageView>(R.id.dialog_image)
        imageView.setImageResource(R.drawable.winner_crown)

        val alertDialog = AlertDialog.Builder(this)
            .setView(view)
            .setMessage("Do you want to play again?")
            .setMessage("Winner is ${activePlayer.name}")
            .setPositiveButton("Yes") { dialog, which ->
                val intent : Intent = Intent(this@GameScreen, GameScreen::class.java)
                intent.putExtra("winner", activePlayer)
                intent.putExtra("player1", player1)
                intent.putExtra("player2", player2)
                finish()
                startActivity(intent)
            }
            .setNegativeButton("No") { dialog, which ->
                val intent : Intent = Intent(this@GameScreen, MainActivity::class.java)
                finish()
                startActivity(intent)
            }
            .create()

        alertDialog.show()
    }

}