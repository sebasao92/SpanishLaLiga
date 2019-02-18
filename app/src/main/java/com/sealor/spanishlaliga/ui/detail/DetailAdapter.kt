package com.sealor.spanishlaliga.ui.detail

import android.view.View
import android.widget.ImageView
import com.sealor.spanishlaliga.R
import com.sealor.spanishlaliga.databinding.TeamDetailsBinding
import com.sealor.spanishlaliga.model.Event
import com.sealor.spanishlaliga.model.Team
import com.squareup.picasso.Picasso

/**
 * Adapter for team details
 */
class DetailAdapter {

    /**
     * List of events of the Team
     */
    private var events : List<Event> = listOf()

    /**
     * Databinding instance
     */
    private lateinit var binding : TeamDetailsBinding

    /**
     * Related team
     */
    private lateinit var team : Team

    fun updateEvents(events : List<Event>){
        this.events = events
    }

    fun getBindingAndTeam(binding: TeamDetailsBinding, team : Team){
        this.binding = binding
        this.team = team
    }

    fun setActivityValues(){
        binding.team = team
        setBadgeAndJersey()
        setEvents()
        setSocialMedia()
        binding.executePendingBindings()
    }

    private fun setEvents(){
        var eventsString = ""
        for(event in events)
            eventsString = eventsString.plus(event.strEvent.plus(" --- ").plus(event.dateEvent).plus('\n'))

        binding.eventsText = eventsString
    }

    private fun setBadgeAndJersey(){
        Picasso.get().load(team.strTeamBadge).into(binding.detailBadge.findViewById(R.id.detail_badge) as ImageView)
        Picasso.get().load(team.strTeamJersey).into(binding.detailJersey.findViewById(R.id.detail_jersey) as ImageView)
    }

    private fun setSocialMedia(){
        if(team.strYoutube != "") binding.youtubeButton.visibility = View.VISIBLE
        if(team.strFacebook != "") binding.facebookButton.visibility = View.VISIBLE
        if(team.strInstagram != "") binding.instagramButton.visibility = View.VISIBLE
        if(team.strWebsite != "") binding.websiteButton.visibility = View.VISIBLE
        if(team.strTwitter != "") binding.twitterButton.visibility = View.VISIBLE
    }
}