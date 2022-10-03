package uz.eloving.quizgame

import android.content.Context
import android.graphics.Color
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import uz.eloving.quizgame.databinding.ActivityMainBinding
import uz.eloving.quizgame.ui.MainFragment

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        window.navigationBarColor = Color.parseColor("#FF6F53FD") // Change navigation bar color
        supportFragmentManager
            .beginTransaction()
            .add(binding.container.id, MainFragment()) // Add Fragment to MainActivity
            .commit()
    }
}