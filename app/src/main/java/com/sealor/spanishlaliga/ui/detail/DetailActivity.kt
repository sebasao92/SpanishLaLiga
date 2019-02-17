package com.sealor.spanishlaliga.ui.detail

import android.content.Intent
import android.databinding.DataBindingUtil
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import com.sealor.spanishlaliga.R
import com.sealor.spanishlaliga.base.BaseActivity
import com.sealor.spanishlaliga.databinding.TeamDetailsBinding
import com.sealor.spanishlaliga.model.Event
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
        setContentView(R.layout.team_details)
        binding = DataBindingUtil.setContentView(this, R.layout.team_details)
        getIncomingIntent()
        presenter.onViewCreated(team.idTeam.toInt())
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.onViewDestroyed()
    }

    override fun onResume() {
        super.onResume()
        setTeamDetails()
        Log.i("ONSTART", "ENTRE")
    }

    private fun getIncomingIntent(){
        team = intent.getSerializableExtra("Team") as Team
    }

    override fun showError(error : String) {
        Toast.makeText(this, error, Toast.LENGTH_SHORT)
    }

    override fun updateEvents(events: EventsResponse) {
        detailAdapter.updateEvents(events.events)
        var eventsString = ""
        for(event in detailAdapter.getEvents())
            eventsString = eventsString.plus(event.strEvent.plus(" --- ").plus(event.dateEvent).plus('\n'))

        this.team_events_detail.text = eventsString
    }

    override fun instantiatePresenter(): DetailPresenter {
        return DetailPresenter(this)
    }

    private fun setSocialMedia(){
        if(team.strYoutube != "") this.youtube_button.visibility = View.VISIBLE
        if(team.strFacebook != "") this.facebook_button.visibility = View.VISIBLE
        if(team.strInstagram != "") this.instagram_button.visibility = View.VISIBLE
        if(team.strWebsite != "") this.website_button.visibility = View.VISIBLE
        if(team.strTwitter != "") this.twitter_button.visibility = View.VISIBLE
    }

    private fun setTeamDetails(){
        this.team_description_detail.text = team.strDescriptionEN
        this.team_year_detail.text = team.intFormedYear
        this.team_name_detail.text = team.strTeam

        Picasso.get().load(team.strTeamBadge).into(this.findViewById(R.id.detail_badge) as ImageView)
        Picasso.get().load(team.strTeamJersey).into(this.findViewById(R.id.detail_jersey) as ImageView)

        setSocialMedia()
    }

    fun clickOnFacebook(view: View){
        Log.i("FACEBOOK", UrlFixer.fixUrl(team.strFacebook as String))
        callUrl(view, team.strFacebook as String, R.id.facebook_button)
    }

    fun clickOnYoutube(view: View){
        Log.i("YOUTUBE", UrlFixer.fixUrl(team.strYoutube as String))
        callUrl(view, team.strYoutube as String, R.id.youtube_button)
    }

    fun clickOnWebsite(view: View){
        Log.i("WEB", UrlFixer.fixUrl(team.strWebsite as String))
        callUrl(view, team.strWebsite as String, R.id.website_button)
    }

    fun clickOnInstagram(view: View){
        Log.i("INSTAGRAM", UrlFixer.fixUrl(team.strInstagram as String))
        callUrl(view, team.strInstagram as String, R.id.instagram_button)
    }

    fun clickOnTwitter(view: View){
        Log.i("TWITTER", UrlFixer.fixUrl(team.strTwitter as String))
        callUrl(view, team.strTwitter as String, R.id.twitter_button)
    }

    private fun callUrl(view: View, url : String, buttonId : Int){
        val button : Button = view.findViewById(buttonId)
        button.setOnClickListener {
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(UrlFixer.fixUrl(url)))
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