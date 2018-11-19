package com.lukmannudin.assosiate.footballclubschedule

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.google.gson.Gson
import com.lukmannudin.assosiate.footballclubschedule.APIRequest.ApiRepository
import com.lukmannudin.assosiate.footballclubschedule.Contract.TeamDetailContract
import com.lukmannudin.assosiate.footballclubschedule.Model.Schedule
import com.lukmannudin.assosiate.footballclubschedule.Model.TeamDetail
import com.lukmannudin.assosiate.footballclubschedule.Presenter.TeamDetailPresenter
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.team_detail_layout.*

class TeamListActivity : AppCompatActivity(), TeamDetailContract {
    private var teamDetails: MutableList<TeamDetail> = mutableListOf()
    lateinit var presenter: TeamDetailPresenter
    private lateinit var IntentData: Schedule


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_test)
        setContentView(R.layout.team_detail_layout)
        getSupportActionBar()?.setDisplayHomeAsUpEnabled(true);

        IntentData = intent.getParcelableExtra<Schedule>("parcel")
        dateEventSchedule.text = IntentData.dateEvent


        val request = ApiRepository()
        val gson = Gson()
        presenter = TeamDetailPresenter(this, request, gson)
        presenter.getHomeTeamDetailList(IntentData.idHomeTeam)
        presenter.getAwayTeamDetailList(IntentData.idAwayTeam)

    }

    override fun showLoading() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun hideLoading() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun showHomeTeamDetailList(data: List<TeamDetail>) {


        teamDetails.clear()
        teamDetails.addAll(data)
        data[0].getStrTeamBadge().let { Picasso.get().load(it).into(teamBadgeHome) }
        teamHomeName.text = data[0].getStrTeam()
        teamHomeGoals.text = IntentData.intHomeScore
        teamHomeGoalDetail.text = IntentData.strHomeGoalDetails.toString().replace(";","\n")
        teamHomeGoalkeeper.text = IntentData.strHomeLineupGoalkeeper.toString().replace(";","\n")
        teamHomeDefense.text = IntentData.strHomeLineupDefense.toString().replace(";","\n")
        teamHomeMidfield.text = IntentData.strHomeLineupMidfield.toString().replace(";","\n")
        teamHomeForward.text = IntentData.strHomeLineupForward.toString().replace(";","\n")
        teamHomeSubstitute.text = IntentData.strHomeLineupSubstitutes.toString().replace(";","\n")
    }

    override fun showAwayTeamDetailList(data: List<TeamDetail>) {
        teamDetails.clear()
        teamDetails.addAll(data)
        data[0].getStrTeamBadge().let { Picasso.get().load(it).into(teamBadgeAway) }
        teamAwayName.text = data[0].getStrTeam()
        teamAwayGoals.text = IntentData.intAwayScore
        teamAwayGoalDetail.text = IntentData.strAwayGoalDetails.toString().replace(";","\n")
        teamAwayGoalkeeper.text = IntentData.strAwayLineupGoalkeeper.toString().replace(";","\n")
        teamAwayDefense.text = IntentData.strAwayLineupDefense.toString().replace(";","\n")
        teamAwayMidfield.text = IntentData.strAwayLineupMidfield.toString().replace(";","\n")
        teamAwayForward.text = IntentData.strAwayLineupForward.toString().replace(";","\n")
        teamAwaySubstitute.text = IntentData.strAwayLineupSubstitutes.toString().replace(";","\n")
    }
}
