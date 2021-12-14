package com.eqra.intents

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText

class phonecall : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_phonecall)
        val btn: Button = findViewById(R.id.btn_call)
        val etPhone: EditText = findViewById(R.id.etPhone);

        btn.setOnClickListener {
            val intent = Intent(Intent.ACTION_DIAL).apply {
                data = Uri.parse("tel:${etPhone.text}")
            }
//                if (intent.resolveActivity(packageManager) != null) {
            startActivity(intent)
        }
//            }
    }
}