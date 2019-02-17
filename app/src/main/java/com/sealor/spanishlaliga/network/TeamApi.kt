package com.sealor.spanishlaliga.network

import com.sealor.spanishlaliga.model.EventsResponse
import com.sealor.spanishlaliga.model.TeamsResponse
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query

interface TeamApi {

    @GET("lookup_all_teams.php")
    fun getTeams(@Query("id") id : Int): Observable<TeamsResponse>

    @GET("eventsnext.php")
    fun getEvents(@Query("id") id : Int): Observable<EventsResponse>
}