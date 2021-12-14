package com.eqra.intents

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.android.gms.actions.NoteIntents

class AddNote : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_note)
        val btn: Button = findViewById(R.id.note)
        val etSubject: EditText = findViewById(R.id.etSubject)
        val etNote: EditText = findViewById(R.id.etNote)

        btn.setOnClickListener {
            val intent = Intent(NoteIntents.ACTION_CREATE_NOTE).apply {
                putExtra(NoteIntents.EXTRA_NAME, etSubject.text.toString())
                putExtra(NoteIntents.EXTRA_TEXT, etNote.text.toString())
            }
            if (intent.resolveActivity(packageManager) != null) {
                startActivity(intent)
            } else {
                Toast.makeText(this, "No activity found to handle intent", Toast.LENGTH_LONG).show()
            }
        }
    }
}