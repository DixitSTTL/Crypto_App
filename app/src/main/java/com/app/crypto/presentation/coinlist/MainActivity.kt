package com.app.crypto.presentation.coinlist

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.app.climate_trace.businesslogic.interfaces.GeneralItemClickListeners
import com.app.crypto.data.model.Coin
import com.app.crypto.databinding.ActivityMainBinding
import com.app.crypto.presentation.coindetail.ActivityCoinDetail
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var factory: CoinViewModelFactory
    lateinit var mBinding: ActivityMainBinding
    lateinit var mViewModel: CoinViewModel
    private val generalItemClickListeners = object : GeneralItemClickListeners {
        override fun onItemClick(view: View?, position: Int, item: Any?) {

            val data = item as Coin
            startActivity(
                Intent(
                    this@MainActivity,
                    ActivityCoinDetail::class.java
                ).putExtra("COIN_ID",data.uuid)
            )
        }

    }
    private val mAdapter = CoinAdapter(generalItemClickListeners)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(mBinding.root)

        mViewModel = ViewModelProvider(this, factory).get(CoinViewModel::class.java)

        _init()

        mViewModel.getCoins().observe(this) {
            mAdapter.setData(it)

        }
    }

    private fun _init() {

        mBinding.apply {

            recCoinList.adapter = mAdapter
            recCoinList.layoutManager =
                LinearLayoutManager(this@MainActivity, LinearLayoutManager.VERTICAL, false)


        }
    }
}