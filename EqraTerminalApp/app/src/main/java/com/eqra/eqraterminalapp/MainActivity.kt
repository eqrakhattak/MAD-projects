package com.eqra.eqraterminalapp

import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.Settings
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.volley.Request
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.eqra.eqraterminalapp.adapters.StudentAdapter
import com.eqra.eqraterminalapp.databinding.ActivityMainBinding
import com.eqra.eqraterminalapp.models.StudentModel
import com.eqra.eqraterminalapp.services.StudentService
import android.content.BroadcastReceiver

import android.content.IntentFilter
import android.util.Log
import com.eqra.eqraterminalapp.services.MyReceiver


class MainActivity : AppCompatActivity(), StudentAdapter.ItemClicker {

    private lateinit var binding: ActivityMainBinding
    var adapterStudents: StudentAdapter? = null
    var mList = ArrayList<StudentModel>()

    private val url =
        "https://run.mocky.io/v3/c3707ba9-61c4-422c-85fa-8508861c8d82"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val requestQueue = Volley.newRequestQueue(this)

        val jsonObjectRequest = JsonObjectRequest(
            Request.Method.GET, url, null,
            { response ->
                for (i in 0..14) {
                    val name = response.getJSONArray("data").getJSONObject(i).getString("name")
                    val reg = response.getJSONArray("data").getJSONObject(i).getString("reg")
                    val phone = response.getJSONArray("data").getJSONObject(i).getString("phone")
                    mList.add(StudentModel(name, reg, phone))
                }

                adapterStudents = StudentAdapter(mList, this)
                binding.apply {
                    recyclerViewStudents.apply {
                        layoutManager = LinearLayoutManager(this@MainActivity)
                        adapter = adapterStudents
                    }
                }
            },
            { error ->
                Toast.makeText(this@MainActivity, error.toString(), Toast.LENGTH_LONG).show()
            }
        )
        requestQueue.add(jsonObjectRequest)

        val intent = Intent(this, StudentService::class.java)
        startService(intent)

        IntentFilter(Intent.ACTION_AIRPLANE_MODE_CHANGED).also {
            registerReceiver(MyReceiver(), it)
        }
    }

    override fun onItemClick(position: Int) {
        val messageIntent = Intent(Intent.ACTION_SENDTO, Uri.parse("smsto:${mList[position].phone}"))
        startActivity(messageIntent)
    }
}