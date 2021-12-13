package com.eqra.intents

import android.content.Intent
import android.graphics.Bitmap
import android.net.Uri
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
<<<<<<< HEAD
        val btn1:Button = findViewById(R.id.button3)
        btn1.setOnClickListener{
            val intent= Intent(this,phonecall::class.java)
            startActivity(intent)

        }
        val btn2:Button = findViewById(R.id.note_btn)
        btn2.setOnClickListener{
            val intent= Intent(this,phonecall::class.java)
            startActivity(intent)

        }

        }

=======
        sendEmailBtn = findViewById<Button>(R.id.sendEmail)
        sendEmailBtn.setOnClickListener {
            val it = Intent(this, send_email::class.java)
        }
        openMapBtn = findViewById(R.id.btnGglMap)
        openMapBtn.setOnClickListener {
            val mapAct = Intent(this, google_map::class.java)
        }
>>>>>>> 8415349d81e2a9a805b55b8545a30fa538bd8576
    }
