package com.app.crypto.presentation.coindetail

import android.app.UiModeManager
import android.content.Context
import android.content.res.Configuration
import android.os.Build
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.view.ViewCompat
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.app.crypto.R
import com.app.crypto.databinding.ActivityCoinDetailBinding
import com.app.crypto.domain.usecase.GetCoinHistoryUseCase
import com.app.crypto.domain.usecase.GetCoinsDetailUseCase
import com.app.crypto.presentation.util.CoinHistoryTimeFrame
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class ActivityCoinDetail : AppCompatActivity() {
    lateinit var factory: CoinDetailViewModelFactory
    @Inject
    lateinit var getCoinsDetailUseCase: GetCoinsDetailUseCase
    @Inject
    lateinit var getCoinHistoryUseCase: GetCoinHistoryUseCase
    private lateinit var mBinding: ActivityCoinDetailBinding
    private lateinit var mViewModel: CoinDetailViewModel
    lateinit var COIN_ID: String


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding =
            DataBindingUtil.setContentView(this@ActivityCoinDetail, R.layout.activity_coin_detail)
        setContentView(mBinding.root)

        readIntent()
        factory = CoinDetailViewModelFactory(
            getCoinsDetailUseCase = getCoinsDetailUseCase,
            getCoinHistoryUseCase = getCoinHistoryUseCase,
            coinId = COIN_ID
        )

        mViewModel =
            ViewModelProvider(this@ActivityCoinDetail, factory)[CoinDetailViewModel::class.java]

        init()
        setToolbar()
        observe()

    }

    private fun readIntent() {

        intent?.let {
            COIN_ID = it.getStringExtra("COIN_ID").toString()
            ViewCompat.setTransitionName(
                mBinding.imageView,
                "coin-" + COIN_ID
            )
            ViewCompat.setTransitionName(
                mBinding.coinTxt,
                "text-" + COIN_ID
            )
        }
    }

    private fun setToolbar() {
        setSupportActionBar(mBinding.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        mBinding.toolbar.setNavigationOnClickListener {
            onBackPressed()
        }
    }


    private fun init() {

        mBinding.mViewModel = mViewModel
        mBinding.lightDark = isSystemInDarkMode()

        mBinding.radioGroup.check(R.id.time24h)
        mBinding.radioGroup.setOnCheckedChangeListener { group, checkedId ->

            when (checkedId) {
                R.id.time1h -> {
                    mViewModel.mutableTimeFrame.value = CoinHistoryTimeFrame.H1
                }

                R.id.time3h -> {
                    mViewModel.mutableTimeFrame.value = CoinHistoryTimeFrame.H3
                }

                R.id.time12h -> {
                    mViewModel.mutableTimeFrame.value = CoinHistoryTimeFrame.H12
                }

                R.id.time24h -> {
                    mViewModel.mutableTimeFrame.value = CoinHistoryTimeFrame.H24
                }

                R.id.time7d -> {
                    mViewModel.mutableTimeFrame.value = CoinHistoryTimeFrame.D7
                }

                R.id.time1m -> {
                    mViewModel.mutableTimeFrame.value = CoinHistoryTimeFrame.D30
                }

                R.id.time3m -> {
                    mViewModel.mutableTimeFrame.value = CoinHistoryTimeFrame.M3
                }

                R.id.time1y -> {
                    mViewModel.mutableTimeFrame.value = CoinHistoryTimeFrame.Y1
                }

                R.id.time3y -> {
                    mViewModel.mutableTimeFrame.value = CoinHistoryTimeFrame.Y3
                }
                R.id.time5y -> {
                    mViewModel.mutableTimeFrame.value = CoinHistoryTimeFrame.Y5
                }
            }
            mViewModel.getHistoryData()


        }


    }

    private fun observe() {

        mViewModel.mutableCoinList.observe(this@ActivityCoinDetail) {
            mBinding.coinData = it

        }
        mViewModel.mutableHistoryData.observe(this@ActivityCoinDetail) {
            mBinding.coinHistoryData = it

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