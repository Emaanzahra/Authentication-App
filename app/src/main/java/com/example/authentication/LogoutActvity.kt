package com.example.authentication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.ActionBarDrawerToggle
import com.example.authentication.databinding.ActivityLogoutActvityBinding
import com.google.android.material.navigation.NavigationView
import com.google.firebase.auth.FirebaseAuth

class LogoutActvity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {  // Implement the interface

    private lateinit var binding: ActivityLogoutActvityBinding
    private lateinit var firebaseAuth: FirebaseAuth
    private lateinit var toggle: ActionBarDrawerToggle

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLogoutActvityBinding.inflate(layoutInflater)
        setContentView(binding.root)

        firebaseAuth = FirebaseAuth.getInstance()

        // Set up ActionBarDrawerToggle for the Navigation Drawer
        toggle = ActionBarDrawerToggle(this, binding.drawerLayout, binding.toolbar, R.string.open, R.string.close)
        binding.drawerLayout.addDrawerListener(toggle)
        toggle.syncState()

        // Set the NavigationView's item selection listener
        binding.navView.setNavigationItemSelectedListener(this)  // Use 'this' as the listener
    }

    // Implement the onNavigationItemSelected method from the interface
    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.logout -> {
                // Log the user out
                firebaseAuth.signOut()

                // Navigate back to MainActivity
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
                finish() // Close the current activity
                return true
            }
        }
        return false
    }
}
