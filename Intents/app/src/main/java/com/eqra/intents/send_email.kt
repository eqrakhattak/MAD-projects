package com.eqra.intents

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.eqra.intents.databinding.ActivitySendEmailBinding

class send_email : AppCompatActivity() {
    private lateinit var binding : ActivitySendEmailBinding


    override fun onCreate(savedInstanceState : Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySendEmailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.sendMail.setOnClickListener{
            val email = binding.emailAddress.text.toString()
            val subject = binding.etSubject.text.toString()
            val bodyMsg = binding.etMessage.text.toString()
            val addresses = email.split(",".toRegex()).toTypedArray()

            val intent = Intent(Intent.ACTION_SENDTO).apply {
                data = Uri.parse("mailto:") // only email apps should handle this
                putExtra(Intent.EXTRA_EMAIL, addresses)
                putExtra(Intent.EXTRA_SUBJECT, subject)
                putExtra(Intent.EXTRA_TEXT, bodyMsg)
            }
//            if (intent.resolveActivity(packageManager) != null) {
            startActivity(intent)
//            }
//            else{
//                Toast.makeText(this@send_email,"Required App is not installed", Toast.LENGTH_SHORT).show()
//            }
        }

    }
}