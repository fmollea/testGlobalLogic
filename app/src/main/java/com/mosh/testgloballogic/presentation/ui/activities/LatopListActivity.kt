package com.mosh.testgloballogic.presentation.ui.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.mosh.testgloballogic.data.repository.LatopRepository
import com.mosh.testgloballogic.data.repository.data.LatopResponse
import com.mosh.testgloballogic.databinding.LatopListActivityBinding
import com.mosh.testgloballogic.domain.LatopItem
import com.mosh.testgloballogic.presentation.ui.adapters.LatopAdapter
import com.mosh.testgloballogic.presentation.viewmodels.LatopViewModel
import com.mosh.testgloballogic.presentation.viewmodels.LatopViewModelFactory
import com.mosh.testgloballogic.utils.NetworkStatus
import com.mosh.testgloballogic.utils.Status

class LatopListActivity : AppCompatActivity() {

    private lateinit var viewModel : LatopViewModel
    private lateinit var adapter : LatopAdapter
    private lateinit var binding : LatopListActivityBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = LatopListActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val factory = LatopViewModelFactory(LatopRepository())
        viewModel = ViewModelProviders.of(this, factory).get(LatopViewModel::class.java)

        initView()
        observeViewModel()
    }

    private fun observeViewModel() {
        val listLatopsObserver = Observer<NetworkStatus<LatopResponse>> {
            when(it.status) {
                Status.LOADING -> showLoading()
                Status.SUCCESS -> {
                    hideLoading()
                    showList(it.data as ArrayList<LatopItem>)
                }
                Status.ERROR -> {
                    hideLoading()
                    showErrorScreen()
                }
            }
        }

        viewModel.getLatops().observe(this, listLatopsObserver)
    }

    private fun showErrorScreen() {
        binding.clErrorContainer.visibility = View.VISIBLE

        binding.btRetry.setOnClickListener {
            binding.clErrorContainer.visibility = View.GONE
            observeViewModel()
        }
    }

    private fun hideLoading() {
        binding.pbLoading.visibility = View.GONE
    }

    private fun showLoading() {
        binding.pbLoading.visibility = View.VISIBLE
    }


    private fun initView() {
        supportActionBar?.title = "List of latops"

        adapter = LatopAdapter(this, listOf())
        binding.rvListLatops.layoutManager = LinearLayoutManager(this)
        binding.rvListLatops.adapter = adapter
    }

    private fun showList(list: List<LatopItem>) {
        binding.rvListLatops.visibility = View.VISIBLE
        adapter.items = list
        adapter.notifyDataSetChanged()
    }
}
