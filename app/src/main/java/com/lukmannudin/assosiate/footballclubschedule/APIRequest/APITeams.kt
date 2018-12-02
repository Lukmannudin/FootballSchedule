package com.lukmannudin.assosiate.footballclubschedule.APIRequest

import android.net.Uri
import com.lukmannudin.assosiate.footballclubschedule.BuildConfig
import com.lukmannudin.assosiate.footballclubschedule.R.array.league

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
    fun getTeamsSearch(searchName: String?):String {
        println(searchName)
        return Uri.parse(BuildConfig.BASE_URL).buildUpon()
            .appendPath("api")
            .appendPath("v1")
            .appendPath("json")
            .appendPath(BuildConfig.TSDB_API_KEY)
            .appendPath("searchteams.php")
            .appendQueryParameter("t",searchName)
            .build()
            .toString()
    }

    fun getPLayerTeams(teamName: String?):String {
        return Uri.parse(BuildConfig.BASE_URL).buildUpon()
            .appendPath("api")
            .appendPath("v1")
            .appendPath("json")
            .appendPath(BuildConfig.TSDB_API_KEY)
            .appendPath("searchplayers.php")
            .appendQueryParameter("t",teamName)
            .build()
            .toString()
    }
}