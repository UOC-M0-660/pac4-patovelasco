package edu.uoc.pac4.ui.profile

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.view.View.GONE
import android.view.View.VISIBLE
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CircleCrop
import edu.uoc.pac4.R
import edu.uoc.pac4.data.network.UnauthorizedException
import edu.uoc.pac4.data.user.User
import edu.uoc.pac4.ui.login.LoginActivity
import kotlinx.android.synthetic.main.activity_profile.*
import kotlinx.coroutines.launch
import org.koin.android.viewmodel.ext.android.viewModel

class ProfileActivity : AppCompatActivity() {

    private val tag = "ProfileActivity"
    private val viewModel: ProfileViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)
        // Get User Profile
        lifecycleScope.launch {
            getUserProfile()
        }
        // Update Description Button Listener
        updateDescriptionButton.setOnClickListener {
            // Hide Keyboard
            val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as? InputMethodManager
            imm?.hideSoftInputFromWindow(it.windowToken, 0)
            // Update User Description
            lifecycleScope.launch {
                updateUserDescription(userDescriptionEditText.text?.toString() ?: "")
            }
        }
        // Logout Button Listener
        logoutButton.setOnClickListener {
            // Logout
            logout()
        }

        viewModel.user.observe(this, { user ->
            // Update the UI with the user data
            setUserInfo(user)
            // Hide Loading
            progressBar.visibility = GONE
        })
    }

    private fun getUserProfile() {
        progressBar.visibility = VISIBLE
        // Retrieve the Twitch User Profile using the API
        try {
            viewModel.getUser()
        } catch (t: UnauthorizedException) {
            showError("Error o get user profile")
            onUnauthorized()
        }
    }


    private fun updateUserDescription(description: String) {
        progressBar.visibility = VISIBLE
        // Update the Twitch User Description using the API
        try {
            viewModel.updateUser(description)
        } catch (t: UnauthorizedException) {
            onUnauthorized()
        }
    }

    private fun setUserInfo(user: User) {
        // Set Texts
        userNameTextView.text = user.userName
        userDescriptionEditText.setText(user.description ?: "")
        // Avatar Image
        user.profileImageUrl?.let {
            Glide.with(this)
                .load(user.getSizedImage(it, 128, 128))
                .centerCrop()
                .transform(CircleCrop())
                .into(imageView)
        }
        // Views
        viewsText.text = getString(R.string.views_text, user.viewCount)
    }

    private fun logout() {
        // Clear local session data
        viewModel.clearDataForLogout()
        // Close this and all parent activities
        finishAffinity()
        // Open Login
        startActivity(Intent(this, LoginActivity::class.java))
    }

    private fun onUnauthorized() {
        Log.e(tag, "Error al obtener la autorización")
        // Clear local access token
        viewModel.clearDataForUnauthorized()
        // User was logged out, close screen and all parent screens and open login
        finishAffinity()
        startActivity(Intent(this, LoginActivity::class.java))
    }

    private fun showError(text: String) {
        Toast.makeText(this, text, Toast.LENGTH_LONG).show()
    }

    // Override Action Bar Home button to just finish the Activity
    // not to re-launch the parent Activity (StreamsActivity)
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return if (item.itemId == android.R.id.home) {
            finish()
            false
        } else {
            super.onOptionsItemSelected(item)
        }
    }
}