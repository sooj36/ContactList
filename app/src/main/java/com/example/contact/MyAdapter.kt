package com.android.customitemview

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.contact.MyItem
import com.example.contact.MyTitle
import com.example.contact.databinding.IsFavoriteBinding
import com.example.contact.databinding.ItemRecyclerviewBinding

class MyAdapter(val mItems: MutableList<MyItem>) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    companion object {
        const val ITEM = 0
        const val TITLE = 1
    }
    interface ItemClick {
        fun onClick(view : View, position : Int)
    }

    var itemClick : ItemClick? = null

    override fun getItemViewType(position: Int): Int {
        return when (mItems[position]) {
            is MyItem -> ITEM
            is MyTitle -> TITLE
            else -> throw  IllegalArgumentException("error")
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder { //새로운 뷰홀더 생성
        return when (viewType) {
            ITEM -> {
                val binding = ItemRecyclerviewBinding.inflate(LayoutInflater.from(parent.context), parent, false)
                Holder(binding)
            }
            TITLE -> {
                val binding = IsFavoriteBinding.inflate(LayoutInflater.from(parent.context), parent, false)
                Holder_fav(binding)
            }
            else -> throw IllegalArgumentException("Invalid view type")
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when(holder) {
            is Holder -> {
                holder.itemView.setOnClickListener {
                    itemClick?.onClick(it, position)
                }

                val myItem = mItems[position] as MyItem

                holder.iconImageView.setImageResource(myItem.aIcon)
                holder.name.text = myItem.aName
                holder.tel.text = myItem.aTel
            }
            is Holder_fav -> {
                val myTitle = mItems[position] as MyTitle

                holder.isFavorite.text = myTitle.aTitle
            }
        }
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getItemCount(): Int {
        return mItems.size
    }

    inner class Holder(val binding: ItemRecyclerviewBinding) : RecyclerView.ViewHolder(binding.root) {
        val iconImageView = binding.iconItem
        val name = binding.textItem1
        val tel = binding.textItem2
    }

    inner class Holder_fav(val binding: IsFavoriteBinding) : RecyclerView.ViewHolder(binding.root) {
        var isFavorite= binding.isFavorite
    }
}