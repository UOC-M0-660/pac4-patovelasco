package edu.uoc.pac4.ui

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import edu.uoc.pac4.R
import edu.uoc.pac4.ui.login.LoginActivity
import edu.uoc.pac4.ui.streams.StreamsActivity
import org.koin.android.viewmodel.ext.android.viewModel

class LaunchActivity : AppCompatActivity() {

    private val viewModel: LaunchViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_launch)
        viewModel.getUserAvailability()
        checkUserSession()
    }

    private fun checkUserSession() {
        viewModel.isUserAvailable.observe(this, { user ->
            if (user) {
                // User is available, start Streams Activity
                startActivity(Intent(this@LaunchActivity, StreamsActivity::class.java))
            } else {
                // User not available, start Login Activity
                startActivity(Intent(this@LaunchActivity, LoginActivity::class.java))
            }
            finish()
        })
    }
}