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

@Suppress("unused")
class DetailActivity : BaseActivity<DetailPresenter>(), DetailView {

    private lateinit var team : Team
    private lateinit var binding : TeamDetailsBinding
    private val detailAdapter = DetailAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.team_details)
        getIncomingIntent()
        presenter.onViewCreated(team.idTeam.toInt())
        detailAdapter.getBindingAndTeam(binding, team)
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
        detailAdapter.setActivityValues()
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
            Toast.makeText(view.context, "Error calling the URL", Toast.LENGTH_SHORT).show()
        }
    }

    override fun hideLoading() {
        binding.progressVisibility = View.GONE
    }

    override fun showLoading() {
        binding.progressVisibility = View.VISIBLE
    }
}