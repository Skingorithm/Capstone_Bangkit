package com.example.capstonegas.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.capstonegas.R
import com.example.capstonegas.databinding.ItemHistoryBinding
import com.example.capstonegas.model.DatalistsetHistory
import com.example.capstonegas.view.detailhistory.DetailHistoryActivity

class ListHistoryAdapter(private val listHistory: List<DatalistsetHistory>) : RecyclerView.Adapter<ListHistoryAdapter.RecyclerViewHolder>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): RecyclerViewHolder {
        val binding = ItemHistoryBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return RecyclerViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RecyclerViewHolder, position: Int) {
        val history = listHistory[position]
        holder.binding.historyDate.text = history.scanDate
        holder.binding.historyScore.text = history.total.toString()

        if (history.total!! < 50) {
            holder.binding.icFace.setImageResource(R.drawable.ic_face_sad)
        } else if (history.total in 50..75) {
            holder.binding.icFace.setImageResource(R.drawable.ic_face_hmm)
        } else {
            holder.binding.icFace.setImageResource(R.drawable.ic_face_satisfied)
        }

        holder.itemView.setOnClickListener {
            val intent = Intent(holder.itemView.context, DetailHistoryActivity::class.java)
            intent.putExtra("history", history)

            holder.itemView.context.startActivity(intent)
        }
    }

    override fun getItemCount() = listHistory.size

    class RecyclerViewHolder(var binding: ItemHistoryBinding): RecyclerView.ViewHolder(binding.root)

}