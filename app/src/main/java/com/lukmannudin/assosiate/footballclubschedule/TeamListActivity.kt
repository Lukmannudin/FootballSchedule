package com.lukmannudin.assosiate.footballclubschedule

import android.database.sqlite.SQLiteConstraintException
import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import android.widget.ProgressBar
import android.widget.Toast
import com.google.gson.Gson
import com.lukmannudin.assosiate.footballclub.database.database
import com.lukmannudin.assosiate.footballclubschedule.APIRequest.ApiRepository
import com.lukmannudin.assosiate.footballclubschedule.Contract.TeamDetailContract
import com.lukmannudin.assosiate.footballclubschedule.Model.Schedule
import com.lukmannudin.assosiate.footballclubschedule.Model.TeamDetail
import com.lukmannudin.assosiate.footballclubschedule.Presenter.TeamDetailPresenter
import com.lukmannudin.assosiate.footballclubschedule.R.drawable.ic_add_to_favorites
import com.lukmannudin.assosiate.footballclubschedule.R.drawable.ic_added_to_favorites
import com.lukmannudin.assosiate.footballclubschedule.R.id.add_to_favorite
import com.lukmannudin.assosiate.footballclubschedule.R.menu.detail_menu
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.team_detail_layout.*
import org.jetbrains.anko.db.classParser
import org.jetbrains.anko.db.delete
import org.jetbrains.anko.db.insert
import org.jetbrains.anko.db.select


class TeamListActivity : AppCompatActivity(), TeamDetailContract {
    private var teamDetails: MutableList<TeamDetail> = mutableListOf()
    lateinit var presenter: TeamDetailPresenter
    private lateinit var IntentData: Schedule
    private var menuItem: Menu? = null
    private var isFavorite: Boolean = false
    private lateinit var progressBar: ProgressBar
    private lateinit var swipeRefresh: SwipeRefreshLayout
    private lateinit var idHomeTeam: String
    private lateinit var idAwayTeam: String
    private lateinit var teamMatchEventId: String



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_test)
        setContentView(R.layout.team_detail_layout)
        supportActionBar?.title = "Team Detail"
        supportActionBar?.setDisplayHomeAsUpEnabled(true)


        IntentData = intent.getParcelableExtra<Schedule>("parcel")
        idHomeTeam = IntentData.idHomeTeam.toString()
        idAwayTeam = IntentData.idAwayTeam.toString()
        teamMatchEventId = IntentData.idEvent.toString()
        dateEventSchedule.text = IntentData.dateEvent

        favoriteState()
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

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
//        return super.onCreateOptionsMenu(menu)
        menuInflater.inflate(detail_menu,menu)
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
            add_to_favorite -> {
                if (isFavorite) removeFromFavorite() else addToFavorite()
                isFavorite = !isFavorite
                setFavorite()
                true
            }

            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun addToFavorite(){
        try {
            database.use {
                insert(Favorite.TABLE_FAVORITE,
                    Favorite.TEAM_MATCH_EVENT_ID to IntentData.idEvent,
                    Favorite.TEAM_MATCH_EVENT_DATE to IntentData.dateEvent,
                    Favorite.TEAM_HOME_ID to IntentData.idHomeTeam,
                    Favorite.TEAM_AWAY_ID to IntentData.idAwayTeam,
                    Favorite.TEAM_MATCH_EVENT_DATE to IntentData.dateEvent,
                    Favorite.TEAM_HOME_NAME to IntentData.strHomeTeam,
                    Favorite.TEAM_AWAY_NAME to IntentData.strAwayTeam,
                    Favorite.TEAM_HOME_SCORE to IntentData.intHomeScore,
                    Favorite.TEAM_AWAY_SCORE to IntentData.intAwayScore
                )
            }
//            snackbar("Added to favorite").show()
            Toast.makeText(this,"Added to favorite",Toast.LENGTH_SHORT).show()
        } catch (e: SQLiteConstraintException){
//            snackbar(e.localizedMessage).show()
            Toast.makeText(this,e.localizedMessage,Toast.LENGTH_SHORT).show()
        }
    }

    private fun removeFromFavorite(){
        try {
            database.use {
                delete(Favorite.TABLE_FAVORITE,
                    "(TEAM_MATCH_EVENT_ID = {eventId})",
                    "eventId" to teamMatchEventId
                )
            }
//            swipeRefresh.snackbar("Removed to favorite").show()
            Toast.makeText(this,"Removed from favorite",Toast.LENGTH_SHORT).show()
        } catch (e: SQLiteConstraintException){
//            swipeRefresh.snackbar(e.localizedMessage).show()
            Toast.makeText(this,e.localizedMessage,Toast.LENGTH_SHORT).show()
        }
    }

    private fun setFavorite() {
        if (isFavorite)
            menuItem?.getItem(0)?.icon = ContextCompat.getDrawable(this, ic_added_to_favorites)
        else
            menuItem?.getItem(0)?.icon = ContextCompat.getDrawable(this, ic_add_to_favorites)
    }
    private fun favoriteState(){
        database.use {
            val result = select(Favorite.TABLE_FAVORITE)
                .whereArgs("(TEAM_MATCH_EVENT_ID = {eventId})",
                    "eventId" to teamMatchEventId)
            println("INI EVENT"+teamMatchEventId)
            val favorite = result.parseList(classParser<Favorite>())
            if (!favorite.isEmpty()) isFavorite = true
        }
    }
}
