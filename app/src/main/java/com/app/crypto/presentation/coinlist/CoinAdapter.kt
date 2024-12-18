package com.app.crypto.presentation.coinlist

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.app.climate_trace.businesslogic.interfaces.GeneralItemClickListeners
import com.app.crypto.data.model.Coin
import com.app.crypto.databinding.ItemLayoutCoinsBinding

class CoinAdapter(val generalItemClickListeners: GeneralItemClickListeners) :
    PagingDataAdapter<Coin, CoinAdapter.ViewHolder>(Comparator) {

    inner class ViewHolder(private var mBinding: ItemLayoutCoinsBinding) :
        RecyclerView.ViewHolder(mBinding.root) {

        fun bind() {
            mBinding.model = getItem(adapterPosition)
            mBinding.generalItemClickListeners = generalItemClickListeners
            mBinding.position = adapterPosition
            mBinding.imageView.transitionName = "coin-" + getItem(adapterPosition)?.uuid
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val mBinding =
            ItemLayoutCoinsBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(mBinding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind()
    }

    object Comparator : DiffUtil.ItemCallback<Coin>() {
        override fun areItemsTheSame(oldItem: Coin, newItem: Coin): Boolean {
            // Id is unique.
            return oldItem.uuid == newItem.uuid
        }

        override fun areContentsTheSame(oldItem: Coin, newItem: Coin): Boolean {
            return oldItem.uuid == newItem.uuid
        }
    }
}