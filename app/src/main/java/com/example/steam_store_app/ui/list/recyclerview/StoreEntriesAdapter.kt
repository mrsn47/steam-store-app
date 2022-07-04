package com.example.steam_store_app.ui.list.recyclerview

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.steam_store_app.R
import com.example.steam_store_app.core.data.model.StoreEntry

class StoreEntriesAdapter(
  val context: Context,
  private var items: List<StoreEntry>,
  private val itemClick: (Int) -> Unit

) : RecyclerView.Adapter<StoreEntriesAdapter.ViewHolder>() {

  fun submit(items: List<StoreEntry>) {
    this.items = items
  }

  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
    val view = LayoutInflater.from(context).inflate(R.layout.store_list_item, parent, false)
    return ViewHolder(view, itemClick)
  }

  override fun onBindViewHolder(holder: ViewHolder, position: Int) {
    holder.bind(items[position], position)
  }

  override fun getItemCount(): Int {
    return items.count()
  }

  inner class ViewHolder(itemView: View, val itemClick: (Int) -> Unit) :
    RecyclerView.ViewHolder(itemView) {
    val appTitle = itemView.findViewById<TextView>(R.id.appTitle)
    val appCost = itemView.findViewById<TextView>(R.id.appCost)
    val appPlatforms = itemView.findViewById<TextView>(R.id.appPlatforms)
    val appImage = itemView.findViewById<ImageView>(R.id.appImage)
    fun bind(item: StoreEntry, position: Int) {

      appTitle.text = item.name

      appCost.text = "${item.finalCurrency} €"
      var platformsText = String()
      if (item.windows)
        platformsText = "${context.resources.getString(R.string.windows)} "
      if (item.linux)
        platformsText += "${context.resources.getString(R.string.linux)} "
      if (item.mac)
        platformsText += "${context.resources.getString(R.string.mac)} "
      appPlatforms.text = platformsText
      Glide.with(context).load(item.tiny_image).into(appImage)

      itemView.setOnClickListener { itemClick(item.id) }

    }
  }

}