package com.eqra.labmid.db

import android.provider.BaseColumns

class ItemContract {
    object UserEntry : BaseColumns {
        const val TABLE_NAME = "userList"
        const val COLUMN_EMAIL = "userEmail"
        const val COLUMN_PASSWORD = "userPassword"
    }
}