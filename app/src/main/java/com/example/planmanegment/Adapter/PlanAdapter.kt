package com.example.planmanegment.Adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.planmanegment.Model.PlanModel
import com.example.planmanegment.R

class PlanAdapter(private val empList: ArrayList<PlanModel>) :
    RecyclerView.Adapter<PlanAdapter.ViewHolder>(){

    private var mListener: OnItemClickListener? = null

    interface OnItemClickListener {
        fun onItemClick(position: Int)
    }

    fun setOnItemClickListener(listener: OnItemClickListener) {
        mListener = listener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView =
            LayoutInflater.from(parent.context).inflate(R.layout.activity_show_all_data, parent, false)
        return ViewHolder(itemView, mListener)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currentEmp = empList[position]

        holder.values.text=currentEmp.planAmount
        holder.ckboxfoods.text=currentEmp.planCategory

    }

    override fun getItemCount(): Int {
        return empList.size
    }

    class ViewHolder(itemView: View, listener: OnItemClickListener?) :
        RecyclerView.ViewHolder(itemView) {
        val values: TextView = itemView.findViewById(R.id.values)
        val ckboxfoods: TextView = itemView.findViewById(R.id.ckboxfoods)
        init {
            itemView.setOnClickListener {
                listener?.onItemClick(adapterPosition)
            }
        }
    }

}