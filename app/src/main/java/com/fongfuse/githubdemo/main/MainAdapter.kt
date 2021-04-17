package com.fongfuse.githubdemo.main

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.fongfuse.githubdemo.R
import com.fongfuse.githubdemo.data.BaseResponse
import com.fongfuse.githubdemo.databinding.VhSearchItemBinding
import com.fongfuse.githubdemo.extension.inflate
import com.fongfuse.githubdemo.extension.setImageUrl

class MainAdapter(val context: Context) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var items = ArrayList<BaseResponse?>()

    companion object {
        const val VH_ITEM = 0
        const val VH_LOADMORE = 1
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view: View
        val viewHolder: RecyclerView.ViewHolder
        when (viewType) {
            VH_ITEM -> {
                view = parent.inflate(R.layout.vh_search_item)
                viewHolder = SearchItemViewHolder(view)
            }
            else -> {
                view = parent.inflate(R.layout.vh_search_item)
                viewHolder = SearchItemViewHolder(view)
            }
        }
        return viewHolder
    }

    override fun getItemCount(): Int {
//        println("getItemCount ::")
//        println("getItemCount2 :: " + items.size)
        return items.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is SearchItemViewHolder -> holder.bind(items[position])
        }
    }

    override fun getItemViewType(position: Int): Int {
        return if (items[position] == null) {
            VH_LOADMORE
        } else {
            VH_ITEM
        }
    }

    fun setItems(items: ArrayList<BaseResponse?>) {
        this.items = items
        notifyDataSetChanged()
    }

    fun clearItems() {
        items.clear()
        notifyDataSetChanged()
    }

    inner class SearchItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val binding = VhSearchItemBinding.bind(itemView)

        fun bind(data: BaseResponse?) {
            binding.apply {
                imvAvatar.setImageUrl(data?.avatar_url)
                tvName.text = data?.login ?: ""
            }
        }
    }
}