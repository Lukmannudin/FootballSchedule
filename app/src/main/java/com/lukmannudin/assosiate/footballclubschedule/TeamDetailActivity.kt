package com.lukmannudin.assosiate.footballclubschedule

import android.content.Context
import android.database.sqlite.SQLiteConstraintException
import android.os.Bundle
import android.support.design.widget.TabLayout
import android.support.v4.content.ContextCompat
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.app.AppCompatActivity
import android.util.AttributeSet
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import com.lukmannudin.assosiate.footballclub.database.database
import com.lukmannudin.assosiate.footballclubschedule.Adapter.TeamFragmentAdapter
import com.lukmannudin.assosiate.footballclubschedule.Contract.TeamDetailContract
import com.lukmannudin.assosiate.footballclubschedule.Model.Teams
import com.lukmannudin.assosiate.footballclubschedule.Presenter.TeamDetailPresenter
import com.lukmannudin.assosiate.footballclubschedule.R.menu.detail_menu
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.team_main.*
import org.jetbrains.anko.db.classParser
import org.jetbrains.anko.db.delete
import org.jetbrains.anko.db.insert
import org.jetbrains.anko.db.select

class TeamDetailActivity : AppCompatActivity(), TeamDetailContract {


    private lateinit var progressBar: ProgressBar
    private var teamId: String? = null

    private var menuItem: Menu? = null
    private var intentData: Teams? = null
    private var isFavorite: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.team_main)
        val intent = intent
//        id = intent.getStringExtra("id")
        supportActionBar?.title = "TeamUtils Detail"
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        intentData = intent.getParcelableExtra(TeamUtils.TEAM_INTENT)
        teamId = intentData?.teamId

        vpTeam.adapter = TeamFragmentAdapter(supportFragmentManager!!, intentData)
        tlTeam.setupWithViewPager(vpTeam)
        initiateView()
        favoriteState()
        setFavorite()
    }

    override fun onCreateView(name: String?, context: Context?, attrs: AttributeSet?): View? {
        return super.onCreateView(name, context, attrs)
    }

    fun initiateView() {
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
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(detail_menu, menu)
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

    private fun addToFavorite() {
        try {
            database.use {
                insert(
                    Favorite.TABLE_TEAM_FAVORITE,
                    Favorite.TEAM_ID to intentData?.teamId,
                    Favorite.TEAM_NAME to intentData?.teamName,
                    Favorite.TEAM_BADGE to intentData?.teamBadge
                )
            }
//            snackbar("Added to favorite").show()
            Toast.makeText(this, "Added to favorite", Toast.LENGTH_SHORT).show()
        } catch (e: SQLiteConstraintException) {
//            snackbar(e.localizedMessage).show()
            Toast.makeText(this, e.localizedMessage, Toast.LENGTH_SHORT).show()
        }
    }

    private fun removeFromFavorite() {
        try {
            database.use {
                delete(
                    Favorite.TABLE_TEAM_FAVORITE,
                    "(TEAM_ID = {teamId})",
                    "teamId" to teamId.toString()
                )
            }
//            swipeRefresh.snackbar("Removed to favorite").show()
            Toast.makeText(this, "Removed from favorite", Toast.LENGTH_SHORT).show()

        } catch (e: SQLiteConstraintException) {
//            swipeRefresh.snackbar(e.localizedMessage).show()
            Toast.makeText(this, e.localizedMessage, Toast.LENGTH_SHORT).show()
        }
    }

    private fun setFavorite() {
        if (isFavorite)
            menuItem?.getItem(0)?.icon = ContextCompat.getDrawable(this, R.drawable.ic_added_to_favorites)
        else
            menuItem?.getItem(0)?.icon = ContextCompat.getDrawable(this, R.drawable.ic_add_to_favorites)
    }

    private fun favoriteState() {
        database.use {
            val result = select(Favorite.TABLE_TEAM_FAVORITE)
                .whereArgs(
                    "(TEAM_ID = {teamId})",
                    "teamId" to intentData?.teamId + ""
                )
            val favorite = result.parseList(classParser<FavoriteTeam>())
            if (!favorite.isEmpty()) {
                isFavorite = true
                menuItem?.getItem(0)?.icon =
                        ContextCompat.getDrawable(applicationContext, R.drawable.ic_added_to_favorites)
            }
        }

    }
}