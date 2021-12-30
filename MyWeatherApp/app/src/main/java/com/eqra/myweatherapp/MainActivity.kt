package com.eqra.myweatherapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.eqra.myweatherapp.adapters.ItemAdapter
import com.eqra.myweatherapp.databinding.ActivityMainBinding
import com.eqra.myweatherapp.models.ItemModel

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

        mList.add(ItemModel("Islamabad", "http://api.openweathermap.org/data/2.5/weather?q=Islamabad&units=metric&appid=8f4b209228e4ff1dc3443bf124f3fe95"))
        mList.add(ItemModel("Attock", "http://api.openweathermap.org/data/2.5/weather?q=Attock&units=metric&appid=8f4b209228e4ff1dc3443bf124f3fe95"))
        mList.add(ItemModel("Lahore", "http://api.openweathermap.org/data/2.5/weather?q=Lahore&units=metric&appid=8f4b209228e4ff1dc3443bf124f3fe95"))
        mList.add(ItemModel("Karachi", "http://api.openweathermap.org/data/2.5/weather?q=Karachi&units=metric&appid=8f4b209228e4ff1dc3443bf124f3fe95"))
        mList.add(ItemModel("Rawalpindi", "http://api.openweathermap.org/data/2.5/weather?q=Rawalpindi&units=metric&appid=8f4b209228e4ff1dc3443bf124f3fe95"))

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
        startActivity(intent)
    }
}