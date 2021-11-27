package com.eqra.labmid

import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.eqra.labmid.databinding.ActivityHomeBinding
import com.eqra.labmid.db.ItemDBHelper
import com.eqra.labmid.db.ItemContract

class HomeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityHomeBinding
    private lateinit var mDatabase: SQLiteDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val dbHelper = ItemDBHelper(this)
        mDatabase = dbHelper.writableDatabase

        getData()
    }

    private fun getData() {

        val cursor = getItem()

        cursor?.moveToLast()

        val email = cursor!!.getString(cursor.getColumnIndex(ItemContract.UserEntry.COLUMN_EMAIL))
        val password = cursor.getString(cursor.getColumnIndex(ItemContract.UserEntry.COLUMN_PASSWORD))

        binding.tvEmail.text = email
        binding.tvPassword.text = password
    }

    fun getItem(): Cursor? {
        return mDatabase.query(
            ItemContract.UserEntry.TABLE_NAME,
            null,
            null,
            null,
            null,
            null,
            null
        )
    }
}