package com.lukmannudin.assosiate.footballclubschedule

import android.os.Bundle
import android.support.design.widget.TabLayout
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import com.lukmannudin.assosiate.footballclubschedule.Adapter.TeamFragmentAdapter
import com.lukmannudin.assosiate.footballclubschedule.Contract.TeamDetailContract
import com.lukmannudin.assosiate.footballclubschedule.Model.Teams
import com.lukmannudin.assosiate.footballclubschedule.Presenter.TeamDetailPresenter
import com.lukmannudin.assosiate.footballclubschedule.R.menu.detail_menu
import kotlinx.android.synthetic.main.team_main.*

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


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.team_main)
        val intent = intent
        id = intent.getStringExtra("id")
        supportActionBar?.title = "Team Detail"
        supportActionBar?.setDisplayHomeAsUpEnabled(true)


        vpTeam.adapter = TeamFragmentAdapter(supportFragmentManager!!)
        tlTeam.setupWithViewPager(vpTeam)


//        linearLayout {
//            lparams(width = matchParent, height = wrapContent)
//            orientation = LinearLayout.VERTICAL
//            backgroundColor = Color.WHITE
//
//            swipeRefresh = swipeRefreshLayout {
//                setColorSchemeResources(colorAccent,
//                    android.R.color.holo_green_light,
//                    android.R.color.holo_orange_light,
//                    android.R.color.holo_red_light)
//
////                scrollView {
////                    isVerticalScrollBarEnabled = false
////                    relativeLayout {
////                        lparams(width = matchParent, height = wrapContent)
////
////                        linearLayout{
////                            lparams(width = matchParent, height = wrapContent)
////                            padding = dip(10)
////                            orientation = LinearLayout.VERTICAL
////                            gravity = Gravity.CENTER_HORIZONTAL
////
////                            teamBadge =  imageView {}.lparams(height = dip(75))
////
////                            teamName = textView{
////                                this.gravity = Gravity.CENTER
////                                textSize = 20f
////                                textColor = ContextCompat.getColor(context, colorAccent)
////                            }.lparams{
////                                topMargin = dip(5)
////                            }
////
////                            teamFormedYear = textView{
////                                this.gravity = Gravity.CENTER
////                            }
////
////                            teamStadium = textView{
////                                this.gravity = Gravity.CENTER
////                                textColor = ContextCompat.getColor(context, colorPrimary)
////                            }
////
////                            tabLayout = tabLayout {
////                                R.id.teams_tab
////                                setSelectedTabIndicatorColor(Color.WHITE)
////                            }.lparams(width = matchParent) {
////                                gravity = Gravity.BOTTOM
////                            }
////
////                            teamDescription = textView().lparams{
////                                topMargin = dip(20)
////                            }
////                        }
////                        progressBar = progressBar {
////                        }.lparams {
////                            centerHorizontally()
////                        }
////                    }
////                }
//            }
//        }

//        favoriteState()
//        val request = ApiRepository()
//        val gson = Gson()
//        presenterMatch = TeamDetailPresenter(this, request, gson)
//        presenterMatch.getTeamDetail(id)
//
//        vpSchedule.setupWithViewPager(vpTeam)
//        vpSchedule.adapter = TeamFragmentAdapter(fragmentManager!!)
//        tabLayout.adapter = FragmentAdapter(childFragmentManager!!)
//        tlSchedule.setupWithViewPager(vpSchedule)
//        swipeRefresh.onRefresh {
//            presenterMatch.getTeamDetail(id)
//        }
    }

//    private fun favoriteState(){
//        database.use {
//            val result = select(Favorite.TABLE_FAVORITE)
//                .whereArgs("(TEAM_ID = {id})",
//                    "id" to id)
//            val favorite = result.parseList(classParser<Favorite>())
//            if (!favorite.isEmpty()) isFavorite = true
//        }
//    }

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