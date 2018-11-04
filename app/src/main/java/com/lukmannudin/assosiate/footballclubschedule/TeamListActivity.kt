package com.lukmannudin.assosiate.footballclubschedule

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.google.gson.Gson
import com.lukmannudin.assosiate.footballclubschedule.APIRequest.ApiRepository
import com.lukmannudin.assosiate.footballclubschedule.Contract.TeamDetailContract
import com.lukmannudin.assosiate.footballclubschedule.Model.TeamDetail
import com.lukmannudin.assosiate.footballclubschedule.Presenter.TeamDetailPresenter
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.team_detail_layout.*

class TeamListActivity : AppCompatActivity(), TeamDetailContract {
    private var teamDetails: MutableList<TeamDetail> = mutableListOf()
    lateinit var presenter: TeamDetailPresenter
    private lateinit var strEvent: String
    private lateinit var strTeamHome: String
    private lateinit var strTeamAway: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_test)
        setContentView(R.layout.team_detail_layout)
        getSupportActionBar()?.setDisplayHomeAsUpEnabled(true);
        strEvent = intent.getStringExtra(MainActivity.teamSchedule)

        dateEventSchedule.text = strEvent
        strTeamHome = intent.getStringExtra(MainActivity.teamHomeName)
        strTeamAway = intent.getStringExtra(MainActivity.teamAwayName)


        val request = ApiRepository()
        val gson = Gson()

        presenter = TeamDetailPresenter(this, request, gson)
        presenter.getHomeTeamDetailList(strTeamHome)
        presenter.getAwayTeamDetailList(strTeamAway)


//        Log.i("CEK TEAM",teamDetails[1].getStrTeam())
    }

    override fun showLoading() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun hideLoading() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun showHomeTeamDetailList(data: List<TeamDetail>) {
        val homeGoal = intent.getStringExtra(MainActivity.teamHomeGoal)
        val homeGoalDetail = intent.getStringExtra(MainActivity.teamHomeGoalDetail)
        val homeGoalKeeper = intent.getStringExtra(MainActivity.teamHomeGoalKeeper)
        val homeDefense = intent.getStringExtra(MainActivity.teamHomeDefense)
        val homeMidfield = intent.getStringExtra(MainActivity.teamHomeMidfield)
        val homeForward = intent.getStringExtra(MainActivity.teamHomeForward)
        val homeSubstitutes = intent.getStringExtra(MainActivity.teamHomeSubstitutes)

        teamDetails.clear()
        teamDetails.addAll(data)
        data[0].getStrTeamBadge().let { Picasso.get().load(it).into(teamBadgeHome) }
        teamHomeGoals.text = homeGoal
        teamHomeGoalDetail.text = homeGoalDetail.replace(";","\n")
        teamHomeGoalkeeper.text = homeGoalKeeper.replace(";","\n")
        teamHomeDefense.text = homeDefense.replace(";","\n")
        teamHomeMidfield.text = homeMidfield.replace(";","\n")
        teamHomeForward.text = homeForward.replace(";","\n")
        teamHomeSubstitute.text = homeSubstitutes.replace(";","\n")
    }

    override fun showAwayTeamDetailList(data: List<TeamDetail>) {
        val awayGoal = intent.getStringExtra(MainActivity.teamAwayGoal)
        val homeAwayDetail = intent.getStringExtra(MainActivity.teamAwayGoalDetail)
        val awayGoalKeeper = intent.getStringExtra(MainActivity.teamAwayGoalKeeper)
        val awayDefense = intent.getStringExtra(MainActivity.teamAwayDefense)
        val awayMidfield = intent.getStringExtra(MainActivity.teamAwayMidfield)
        val awayForward = intent.getStringExtra(MainActivity.teamAwayForward)
        val awaySubstitutes = intent.getStringExtra(MainActivity.teamAwaySubstitutes)

        teamDetails.clear()
        teamDetails.addAll(data)
        data[0].getStrTeamBadge().let { Picasso.get().load(it).into(teamBadgeAway) }
        teamAwayGoals.text = awayGoal
        teamAwayGoalDetail.text = homeAwayDetail.replace(";","\n")
        teamAwayGoalkeeper.text = awayGoalKeeper.replace(";","\n")
        teamAwayDefense.text = awayDefense.replace(";","\n")
        teamAwayMidfield.text = awayMidfield.replace(";","\n")
        teamAwayForward.text = awayForward.replace(";","\n")
        teamAwaySubstitute.text = awaySubstitutes.replace(";","\n")
    }
}
