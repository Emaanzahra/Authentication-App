package com.example.authentication

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.authentication.databinding.ActivityMainBinding
import com.google.firebase.auth.FirebaseAuth

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var firebaseAuth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        firebaseAuth = FirebaseAuth.getInstance()

        binding.signupbutton.setOnClickListener {
            val email = binding.emailLayout.text.toString().trim()
            val password = binding.passwordLayout.text.toString().trim()

            if (email.isNotEmpty() && password.isNotEmpty()) {
                // Register user using Firebase Authentication
                firebaseAuth.createUserWithEmailAndPassword(email, password)
                    .addOnCompleteListener { task ->
                        if (task.isSuccessful) {
                            // Successfully registered
                            Toast.makeText(this, "Registration successful! You can now log in.", Toast.LENGTH_LONG).show()
                        } else {
                            // Failed registration, show error
                            val errorMessage = task.exception?.message ?: "Registration failed"
                            Toast.makeText(this, errorMessage, Toast.LENGTH_LONG).show()
                        }
                    }
            } else {
                Toast.makeText(this, "Please enter both email and password", Toast.LENGTH_SHORT).show()
            }
        }






        binding.siginbutton.setOnClickListener {
            val email = binding.emailLayout.text.toString().trim()
            val password = binding.passwordLayout.text.toString().trim()

            if (email.isNotEmpty() && password.isNotEmpty()) {
                // Sign in using Firebase Authentication
                firebaseAuth.signInWithEmailAndPassword(email, password)
                    .addOnCompleteListener { task ->
                        if (task.isSuccessful) {
                            // Successfully logged in, navigate to LogoutActivity
                            val intent = Intent(this, LogoutActvity::class.java)
                            startActivity(intent)
                            finish()
                        } else {
                            // Failed login, show error
                            Toast.makeText(this, "Incorrect credentials. Please try again.", Toast.LENGTH_SHORT).show()
                        }
                    }
            } else {
                Toast.makeText(this, "Please enter both email and password", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
