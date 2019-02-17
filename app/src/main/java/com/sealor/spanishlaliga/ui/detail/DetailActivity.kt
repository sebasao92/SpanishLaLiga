package com.sealor.spanishlaliga.ui.detail

import android.content.Intent
import android.databinding.DataBindingUtil
import android.net.Uri
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import com.sealor.spanishlaliga.R
import com.sealor.spanishlaliga.base.BaseActivity
import com.sealor.spanishlaliga.databinding.TeamDetailsBinding
import com.sealor.spanishlaliga.model.EventsResponse
import com.sealor.spanishlaliga.model.Team
import com.sealor.spanishlaliga.utils.UrlFixer
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.team_details.*

class DetailActivity : BaseActivity<DetailPresenter>(), DetailView {

    private lateinit var team : Team
    private lateinit var binding : TeamDetailsBinding
    private val detailAdapter = DetailAdapter(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //binding = DataBindingUtil.setContentView(this, R.layout.team_details)
        setContentView(R.layout.team_details)
        getIncomingIntent()
        //presenter.onViewCreated(team.idTeam.toInt())
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

    override fun showError(error : String) {
        Toast.makeText(this, error, Toast.LENGTH_SHORT)
    }

    override fun updateEvents(events: EventsResponse) {
        null
    }

    override fun instantiatePresenter(): DetailPresenter {
        return DetailPresenter(this)
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

    override fun hideLoading() {
        null
    }

    override fun showLoading() {
        null
    }
}