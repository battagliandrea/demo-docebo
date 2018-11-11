package com.demodocebo.test.ui.view.catalog

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.bumptech.glide.request.RequestOptions
import com.demodocebo.test.R
import com.demodocebo.test.data.api.models.Item
import com.demodocebo.test.ui.utils.toCurrency
import com.demodocebo.test.ui.utils.toSpanned
import kotlinx.android.synthetic.main.view_catalog_item.view.*
import javax.inject.Inject


open class CatalogAdapter @Inject constructor() : RecyclerView.Adapter<CatalogAdapter.ViewHolder>() {

    var data: MutableList<Item> = mutableListOf()
        set(value) {
            field.addAll(value)
            notifyDataSetChanged()
        }

    var onItemClickListener: (Item) -> Unit = {}


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.view_catalog_item, parent, false)
        return ViewHolder(v)
    }


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.render(data[position])
    }


    override fun onBindViewHolder(holder: ViewHolder, position: Int, payloads: MutableList<Any>) {
        super.onBindViewHolder(holder, position, payloads)
    }


    override fun getItemCount(): Int {
        return data.size
    }


    open inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        open fun render(item: Item) = with(itemView) {

            if(item.item_thumbnail.isNotEmpty()){
                Glide.with(context)
                        .load("https:${item.item_thumbnail}")
                        .apply(RequestOptions.centerCropTransform())
                        .transition(DrawableTransitionOptions.withCrossFade())
                        .into(image)
            } else {
                Glide.with(context).clear(image)
            }

            tv_title.text = item.item_name
            tv_type_price.text = "${item.item_type} | ${item.item_price.toCurrency()}"
            tv_description.text = item.item_description.toSpanned()

            itemView.setOnClickListener {
                val position = adapterPosition
                if (position != RecyclerView.NO_POSITION) {
                    onItemClickListener(data[position])
                }
            }
        }
    }
}