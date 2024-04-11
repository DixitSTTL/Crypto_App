package com.app.crypto.presentation.coindetail

import android.app.UiModeManager
import android.content.Context
import android.content.res.Configuration
import android.os.Build
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.app.crypto.R
import com.app.crypto.databinding.ActivityCoinDetailBinding
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class ActivityCoinDetail : AppCompatActivity() {
    @Inject
    lateinit var factory: CoinDetailViewModelFactory
    private lateinit var mBinding: ActivityCoinDetailBinding
    private lateinit var mViewModel: CoinDetailViewModel
    lateinit var COIN_ID: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding =
            DataBindingUtil.setContentView(this@ActivityCoinDetail, R.layout.activity_coin_detail)
        setContentView(mBinding.root)
        mViewModel =
            ViewModelProvider(this@ActivityCoinDetail, factory)[CoinDetailViewModel::class.java]



        _init()
        observe()

    }


    private fun _init() {
        mBinding.mViewModel = mViewModel
        mBinding.lightDark = isSystemInDarkMode()

        intent?.let {
            COIN_ID = it.getStringExtra("COIN_ID").toString()

        }
        setSupportActionBar(mBinding.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        mBinding.toolbar.setNavigationOnClickListener {
            onBackPressed()
        }


    }

    private fun observe() {

        mViewModel.getCoinsDetail(COIN_ID).observe(this@ActivityCoinDetail) {
            mBinding.coinData = it

        }
    }


    fun isSystemInDarkMode(): Boolean {
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            val uiModeManager = getSystemService(Context.UI_MODE_SERVICE) as? UiModeManager

            uiModeManager?.nightMode == AppCompatDelegate.MODE_NIGHT_YES
        } else {
            @Suppress("DEPRECATION")
            val currentNightMode =
                resources.configuration.uiMode and Configuration.UI_MODE_NIGHT_MASK

            currentNightMode == Configuration.UI_MODE_NIGHT_YES

        }
    }


}