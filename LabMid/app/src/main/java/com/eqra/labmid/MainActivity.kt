package com.eqra.labmid

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.eqra.labmid.databinding.ActivityMainBinding
import android.content.ContentValues
import android.content.Context
import android.content.Intent
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.view.View
import android.widget.Toast
import com.eqra.labmid.db.ItemContract
import com.eqra.labmid.db.ItemDBHelper
import android.view.Menu
import android.view.MenuItem

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var mDatabase: SQLiteDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val dbHelper = ItemDBHelper(this)
        mDatabase = dbHelper.writableDatabase

        if (getDefaults()) {
            binding.tvText.visibility = View.VISIBLE
        }

        initListeners()
    }

    private fun initListeners() {

        binding.btnSignUp.setOnClickListener {
            createAccount()
        }
    }

    private fun createAccount() {

        if (isEmailExists(binding.etEmail.text.toString())) {
            Toast.makeText(this, "Email already exists", Toast.LENGTH_LONG).show()
            return
        }

        val cv = ContentValues()
        cv.put(ItemContract.UserEntry.COLUMN_EMAIL, binding.etEmail.text.toString())
        cv.put(ItemContract.UserEntry.COLUMN_PASSWORD, binding.etPassword.text.toString())

        mDatabase.insert(ItemContract.UserEntry.TABLE_NAME, null, cv)

        setDefaults()
    }

    private fun isEmailExists(email: String): Boolean {

        val cursor: Cursor? = mDatabase.query(
            ItemContract.UserEntry.TABLE_NAME,
            arrayOf(
                ItemContract.UserEntry.COLUMN_EMAIL,
                ItemContract.UserEntry.COLUMN_PASSWORD
            ),

            ItemContract.UserEntry.COLUMN_EMAIL + "=?",
            arrayOf(email),
            null,
            null,
            null
        )

        if (cursor != null && cursor.moveToFirst() && cursor.count > 0) {
            Toast.makeText(this, "Email already registered", Toast.LENGTH_SHORT).show()
            cursor.close()
            return true
        }

        cursor?.close()
        return false
    }

    private fun setDefaults() {

        val sharedPref = getSharedPreferences(
            getString(R.string.credentials_key), Context.MODE_PRIVATE
        )
        with(sharedPref.edit()) {
            putBoolean("loggedIn", true)
            apply()

            binding.tvText.visibility = View.VISIBLE
        }
    }

    private fun getDefaults(): Boolean {

        val sharedPref = getSharedPreferences(
            getString(R.string.credentials_key), Context.MODE_PRIVATE
        )

        return sharedPref.getBoolean("loggedIn", false)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {

        val menuInflater = menuInflater
        menuInflater.inflate(R.menu.menu, menu)

        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {

            R.id.nav_home -> {
                if (getDefaults()) {
                    val intent = Intent(this, HomeActivity::class.java)
                    startActivity(intent)
                } else {
                    Toast.makeText(this, "Sign up first", Toast.LENGTH_SHORT).show()
                }
                true
            }
            R.id.nav_settings -> {
                val intent = Intent(this, WebViewActivity::class.java)
                startActivity(intent)
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}