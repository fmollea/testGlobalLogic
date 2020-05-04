package com.mosh.testgloballogic.presentation.ui.adapters

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.mosh.testgloballogic.R
import com.mosh.testgloballogic.domain.LatopItem
import com.mosh.testgloballogic.presentation.ui.activities.LatopDetailActivity
import kotlinx.android.synthetic.main.latop_item_row.view.*

class LatopAdapter(
    private val context: Context,
    var items: List<LatopItem>
) : RecyclerView.Adapter<LatopAdapter.LatopViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = LatopViewHolder(LayoutInflater
        .from(parent.context).inflate(R.layout.latop_item_row, parent, false))

    override fun getItemCount() = items.size

    override fun onBindViewHolder(holder: LatopViewHolder, position: Int) {
        val currentItem = items[position]

        holder.itemView.tvTitle.text = currentItem.title
        holder.itemView.tvDescription.text = currentItem.description
        Glide.with(context)
            .applyDefaultRequestOptions(RequestOptions()
                .placeholder(R.drawable.ic_default)
                .error(R.drawable.ic_default))
            .load(currentItem.image)
            .into(holder.itemView.ivImage)

        holder.itemView.setOnClickListener {
            val intent = Intent(context, LatopDetailActivity::class.java)
            intent.putExtra("LATOP", currentItem)
            startActivity(context, intent, null)
        }
    }

    inner class LatopViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)
}