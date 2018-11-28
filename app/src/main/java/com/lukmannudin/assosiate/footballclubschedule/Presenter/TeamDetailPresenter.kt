package com.lukmannudin.assosiate.footballclubschedule.Presenter

import com.google.gson.Gson
import com.lukmannudin.assosiate.footballclubschedule.APIRequest.APITeams
import com.lukmannudin.assosiate.footballclubschedule.APIRequest.ApiRepository
import com.lukmannudin.assosiate.footballclubschedule.Contract.TeamDetailContract
import com.lukmannudin.assosiate.footballclubschedule.CoroutineContextProvider
import com.lukmannudin.assosiate.footballclubschedule.Response.TeamResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch


class TeamDetailPresenter(
    private val view: TeamDetailContract,
    private val apiRepository: ApiRepository,
    private val gson: Gson,
    private val context: CoroutineContextProvider = CoroutineContextProvider()
) {

    fun getTeamDetail(teamId: String) {
        view.showLoading()
        GlobalScope.launch(Dispatchers.Main) {
            val data = gson.fromJson(
                apiRepository
                    .doRequest(APITeams.getTeamDetail(teamId)).await(),
                TeamResponse::class.java
            )

            view.hideLoading()
            view.showDetailTeam(data.teams)
        }
    }
}