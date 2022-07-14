package com.nishantp.bonialcodingchallenge.feature.mainfeed.presentation

import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.nishantp.bonialcodingchallenge.R
import com.nishantp.bonialcodingchallenge.databinding.ListItemFeedBinding
import com.nishantp.bonialcodingchallenge.feature.mainfeed.domain.model.Brochure
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class FeedItemViewHolder(
    private val listItemFeedBinding: ListItemFeedBinding
) :
    RecyclerView.ViewHolder(listItemFeedBinding.root) {

    fun bind(brochure: Brochure) {

        CoroutineScope(Dispatchers.Main).launch {
            listItemFeedBinding.image.layout(0, 0, 0, 0)
            Glide.with(listItemFeedBinding.image.context)
                .load(brochure.imageUrl)
                .placeholder(R.drawable.ic_image_loading)
                .into(listItemFeedBinding.image)
        }

        listItemFeedBinding.retailerName.text = brochure.retailerName
    }
}