package com.lukmannudin.assosiate.footballclubschedule

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import com.lukmannudin.assosiate.footballclubschedule.Adapter.PlayerAdapter
import com.lukmannudin.assosiate.footballclubschedule.Model.Players
import kotlinx.android.synthetic.main.activity_team_player.*

class TeamPlayerActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_team_player)
        val list = ArrayList<Players>()
        val listUsers = arrayOf(
            "Google",
            "Apple",
            "Microsoft",
            "Asus",
            "Zenpone",
            "Acer"
        )

        mRecyclerView.setHasFixedSize(true)
        mRecyclerView.layoutManager = LinearLayoutManager(this)
        for (i in 0 until listUsers.size){

            list.add(Players(listUsers.get(i)))

            if(listUsers.size - 1 == i){
                // init adapter yang telah dibuat tadi
                val adapter = PlayerAdapter(list)
                adapter.notifyDataSetChanged()

                //tampilkan data dalam recycler view
                mRecyclerView.adapter = adapter
            }
        }
    }
}
