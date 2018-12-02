package com.lukmannudin.assosiate.footballclubschedule

import android.os.Bundle
import android.support.design.widget.NavigationView
import android.support.design.widget.TabLayout
import android.support.v4.app.FragmentManager
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.app.AppCompatActivity
import android.text.TextUtils.replace
import android.util.Log
import android.view.Menu
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import com.lukmannudin.assosiate.footballclubschedule.Adapter.TeamFragmentAdapter
import com.lukmannudin.assosiate.footballclubschedule.Contract.TeamDetailContract
import com.lukmannudin.assosiate.footballclubschedule.Model.Teams
import com.lukmannudin.assosiate.footballclubschedule.Presenter.TeamDetailPresenter
import com.lukmannudin.assosiate.footballclubschedule.R.menu.detail_menu
import com.lukmannudin.assosiate.footballclubschedule.TeamsView.OverviewTeamFragment
import com.lukmannudin.assosiate.footballclubschedule.TeamsView.TeamsFragment
import com.lukmannudin.assosiate.footballclubschedule.navigation_view.NavigationMain
import com.lukmannudin.assosiate.footballclubschedule.navigation_view.NavigationTeam
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.team_main.*
import kotlinx.android.synthetic.main.team_main.view.*
import kotlinx.android.synthetic.main.team_overview.*
import org.jetbrains.anko.internals.AnkoInternals.initiateView

class TeamDetailActivity : AppCompatActivity(), TeamDetailContract {


    private lateinit var progressBar: ProgressBar
    private lateinit var swipeRefresh: SwipeRefreshLayout

    private lateinit var teamBadge: ImageView
    private lateinit var teamName: TextView
    private lateinit var teamFormedYear: TextView
    private lateinit var teamStadium: TextView
    private lateinit var teamDescription: TextView
    private lateinit var tabLayout: TabLayout


    private lateinit var presenterMatch: TeamDetailPresenter
    private lateinit var teams: Teams
    private lateinit var id: String

    private var menuItem: Menu? = null
    private var isFavorite: Boolean = false
    private var intentData: Teams? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.team_main)
        val intent = intent
//        id = intent.getStringExtra("id")
        supportActionBar?.title = "TeamUtils Detail"
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        intentData = intent.getParcelableExtra(TeamUtils.TEAM_INTENT)

        vpTeam.adapter = TeamFragmentAdapter(supportFragmentManager!!, intentData)
        tlTeam.setupWithViewPager(vpTeam)

        initiateView()
    }

    fun initiateView(){
        Picasso.get().load(intentData?.teamBadge).into(imgTeamBadge)
        tvStrTeam.text = intentData?.teamName
        tvFormedYear.text = intentData?.teamFormedYear
        tvStadium.text = intentData?.teamStadium
    }

    override fun showLoading() {
        progressBar.visible()
    }

    override fun hideLoading() {
        progressBar.invisible()
    }

    override fun showDetailTeam(data: List<Teams>) {
//        teams = Teams(data[0].teamId,
//            data[0].teamName,
//            data[0].teamBadge)
//        swipeRefresh.isRefreshing = false
//        Picasso.get().load(data[0].teamBadge).into(teamBadge)
//        teamName.text = data[0].teamName
//        teamDescription.text = data[0].teamDescription
//        teamFormedYear.text = data[0].teamFormedYear
//        teamStadium.text = data[0].teamStadium
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(detail_menu, menu)
        menuItem = menu
//        setFavorite()
        return true
    }

//    override fun onOptionsItemSelected(item: MenuItem): Boolean {
//        return when (item.itemId) {
//            android.R.id.home -> {
//                finish()
//                true
//            }
//            add_to_favorite -> {
//                if (isFavorite) removeFromFavorite() else addToFavorite()
//
//                isFavorite = !isFavorite
//                setFavorite()
//
//                true
//            }
//
//            else -> super.onOptionsItemSelected(item)
//        }
//    }

//    private fun addToFavorite(){
//        try {
//            database.use {
//                insert(Favorite.TABLE_FAVORITE,
//                    Favorite.TEAM_ID to teams.teamId,
//                    Favorite.TEAM_NAME to teams.teamName,
//                    Favorite.TEAM_BADGE to teams.teamBadge)
//            }
//            swipeRefresh.snackbar("Added to favorite").show()
//        } catch (e: SQLiteConstraintException){
//            swipeRefresh.snackbar(e.localizedMessage).show()
//        }
//    }
//
//    private fun removeFromFavorite(){
//        try {
//            database.use {
//                delete(Favorite.TABLE_FAVORITE, "(TEAM_ID = {id})",
//                    "id" to id)
//            }
//            swipeRefresh.snackbar("Removed to favorite").show()
//        } catch (e: SQLiteConstraintException){
//            swipeRefresh.snackbar(e.localizedMessage).show()
//        }
//    }

//    private fun setFavorite() {
//        if (isFavorite)
//            menuItem?.getItem(0)?.icon = ContextCompat.getDrawable(this, ic_added_to_favorites)
//        else
//            menuItem?.getItem(0)?.icon = ContextCompat.getDrawable(this, ic_add_to_favorites)
//    }

}