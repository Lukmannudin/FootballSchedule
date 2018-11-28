package com.lukmannudin.assosiate.footballclubschedule.Presenter

import com.google.gson.Gson
import com.lukmannudin.assosiate.footballclubschedule.APIRequest.APITeamDetails
import com.lukmannudin.assosiate.footballclubschedule.APIRequest.ApiRepository
import com.lukmannudin.assosiate.footballclubschedule.Contract.TeamMatchDetailContract
import com.lukmannudin.assosiate.footballclubschedule.CoroutineContextProvider
import com.lukmannudin.assosiate.footballclubschedule.Response.TeamDetailResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class TeamMatchDetailPresenter(
    private val view: TeamMatchDetailContract,
    private val apiRepository: ApiRepository,
    private val gson: Gson,
    private val context: CoroutineContextProvider = CoroutineContextProvider()
) {
    fun getHomeTeamDetailList(idTeam: String?) {

        GlobalScope.launch(Dispatchers.Main) {
            val data = gson.fromJson(
                apiRepository
                    .doRequest(APITeamDetails.getTeamDetail(idTeam)).await(),
                TeamDetailResponse::class.java
            )
            view.showHomeTeamDetailList(data.teamDetail)
        }
    }

    fun getAwayTeamDetailList(idTeam: String?) {
        GlobalScope.launch(Dispatchers.Main) {
            val data = gson.fromJson(
                apiRepository
                    .doRequest(APITeamDetails.getTeamDetail(idTeam)).await(),
                TeamDetailResponse::class.java
            )
            view.showAwayTeamDetailList(data.teamDetail)

        }
    }
}