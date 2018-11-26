package com.lukmannudin.assosiate.footballclubschedule.Presenter

import com.google.gson.Gson
import com.lukmannudin.assosiate.footballclubschedule.APIRequest.APITeamDetails
import com.lukmannudin.assosiate.footballclubschedule.APIRequest.ApiRepository
import com.lukmannudin.assosiate.footballclubschedule.Contract.TeamDetailContract
import com.lukmannudin.assosiate.footballclubschedule.Response.TeamDetailResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class TeamDetailPresenter(
    private val view: TeamDetailContract,
    private val apiRepository: ApiRepository,
    private val gson: Gson
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