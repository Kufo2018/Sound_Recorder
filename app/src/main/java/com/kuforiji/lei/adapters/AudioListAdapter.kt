package com.kuforiji.lei.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.kuforiji.lei.R
import com.kuforiji.lei.datasource.model.FetchUrlResponse

class AudioListAdapter(
    private val urlList: ArrayList<FetchUrlResponse>,
    private val clickListener: (FetchUrlResponse) -> Unit
) : RecyclerView.Adapter<AudioListAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AudioListAdapter.ViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(
                R.layout.recyclerview_items, parent, false
            )
        return ViewHolder(view)
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bindItems(
            fetchUrlResponse: FetchUrlResponse,
            clickListener: (FetchUrlResponse) -> Unit
        ) {
            itemView.findViewById<TextView>(R.id.file_name).text = fetchUrlResponse.fileName
            itemView.setOnClickListener { clickListener(fetchUrlResponse) }
        }
    }

    override fun onBindViewHolder(holder: AudioListAdapter.ViewHolder, position: Int) {
        holder.bindItems(urlList[position], clickListener)
    }

    override fun getItemCount(): Int {
        return urlList.size
    }


}