package com.lukmannudin.assosiate.footballclubschedule.Adapter

import android.support.v7.widget.RecyclerView
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import com.lukmannudin.assosiate.footballclubschedule.Model.Schedule
import com.lukmannudin.assosiate.footballclubschedule.R
import com.lukmannudin.assosiate.footballclubschedule.R.id.*
import org.jetbrains.anko.*

class ScheduleAdapter(private val schedules: List<Schedule>, private val listener: (Schedule) -> Unit)
    : RecyclerView.Adapter<TeamViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TeamViewHolder {
        return TeamViewHolder(
            ScheduleUI().createView(
                AnkoContext.create(parent.context, parent)
            )
        )
    }

    override fun getItemCount(): Int = schedules.size

    override fun onBindViewHolder(holder: TeamViewHolder, position: Int) {
        holder.bindItem(schedules[position], listener)
    }

}

class ScheduleUI : AnkoComponent<ViewGroup> {
    override fun createView(ui: AnkoContext<ViewGroup>): View {
        val fontSize: Float = 16f
        return with(ui) {
            linearLayout {
                lparams(width = matchParent, height = wrapContent)
                padding = dip(16)
                orientation = LinearLayout.VERTICAL


                textView {
                    id = R.id.dateEvent
                    textSize = fontSize
                    gravity = Gravity.CENTER
                    text = "2016-10-12"
                }

                relativeLayout{
                    lparams(width = matchParent, height = wrapContent)

                    textView {
                        id = R.id.tHome
                        textSize = fontSize
                        text = "Jerman"
                        gravity = Gravity.LEFT
                        width = dip(100)
                    }

                    textView {
                        id = R.id.tHomeScoring
                        textSize = fontSize
                        text = "0"
                        gravity = Gravity.CENTER
                        width = dip(10)
                    }.lparams{
                        rightOf(tHome)
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
                        rightOf(tHomeScoring)
                    }

                    textView {
                        id = R.id.tAwayScoring
                        textSize = fontSize
                        text = "1"
                        gravity = Gravity.CENTER
                        width = dip(10)
                    }.lparams{
                        rightOf(separatorTeam)
                    }

                    textView {
                        id = R.id.tAway
                        textSize = fontSize
                        text = "Spanyol"
                        width = dip(100)
                        gravity  = Gravity.RIGHT
                    }.lparams {
                        rightOf(tAwayScoring)
                    }
                }.lparams{
                    gravity = Gravity.CENTER
                }
            }
        }
    }

}

class TeamViewHolder(view: View) : RecyclerView.ViewHolder(view){

    private val teamEvent: TextView = view.find(dateEvent)
    private val teamHomeScoring : TextView = view.find(tHomeScoring)
    private val teamAwayScoring : TextView = view.find(tAwayScoring)
    private val teamHome: TextView = view.find(tHome)
    private val teamAway: TextView = view.find(tAway)

    fun bindItem(teams: Schedule, listener:(Schedule)->Unit) {
        teamEvent.text = teams.dateEvent
        teamHome.text = teams.strHomeTeam
        teamAway.text = teams.strAwayTeam
        teamHomeScoring.text = teams.intHomeScore
        teamAwayScoring.text = teams.intAwayScore
        itemView.setOnClickListener { listener(teams)
        }
    }
}