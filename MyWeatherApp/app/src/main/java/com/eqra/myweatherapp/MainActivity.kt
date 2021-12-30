package com.eqra.myweatherapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.eqra.myweatherapp.adapters.ItemAdapter
import com.eqra.myweatherapp.databinding.ActivityMainBinding
import com.eqra.myweatherapp.models.ItemModel
import com.eqra.myweatherapp.services.MyService

class MainActivity : AppCompatActivity(), ItemAdapter.ItemClicker {

    private lateinit var binding: ActivityMainBinding
    var adapterItem: ItemAdapter? = null
    var mList = ArrayList<ItemModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initViews()
    }

    private fun initViews() {

        val intent = Intent(this, MyService::class.java)
        startService(intent)

        mList.add(ItemModel("Islamabad", "http://api.openweathermap.org/data/2.5/weather?q=Islamabad&units=metric&appid=8f4b209228e4ff1dc3443bf124f3fe95", "https://www.accuweather.com/en/pk/islamabad/258278/weather-forecast/258278"))
        mList.add(ItemModel("Attock", "http://api.openweathermap.org/data/2.5/weather?q=Attock&units=metric&appid=8f4b209228e4ff1dc3443bf124f3fe95", "https://www.accuweather.com/en/pk/attock-city/259652/hourly-weather-forecast/259652"))
        mList.add(ItemModel("Lahore", "http://api.openweathermap.org/data/2.5/weather?q=Lahore&units=metric&appid=8f4b209228e4ff1dc3443bf124f3fe95", "https://www.accuweather.com/en/pk/lahore/260622/weather-forecast/260622"))
        mList.add(ItemModel("Karachi", "http://api.openweathermap.org/data/2.5/weather?q=Karachi&units=metric&appid=8f4b209228e4ff1dc3443bf124f3fe95", "https://www.accuweather.com/en/pk/karachi/261158/weather-forecast/261158"))
        mList.add(ItemModel("Rawalpindi", "http://api.openweathermap.org/data/2.5/weather?q=Rawalpindi&units=metric&appid=8f4b209228e4ff1dc3443bf124f3fe95", "https://www.accuweather.com/en/pk/rawalpindi/260625/hourly-weather-forecast/260625"))

        adapterItem = ItemAdapter(mList)
        adapterItem?.mListener = this
        binding.apply {
            recyclerView.apply {
                layoutManager = LinearLayoutManager(this@MainActivity)
                adapter = adapterItem
            }
        }
    }

    override fun onItemClick(position: Int) {

        val intent = Intent(this, DetailActivity::class.java)
        intent.putExtra("url", mList[position].url)
        intent.putExtra("webView", mList[position].webView)
        startActivity(intent)
    }
}