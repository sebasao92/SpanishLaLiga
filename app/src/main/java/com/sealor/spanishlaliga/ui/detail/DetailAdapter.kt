package com.sealor.spanishlaliga.ui.detail

import android.content.Context
import com.sealor.spanishlaliga.model.Event

class DetailAdapter(private val context : Context) {

    private var events : List<Event> = listOf()

    fun updateEvents(events : List<Event>){
        this.events = events
    }

    fun getEvents() : List<Event> {
        return events
    }
}