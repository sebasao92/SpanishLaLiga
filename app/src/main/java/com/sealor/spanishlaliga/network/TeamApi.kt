package com.sealor.spanishlaliga.network

import com.sealor.spanishlaliga.model.EventsResponse
import com.sealor.spanishlaliga.model.TeamsResponse
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Path

interface TeamApi {

    @GET("lookup_all_teams.php?id=4335")
    fun getTeams(): Observable<TeamsResponse>

    @GET("eventsnext.php?id={id}")
    fun getEvents(@Path("id") id : Int): Observable<EventsResponse>
}