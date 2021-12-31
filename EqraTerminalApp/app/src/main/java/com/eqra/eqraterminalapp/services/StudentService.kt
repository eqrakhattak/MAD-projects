package com.eqra.eqraterminalapp.services

import android.app.Service
import android.content.Intent
import android.os.IBinder
import android.widget.Toast

class StudentService : Service() {

    override fun onBind(intent: Intent): IBinder? {
        throw UnsupportedOperationException("Not yet implemented")
    }

    override fun onStartCommand(intent: Intent, flags: Int, startId: Int): Int {
        Toast.makeText(this, "I am started", Toast.LENGTH_LONG).show()
        return START_STICKY
    }

    override fun onDestroy() {
        super.onDestroy()
        Toast.makeText(this, "I am destroyed", Toast.LENGTH_LONG).show()
    }
}