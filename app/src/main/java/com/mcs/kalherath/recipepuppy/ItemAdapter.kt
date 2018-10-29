package com.mcs.kalherath.recipepuppy

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_view.view.*

class ItemAdapter (private val dataList : MutableList<Item>, private val listener : Listener) : RecyclerView.Adapter<ItemAdapter.ViewHolder>()  {


    interface Listener {
        fun onItemClick(item : Item)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(dataList[position], listener, position)
    }

    override fun getItemCount(): Int = dataList.count()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_view, parent, false)

        return ViewHolder(view)
    }

    class ViewHolder(view : View) : RecyclerView.ViewHolder(view) {

        val v = view

        fun bind(item: Item, listener: Listener, position: Int) {
            v.title.text = item.title
            v.ingredients.text = item.ingredients
            v.setOnClickListener{listener.onItemClick(item)}
            if(!item.thumbnail.isEmpty())
                Picasso.get()
                    .load(item.thumbnail)
                    .into(v.image)
            else
                Picasso.get()
                        .load("http://vignette.wikia.nocookie.net/simpsons/images/6/60/No_Image_Available.png/revision/latest?cb=20170219125728")
                        .into(v.image)

        }
    }

    fun addData(listItems: MutableList<Item>) {
        var size = this.dataList.size
        this.dataList.addAll(listItems)
        var sizeNew = this.dataList.size
        notifyItemRangeChanged(size, sizeNew)
        notifyDataSetChanged()
    }

    fun dataAdd(){

    }
}