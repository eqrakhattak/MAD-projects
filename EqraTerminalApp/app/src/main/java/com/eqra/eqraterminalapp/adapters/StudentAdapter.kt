package com.eqra.eqraterminalapp.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.eqra.eqraterminalapp.R
import com.eqra.eqraterminalapp.models.StudentModel
import java.util.ArrayList

class StudentAdapter(private val mList: ArrayList<StudentModel>, private val mListener: ItemClicker) : RecyclerView.Adapter<StudentAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        val studentView = LayoutInflater.from(parent.context).inflate(R.layout.layout_student, parent, false)
        return ViewHolder(studentView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        holder.textViewName.text = mList[position].name
        holder.textViewReg.text = mList[position].reg
        holder.textViewPhone.text = mList[position].phone

        holder.buttonSms.setOnClickListener {
            mListener.onItemClick(holder.adapterPosition)
        }
    }

    override fun getItemCount(): Int {
        return mList.size
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        val textViewName = view.findViewById<TextView>(R.id.text_view_name)
        val textViewReg = view.findViewById<TextView>(R.id.text_view_reg)
        val textViewPhone = view.findViewById<TextView>(R.id.text_view_phone)
        val buttonSms = view.findViewById<TextView>(R.id.button_sms)
    }

    interface ItemClicker {
        fun onItemClick(position: Int)
    }
}