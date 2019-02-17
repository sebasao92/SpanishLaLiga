package com.sealor.spanishlaliga.ui.team

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.sealor.spanishlaliga.R
import com.sealor.spanishlaliga.model.Event
import com.sealor.spanishlaliga.model.Team
import com.sealor.spanishlaliga.utils.UrlFixer
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.team_details.*

class DetailActivity : AppCompatActivity() {

    private lateinit var team : Team
    private lateinit var events : List<Event>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.team_details)
        getIncomingIntent()
        setTeamDetails()
        clickOnFacebook()
        clickOnInstagram()
        clickOnYoutube()
        clickOnWebsite()
        clickOnTwitter()
    }

    private fun getIncomingIntent(){
        team = intent.getSerializableExtra("Team") as Team
    }

    private fun setSocialMedia(){
        if(team.strYoutube != "") this.youtube_text.visibility = View.VISIBLE
        if(team.strFacebook != "") this.facebook_text.visibility = View.VISIBLE
        if(team.strInstagram != "") this.instagram_text.visibility = View.VISIBLE
        if(team.strWebsite != "") this.website_text.visibility = View.VISIBLE
        if(team.strTwitter != "") this.twitter_text.visibility = View.VISIBLE
    }

    private fun setTeamDetails(){
        this.team_description_detail.text = team.strDescriptionEN
        this.team_year_detail.text = team.intFormedYear
        this.team_name_detail.text = team.strTeam

        Picasso.get().load(team.strTeamBadge).into(this.findViewById(R.id.detail_badge) as ImageView)
        Picasso.get().load(team.strTeamJersey).into(this.findViewById(R.id.detail_jersey) as ImageView)

        setSocialMedia()
    }

    fun clickOnFacebook(){
        val facebookText : TextView = this.findViewById(R.id.facebook_text)
        facebookText.setOnClickListener {
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(UrlFixer.fixUrl(team.strFacebook as String)))
            startActivity(intent)
        }
    }

    fun clickOnYoutube(){
        val youtubeText : TextView = this.findViewById(R.id.facebook_text)
        youtubeText.setOnClickListener {
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(UrlFixer.fixUrl(team.strFacebook as String)))
            startActivity(intent)
        }
    }

    fun clickOnWebsite(){
        val websiteText : TextView = this.findViewById(R.id.facebook_text)
        websiteText.setOnClickListener {
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(UrlFixer.fixUrl(team.strFacebook as String)))
            startActivity(intent)
        }
    }

    fun clickOnInstagram(){
        val instagramText : TextView = this.findViewById(R.id.facebook_text)
        instagramText.setOnClickListener {
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(UrlFixer.fixUrl(team.strFacebook as String)))
            startActivity(intent)
        }
    }

    fun clickOnTwitter(){
        val twitterText : TextView = this.findViewById(R.id.facebook_text)
        twitterText.setOnClickListener {
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(UrlFixer.fixUrl(team.strFacebook as String)))
            startActivity(intent)
        }
    }
}