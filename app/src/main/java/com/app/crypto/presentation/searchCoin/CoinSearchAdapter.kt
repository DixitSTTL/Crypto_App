package com.app.crypto.presentation.searchCoin

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.app.climate_trace.businesslogic.interfaces.GeneralItemClickListeners
import com.app.crypto.data.model.Coin
import com.app.crypto.databinding.ItemLayoutSearchCoinsBinding

class CoinSearchAdapter(val generalItemClickListeners: GeneralItemClickListeners) :
    RecyclerView.Adapter<CoinSearchAdapter.ViewHolder>() {

        var itemList = ArrayList<Coin>()
    inner class ViewHolder(private var mBinding: ItemLayoutSearchCoinsBinding) :
        RecyclerView.ViewHolder(mBinding.root) {

        fun bind(position: Int) {
            mBinding.model = itemList[position]
            mBinding.generalItemClickListeners = generalItemClickListeners
            mBinding.position = position
            mBinding.imageView.transitionName = "coin-" + itemList[position].uuid
            Log.d("bddbd","  "+itemList[position].uuid)
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val mBinding =
            ItemLayoutSearchCoinsBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(mBinding)
    }

    override fun getItemCount(): Int {
        return itemList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(position)
    }

    fun setItemList(it: List<Coin>) {
        this.itemList.clear()
        this.itemList.addAll(it)

        notifyDataSetChanged()
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