package com.android.nanden.flickerapp.search

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.android.nanden.flickerapp.R
import com.android.nanden.flickerapp.data.GalleryItem
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.item_search_photo.view.*

/**
 * - SearchPhotoAdapter
 */

class SearchPhotoAdapter(private val items : ArrayList<GalleryItem>, private val context: Context): RecyclerView.Adapter<SearchPhotoAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder
        = ViewHolder(LayoutInflater.from(context).inflate(R.layout.item_search_photo, parent, false))


    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = items[position]
        holder.bind(item)
    }

    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {

        fun bind(item: GalleryItem) {
            val ivPhoto = itemView.ivPhoto!!
            if (!item.url.isNullOrEmpty()) {
                Glide.with(itemView.context)
                        .load(item.url)
                        .fitCenter()
                        .into(ivPhoto)
            }
            ivPhoto.setOnClickListener {
                if (!item.title.isNullOrEmpty() && !item.owner.isNullOrEmpty())
                    Toast.makeText(itemView.context, "${item.title} by ${item.owner}", Toast.LENGTH_SHORT).show()
            }
        }

    }

}