package com.sealor.spanishlaliga.ui

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.os.Handler
import com.sealor.spanishlaliga.R
import com.sealor.spanishlaliga.ui.team.TeamActivity
import com.sealor.spanishlaliga.utils.SPLASH_DELAY

class SplashActivity : AppCompatActivity() {

    private var delayHandler: Handler? = null

    private val mRunnable: Runnable = Runnable {
        if (!isFinishing) {

            val intent = Intent(applicationContext, TeamActivity::class.java)
            startActivity(intent)
            finish()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.splash_view)

        //Initialize the Handler
        delayHandler = Handler()

        //Navigate with delay
        delayHandler!!.postDelayed(mRunnable, SPLASH_DELAY)

    }

    public override fun onDestroy() {

        if (delayHandler != null) {
            delayHandler!!.removeCallbacks(mRunnable)
        }

        super.onDestroy()
    }

}