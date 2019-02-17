package com.sealor.spanishlaliga.ui.detail

import android.content.Intent
import android.databinding.DataBindingUtil
import android.net.Uri
import android.os.Bundle
import android.speech.tts.TextToSpeech
import android.view.View
import android.widget.ImageView
import android.widget.Toast
import com.sealor.spanishlaliga.R
import com.sealor.spanishlaliga.base.BaseActivity
import com.sealor.spanishlaliga.databinding.TeamDetailsBinding
import com.sealor.spanishlaliga.model.EventsResponse
import com.sealor.spanishlaliga.model.Team
import com.sealor.spanishlaliga.utils.UrlFixer
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.team_details.*

@Suppress("unused")
class DetailActivity : BaseActivity<DetailPresenter>(), DetailView {

    private lateinit var team : Team
    private lateinit var binding : TeamDetailsBinding
    private val detailAdapter = DetailAdapter(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.team_details)
        getIncomingIntent()
        presenter.onViewCreated(team.idTeam.toInt())
        setTeamDetails()
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.onViewDestroyed()
    }

    override fun instantiatePresenter(): DetailPresenter {
        return DetailPresenter(this)
    }

    private fun getIncomingIntent(){
        team = intent.getSerializableExtra("Team") as Team
    }

    override fun showError(error : String) {
        Toast.makeText(this, error, Toast.LENGTH_SHORT).show()
    }

    override fun updateEvents(events: EventsResponse) {
        detailAdapter.updateEvents(events.events)
        var eventsString = ""
        for(event in detailAdapter.getEvents())
            eventsString = eventsString.plus(event.strEvent.plus(" --- ").plus(event.dateEvent).plus('\n'))

        this.team_events_detail.text = eventsString
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
        callUrl(view, team.strFacebook as String)
    }

    fun clickOnYoutube(view: View){
        callUrl(view,team.strYoutube as String)
    }

    fun clickOnWebsite(view: View){
        callUrl(view,team.strWebsite as String)
    }

    fun clickOnInstagram(view: View){
        callUrl(view, team.strInstagram as String)
    }

    fun clickOnTwitter(view: View){
        callUrl(view,team.strTwitter as String)
    }

    private fun callUrl(view: View, url : String){
        try{
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(UrlFixer.fixUrl(url)))
            startActivity(intent)
        } catch (t : Throwable){
            Toast.makeText(view.context, "Error al llamar la URL", Toast.LENGTH_SHORT).show()
        }
    }

    override fun hideLoading() {
        binding.progressVisibility = View.GONE
    }

    override fun showLoading() {
        binding.progressVisibility = View.VISIBLE
    }
}