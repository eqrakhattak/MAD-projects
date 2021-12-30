package com.eqra.myweatherapp

import android.content.ContentValues
import android.content.Context
import android.content.Intent
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Patterns
import android.view.View
import android.widget.Toast
import com.eqra.labmid.db.ItemContract
import com.eqra.labmid.db.ItemDBHelper
import com.eqra.myweatherapp.databinding.ActivitySignUpBinding

class SignUpActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySignUpBinding
    private lateinit var mDatabase: SQLiteDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignUpBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initViews()
        initListeners()
    }

    private fun initViews() {

        val dbHelper = ItemDBHelper(this)
        mDatabase = dbHelper.writableDatabase
    }

    private fun initListeners() {

        binding.buttonSignUp.setOnClickListener {
            createAccount()
        }
    }

    private fun createAccount() {

        binding.apply {

            val email: String = editTextEmail.text.toString().trim { it <= ' ' }
            val password: String = editTextPassword.text.toString().trim { it <= ' ' }

            if (email.isEmpty()) {
                editTextEmail.error = "Email is required"
                editTextEmail.requestFocus()
                return
            }

            if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                editTextEmail.error = "Email is not valid"
                editTextEmail.requestFocus()
                return
            }

            if (password.isEmpty()) {
                editTextPassword.error = "Password is required"
                editTextPassword.requestFocus()
                return
            }

            if (password.length < 6) {
                editTextPassword.error = "Password is weak"
                editTextPassword.requestFocus()
                return
            }

            if (isEmailExists(binding.editTextEmail.text.toString())) {
                return
            }

            val cv = ContentValues()
            cv.put(ItemContract.UserEntry.COLUMN_EMAIL, email)
            cv.put(ItemContract.UserEntry.COLUMN_PASSWORD, password)

            mDatabase.insert(ItemContract.UserEntry.TABLE_NAME, null, cv)

            setDefaults()
        }
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
        }

        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
    }
}