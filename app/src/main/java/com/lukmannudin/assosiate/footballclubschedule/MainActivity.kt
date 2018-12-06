package com.lukmannudin.assosiate.footballclubschedule

import android.app.Activity
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import android.text.TextUtils
import android.speech.RecognizerIntent
import android.content.Intent
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import br.com.mauker.materialsearchview.MaterialSearchView.OnVoiceClickedListener
import android.widget.AdapterView
import android.widget.Button
import br.com.mauker.materialsearchview.MaterialSearchView


class MainActivity : AppCompatActivity() {
    private val LOG_TAG = MainActivity::class.java.simpleName

    private var searchView: MaterialSearchView? = null

    private var bt_clearHistory: Button? = null
    private var bt_clearSuggestions: Button? = null
    private var bt_clearAll: Button? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportActionBar?.title = "TeamUtils Detail"

        searchView = findViewById<MaterialSearchView>(R.id.search_view)

        bt_clearHistory = findViewById(R.id.bt_clearHistory)
        bt_clearSuggestions = findViewById(R.id.bt_clearSuggestions)
        bt_clearAll = findViewById(R.id.bt_clearAll)

        searchView!!.setOnQueryTextListener(object : MaterialSearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String): Boolean {
                return false
            }
        })

        searchView!!.setSearchViewListener(object : MaterialSearchView.SearchViewListener {
            override fun onSearchViewOpened() {
                // Do something once the view is open.
            }

            override fun onSearchViewClosed() {
                // Do something once the view is closed.
            }
        })

        searchView!!.setOnItemClickListener(object : AdapterView.OnItemClickListener {
            override fun onItemClick(parent: AdapterView<*>, view: View, position: Int, id: Long) {
                // Do something when the suggestion list is clicked.
                val suggestion = searchView!!.getSuggestionAtPosition(position)

                searchView!!.setQuery(suggestion, false)
            }
        })

        bt_clearHistory!!.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View) {
                clearHistory()
            }
        })

        bt_clearSuggestions!!.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View) {
                clearSuggestions()
            }
        })

        bt_clearAll!!.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View) {
                clearAll()
            }
        })

        //        searchView.setTintAlpha(200);
        searchView!!.adjustTintAlpha(0.8f)

        val context = this
        searchView!!.setOnItemLongClickListener(object : AdapterView.OnItemLongClickListener {
            override fun onItemLongClick(adapterView: AdapterView<*>, view: View, i: Int, l: Long): Boolean {
                Toast.makeText(context, "Long clicked position: $i", Toast.LENGTH_SHORT).show()
                return true
            }
        })

        searchView!!.setOnVoiceClickedListener(object : MaterialSearchView.OnVoiceClickedListener {
            override fun onVoiceClicked() {
                Toast.makeText(context, "Voice clicked!", Toast.LENGTH_SHORT).show()
            }
        })
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_team, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle toolbar item clicks here. It'll
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        val id = item.getItemId()

        when (id) {
            R.id.action_search -> {
                // Open the search view on the menu item click.

                searchView!!.openSearch()
                return true
            }
        }

        return super.onOptionsItemSelected(item)
    }

    override fun onBackPressed() {
        if (searchView!!.isOpen()) {
            // Close the search on the back button press.
            searchView!!.closeSearch()
        } else {
            super.onBackPressed()
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode == MaterialSearchView.REQUEST_VOICE && resultCode == Activity.RESULT_OK) {
            val matches = data!!.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS)
            if (matches != null && matches.size > 0) {
                val searchWrd = matches[0]
                if (!TextUtils.isEmpty(searchWrd)) {
                    searchView!!.setQuery(searchWrd, false)
                }
            }

            return
        }
        super.onActivityResult(requestCode, resultCode, data)
    }

    override fun onPause() {
        super.onPause()
        searchView!!.clearSuggestions()
    }

    override fun onResume() {
        super.onResume()
        searchView!!.activityResumed()
        val arr = resources.getStringArray(R.array.suggestions)

        searchView!!.addSuggestions(arr)
    }

    private fun clearHistory() {
        searchView!!.clearHistory()
    }

    private fun clearSuggestions() {
        searchView!!.clearSuggestions()
    }

    private fun clearAll() {
        searchView!!.clearAll()
    }
}


