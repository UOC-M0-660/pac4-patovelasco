package edu.uoc.pac4.ui.streams

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import edu.uoc.pac4.R
import edu.uoc.pac4.data.network.UnauthorizedException
import edu.uoc.pac4.ui.login.LoginActivity
import edu.uoc.pac4.ui.profile.ProfileActivity
import kotlinx.android.synthetic.main.activity_streams.*
import org.koin.android.viewmodel.ext.android.viewModel

class StreamsActivity : AppCompatActivity() {

    private val tag = "StreamsActivity"

    private val adapter = StreamsAdapter()
    private val layoutManager = LinearLayoutManager(this)

    private val viewModel: StreamsViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_streams)
        // Init RecyclerView
        initRecyclerView()
        // Swipe to Refresh Listener
        swipeRefreshLayout.setOnRefreshListener {
            getStreams()
        }
        // Get Streams
        getStreams()
    }

    private fun initRecyclerView() {
        // Set Layout Manager
        recyclerView.layoutManager = layoutManager
        // Set Adapter
        recyclerView.adapter = adapter
        // Set Pagination Listener
        recyclerView.addOnScrollListener(object : PaginationScrollListener(layoutManager) {
            override fun loadMoreItems() {
                getStreams(nextCursor)
            }

            override fun isLastPage(): Boolean {
                return nextCursor == null
            }

            override fun isLoading(): Boolean {
                return swipeRefreshLayout.isRefreshing
            }
        })
    }

    private var nextCursor: String? = null
    private fun getStreams(cursor: String? = null) {
        Log.d(tag, "Requesting streams with cursor $cursor")

        // Show Loading
        swipeRefreshLayout.isRefreshing = true

        // Get Twitch Streams
        try {
            viewModel.getStreams(cursor)
            viewModel.streams.observe(this, { result ->
                // Success :)
                Log.d(tag, "Streams => $result")

                val streams = result.second
                // Update UI with Streams
                if (cursor != null) {
                    // We are adding more items to the list
                    adapter.submitList(adapter.currentList.plus(streams))
                } else {
                    // It's the first n items, no pagination yet
                    adapter.submitList(streams)
                }

                // Save cursor for next request
                nextCursor = result.first

                // Hide Loading
                swipeRefreshLayout.isRefreshing = false
            })
        } catch (t: UnauthorizedException) {
            Log.w(tag, "Unauthorized Error getting streams", t)
            // Clear local access token
            viewModel.clearAccessToken()
            // User was logged out, close screen and open login
            finish()
            startActivity(Intent(this@StreamsActivity, LoginActivity::class.java))
        }
    }

    // region Menu
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        // Inflate Menu
        menuInflater.inflate(R.menu.menu_streams, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle item selection
        return when (item.itemId) {
            R.id.menu_user -> {
                startActivity(Intent(this, ProfileActivity::class.java))
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
    // endregion
}