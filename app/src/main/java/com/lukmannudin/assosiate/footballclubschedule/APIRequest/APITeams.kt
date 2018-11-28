package com.lukmannudin.assosiate.footballclubschedule.APIRequest

import android.net.Uri
import com.lukmannudin.assosiate.footballclubschedule.BuildConfig

object APITeams {
    fun getTeams(league: String?):String {
        println(league)
        return Uri.parse(BuildConfig.BASE_URL).buildUpon()
            .appendPath("api")
            .appendPath("v1")
            .appendPath("json")
            .appendPath(BuildConfig.TSDB_API_KEY)
            .appendPath("search_all_teams.php")
            .appendQueryParameter("l",league)
//                .appendQueryParameter("c","Spain")
            .build()
            .toString()
    }
    fun getTeamDetail(teamId: String?): String{
        return Uri.parse(BuildConfig.BASE_URL).buildUpon()
            .appendPath("api")
            .appendPath("v1")
            .appendPath("json")
            .appendPath(BuildConfig.TSDB_API_KEY)
            .appendPath("lookupteam.php")
            .appendQueryParameter("id", teamId)
            .build()
            .toString()
    }
}