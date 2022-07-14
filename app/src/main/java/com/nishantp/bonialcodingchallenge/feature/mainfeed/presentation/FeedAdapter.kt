package com.nishantp.bonialcodingchallenge.feature.mainfeed.presentation

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.nishantp.bonialcodingchallenge.databinding.ListItemFeedBinding
import com.nishantp.bonialcodingchallenge.feature.mainfeed.domain.model.Brochure

class FeedAdapter : ListAdapter<Brochure, FeedItemViewHolder>(Companion) {

    companion object : DiffUtil.ItemCallback<Brochure>() {
        override fun areItemsTheSame(oldItem: Brochure, newItem: Brochure): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Brochure, newItem: Brochure): Boolean {
            return oldItem == newItem
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FeedItemViewHolder {
        return FeedItemViewHolder(
            ListItemFeedBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: FeedItemViewHolder, position: Int) {
        holder.bind(brochure = getItem(position))
    }
}