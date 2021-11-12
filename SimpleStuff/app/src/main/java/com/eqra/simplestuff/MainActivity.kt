package com.eqra.simplestuff

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Patterns
import android.widget.Toast
import com.eqra.simplestuff.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initViews()
    }

    private fun initViews() {

        if (!getDefaults()) {
            updateUI()
        }

        getData()

        binding.buttonSave.setOnClickListener { saveName() }
    }

    private fun getDefaults(): Boolean {

        val sharedPref = getSharedPreferences(
            getString(R.string.credentials_key), Context.MODE_PRIVATE
        )

        return sharedPref.getBoolean("loggedIn", false)
    }

    private fun getData() {

        val sharedPref = getSharedPreferences(
            getString(R.string.credentials_key), Context.MODE_PRIVATE
        )

        val email = sharedPref.getString("email", "")
        val name = sharedPref.getString("name", "")

        binding.editTextEmail.setText(email)
        binding.editTextName.setText(name)
    }

    private fun saveName() {

        binding.apply {

            val name: String = editTextName.text.toString().trim { it <= ' ' }

            if (name.isEmpty()) {
                editTextName.error = "Name is required"
                editTextName.requestFocus()
                return
            }

            val sharedPref = getSharedPreferences(
                getString(R.string.credentials_key), Context.MODE_PRIVATE
            )
            with(sharedPref.edit()) {
                putString("name", name)
                apply()

                Toast.makeText(this@MainActivity, "Nick name saved", Toast.LENGTH_LONG).show()
            }
        }
    }

    private fun updateUI() {

        finish()
        val intent = Intent(this@MainActivity, LoginActivity::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)
        startActivity(intent)
    }
}