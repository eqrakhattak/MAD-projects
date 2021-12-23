package com.eqra.currentweather

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.eqra.currentweather.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val url =
        "http://api.openweathermap.org/data/2.5/weather?q=Islamabad&units=metric&appid=8f4b209228e4ff1dc3443bf124f3fe95"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initViews()
    }

    @SuppressLint("SetTextI18n")
    private fun initViews() {

        val requestQueue = Volley.newRequestQueue(this)

        val jsonObjectRequest = JsonObjectRequest(
            Request.Method.GET, url, null,
            { response ->
                binding.address.text = response.getString("name")

                Glide.with(this)
                    .load("https://openweathermap.org/img/wn/" +
                            "${response.getJSONArray("weather").getJSONObject(0).getString("icon")}@2x.png")
                    .transform(CenterCrop())
                    .into(binding.icon)

                binding.status.text = response.getJSONArray("weather").getJSONObject(0).getString("main")

                binding.temp.text = response.getJSONObject("main").getString("temp").split(".")[0] + "°C"
                binding.tempMin.text = "Min Temp: " + response.getJSONObject("main").getString("temp_min").split(".")[0] + "°C"
                binding.tempMax.text = "Max Temp: " + response.getJSONObject("main").getString("temp_max").split(".")[0] + "°C"
                binding.feelsLike.text = response.getJSONObject("main").getString("feels_like").split(".")[0] + "°C"

                binding.windDirection.text = response.getJSONObject("wind").getString("deg") + "°"
                binding.wind.text = response.getJSONObject("wind").getString("speed")

                binding.pressure.text = response.getJSONObject("main").getString("pressure")
                binding.humidity.text = response.getJSONObject("main").getString("humidity")

                binding.mainContainer.visibility = View.VISIBLE
            },
            { error ->
                binding.errorText.visibility = View.VISIBLE
            }
        )

        requestQueue.add(jsonObjectRequest)
    }
}