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
import com.mosh.testgloballogic.presentation.ui.activities.LaptopDetailActivity
import kotlinx.android.synthetic.main.laptop_item_row.view.*

class LaptopAdapter(
    private val context: Context,
    var items: List<LatopItem>
) : RecyclerView.Adapter<LaptopAdapter.LaptopViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = LaptopViewHolder(LayoutInflater
        .from(parent.context).inflate(R.layout.laptop_item_row, parent, false))

    override fun getItemCount() = items.size

    override fun onBindViewHolder(holder: LaptopViewHolder, position: Int) {
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
            val intent = Intent(context, LaptopDetailActivity::class.java)
            intent.putExtra("LAPTOP", currentItem)
            startActivity(context, intent, null)
        }
    }

    inner class LaptopViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)
}