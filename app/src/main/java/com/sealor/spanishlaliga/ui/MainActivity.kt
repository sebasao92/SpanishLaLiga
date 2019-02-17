package com.sealor.spanishlaliga.ui

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.Toast
import com.sealor.spanishlaliga.R
import com.sealor.spanishlaliga.ui.team.TeamActivity

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun clickOnTeam1(view : View){
        callToLeagueView(view, 1)
    }

    fun clickOnTeam2(view : View){
        callToLeagueView(view, 2)
    }

    fun clickOnTeam3(view : View){
        callToLeagueView(view, 3)
    }

    private fun callToLeagueView(view : View, id : Int){

        val league : Int = when(id){
            1 -> 4335
            2 -> 4328
            3 -> 4334
            else -> 0
        }

        if(league != 0) {
            try{
                val intent = Intent(view.context, TeamActivity::class.java)
                intent.putExtra("leagueId", league)
                startActivity(intent)
            }catch (t : Throwable){
                Toast.makeText(this, "Error calling Teams View", Toast.LENGTH_SHORT).show()
            }
        }
    }
}