package com.app.crypto.presentation.coinlist

import android.app.ActivityOptions
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.app.climate_trace.businesslogic.interfaces.GeneralItemClickListeners
import com.app.crypto.R
import com.app.crypto.data.model.Coin
import com.app.crypto.databinding.ActivityMainBinding
import com.app.crypto.presentation.coindetail.ActivityCoinDetail
import com.app.crypto.presentation.coinlist.CoinsState.Error
import com.app.crypto.presentation.coinlist.CoinsState.Loading
import com.app.crypto.presentation.coinlist.CoinsState.Success
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject
import android.util.Pair as UtilPair


@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var factory: CoinViewModelFactory
    lateinit var mBinding: ActivityMainBinding
    lateinit var mViewModel: CoinViewModel

    private val generalItemClickListeners = object : GeneralItemClickListeners {
        override fun onItemClick(view: View?, position: Int, item: Any?) {
            val data = item as Coin

            val options = ActivityOptions.makeSceneTransitionAnimation(
                this@MainActivity,
                UtilPair.create(
                    view?.findViewById<ImageView>(R.id.imageView)!!,
                    "coin-" + data.uuid
                ),
                UtilPair.create(view.findViewById<ImageView>(R.id.coinTxt)!!, "text-" + data.uuid),
            )
            startActivity(
                Intent(
                    this@MainActivity,
                    ActivityCoinDetail::class.java
                ).putExtra("COIN_ID", data.uuid),
                options.toBundle()
            )
        }

    }
    private val mAdapter = CoinAdapter(generalItemClickListeners)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(mBinding.root)

        mViewModel = ViewModelProvider(this, factory).get(CoinViewModel::class.java)

        init()
        observe()

    }


    private fun observe() {
        mViewModel.coinsState.observe(this) { state ->
            when (state) {
                Loading -> {
                    mBinding.progress.visibility = VISIBLE
                }

                is Error -> {
                    mBinding.progress.visibility = GONE
                    Toast.makeText(this, state.message, Toast.LENGTH_SHORT).show()
                }

                is Success -> {
                    mBinding.progress.visibility = GONE
                    mAdapter.setData(state.coins)
                }

                null -> {}
            }
        }
    }

    private fun init() {
        mBinding.apply {

            recCoinList.adapter = mAdapter
            recCoinList.layoutManager =
                LinearLayoutManager(this@MainActivity, LinearLayoutManager.VERTICAL, false)


        }
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d("onDestroy", "onDestroy")
    }
}