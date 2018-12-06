package com.lukmannudin.assosiate.footballclubschedule

import android.database.sqlite.SQLiteConstraintException
import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import com.google.gson.Gson
import com.lukmannudin.assosiate.footballclub.database.database
import com.lukmannudin.assosiate.footballclubschedule.APIRequest.ApiRepository
import com.lukmannudin.assosiate.footballclubschedule.Contract.ScheduleContract
import com.lukmannudin.assosiate.footballclubschedule.Contract.TeamMatchDetailContract
import com.lukmannudin.assosiate.footballclubschedule.Model.Schedule
import com.lukmannudin.assosiate.footballclubschedule.Model.TeamDetail
import com.lukmannudin.assosiate.footballclubschedule.Presenter.SchedulePresenter
import com.lukmannudin.assosiate.footballclubschedule.Presenter.TeamMatchDetailPresenter
import com.lukmannudin.assosiate.footballclubschedule.navigation_view.NavigationFavorites
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.team_detail_layout.*
import org.jetbrains.anko.db.delete
import org.jetbrains.anko.intentFor
import org.jetbrains.anko.startActivity


class FavoritesMatchDetailActivity : AppCompatActivity(), TeamMatchDetailContract, ScheduleContract {

    private var teamDetails: MutableList<TeamDetail> = mutableListOf()
    lateinit var presenterMatch: TeamMatchDetailPresenter
    lateinit var favoriteDetailPresenter: SchedulePresenter
    lateinit var matchId: String
    lateinit var strEvent: String
    private var menuItem: Menu? = null
    private var isFavorite: Boolean = false



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.team_detail_layout)
        supportActionBar?.title = "TeamUtils Detail"
        supportActionBar?.setDisplayHomeAsUpEnabled(true)


        matchId = intent.getStringExtra("teamMatchEventId")

        strEvent = intent.getStringExtra("teamEvent")
        val homeTeamId = intent.getStringExtra("teamHomeId")
        val awayTeamId = intent.getStringExtra("teamAwayId")
        val dateEvent = intent.getStringExtra("eventMatchDate")
        dateEventSchedule.text = dateEvent

        val request = ApiRepository()
        val gson = Gson()
        presenterMatch = TeamMatchDetailPresenter(this, request, gson)
        presenterMatch.getHomeTeamDetailList(homeTeamId)
        presenterMatch.getAwayTeamDetailList(awayTeamId)

        favoriteDetailPresenter = SchedulePresenter(this,request,gson)
        favoriteDetailPresenter.getScheduleDetails(matchId)

    }



    override fun showTeamList(data: List<Schedule>) {
        teamHomeGoals.text = data[0].intHomeScore
        teamHomeGoalDetail.text = data[0].strHomeGoalDetails
        teamHomeGoalkeeper.text = data[0].strHomeLineupGoalkeeper
        teamHomeDefense.text = data[0].strHomeLineupDefense
        teamHomeMidfield.text = data[0].strHomeLineupMidfield
        teamHomeForward.text = data[0].strHomeLineupForward
        teamHomeSubstitute.text = data[0].strHomeLineupSubstitutes

        teamAwayGoals.text = data[0].intAwayScore
        teamAwayGoalDetail.text = data[0].strAwayGoalDetails
        teamAwayGoalkeeper.text = data[0].strAwayLineupGoalkeeper
        teamAwayDefense.text = data[0].strAwayLineupDefense
        teamAwayMidfield.text = data[0].strAwayLineupMidfield
        teamAwayForward.text = data[0].strAwayLineupForward
        teamAwaySubstitute.text = data[0].strAwayLineupSubstitutes

    }

    override fun showHomeTeamDetailList(data: List<TeamDetail>) {
        teamDetails.clear()
        teamDetails.addAll(data)
        data[0].getStrTeamBadge().let { Picasso.get().load(it).into(teamBadgeHome) }
        teamHomeName.text = data[0].getStrTeam()
    }

    override fun showAwayTeamDetailList(data: List<TeamDetail>) {
        teamDetails.clear()
        teamDetails.addAll(data)
        data[0].getStrTeamBadge().let { Picasso.get().load(it).into(teamBadgeAway) }
        teamAwayName.text = data[0].getStrTeam()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
//        return super.onCreateOptionsMenu(menu)
        menuInflater.inflate(R.menu.detail_menu,menu)
        menuItem = menu
        setFavorite()
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                finish()
                true
            }
            R.id.add_to_favorite -> {
                removeFromFavorite()
                    supportFragmentManager.popBackStack()
                true
            }

            else -> super.onOptionsItemSelected(item)
        }

    }



    private fun removeFromFavorite(){
        try {
            database.use {
                delete(Favorite.TABLE_FAVORITE,
                    "(TEAM_MATCH_EVENT_ID = {eventId})",
                    "eventId" to matchId
                )
            }
//            swipeRefresh.snackbar("Removed to favorite").show()
            Toast.makeText(this,strEvent+" removed from favorite", Toast.LENGTH_SHORT).show()
            startActivity(intentFor<HomeActivity>("itemid" to "favorites"))
        } catch (e: SQLiteConstraintException){
//            swipeRefresh.snackbar(e.localizedMessage).show()
            Toast.makeText(this,e.localizedMessage, Toast.LENGTH_SHORT).show()
        }
    }

    private fun setFavorite() {
        menuItem?.getItem(0)?.icon = ContextCompat.getDrawable(this, R.drawable.ic_added_to_favorites)

    }

    override fun showLoading() {
    }

    override fun hideLoading() {
    }


}