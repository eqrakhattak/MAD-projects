package com.eqra.myweatherapp.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.eqra.myweatherapp.R
import com.eqra.myweatherapp.databinding.LayoutItemBinding
import com.eqra.myweatherapp.models.ItemModel
import java.util.ArrayList

class ItemAdapter(var mList: ArrayList<ItemModel>) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    lateinit var binding: LayoutItemBinding
    lateinit var mListener: ItemClicker

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {

        binding = DataBindingUtil.inflate(LayoutInflater.from(parent.context), R.layout.layout_item, parent, false)
        return ItemViewHolder(binding.root)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {

        val mHolder = holder as ItemViewHolder

        mHolder.binding.textViewCityName.text = mList[position].name

        mHolder.itemView.setOnClickListener {
            mListener.onItemClick(mHolder.adapterPosition)
        }
    }

    override fun getItemCount(): Int {
        return mList.size
    }

    class ItemViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val binding: LayoutItemBinding = DataBindingUtil.bind(view)!!
    }

    interface ItemClicker {
        fun onItemClick(position: Int)
    }
}