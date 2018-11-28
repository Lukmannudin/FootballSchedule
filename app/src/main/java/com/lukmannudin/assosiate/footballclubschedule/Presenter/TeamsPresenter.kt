package com.lukmannudin.assosiate.footballclubschedule.Presenter

import android.util.Log
import com.google.gson.Gson
import com.lukmannudin.assosiate.footballclubschedule.APIRequest.APITeams
import com.lukmannudin.assosiate.footballclubschedule.APIRequest.ApiRepository
import com.lukmannudin.assosiate.footballclubschedule.Contract.TeamsContract
import com.lukmannudin.assosiate.footballclubschedule.CoroutineContextProvider
import com.lukmannudin.assosiate.footballclubschedule.Response.TeamResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread

class TeamsPresenter(
    private val view: TeamsContract,
    private val apiRepository: ApiRepository,
    private val gson: Gson,
    private val context: CoroutineContextProvider = CoroutineContextProvider()
) {
    fun getTeamList(league: String?) {
        view.showLoading()
        GlobalScope.launch(Dispatchers.Main) {
            val data = gson.fromJson(
                apiRepository
                    .doRequest(APITeams.getTeams(league)).await(),
                TeamResponse::class.java
            )
            view.hideLoading()
            view.showTeamList(data.teams)
        }
    }

}