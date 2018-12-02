package com.lukmannudin.assosiate.footballclubschedule.Presenter

import com.google.gson.Gson
import com.lukmannudin.assosiate.footballclubschedule.APIRequest.APINextScheduleTeam
import com.lukmannudin.assosiate.footballclubschedule.APIRequest.APITeams
import com.lukmannudin.assosiate.footballclubschedule.APIRequest.ApiRepository
import com.lukmannudin.assosiate.footballclubschedule.Contract.PlayerContract
import com.lukmannudin.assosiate.footballclubschedule.Contract.ScheduleContract
import com.lukmannudin.assosiate.footballclubschedule.R.array.league
import com.lukmannudin.assosiate.footballclubschedule.Response.ScheduleResponse
import com.lukmannudin.assosiate.footballclubschedule.Response.playerResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class PlayerPresenter(
    private val view: PlayerContract,
    private val apiRepository: ApiRepository,
    private val gson: Gson
) {
    fun getPlayers(teamName: String?) {
        view.showLoading()
        GlobalScope.launch(Dispatchers.Main) {
            val data = gson.fromJson(
                apiRepository
                    .doRequest(APITeams.getPLayerTeams(teamName)).await(),
                playerResponse::class.java
            )
            view.hideLoading()
            view.showTeamList(data.player)
        }
    }
}