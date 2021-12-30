package com.eqra.myweatherapp.services

import android.app.Service
import android.content.Intent
import android.os.IBinder
import android.util.Log
import android.widget.Toast

class MyService : Service() {

    override fun onBind(intent: Intent): IBinder? {
        throw UnsupportedOperationException("Not yet implemented")
    }

    override fun onStartCommand(intent: Intent, flags: Int, startId: Int): Int {

        Toast.makeText(this, "Weather fetching service started", Toast.LENGTH_LONG).show()
        Log.d("TAGO", "onDestroy: Weather fetching service started")

        return START_STICKY
    }

    override fun onDestroy() {
        super.onDestroy()

        Toast.makeText(this, "Weather fetching service destroyed", Toast.LENGTH_LONG).show()
        Log.d("TAGO", "onDestroy: Weather fetching service destroyed")
    }
}