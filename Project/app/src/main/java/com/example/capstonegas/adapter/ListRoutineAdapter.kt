package com.example.capstonegas.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.capstonegas.databinding.ItemRoutineBinding
import com.example.capstonegas.model.AlarmItem

class ListRoutineAdapter(private val listRoutine: ArrayList<AlarmItem>): RecyclerView.Adapter<ListRoutineAdapter.ListViewHolder>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ListRoutineAdapter.ListViewHolder {
        val binding = ItemRoutineBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ListViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ListRoutineAdapter.ListViewHolder, position: Int) {
        holder.binding.checkBox.text = listRoutine[position].routinityName
        holder.binding.clockText.text = listRoutine[position].notifyHour

    }

    override fun getItemCount(): Int {
        TODO("Not yet implemented")
    }

    class ListViewHolder(var binding: ItemRoutineBinding) : RecyclerView.ViewHolder(binding.root)
}