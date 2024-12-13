package com.app.crypto.presentation.coinlist

import android.app.ActivityOptions
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.paging.LoadState
import androidx.recyclerview.widget.LinearLayoutManager
import com.app.climate_trace.businesslogic.interfaces.GeneralItemClickListeners
import com.app.crypto.R
import com.app.crypto.data.model.Coin
import com.app.crypto.databinding.ActivityMainBinding
import com.app.crypto.presentation.coindetail.ActivityCoinDetail
import com.app.crypto.presentation.coinlist.CoinsState.Error
import com.app.crypto.presentation.coinlist.CoinsState.Loading
import com.app.crypto.presentation.coinlist.CoinsState.Success
import com.app.crypto.presentation.searchCoin.SearchActivity
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
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
//                UtilPair.create(view.findViewById<ImageView>(R.id.coinTxt)!!, "text-" + data.uuid),
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

        mViewModel = ViewModelProvider(this, factory)[CoinViewModel::class.java]

        init()
        observe()

    }


    private fun observe() {
        mAdapter.addLoadStateListener { loadState ->

            if (loadState.refresh is LoadState.Loading || loadState.append is LoadState.Loading)
                mBinding.progress.visibility = VISIBLE
            else {
                mBinding.progress.visibility = GONE

                // If we have an error, show a toast
                val errorState = when {
                    loadState.append is LoadState.Error -> loadState.append as LoadState.Error
                    loadState.prepend is LoadState.Error -> loadState.prepend as LoadState.Error
                    loadState.refresh is LoadState.Error -> loadState.refresh as LoadState.Error
                    else -> null
                }
                errorState?.let {
//                    Toast.makeText(mContext, it.error.toString(), Toast.LENGTH_LONG).show()
                }

            }
        }

        lifecycleScope.launch {

            mViewModel.coinsState.observe(this@MainActivity) { state ->
                when (state) {
                    Loading -> {
                    }


                    is Error -> {
                        mBinding.progress.visibility = GONE
                        Toast.makeText(this@MainActivity, state.message, Toast.LENGTH_SHORT).show()
                    }

                    is Success -> {
                        mBinding.progress.visibility = GONE
                        mAdapter.submitData(lifecycle, state.coins)
                    }

                    null -> {}

                }
            }
        }

    }

    private fun init() {
        setSupportActionBar(mBinding.toolbar)

        mBinding.apply {

            recCoinList.adapter = mAdapter
            recCoinList.layoutManager =
                LinearLayoutManager(this@MainActivity, LinearLayoutManager.VERTICAL, false)


        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menu?.clear()
        menuInflater.inflate(R.menu.home_menu,menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.action_search) {
            startActivity(Intent(this@MainActivity, SearchActivity::class.java))
        }
        return super.onOptionsItemSelected(item)
    }

}