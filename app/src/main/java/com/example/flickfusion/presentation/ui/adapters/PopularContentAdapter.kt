package com.example.flickfusion.presentation.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.flickfusion.base.BaseDiffUtilItemCallback
import com.example.flickfusion.databinding.ItemPopularContentBinding
import com.example.flickfusion.models.PopularModelUI

class PopularContentAdapter :
    PagingDataAdapter<PopularModelUI, PopularContentAdapter.PopularContentViewHolder>(
        BaseDiffUtilItemCallback()
    ) {

    class PopularContentViewHolder(private val binding: ItemPopularContentBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun onBind(item: PopularModelUI?) {
            Glide.with(binding.itemPopularImage)
                .load("https://image.tmdb.org/t/p/w500/${item?.posterPath}")
                .into(binding.itemPopularImage)
            binding.itemPopularTxt.text = item?.originalTitle
        }
    }

    override fun onBindViewHolder(holder: PopularContentViewHolder, position: Int) {
        holder.onBind(getItem(position))
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PopularContentViewHolder {
        return PopularContentViewHolder(
            ItemPopularContentBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }
}