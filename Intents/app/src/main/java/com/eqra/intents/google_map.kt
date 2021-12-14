package com.eqra.intents

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class google_map : AppCompatActivity() {
    private lateinit var openMapBtn: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_google_map)
        openMapBtn = findViewById(R.id.btnMap)
        openMapBtn.setOnClickListener {
            //Open Map at Specific Location
            val gmapIntentUri = Uri.parse("geo:31.93084,75.84974")
            val gmapIntent = Intent(Intent.ACTION_VIEW, gmapIntentUri)
            gmapIntent.setPackage("com.google.android.apps.maps")
            startActivity(gmapIntent)
        }
    }
}