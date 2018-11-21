package com.lukmannudin.assosiate.footballclubschedule.Adapter

import android.graphics.Color
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import com.lukmannudin.assosiate.footballclubschedule.Favorite
import com.lukmannudin.assosiate.footballclubschedule.Model.Schedule
import com.lukmannudin.assosiate.footballclubschedule.R
import org.jetbrains.anko.*


class FavoritesAdapter(private val favorites: List<Favorite>, private val listener: (Favorite) -> Unit)
    : RecyclerView.Adapter<FavoritesViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoritesViewHolder {
        return FavoritesViewHolder(
            ScheduleUI().createView(
                AnkoContext.create(parent.context, parent)
            )
        )
    }

    override fun getItemCount(): Int = favorites.size

    override fun onBindViewHolder(holder: FavoritesViewHolder, position: Int) {
        holder.bindItem(favorites[position], listener)
    }

}

class FavoritesUI : AnkoComponent<ViewGroup> {
    override fun createView(ui: AnkoContext<ViewGroup>): View {
        val fontSize: Float = 16f
        return with(ui) {
            linearLayout {
                lparams(width = matchParent, height = wrapContent)
                padding = dip(16)
                orientation = LinearLayout.VERTICAL


                textView {
                    id = R.id.dateEvent
                    textSize = 20f
                    gravity = Gravity.CENTER
                    textColor = Color.LTGRAY
                }

                relativeLayout{
                    lparams(width = matchParent, height = wrapContent)

                    textView {
                        id = R.id.tHome
                        textSize = fontSize
                        text = "Jerman"
                        gravity = Gravity.LEFT
                        width = dip(150)
                        rightPadding = dip(20)
                    }

                    textView {
                        id = R.id.tHomeScoring
                        textSize = fontSize
                        text = "0"
                        gravity = Gravity.CENTER
                        width = dip(10)
                    }.lparams{
                        rightOf(R.id.tHome)
                    }

                    textView {
                        id = R.id.separatorTeam
                        textSize = fontSize
                        text = "vs"
                        gravity = Gravity.CENTER
                        width = dip(50)
                        leftPadding = dip(1)
                        rightPadding = dip(1)
                    }.lparams{
                        rightOf(R.id.tHomeScoring)
                    }

                    textView {
                        id = R.id.tAwayScoring
                        textSize = fontSize
                        text = "1"
                        gravity = Gravity.CENTER
                        width = dip(10)
                    }.lparams{
                        rightOf(R.id.separatorTeam)
                    }

                    textView {
                        id = R.id.tAway
                        textSize = fontSize
                        text = "Spanyol"
                        width = dip(150)
                        gravity  = Gravity.RIGHT
                        leftPadding = dip(20)
                    }.lparams {
                        rightOf(R.id.tAwayScoring)
                    }
                }.lparams{
                    gravity = Gravity.CENTER
                }
            }
        }
    }

}

class FavoritesViewHolder(view: View) : RecyclerView.ViewHolder(view){

    private val teamEvent: TextView = view.find(R.id.dateEvent)
    private val teamHomeScoring : TextView = view.find(R.id.tHomeScoring)
    private val teamAwayScoring : TextView = view.find(R.id.tAwayScoring)
    private val teamHome: TextView = view.find(R.id.tHome)
    private val teamAway: TextView = view.find(R.id.tAway)

    fun bindItem(teams: Favorite, listener:(Favorite)->Unit) {
        teamEvent.text = teams.teamMatchEventDate
        teamHome.text = teams.teamHomeName
        teamAway.text = teams.teamAwayName
        teamHomeScoring.text = teams.teamHomeScore
        teamAwayScoring.text = teams.teamAwayScore
        itemView.setOnClickListener { listener(teams)
        }
    }
}