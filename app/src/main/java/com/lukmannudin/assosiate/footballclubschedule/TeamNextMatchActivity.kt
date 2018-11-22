package com.lukmannudin.assosiate.footballclubschedule

import android.database.sqlite.SQLiteConstraintException
import android.os.Bundle
import android.os.PersistableBundle
import android.support.v4.content.ContextCompat
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import com.google.gson.Gson
import com.lukmannudin.assosiate.footballclub.database.database
import com.lukmannudin.assosiate.footballclubschedule.APIRequest.ApiRepository
import com.lukmannudin.assosiate.footballclubschedule.Contract.TeamDetailContract
import com.lukmannudin.assosiate.footballclubschedule.Model.Team
import com.lukmannudin.assosiate.footballclubschedule.Model.TeamDetail
import com.lukmannudin.assosiate.footballclubschedule.Presenter.SchedulePresenter
import com.lukmannudin.assosiate.footballclubschedule.Presenter.TeamDetailPresenter
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.team_detail_layout.*
import org.jetbrains.anko.db.classParser
import org.jetbrains.anko.db.delete
import org.jetbrains.anko.db.insert
import org.jetbrains.anko.db.select

class TeamNextMatchActivity : AppCompatActivity(), TeamDetailContract{

    private lateinit var presenter: TeamDetailPresenter
    private var teamDetails: MutableList<TeamDetail> = mutableListOf()
    private var menuItem: Menu? = null
    private var isFavorite: Boolean = false
    private lateinit var eventId: String
    private lateinit var homeId: String
    private lateinit var awayId: String
    private lateinit var dateEvent: String
    private lateinit var homeTeamName: String
    private lateinit var awayTeamName: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.team_detail_layout)
        supportActionBar?.title = "Next Match Schedule"
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        eventId = intent.getStringExtra(Team.TEAM_MATCH_EVENT_ID)
        homeId = intent.getStringExtra(Team.TEAM_HOME_ID)
        awayId = intent.getStringExtra(Team.TEAM_AWAY_ID)
        dateEvent = intent.getStringExtra(Team.TEAM_MATCH_EVENT_DATE)
        homeTeamName = intent.getStringExtra(Team.TEAM_HOME_NAME)
        awayTeamName = intent.getStringExtra(Team.TEAM_AWAY_NAME)

        Log.i("eventId:homeId:awayId",eventId+":"+homeId+":"+awayId)

        dateEventSchedule.text = dateEvent
        val request = ApiRepository()
        val gson = Gson()
        presenter = TeamDetailPresenter(this, request, gson)
        presenter.getHomeTeamDetailList(homeId)
        presenter.getAwayTeamDetailList(awayId)
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
                    Favorite.TEAM_MATCH_EVENT_ID to eventId,
                    Favorite.TEAM_MATCH_EVENT_DATE to eventId,
                    Favorite.TEAM_HOME_ID to homeId,
                    Favorite.TEAM_AWAY_ID to awayId,
                    Favorite.TEAM_MATCH_EVENT_DATE to dateEvent,
                    Favorite.TEAM_HOME_NAME to homeTeamName,
                    Favorite.TEAM_AWAY_NAME to awayTeamName,
                    Favorite.TEAM_HOME_SCORE to "",
                    Favorite.TEAM_AWAY_SCORE to ""
                )
            }
//            snackbar("Added to favorite").show()
            Toast.makeText(this,"Added to favorite", Toast.LENGTH_SHORT).show()
        } catch (e: SQLiteConstraintException){
//            snackbar(e.localizedMessage).show()
            Toast.makeText(this,e.localizedMessage, Toast.LENGTH_SHORT).show()
        }
    }

    private fun removeFromFavorite(){
        try {
            database.use {
                delete(Favorite.TABLE_FAVORITE,
                    "(TEAM_MATCH_EVENT_ID = {eventId})",
                    "eventId" to eventId
                )
            }
//            swipeRefresh.snackbar("Removed to favorite").show()
            Toast.makeText(this,"Removed to favorite", Toast.LENGTH_SHORT).show()
        } catch (e: SQLiteConstraintException){
//            swipeRefresh.snackbar(e.localizedMessage).show()
            Toast.makeText(this,e.localizedMessage, Toast.LENGTH_SHORT).show()
        }
    }

    private fun setFavorite() {
        if (isFavorite)
            menuItem?.getItem(0)?.icon = ContextCompat.getDrawable(this, R.drawable.ic_added_to_favorites)
        else
            menuItem?.getItem(0)?.icon = ContextCompat.getDrawable(this, R.drawable.ic_add_to_favorites)
    }
    private fun favoriteState(){
        database.use {
            val result = select(Favorite.TABLE_FAVORITE)
                .whereArgs("(TEAM_MATCH_EVENT_ID = {eventId})",
                    "eventId" to eventId)
            println("INI EVENT"+eventId)
            val favorite = result.parseList(classParser<Favorite>())
            if (!favorite.isEmpty()) isFavorite = true
        }
    }
}