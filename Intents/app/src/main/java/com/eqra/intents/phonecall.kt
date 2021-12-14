package com.eqra.intents

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class phonecall : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_phonecall)
        val btn: Button = findViewById(android.R.id.btn_call)
        btn.setOnClickListener{
            fun dialPhoneNumber(phoneNumber: String) {
                val intent = Intent(Intent.ACTION_DIAL).apply {
                    data = Uri.parse("tel:$phoneNumber")
                }
                if (intent.resolveActivity(packageManager) != null) {
                    startActivity(intent)
                }
            }

        }
    }
}