package com.sealor.spanishlaliga.network

import com.sealor.spanishlaliga.model.EventsResponse
import com.sealor.spanishlaliga.model.TeamsResponse
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query

interface TeamApi {

    /**
     *  Asks for all teams by League
     */
    @GET("lookup_all_teams.php")
    fun getTeams(@Query("id") id : Int): Observable<TeamsResponse>

    /**
     * Asks for all events by Team
     */
    @GET("eventsnext.php")
    fun getEvents(@Query("id") id : Int): Observable<EventsResponse>
}