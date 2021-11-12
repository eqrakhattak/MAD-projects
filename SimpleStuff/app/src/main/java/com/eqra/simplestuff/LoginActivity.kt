package com.eqra.simplestuff

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.preference.PreferenceManager
import android.util.Patterns
import com.eqra.simplestuff.databinding.ActivityLoginBinding
import kotlin.math.log

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initViews()
    }

    private fun initViews() {

        binding.buttonLogin.setOnClickListener { login() }
    }

    private fun login() {

        binding.apply {

            val email: String = editTextEmail.getText().toString().trim { it <= ' ' }
            val password: String = editTextPassword.getText().toString().trim { it <= ' ' }

            if (email.isEmpty()) {
                editTextEmail.setError("Email is required")
                editTextEmail.requestFocus()
                return
            }

            if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                editTextEmail.setError("Email is not valid")
                editTextEmail.requestFocus()
                return
            }

            if (password.isEmpty()) {
                editTextPassword.setError("Password is required")
                editTextPassword.requestFocus()
                return
            }

            if (password.length < 6) {
                editTextPassword.setError("Password is weak")
                editTextPassword.requestFocus()
                return
            }
            setDefaults(email, password)
        }
    }

    private fun setDefaults(email: String, password: String) {

        val sharedPref = getSharedPreferences(
            getString(R.string.credentials_key), Context.MODE_PRIVATE
        )
        with(sharedPref.edit()) {
            putString("email", email)
            putString("password", password)
            putBoolean("loggedIn", true)
            apply()

            updateUI()
        }
    }

    private fun updateUI() {

        finish()
        val intent = Intent(this@LoginActivity, MainActivity::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)
        startActivity(intent)
    }
}