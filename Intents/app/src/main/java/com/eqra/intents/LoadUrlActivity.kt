package com.eqra.intents

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.eqra.intents.databinding.ActivityLoadUrlBinding

class LoadUrlActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoadUrlBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoadUrlBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initListeners()
    }

    private fun initListeners() {

        binding.buttonProceed.setOnClickListener {
            openWebPage(binding.editTextUrl.text.toString())
        }
    }

    private fun openWebPage(url: String) {

        val webpage: Uri = Uri.parse(url)
        val intent = Intent(Intent.ACTION_VIEW, webpage)
        startActivity(intent)
    }
}