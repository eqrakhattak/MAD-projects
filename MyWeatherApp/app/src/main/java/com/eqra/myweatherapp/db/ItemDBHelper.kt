package com.eqra.labmid.db

import android.content.Context
import android.database.sqlite.SQLiteOpenHelper
import com.eqra.labmid.db.ItemDBHelper
import android.database.sqlite.SQLiteDatabase
import com.eqra.labmid.db.ItemContract

class ItemDBHelper(private val fContext: Context) : SQLiteOpenHelper(
    fContext, DATABASE_NAME, null, DATABASE_VERSION
) {
    override fun onCreate(db: SQLiteDatabase) {
        val SQL_CREATE_USER_TABLE = "CREATE TABLE " +
                ItemContract.UserEntry.TABLE_NAME + " (" +
                ItemContract.UserEntry.COLUMN_EMAIL + " TEXT PRIMARY KEY, " +
                ItemContract.UserEntry.COLUMN_PASSWORD + " TEXT NOT NULL " +
                ");"
        db.execSQL(SQL_CREATE_USER_TABLE)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL("DROP TABLE IF EXISTS " + ItemContract.UserEntry.TABLE_NAME)
        onCreate(db)
    }

    companion object {
        const val DATABASE_NAME = "itemList.db"
        const val DATABASE_VERSION = 1
    }
}