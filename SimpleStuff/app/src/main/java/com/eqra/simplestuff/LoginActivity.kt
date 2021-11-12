package com.eqra.simplestuff

import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.preference.PreferenceManager
import android.util.Patterns
import com.eqra.simplestuff.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initViews()
    }

    private fun initViews() {

        binding.buttonLogin.setOnClickListener { }
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
        }
    }

//    fun setDefaults(name: String, email: String) {
//
//        val preferences = SharedPreferences.getDefaultSharedPreferences(this)
//        val editor = preferences.edit()
//        editor.putString("name", name)
//        editor.putString("email", email)
//        editor.putBoolean("loggedIn", true)
//        editor.apply()
//        updateUI()
//    }
}