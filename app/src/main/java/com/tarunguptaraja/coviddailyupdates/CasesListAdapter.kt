package com.tarunguptaraja.coviddailyupdates

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class CasesListAdapter(): RecyclerView.Adapter<CasesViewHolder>() {

    private val items: ArrayList<Cases> = ArrayList()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CasesViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_cases,parent,false)
        val viewHolder = CasesViewHolder(view)
        return viewHolder
    }

    override fun onBindViewHolder(holder: CasesViewHolder, position: Int) {
        val currentItem = items[position]
        holder.state.text=currentItem.state
        holder.total.text=currentItem.cases
    }

    override fun getItemCount(): Int {
        return items.size
    }
    fun updateCases (updatedCases:ArrayList<Cases>){
        items.clear()
        items.addAll(updatedCases)

        notifyDataSetChanged()
    }

}
class CasesViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
    val state = itemView.findViewById<TextView>(R.id.state)
    val total = itemView.findViewById<TextView>(R.id.total)
}
