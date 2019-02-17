package com.sealor.spanishlaliga.model
import java.io.Serializable

/**
 * Receives the necessary values about just one team
 * It is serializable because it is sent from one activity to other one
 */
data class Team( val idTeam : String,
                 val strTeam : String?,
                 val intFormedYear : String?,
                 val strStadium : String?,
                 val strWebsite : String?,
                 val strFacebook : String?,
                 val strTwitter : String?,
                 val strInstagram : String?,
                 val strDescriptionEN : String?,
                 val strTeamBadge : String?,
                 val strTeamJersey : String?,
                 val strYoutube : String?
                ):Serializable