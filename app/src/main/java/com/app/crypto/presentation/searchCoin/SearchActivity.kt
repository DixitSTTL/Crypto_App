package com.app.crypto.presentation.searchCoin

import android.annotation.SuppressLint
import android.app.ActivityOptions
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.util.Pair
import android.view.Menu
import android.view.View
import android.widget.ImageView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.app.climate_trace.businesslogic.interfaces.GeneralItemClickListeners
import com.app.crypto.R
import com.app.crypto.data.model.Coin
import com.app.crypto.data.model.History
import com.app.crypto.databinding.ActivitySearchBinding
import com.app.crypto.presentation.coindetail.ActivityCoinDetail
import com.app.crypto.presentation.util.ResultResponse
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class SearchActivity : AppCompatActivity() {

    @Inject
    lateinit var factory:CoinSearchViewModelFactory
    lateinit var mViewModel:ViewModelSearch
    lateinit var mBinding: ActivitySearchBinding

    private val generalItemClickListeners = object : GeneralItemClickListeners {
        override fun onItemClick(view: View?, position: Int, item: Any?) {
            val data = item as Coin

            val options = ActivityOptions.makeSceneTransitionAnimation(
                this@SearchActivity,
                Pair.create(
                    view?.findViewById<ImageView>(R.id.imageView)!!,
                    "coin-" + data.uuid
                ),
//                UtilPair.create(view.findViewById<ImageView>(R.id.coinTxt)!!, "text-" + data.uuid),
            )
            startActivity(
                Intent(
                    this@SearchActivity,
                    ActivityCoinDetail::class.java
                ).putExtra("COIN_ID", data.uuid),
                options.toBundle()
            )
        }


    }
    private val adapter = CoinSearchAdapter(generalItemClickListeners)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        mBinding = DataBindingUtil.inflate(layoutInflater,R.layout.activity_search,null,false)
        setContentView(mBinding.root)
        mViewModel = ViewModelProvider(this@SearchActivity,factory)[ViewModelSearch::class.java]
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        initView()
        observer()
    }

    @SuppressLint("NotifyDataSetChanged")
    private fun observer() {

        mViewModel.searchCoin.observe(this) {
            mBinding.progress.visibility = View.GONE
            adapter.setItemList(it.data.coins)
        }

    }

    private fun initView() {

        mBinding.apply {
            setSupportActionBar(toolbar)
            supportActionBar?.setDisplayHomeAsUpEnabled(true)

            toolbar.setNavigationOnClickListener({ onBackPressed() })
            val lay = LinearLayoutManager(this@SearchActivity, LinearLayoutManager.VERTICAL,false)
            recSearch.layoutManager = lay
            recSearch.adapter = adapter
        }




    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menu?.clear()
        menuInflater.inflate(R.menu.search_menu, menu)
        val searchItem = menu?.findItem(R.id.action_search)
        val searchView: SearchView = searchItem?.actionView as SearchView
        searchView.isIconified = false
        searchView.clearFocus()

        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextChange(newText: String?): Boolean {
                return false
            }

            override fun onQueryTextSubmit(query: String?): Boolean {
                query?.let {
                    mBinding.progress.visibility = View.VISIBLE
                    mViewModel.searchCoin(query)
                }
                return true
            }
        })
        return true
    }
}