package com.eqra.intents

import android.content.Intent
import android.graphics.Bitmap
import android.os.Bundle
import android.provider.MediaStore
import android.view.View
import android.widget.Button
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity


class MainActivity : AppCompatActivity() {

    private lateinit var sendEmailBtn : Button
    private lateinit var openMapBtn : Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        sendEmailBtn = findViewById<Button>(R.id.sendEmail)
        sendEmailBtn.setOnClickListener {
            val intent = Intent(this, send_email::class.java)
            startActivity(intent)
        }
        openMapBtn = findViewById(R.id.btnGglMap)
        openMapBtn.setOnClickListener {
            val mapAct = Intent(this, google_map::class.java)
            startActivity(mapAct)
        }
        val btn1:Button = findViewById(R.id.button3)
        btn1.setOnClickListener{
            val intent= Intent(this,phonecall::class.java)
            startActivity(intent)
        }
        val btn2:Button = findViewById(R.id.button4)
        btn2.setOnClickListener{
            val intent= Intent(this,AddNote::class.java)
            startActivity(intent)
        }

        findViewById<Button>(R.id.btnLoadUrl).setOnClickListener {
            val intent= Intent(this, LoadUrlActivity::class.java)
            startActivity(intent)
        }

        findViewById<Button>(R.id.btnAddEvent).setOnClickListener {
            val intent= Intent(this, AddEventActivity::class.java)
            startActivity(intent)
        }
    }
}
