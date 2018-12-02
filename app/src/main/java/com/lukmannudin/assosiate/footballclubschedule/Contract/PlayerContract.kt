package com.lukmannudin.assosiate.footballclubschedule.Contract

import com.lukmannudin.assosiate.footballclubschedule.Model.Players
import com.lukmannudin.assosiate.footballclubschedule.Model.Schedule

interface PlayerContract {
    fun showLoading()
    fun hideLoading()
    fun showTeamList(data: List<Players>)
}