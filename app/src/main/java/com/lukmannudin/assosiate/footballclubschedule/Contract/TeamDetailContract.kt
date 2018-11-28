package com.lukmannudin.assosiate.footballclubschedule.Contract

import com.lukmannudin.assosiate.footballclubschedule.Model.Teams


interface TeamDetailContract {
    fun showLoading()
    fun hideLoading()
    fun showDetailTeam(data: List<Teams>)
}