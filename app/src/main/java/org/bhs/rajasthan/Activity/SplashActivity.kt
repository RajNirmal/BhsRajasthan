package org.bhs.rajasthan.Activity

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity
import org.bhs.rajasthan.R

class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val handler = Handler()
        handler.postDelayed(Runnable {
            moveToHomePage()
        }, 1000)
    }

    private fun moveToHomePage() {
        val homePageIntent = Intent(this, HomeActivity::class.java)
        startActivity(homePageIntent)
    }
}