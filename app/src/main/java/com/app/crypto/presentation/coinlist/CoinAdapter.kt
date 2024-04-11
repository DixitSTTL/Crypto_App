package com.app.crypto.presentation.coinlist

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.app.climate_trace.businesslogic.interfaces.GeneralItemClickListeners
import com.app.crypto.data.model.Coin
import com.app.crypto.databinding.ItemLayoutCoinsBinding

class CoinAdapter(val generalItemClickListeners: GeneralItemClickListeners) :
    RecyclerView.Adapter<CoinAdapter.ViewHolder>() {
    var dataList = ArrayList<Coin>()

    inner class ViewHolder(private var mBinding: ItemLayoutCoinsBinding) :
        RecyclerView.ViewHolder(mBinding.root) {

        fun bind() {
            mBinding.model = dataList[adapterPosition]
            mBinding.generalItemClickListeners = generalItemClickListeners
            mBinding.position = adapterPosition
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val mBinding =
            ItemLayoutCoinsBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(mBinding)
    }

    override fun getItemCount(): Int {
        return dataList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind()
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setData(it: List<Coin>) {
        this.dataList = it as ArrayList<Coin>
        notifyDataSetChanged()
    }
}