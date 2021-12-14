package com.eqra.intents

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.CalendarContract
import com.eqra.intents.databinding.ActivityAddEventBinding

class AddEventActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAddEventBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddEventBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initListeners()
    }

    private fun initListeners() {

        binding.buttonSave.setOnClickListener {
            addEvent(binding.editTextTitle.text.toString(), binding.editTextLocation.text.toString())
        }
    }

    private fun addEvent(title: String, location: String) {

        val intent = Intent(Intent.ACTION_INSERT).apply {
            data = CalendarContract.Events.CONTENT_URI
            putExtra(CalendarContract.Events.TITLE, title)
            putExtra(CalendarContract.Events.EVENT_LOCATION, location)
        }
        startActivity(intent)
    }

}