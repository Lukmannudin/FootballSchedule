package com.lukmannudin.assosiate.footballclubschedule

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.google.gson.Gson
import com.lukmannudin.assosiate.footballclubschedule.APIRequest.ApiRepository
import com.lukmannudin.assosiate.footballclubschedule.Contract.TeamDetailContract
import com.lukmannudin.assosiate.footballclubschedule.Model.TeamDetail
import com.lukmannudin.assosiate.footballclubschedule.Presenter.TeamDetailPresenter
import kotlinx.android.synthetic.main.team_detail_layout.*

class TeamListActivity : AppCompatActivity(), TeamDetailContract {
    private var teamDetails: MutableList<TeamDetail> = mutableListOf()
    lateinit var presenter: TeamDetailPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_test)
        setContentView(R.layout.team_detail_layout)
        val request = ApiRepository()
        val gson = Gson()

        presenter = TeamDetailPresenter(this, request, gson)
        presenter.getTeamDetailList("133616")
        val strEvent:String = intent.getStringExtra(MainActivity.teamSchedule)
        dateEventSchedule.text = strEvent
//        Log.i("CEK TEAM",teamDetails[1].getStrTeam())
    }

    override fun showLoading() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun hideLoading() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun showTeamDetailList(data: List<TeamDetail>) {
        teamDetails.clear()
        teamDetails.addAll(data)
//        dateEventSchedule.text = data[0]
    }
}
